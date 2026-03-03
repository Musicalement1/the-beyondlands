package net.beyondLands.tbl.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ForceFieldBlockEntity extends BlockEntity {


    private double force = 1.0;   // intensity basically
    private double radius = 5.0;  // radius max in blocks

    public ForceFieldBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FORCE_FIELD_BE.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, ForceFieldBlockEntity be) {
        if (level.isClientSide) return;

        double radius = be.radius;
        double maxDistSq = radius * radius;

        //centred
        AABB box = new AABB(
                pos.getX() + 0.5 - radius, pos.getY() + 0.5 - radius, pos.getZ() + 0.5 - radius,
                pos.getX() + 0.5 + radius, pos.getY() + 0.5 + radius, pos.getZ() + 0.5 + radius
        );

        List<Entity> entities = level.getEntities(null, box); // Maybe add items & falling blocks later?

        Vec3 center = new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity living && !living.isDeadOrDying()) {
                applyForceToEntity(living, center, be.force, maxDistSq, /*repulse=*/true);
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

