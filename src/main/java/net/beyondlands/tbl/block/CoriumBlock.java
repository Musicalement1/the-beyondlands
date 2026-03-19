package net.beyondlands.tbl.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class CoriumBlock extends Block {

    public CoriumBlock(Properties properties) {
        super(properties);
    }

    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity) {
            entity.hurt(level.damageSources().hotFloor(), 8.0F);
        }

        super.stepOn(level, pos, state, entity);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.isClientSide) return;
        double radius = 3;

        AABB box = new AABB(
                pos.getX() + 0.5 - radius, pos.getY() + 0.5 - radius, pos.getZ() + 0.5 - radius,
                pos.getX() + 0.5 + radius, pos.getY() + 0.5 + radius, pos.getZ() + 0.5 + radius
        );

        List<Entity> entities = level.getEntities(null, box);

        for (Entity entity : entities) {
            boolean isLiving = entity instanceof LivingEntity living && !living.isDeadOrDying();

            if (isLiving) {
                entity.hurt(level.damageSources().hotFloor(), 8.0F);
            }
        }
        //level.scheduleTick(pos, this, 20);
    }
    /*@Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        level.scheduleTick(pos, this, 20);
    }
     */
}
