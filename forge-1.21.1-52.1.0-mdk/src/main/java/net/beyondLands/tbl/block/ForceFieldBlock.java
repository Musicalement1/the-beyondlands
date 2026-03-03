package net.beyondLands.tbl.block;

import net.beyondLands.tbl.block.entity.ForceFieldBlockEntity;
import net.beyondLands.tbl.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class ForceFieldBlock extends Block implements EntityBlock {

    public ForceFieldBlock(Properties props) {
        super(props);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ForceFieldBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level,
                                                                  BlockState state,
                                                                  BlockEntityType<T> type) {

        return type == ModBlockEntities.FORCE_FIELD_BE.get()
                ? (lvl, pos, st, be) -> ForceFieldBlockEntity.tick(lvl, pos, st, (ForceFieldBlockEntity) be)
                : null;
    }
}
