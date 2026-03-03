package net.beyondLands.tbl.block.entity;

import net.beyondLands.tbl.block.ForceFieldBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;


import java.util.List;

public class ForceFieldBlockEntity extends BlockEntity {


    // default vals
    private double force = 1.0;
    private double radius = 16.0;
    //private boolean repulse = true;

    public ForceFieldBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FORCE_FIELD_BE.get(), pos, state);
    }

    // getters
    public double getForce() {
        return force;
    }

    public double getRadius() {
        return radius;
    }

    public double getMaxDistSq() {
        return radius * radius;
    }

    public boolean isRepulse() {
        BlockState state = getBlockState();
        if (state.hasProperty(ForceFieldBlock.ATTRACT)) {
            boolean attract = state.getValue(ForceFieldBlock.ATTRACT);
            return !attract; // attract=true => repulse=false
        }
        return true;//default repulse
    }

    // setters
    public void setForce(double force) {
        this.force = force;
        setChanged();
    }

    public void setRadius(double radius) {
        this.radius = radius;
        setChanged();
    }

    /*public void setRepulse(boolean repulse) {
        this.repulse = repulse;
        setChanged();
    }*/

    public static void tick(Level level, BlockPos pos, BlockState state, ForceFieldBlockEntity be) {
        if (level.isClientSide) return;

        double radius = be.radius;
        double maxDistSq = radius * radius;

        AABB box = new AABB(
                pos.getX() + 0.5 - radius, pos.getY() + 0.5 - radius, pos.getZ() + 0.5 - radius,
                pos.getX() + 0.5 + radius, pos.getY() + 0.5 + radius, pos.getZ() + 0.5 + radius
        );

        List<Entity> entities = level.getEntities(null, box);

        Vec3 center = new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);

        for (Entity entity : entities) {
            boolean isLiving = entity instanceof LivingEntity living && !living.isDeadOrDying();
            boolean isProjectile = entity instanceof Projectile;
            boolean isItem = entity instanceof ItemEntity;
            boolean isFalling = entity instanceof FallingBlockEntity;

            if (isLiving || isProjectile  || isItem || isFalling) {
                applyForceToEntity(entity, center, be.getForce(), be.getMaxDistSq(), be.isRepulse());
            }
        }
    }

    private static void applyForceToEntity(Entity entity, Vec3 center,
                                           double baseForce, double maxDistSq,
                                           boolean repulse) {
        Vec3 entityPos = entity.position();
        Vec3 dir = entityPos.subtract(center);    //push
        if (!repulse) {
            dir = center.subtract(entityPos);     //attract
        }

        double distSq = dir.lengthSqr();
        if (distSq < 1.0e-4 || distSq > maxDistSq) {
            return; // if too close we don't
        }

        Vec3 dirNorm = dir.normalize();

        // force ~ 1 / d^2
        double forceMag = baseForce / distSq;

        double maxPerTick = 0.2;
        if (forceMag > maxPerTick) {
            forceMag = maxPerTick;
        }

        Vec3 impulse = dirNorm.scale(forceMag);

        // apply it hehe
        Vec3 delta = entity.getDeltaMovement().add(impulse);
        entity.setDeltaMovement(delta);
        entity.hurtMarked = true; // client sync
    }
}

