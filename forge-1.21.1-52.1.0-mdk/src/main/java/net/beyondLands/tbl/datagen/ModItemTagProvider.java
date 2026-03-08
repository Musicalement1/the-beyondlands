package net.beyondLands.tbl.datagen;


import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.item.ModItems;
import net.beyondLands.tbl.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import net.beyondLands.tbl.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
                              CompletableFuture<TagLookup<Block>> lookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, lookupCompletableFuture, TBL.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Items.HYDROREACTIVE_ITEMS)
                .add(ModItems.LITHIUM.get())
                .add(ModItems.RAW_LITHIUM.get());

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.STEEL_HELMET.get())
                .add(ModItems.STEEL_CHESTPLATE.get())
                .add(ModItems.STEEL_LEGGINGS.get())
                .add(ModItems.STEEL_BOOTS.get());

        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.ASH_LOG.get().asItem())
                .add(ModBlocks.ASH_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_ASH_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_ASH_WOOD.get().asItem());

        tag(ModTags.Items.BATTERY)
                .add(ModItems.LITHIUM_BATTERY.get())
                .add(ModItems.LITHIUM_BATTERY_STACK.get())
                .add(ModItems.LITHIUM_BATTERY_BOOSTED.get())
                .add(ModItems.LITHIUM_BATTERY_STACK_BOOSTED.get());

        tag(ItemTags.PLANKS)
                .add(ModBlocks.ASH_PLANKS.get().asItem());

        tag(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(ModBlocks.GNEISS.get().asItem());
        tag(ItemTags.STONE_TOOL_MATERIALS)
                .add(ModBlocks.GNEISS.get().asItem());
    }
}