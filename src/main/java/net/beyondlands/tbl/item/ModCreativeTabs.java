package net.beyondlands.tbl.item;


import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TBL.MOD_ID);

    public static final Supplier<CreativeModeTab> TBL_STUFF_TAB = CREATIVE_MODE_TABS.register("tbl_stuff_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LITHIUM.get()))
                    .title(Component.translatable("creativetab.tbl.tbl_stuff"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.LITHIUM.get());
                        output.accept(ModItems.RAW_LITHIUM.get());
                        output.accept(ModBlocks.LITHIUM_BLOCK.get());
                        output.accept(ModBlocks.RAW_LITHIUM_BLOCK.get());
                        output.accept(ModBlocks.LITHIUM_ORE.get());
                        output.accept(ModBlocks.LITHIUM_DEEPSLATE_ORE.get());
                        output.accept(ModItems.STEEL_INGOT.get());
                        output.accept(ModItems.LITHIUM_BATTERY.get());
                        output.accept(ModItems.LITHIUM_BATTERY_BOOSTED.get());
                        output.accept(ModItems.LITHIUM_BATTERY_STACK.get());
                        output.accept(ModItems.LITHIUM_BATTERY_STACK_BOOSTED.get());
                        output.accept(ModBlocks.FORCE_FIELD_BLOCK.get());
                        output.accept(ModBlocks.FORCE_FIELD_BLOCK_ATTRACT.get());
                        output.accept(ModItems.PEPPER.get());
                        output.accept(ModItems.ASH.get());
                        output.accept(ModItems.ASHLING_SPAWN_EGG.get());
                        output.accept(ModItems.ASH_ZOMBIE_SPAWN_EGG.get());
                        output.accept(ModItems.STEEL_SWORD.get());
                        output.accept(ModItems.STEEL_KNIFE.get());
                        output.accept(ModItems.STEEL_AXE.get());
                        output.accept(ModItems.STEEL_PICKAXE.get());
                        output.accept(ModItems.STEEL_HOE.get());
                        output.accept(ModItems.STEEL_SHOVEL.get());
                        output.accept(ModItems.STEEL_HAMMER.get());
                        output.accept(ModItems.STEEL_HELMET.get());
                        output.accept(ModItems.STEEL_CHESTPLATE.get());
                        output.accept(ModItems.STEEL_LEGGINGS.get());
                        output.accept(ModItems.STEEL_BOOTS.get());
                        output.accept(ModItems.STEEL_HORSE_ARMOR.get());
                        output.accept(ModBlocks.ASH_LOG.get());
                        output.accept(ModBlocks.ASH_LEAVES.get());
                        output.accept(ModBlocks.ASH_WOOD.get());
                        output.accept(ModBlocks.ASH_PLANKS.get());
                        output.accept(ModBlocks.ASH_SAPLING.get());
                        output.accept(ModItems.PEPPER_SEEDS.get());
                        output.accept(ModBlocks.BL_PORTAL.get());
                        output.accept(ModBlocks.GNEISS.get());
                        output.accept(ModBlocks.ASH_BLOCK.get());
                        output.accept(ModBlocks.LAB_BLOCK.get());
                        output.accept(ModBlocks.GATE_OPENER.get());
                        output.accept(ModBlocks.CORIUM.get());
                        output.accept(ModItems.NUKER_SPAWN_EGG.get());
                        output.accept(ModBlocks.GREEN_MUSHROOM.get());
                        output.accept(ModBlocks.GREEN_MUSHROOM_BLOCK.get());
                        output.accept(ModItems.PROTOTYPE_002.get());
                        output.accept(ModItems.ENCHANTITE_SHARD.get());
                        output.accept(ModBlocks.BOOSTING_TABLE.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
