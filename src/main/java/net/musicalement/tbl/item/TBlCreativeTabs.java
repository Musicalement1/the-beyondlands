package net.musicalement.tbl.item;


import net.musicalement.tbl.TBL;
import net.musicalement.tbl.block.TBlBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TBlCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TBL.MOD_ID);

    public static final Supplier<CreativeModeTab> TBL_STUFF_TAB = CREATIVE_MODE_TABS.register("tbl_stuff_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(TBlItems.LITHIUM.get()))
                    .title(Component.translatable("creativetab.tbl.tbl_stuff"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(TBlItems.LITHIUM.get());
                        output.accept(TBlItems.RAW_LITHIUM.get());
                        output.accept(TBlBlocks.LITHIUM_BLOCK.get());
                        output.accept(TBlBlocks.RAW_LITHIUM_BLOCK.get());
                        output.accept(TBlBlocks.LITHIUM_ORE.get());
                        output.accept(TBlBlocks.LITHIUM_DEEPSLATE_ORE.get());
                        output.accept(TBlItems.STEEL_INGOT.get());
                        output.accept(TBlItems.LITHIUM_BATTERY.get());
                        output.accept(TBlItems.LITHIUM_BATTERY_BOOSTED.get());
                        output.accept(TBlItems.LITHIUM_BATTERY_STACK.get());
                        output.accept(TBlItems.LITHIUM_BATTERY_STACK_BOOSTED.get());
                        output.accept(TBlBlocks.FORCE_FIELD_BLOCK.get());
                        output.accept(TBlBlocks.FORCE_FIELD_BLOCK_ATTRACT.get());
                        output.accept(TBlItems.PEPPER.get());
                        output.accept(TBlItems.ASH.get());
                        output.accept(TBlItems.ASHLING_SPAWN_EGG.get());
                        output.accept(TBlItems.ASH_ZOMBIE_SPAWN_EGG.get());
                        output.accept(TBlItems.NUKER_SPAWN_EGG.get());
                        output.accept(TBlItems.STEEL_SWORD.get());
                        output.accept(TBlItems.STEEL_KNIFE.get());
                        output.accept(TBlItems.STEEL_AXE.get());
                        output.accept(TBlItems.STEEL_PICKAXE.get());
                        output.accept(TBlItems.STEEL_HOE.get());
                        output.accept(TBlItems.STEEL_SHOVEL.get());
                        output.accept(TBlItems.STEEL_HAMMER.get());
                        output.accept(TBlItems.STEEL_HELMET.get());
                        output.accept(TBlItems.STEEL_CHESTPLATE.get());
                        output.accept(TBlItems.STEEL_LEGGINGS.get());
                        output.accept(TBlItems.STEEL_BOOTS.get());
                        output.accept(TBlItems.STEEL_HORSE_ARMOR.get());
                        output.accept(TBlBlocks.ASH_LOG.get());
                        output.accept(TBlBlocks.ASH_LEAVES.get());
                        output.accept(TBlBlocks.ASH_WOOD.get());
                        output.accept(TBlBlocks.ASH_PLANKS.get());
                        output.accept(TBlBlocks.ASH_SAPLING.get());
                        output.accept(TBlItems.PEPPER_SEEDS.get());
                        output.accept(TBlBlocks.BL_PORTAL.get());
                        output.accept(TBlBlocks.GNEISS.get());
                        output.accept(TBlBlocks.ASH_BLOCK.get());
                        output.accept(TBlBlocks.LAB_BLOCK.get());
                        output.accept(TBlBlocks.GATE_OPENER.get());
                        output.accept(TBlBlocks.CORIUM.get());
                        output.accept(TBlBlocks.GREEN_MUSHROOM.get());
                        output.accept(TBlBlocks.GREEN_MUSHROOM_BLOCK.get());
                        output.accept(TBlItems.PROTOTYPE_002.get());
                        output.accept(TBlItems.ENCHANTITE_SHARD.get());
                        output.accept(TBlBlocks.BOOSTING_TABLE.get());
                        output.accept(TBlBlocks.PROPULSOR.get());
                        output.accept(TBlBlocks.NAPALM.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
