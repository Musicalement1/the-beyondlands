package net.beyondlands.tbl.block;

import net.beyondlands.tbl.block.entity.ForceFieldBlockEntity;
import net.beyondlands.tbl.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import javax.annotation.Nullable;

public class ForceFieldBlock extends Block implements EntityBlock {

    // true = attract, false = repulse
    public static final BooleanProperty ATTRACT = BooleanProperty.create("attract");


    public ForceFieldBlock(Properties props) {
        super(props);
        //default
        this.registerDefaultState(this.stateDefinition.any().setValue(ATTRACT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ATTRACT);
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
