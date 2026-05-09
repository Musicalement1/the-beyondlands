package net.musicalement.tbl.block.other;

import net.musicalement.tbl.block.TBlBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;

public class TBlFlammableRotatedPillarBlock extends RotatedPillarBlock {
    public TBlFlammableRotatedPillarBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
            if(state.is(TBlBlocks.ASH_LOG.get())) {
                return TBlBlocks.STRIPPED_ASH_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(TBlBlocks.ASH_WOOD.get())) {
                return TBlBlocks.STRIPPED_ASH_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }
}