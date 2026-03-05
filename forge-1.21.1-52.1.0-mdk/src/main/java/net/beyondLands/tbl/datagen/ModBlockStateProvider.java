package net.beyondLands.tbl.datagen;


import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TBL.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.LITHIUM_BLOCK);
        blockWithItem(ModBlocks.RAW_LITHIUM_BLOCK);

        blockWithItem(ModBlocks.LITHIUM_ORE);
        blockWithItem(ModBlocks.LITHIUM_DEEPSLATE_ORE);

        forceFieldBlock(ModBlocks.FORCE_FIELD_BLOCK);
        forceFieldBlockAttract(ModBlocks.FORCE_FIELD_BLOCK_ATTRACT);

        logBlock(ModBlocks.ASH_LOG.get());
        axisBlock(ModBlocks.ASH_WOOD.get(), blockTexture(ModBlocks.ASH_LOG.get()), blockTexture(ModBlocks.ASH_LOG.get()));
        logBlock(ModBlocks.STRIPPED_ASH_LOG.get());
        axisBlock(ModBlocks.STRIPPED_ASH_WOOD.get(), blockTexture(ModBlocks.STRIPPED_ASH_LOG.get()), blockTexture(ModBlocks.STRIPPED_ASH_LOG.get()));

        blockItem(ModBlocks.ASH_LOG);
        blockItem(ModBlocks.ASH_WOOD);
        blockItem(ModBlocks.STRIPPED_ASH_LOG);
        blockItem(ModBlocks.STRIPPED_ASH_WOOD);

        blockWithItem(ModBlocks.ASH_PLANKS);

        leavesBlock(ModBlocks.ASH_LEAVES);
        saplingBlock(ModBlocks.ASH_SAPLING);
    }


    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }


    private void forceFieldBlock(RegistryObject<Block> blockRegistryObject) {
        var model = models().getExistingFile(modLoc("block/force_field_block"));
        simpleBlock(blockRegistryObject.get(), model);
        simpleBlockItem(blockRegistryObject.get(), model);
    }

    private void forceFieldBlockAttract(RegistryObject<Block> blockRegistryObject) {
        var model = models().getExistingFile(modLoc("block/force_field_block_attract"));
        simpleBlock(blockRegistryObject.get(), model);
        simpleBlockItem(blockRegistryObject.get(), model);
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("tbl:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    /*private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("tutorialmod:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }*/
}