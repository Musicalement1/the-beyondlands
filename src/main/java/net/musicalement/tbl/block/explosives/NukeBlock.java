package net.musicalement.tbl.block.explosives;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.musicalement.tbl.entity.TBlEntities;
import net.musicalement.tbl.entity.custom.NukeExplosionEntity;

import javax.annotation.Nullable;

public class NukeBlock extends Block {

    public static final float NUKE_POWER = 150f;

    public NukeBlock(Properties properties) {
        super(properties);
    }

    public void explodeMethod(BlockState state, Level world, BlockPos pos,
                              @Nullable Direction face,
                              @Nullable LivingEntity igniter) {

        explode(world, pos, igniter);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos,
                                   Block block, BlockPos fromPos, boolean isMoving) {

        if (level.hasNeighborSignal(pos)) {
            explodeMethod(state, level, pos, null, null);
            level.removeBlock(pos, false);
        }
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos,
                           BlockState oldState, boolean isMoving) {

        if (!oldState.is(state.getBlock()) && level.hasNeighborSignal(pos)) {
            explodeMethod(state, level, pos, null, null);
            level.removeBlock(pos, false);
        }
    }

    private static void explode(Level level,
                                BlockPos pos,
                                @Nullable LivingEntity entity) {

        if (!(level instanceof ServerLevel server))
            return;

        NukeExplosionEntity explosion =
                new NukeExplosionEntity(
                        TBlEntities.NUKE_EXPLOSION.get(),
                        server,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        NUKE_POWER
                );

        server.addFreshEntity(explosion);
    }
}