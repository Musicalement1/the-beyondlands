package net.beyondLands.tbl.datagen;


import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.block.ModBlocks;
import net.beyondLands.tbl.item.ModItems;
import net.beyondLands.tbl.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TBL.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.LITHIUM_BLOCK.get())
                .add(ModBlocks.RAW_LITHIUM_BLOCK.get())
                .add(ModBlocks.LITHIUM_ORE.get())
                .add(ModBlocks.LITHIUM_DEEPSLATE_ORE.get())
                .add(ModBlocks.FORCE_FIELD_BLOCK.get())
                .add(ModBlocks.FORCE_FIELD_BLOCK_ATTRACT.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.LITHIUM_ORE.get())
                .add(ModBlocks.RAW_LITHIUM_BLOCK.get())
                .add(ModBlocks.LITHIUM_DEEPSLATE_ORE.get());

        /*tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.RAW_LITHIUM_BLOCK.get());*/


        tag(ModTags.Blocks.NEEDS_STEEL_TOOL)
                //.add(ModBlocks.RAW_LITHIUM_BLOCK.get())
                .add(Blocks.OBSIDIAN)
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(ModTags.Blocks.NEEDS_STEEL_TOOL);
    }
}