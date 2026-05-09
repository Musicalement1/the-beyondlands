package net.musicalement.tbl.block.entity;


import net.musicalement.tbl.TBL;
import net.musicalement.tbl.block.TBlBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TBlBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, TBL.MOD_ID);

    public static final Supplier<BlockEntityType<ForceFieldBlockEntity>> FORCE_FIELD_BE =
            BLOCK_ENTITIES.register("force_field_be", () ->
                    BlockEntityType.Builder.of(ForceFieldBlockEntity::new,
                            TBlBlocks.FORCE_FIELD_BLOCK.get(),
                            TBlBlocks.FORCE_FIELD_BLOCK_ATTRACT.get()
                    ).build(null));


    public static final Supplier<BlockEntityType<GateOpenerBlockEntity>> GATE_BE =
            BLOCK_ENTITIES.register("gate_be", () -> BlockEntityType.Builder.of(
                    GateOpenerBlockEntity::new, TBlBlocks.GATE_OPENER.get()).build(null));

    public static final Supplier<BlockEntityType<BoostingTableBlockEntity>> BOOSTING_TABLE_BE =
            BLOCK_ENTITIES.register("boosting_table_be", () -> BlockEntityType.Builder.of(
                    BoostingTableBlockEntity::new, TBlBlocks.BOOSTING_TABLE.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
