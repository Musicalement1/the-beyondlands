package net.musicalement.tbl.datagen;


import net.musicalement.tbl.TBL;
import net.musicalement.tbl.block.TBlBlocks;
import net.musicalement.tbl.util.TBlTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TBlBlockTagProvider extends BlockTagsProvider {
    public TBlBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TBL.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(TBlBlocks.LITHIUM_BLOCK.get())
                .add(TBlBlocks.RAW_LITHIUM_BLOCK.get())
                .add(TBlBlocks.LITHIUM_ORE.get())
                .add(TBlBlocks.LITHIUM_DEEPSLATE_ORE.get())
                .add(TBlBlocks.FORCE_FIELD_BLOCK.get())
                .add(TBlBlocks.FORCE_FIELD_BLOCK_ATTRACT.get())
                .add(TBlBlocks.GNEISS.get())
                .add(TBlBlocks.LAB_BLOCK.get())
                .add(TBlBlocks.CORIUM.get())
                .add(TBlBlocks.GATE_OPENER.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(TBlBlocks.PROPULSOR.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(TBlBlocks.LITHIUM_ORE.get())
                .add(TBlBlocks.RAW_LITHIUM_BLOCK.get())
                .add(TBlBlocks.LITHIUM_DEEPSLATE_ORE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(TBlBlocks.CORIUM.get());


        tag(TBlTags.Blocks.NEEDS_STEEL_TOOL)
                .add(TBlBlocks.CORIUM.get())
                //.add(ModBlocks.RAW_LITHIUM_BLOCK.get())
                .add(Blocks.OBSIDIAN)
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(TBlTags.Blocks.INCORRECT_FOR_STEEL_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(TBlTags.Blocks.NEEDS_STEEL_TOOL);

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(TBlBlocks.ASH_LOG.get())
                .add(TBlBlocks.ASH_WOOD.get())
                .add(TBlBlocks.STRIPPED_ASH_LOG.get())
                .add(TBlBlocks.STRIPPED_ASH_WOOD.get());
    }
}