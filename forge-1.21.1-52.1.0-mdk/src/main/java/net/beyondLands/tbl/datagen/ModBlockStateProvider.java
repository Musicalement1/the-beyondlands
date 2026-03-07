package net.beyondLands.tbl.datagen;


import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.block.ModBlocks;
import net.beyondLands.tbl.block.crop.PepperCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

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

        getExistingModel(ModBlocks.FORCE_FIELD_BLOCK, "force_field_block");
        getExistingModel(ModBlocks.FORCE_FIELD_BLOCK_ATTRACT, "force_field_block_attract");

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

        makeCrop(((CropBlock) ModBlocks.PEPPER_CROP.get()), "pepper_crop_stage", "pepper_crop_stage");

        blockWithItem(ModBlocks.BL_PORTAL);
        randomRotatedExistingModel(ModBlocks.GNEISS, "gneiss");

        randomRotatedCubeBlockAll(ModBlocks.ASH_BLOCK);

        randomRotatedCubeBlockAll(ModBlocks.LAB_BLOCK);
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((PepperCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(TBL.MOD_ID, "block/" + textureName + state.getValue(((PepperCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
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

    private void getExistingModel(RegistryObject<Block> blockRegistryObject, String name) {
        var model = models().getExistingFile(modLoc("block/" + name));
        simpleBlock(blockRegistryObject.get(), model);
        simpleBlockItem(blockRegistryObject.get(), model);
    }
    protected void randomRotatedCubeBlockAll(RegistryObject<Block> block) {
        String name = block.getId().getPath();

        var model = models().cubeAll(name, modLoc("block/" + name));

        getVariantBuilder(block.get()).partialState().setModels(
                ConfiguredModel.builder()
                        .modelFile(model).rotationY(0).nextModel()
                        .modelFile(model).rotationY(90).nextModel()
                        .modelFile(model).rotationY(180).nextModel()
                        .modelFile(model).rotationY(270)
                        .build()
        );

        simpleBlockItem(block.get(), model);
    }


    protected void randomRotatedExistingModel(RegistryObject<Block> block, String modelName) {
        ModelFile model = models().getExistingFile(modLoc("block/" + modelName));

        getVariantBuilder(block.get()).partialState().setModels(
                ConfiguredModel.builder()
                        .modelFile(model).rotationY(0).nextModel()
                        .modelFile(model).rotationY(90).nextModel()
                        .modelFile(model).rotationY(180).nextModel()
                        .modelFile(model).rotationY(270)
                        .build()
        );

        simpleBlockItem(block.get(), model);
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