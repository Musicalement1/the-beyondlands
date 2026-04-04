package net.beyondlands.tbl.block.entity;


import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, TBL.MOD_ID);

    public static final Supplier<BlockEntityType<ForceFieldBlockEntity>> FORCE_FIELD_BE =
            BLOCK_ENTITIES.register("force_field_be", () ->
                    BlockEntityType.Builder.of(ForceFieldBlockEntity::new,
                            ModBlocks.FORCE_FIELD_BLOCK.get(),
                            ModBlocks.FORCE_FIELD_BLOCK_ATTRACT.get()
                    ).build(null));


    public static final Supplier<BlockEntityType<GateOpenerBlockEntity>> GATE_BE =
            BLOCK_ENTITIES.register("gate_be", () -> BlockEntityType.Builder.of(
                    GateOpenerBlockEntity::new, ModBlocks.GATE_OPENER.get()).build(null));

    public static final Supplier<BlockEntityType<BoostingTableBlockEntity>> BOOSTING_TABLE_BE =
            BLOCK_ENTITIES.register("boosting_table_be", () -> BlockEntityType.Builder.of(
                    BoostingTableBlockEntity::new, ModBlocks.BOOSTING_TABLE.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
