package net.musicalement.tbl.entity.custom;

import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.musicalement.tbl.block.explosives.ExplosionRay;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class NukeExplosionEntity extends Entity {

    private final List<ExplosionRay> rays = new ArrayList<>();

    private final LongOpenHashSet destroyed = new LongOpenHashSet();

    private static final double STEP_SIZE = 0.8;


    private boolean initialized = false;
    private float pendingPower = 0;
    private float maxDistance;
    private static final int MAX_LIFETIME = 20 * 100;

    public NukeExplosionEntity(EntityType<?> type, Level level) {
        super(type, level);
    }


    public NukeExplosionEntity(EntityType<?> type,
                               Level level,
                               double x,
                               double y,
                               double z,
                               float power) {

        super(type, level);

        this.noPhysics = true;

        setPos(x, y, z);

        this.pendingPower = power;
        this.maxDistance = power;
    }

    @Override
    public void onAddedToLevel() {

        super.onAddedToLevel();

        if (!initialized) {

            initialized = true;

            generateRays(pendingPower);
        }
    }

    private void generateRays(float power) {

        int rayCount = Math.max(1000, (int)(power * power * 1.5f));

        double goldenAngle = Math.PI * (3 - Math.sqrt(5));

        for (int i = 0; i < rayCount; i++) {

            double t = (double)i / rayCount;

            double inclination = Math.acos(1 - 2 * t);
            double azimuth = goldenAngle * i;

            double dx = Math.sin(inclination) * Math.cos(azimuth);
            double dy = Math.cos(inclination);
            double dz = Math.sin(inclination) * Math.sin(azimuth);

            float energy = power;

            //horizontal boost 4 explosion
            float horizontalFactor = 1f - (float)Math.abs(dy);
            energy *= 1f + horizontalFactor * 0.5f;


            if (dy < -0.3) {
                energy *= 0.7f;
            }

            rays.add(new ExplosionRay(
                    getX(),
                    getY(),
                    getZ(),
                    dx,
                    dy,
                    dz,
                    energy
            ));
        }
    }

    @Override
    public void tick() {
        if (tickCount > MAX_LIFETIME) {
            discard();
            return;
        }
        super.tick();

        if (level().isClientSide)
            return;

        int alive = 0;

        for (ExplosionRay ray : rays) {

            if (ray.dead)
                continue;

            alive++;

            //8 per tick so that its not slow
            for (int i = 0; i < 8; i++) {

                processRay(ray);

                if (ray.dead)
                    break;
            }
        }

        spawnMushroomParticles();

        if (alive == 0) {
            discard();
        }
    }

    private void processRay(ExplosionRay ray) {

        if (ray.energy <= 0f) {
            ray.dead = true;
            return;
        }

        ray.x += ray.dx * STEP_SIZE;
        ray.y += ray.dy * STEP_SIZE;
        ray.z += ray.dz * STEP_SIZE;
        ray.distanceTravelled += STEP_SIZE;

        BlockPos pos = BlockPos.containing(ray.x, ray.y, ray.z);

        if (!level().isInWorldBounds(pos)) {
            ray.dead = true;
            return;
        }

        if (ray.distanceTravelled >= maxDistance) {
            ray.dead = true;
            return;
        }

        long packed = pos.asLong();

        BlockState state = level().getBlockState(pos);

        if (!state.isAir()) {

            float resistance =
                    state.getExplosionResistance(level(), pos, null);

            ray.energy -= Math.max(0.5f, resistance * 0.08f);

            if (ray.energy > 0f) {

                if (!destroyed.contains(packed)) {

                    destroyed.add(packed);

                    level().setBlock(
                            pos,
                            Blocks.AIR.defaultBlockState(),
                            3
                    );
                }
            }

        } else {

            ray.energy -= 0.05f;
        }


    }

    // PARTICLES (bugged)//

    private void spawnMushroomParticles() {

        if (!(level() instanceof ServerLevel server))
            return;

        int age = tickCount;

        //column
        double height = Math.min(120, age * 0.8);

        // 1 : Column
        if (age < 120) {

            for (int i = 0; i < 20; i++) {

                double ox = (random.nextDouble() - 0.5) * 6;
                double oz = (random.nextDouble() - 0.5) * 6;

                server.sendParticles(
                        new DustParticleOptions(
                                new Vector3f(1f, 1f, 0f),
                                4f
                        ),
                        getX() + ox,
                        getY() + height,
                        getZ() + oz,
                        1,
                        0,0,0,
                        0
                );
            }
        }

        // 2: Top Mush
        else if (age < 240) {

            double radius = 10 + (age - 120) * 0.3;

            for (int i = 0; i < 40; i++) {

                double angle = random.nextDouble() * Math.PI * 2;
                double r = random.nextDouble() * radius;

                double x = Math.cos(angle) * r;
                double z = Math.sin(angle) * r;

                server.sendParticles(
                        new DustParticleOptions(
                                new Vector3f(1f, 0.5f, 0f),
                                5f
                        ),
                        getX() + x,
                        getY() + 120,
                        getZ() + z,
                        1,
                        0,0,0,
                        0
                );
            }
        }

        // 3: rings
        else if (age < 360) {

            double radius = 20 + (age - 240) * 0.4;

            for (int i = 0; i < 50; i++) {

                double angle = (Math.PI * 2 * i) / 50;

                double x = Math.cos(angle) * radius;
                double z = Math.sin(angle) * radius;

                server.sendParticles(
                        new DustParticleOptions(
                                new Vector3f(1f, 0.4f, 0f),
                                4f
                        ),
                        getX() + x,
                        getY() + 110,
                        getZ() + z,
                        1,
                        0,0,0,
                        0
                );
            }
        }

        // 4: Fade
        else {

            for (int i = 0; i < 40; i++) {

                double angle = random.nextDouble() * Math.PI * 2;
                double r = random.nextDouble() * 40;

                double x = Math.cos(angle) * r;
                double z = Math.sin(angle) * r;

                server.sendParticles(
                        new DustParticleOptions(
                                new Vector3f(0.1f, 0.1f, 0.1f),
                                4f
                        ),
                        getX() + x,
                        getY() + 100 + random.nextDouble() * 30,
                        getZ() + z,
                        1,
                        0,0,0,
                        0
                );
            }
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }
    @Override
    protected void readAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
    }

    @Override
    protected void addAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
    }
}