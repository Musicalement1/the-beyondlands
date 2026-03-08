package net.beyondLands.tbl.block.entity;


import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TBL.MOD_ID);

    public static final RegistryObject<BlockEntityType<ForceFieldBlockEntity>> FORCE_FIELD_BE =
            BLOCK_ENTITIES.register("force_field_be", () ->
                    BlockEntityType.Builder.of(ForceFieldBlockEntity::new,
                            ModBlocks.FORCE_FIELD_BLOCK.get(),
                            ModBlocks.FORCE_FIELD_BLOCK_ATTRACT.get()
                    ).build(null));


    public static final RegistryObject<BlockEntityType<GateOpenerBlockEntity>> GATE_BE =
            BLOCK_ENTITIES.register("gate_be", () -> BlockEntityType.Builder.of(
                    GateOpenerBlockEntity::new, ModBlocks.GATE_OPENER.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
