package net.beyondlands.tbl.block;

import net.beyondlands.tbl.block.entity.BoostingTableBlockEntity;
import net.beyondlands.tbl.screen.custom.BoostingTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.MenuProvider;

public class BoostingTableBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public BoostingTableBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(
                    new SimpleMenuProvider(
                            (containerId, inv, p) -> new BoostingTableMenu(containerId, inv, level.getBlockEntity(pos)),
                            Component.translatable("block.tbl.boosting_table")
                    ),
                    buf -> buf.writeBlockPos(pos)
            );
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }


    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        var be = level.getBlockEntity(pos);
        if (be instanceof BoostingTableBlockEntity boostingTableBE) {
            return boostingTableBE;
        }
        return null;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BoostingTableBlockEntity(pos, state);
    }
}
