package net.beyondlands.tbl.block;


import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.block.crop.PepperCropBlock;
import net.beyondlands.tbl.block.other.GreenMushroom;
import net.beyondlands.tbl.block.other.ModFlammableRotatedPillarBlock;
import net.beyondlands.tbl.item.ModItems;
import net.beyondlands.tbl.worldgen.ModConfiguredFeatures;
import net.beyondlands.tbl.worldgen.ModFeatures;
import net.beyondlands.tbl.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(TBL.MOD_ID);

    public static final DeferredBlock<Block> LITHIUM_BLOCK = registerBlock("lithium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> RAW_LITHIUM_BLOCK = registerBlock("raw_lithium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops()));


    public static final DeferredBlock<Block> LITHIUM_ORE = registerBlock("lithium_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> LITHIUM_DEEPSLATE_ORE = registerBlock("lithium_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.of()
                    .strength(5f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));


    public static final DeferredBlock<Block> FORCE_FIELD_BLOCK =
            registerBlock("force_field_block",
                    () -> new ForceFieldBlock(BlockBehaviour.Properties.of().noOcclusion().strength(10f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> FORCE_FIELD_BLOCK_ATTRACT =
            registerBlock("force_field_block_attract",
                    () -> new AttractFieldBlock(BlockBehaviour.Properties.of().noOcclusion().strength(10f).requiresCorrectToolForDrops()));




    public static final DeferredBlock<RotatedPillarBlock> ASH_LOG = registerBlock("ash_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<RotatedPillarBlock> ASH_WOOD = registerBlock("ash_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_ASH_LOG = registerBlock("stripped_ash_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_ASH_WOOD = registerBlock("stripped_ash_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));


    public static final DeferredBlock<Block> BL_PORTAL = registerBlock("bl_portal",
            () -> new ModPortalBlock(BlockBehaviour.Properties.of().noLootTable().noOcclusion().noCollission()));

    public static final DeferredBlock<Block> PEPPER_CROP = BLOCKS.register("pepper_crop",
            () -> new PepperCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static final DeferredBlock<Block> ASH_PLANKS = registerBlock("ash_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final DeferredBlock<Block> ASH_LEAVES = registerBlock("ash_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final DeferredBlock<Block> ASH_SAPLING = registerBlock("ash_sapling",
            () -> new SaplingBlock(ModTreeGrowers.ASH, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));


    public static final DeferredBlock<Block> GNEISS = registerBlock("gneiss",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.5f, 6f)
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.STONE)
                    .sound(SoundType.STONE)));
    //new ColoredFallingBlock(new ColorRGBA(0x303030),BlockBehaviour.Properties.of()
    public static final DeferredBlock<Block> ASH_BLOCK = registerBlock("ash_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.7f, 0.2f)
                    .noLootTable()
                    .sound(SoundType.SAND)
            ));

    public static final DeferredBlock<Block> LAB_BLOCK = registerBlock("lab_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.8f, 8f)
                    .sound(SoundType.BASALT)
                    .requiresCorrectToolForDrops()
            ));

    public static final DeferredBlock<Block> GATE_OPENER = registerBlock("gate_opener",
            () -> new GateOpenerBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .strength(10f, 100f)
            ));

    public static final DeferredBlock<Block> CORIUM = registerBlock("corium",
            () -> new CoriumBlock(BlockBehaviour.Properties.of()
                    .strength(40.0f, 1000f)
                    .sound(SoundType.ANCIENT_DEBRIS)
                    .requiresCorrectToolForDrops()
                    .lightLevel((state) -> 15)
            ));

    public static final DeferredBlock<Block> GREEN_MUSHROOM = registerBlock("green_mushroom",
            () -> new GreenMushroom(ModConfiguredFeatures.HUGE_GREEN_MUSHROOM, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .lightLevel((state) -> 5)
                    .hasPostProcess((state, level, pos) -> true)
                    .pushReaction(PushReaction.DESTROY)));


    public static final DeferredBlock<Block> GREEN_MUSHROOM_BLOCK = registerBlock("green_mushroom_block",
            () -> new HugeMushroomBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_GREEN)
                            .instrument(NoteBlockInstrument.BASS)
                            .strength(0.2F)
                            .sound(SoundType.WOOD)
                            .ignitedByLava()
            )
            );


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}