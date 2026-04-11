package net.beyondlands.tbl.datagen;


import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.block.ModBlocks;
import net.beyondlands.tbl.block.crop.PepperCropBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.Condition;
import net.minecraft.data.models.blockstates.MultiPartGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TBL.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.CORIUM);
        blockWithItem(ModBlocks.LITHIUM_BLOCK);
        blockWithItem(ModBlocks.RAW_LITHIUM_BLOCK);
        blockWithItem(ModBlocks.BOOSTING_TABLE);
        blockWithItem(ModBlocks.LITHIUM_ORE);
        blockWithItem(ModBlocks.LITHIUM_DEEPSLATE_ORE);

        getExistingModel(ModBlocks.FORCE_FIELD_BLOCK, "force_field_block");
        getExistingModel(ModBlocks.FORCE_FIELD_BLOCK_ATTRACT, "force_field_block_attract");
        getExistingModel(ModBlocks.GATE_OPENER, "gate_opener");
        getExistingModel(ModBlocks.PROPULSOR, "propulsor");

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
        saplingBlock(ModBlocks.GREEN_MUSHROOM);
        makeCrop(((CropBlock) ModBlocks.PEPPER_CROP.get()), "pepper_crop_stage", "pepper_crop_stage");
        createMushroomBlock(ModBlocks.GREEN_MUSHROOM_BLOCK.get());
        blockWithItem(ModBlocks.BL_PORTAL);
        randomRotatedExistingModel(ModBlocks.GNEISS, "gneiss");

        randomRotatedCubeBlockAll(ModBlocks.ASH_BLOCK);

        randomRotatedCubeBlockAll(ModBlocks.LAB_BLOCK);
    }
    private void createMushroomBlock(Block block) {

        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();

        ResourceLocation outside = models()
                .singleTexture(name,
                        mcLoc("block/template_single_face"),
                        "texture",
                        blockTexture(block))
                .renderType("cutout")
                .getLocation();

        ResourceLocation inside = mcLoc("block/mushroom_block_inside");

        getMultipartBuilder(block)

                // NORTH
                .part().modelFile(models().getExistingFile(outside)).addModel()
                .condition(BlockStateProperties.NORTH, true)
                .end()

                .part().modelFile(models().getExistingFile(inside)).addModel()
                .condition(BlockStateProperties.NORTH, false)
                .end()

                // SOUTH
                .part().modelFile(models().getExistingFile(outside)).rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.SOUTH, true)
                .end()

                .part().modelFile(models().getExistingFile(inside)).rotationY(180).addModel()
                .condition(BlockStateProperties.SOUTH, false)
                .end()

                // EAST
                .part().modelFile(models().getExistingFile(outside)).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.EAST, true)
                .end()

                .part().modelFile(models().getExistingFile(inside)).rotationY(90).addModel()
                .condition(BlockStateProperties.EAST, false)
                .end()

                // WEST
                .part().modelFile(models().getExistingFile(outside)).rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.WEST, true)
                .end()

                .part().modelFile(models().getExistingFile(inside)).rotationY(270).addModel()
                .condition(BlockStateProperties.WEST, false)
                .end()

                // UP
                .part().modelFile(models().getExistingFile(outside)).rotationX(270).uvLock(true).addModel()
                .condition(BlockStateProperties.UP, true)
                .end()

                .part().modelFile(models().getExistingFile(inside)).rotationX(270).addModel()
                .condition(BlockStateProperties.UP, false)
                .end()

                // DOWN
                .part().modelFile(models().getExistingFile(outside)).rotationX(90).uvLock(true).addModel()
                .condition(BlockStateProperties.DOWN, true)
                .end()

                .part().modelFile(models().getExistingFile(inside)).rotationX(90).addModel()
                .condition(BlockStateProperties.DOWN, false)
                .end();

        simpleBlockItem(block, models().cubeAll(
                BuiltInRegistries.BLOCK.getKey(block).getPath(),
                blockTexture(block)
        ));
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

    private void saplingBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockWithItem(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void getExistingModel(DeferredBlock<Block> blockRegistryObject, String name) {
        var model = models().getExistingFile(modLoc("block/" + name));
        simpleBlock(blockRegistryObject.get(), model);
        simpleBlockItem(blockRegistryObject.get(), model);
    }
    protected void randomRotatedCubeBlockAll(DeferredBlock<Block> block) {
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


    protected void randomRotatedExistingModel(DeferredBlock<Block> block, String modelName) {
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


    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("tbl:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("tbl:block/" + deferredBlock.getId().getPath() + appendix));
    }

    /*private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("tutorialmod:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }*/
}