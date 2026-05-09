package net.musicalement.tbl.datagen;


import net.musicalement.tbl.TBL;
import net.musicalement.tbl.item.TBlItems;
import net.musicalement.tbl.util.TBlTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import net.musicalement.tbl.block.TBlBlocks;

import java.util.concurrent.CompletableFuture;

public class TBlItemTagProvider extends ItemTagsProvider {
    public TBlItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
                              CompletableFuture<TagLookup<Block>> lookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, lookupCompletableFuture, TBL.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(TBlTags.Items.HYDROREACTIVE_ITEMS)
                .add(TBlItems.LITHIUM.get())
                .add(TBlItems.RAW_LITHIUM.get());

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(TBlItems.STEEL_HELMET.get())
                .add(TBlItems.STEEL_CHESTPLATE.get())
                .add(TBlItems.STEEL_LEGGINGS.get())
                .add(TBlItems.STEEL_BOOTS.get());

        tag(ItemTags.CHEST_ARMOR)
            .add(TBlItems.STEEL_CHESTPLATE.get());

        tag(ItemTags.HEAD_ARMOR)
                .add(TBlItems.STEEL_HELMET.get());

        tag(ItemTags.LEG_ARMOR)
                .add(TBlItems.STEEL_LEGGINGS.get());

        tag(ItemTags.FOOT_ARMOR)
                .add(TBlItems.STEEL_BOOTS.get());

        tag(ItemTags.PICKAXES)
                .add(TBlItems.STEEL_PICKAXE.get())
                .add(TBlItems.STEEL_HAMMER.get());

        tag(ItemTags.HOES)
                .add(TBlItems.STEEL_HOE.get());

        tag(ItemTags.AXES)
                .add(TBlItems.STEEL_AXE.get());

        tag(ItemTags.SWORDS)
                .add(TBlItems.STEEL_SWORD.get())
                .add(TBlItems.STEEL_KNIFE.get());

        tag(ItemTags.SHOVELS)
            .add(TBlItems.STEEL_SHOVEL.get());

        tag(ItemTags.LOGS_THAT_BURN)
                .add(TBlBlocks.ASH_LOG.get().asItem())
                .add(TBlBlocks.ASH_WOOD.get().asItem())
                .add(TBlBlocks.STRIPPED_ASH_LOG.get().asItem())
                .add(TBlBlocks.STRIPPED_ASH_WOOD.get().asItem());

        tag(TBlTags.Items.BATTERY)
                .add(TBlItems.LITHIUM_BATTERY.get())
                .add(TBlItems.LITHIUM_BATTERY_STACK.get())
                .add(TBlItems.LITHIUM_BATTERY_BOOSTED.get())
                .add(TBlItems.LITHIUM_BATTERY_STACK_BOOSTED.get());

        tag(ItemTags.PLANKS)
                .add(TBlBlocks.ASH_PLANKS.get().asItem());

        tag(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(TBlBlocks.GNEISS.get().asItem());
        tag(ItemTags.STONE_TOOL_MATERIALS)
                .add(TBlBlocks.GNEISS.get().asItem());

        tag(ItemTags.BOW_ENCHANTABLE)
                .add(TBlItems.PROTOTYPE_002.get());
        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(TBlItems.PROTOTYPE_002.get());
    }
}