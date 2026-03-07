package net.beyondLands.tbl.block;


import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.block.crop.PepperCropBlock;
import net.beyondLands.tbl.block.other.ModFlammableRotatedPillarBlock;
import net.beyondLands.tbl.item.ModItems;
import net.beyondLands.tbl.worldgen.tree.ModTreeGrowers;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.util.valueproviders.UniformInt;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TBL.MOD_ID);

    public static final RegistryObject<Block> LITHIUM_BLOCK = registerBlock("lithium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> RAW_LITHIUM_BLOCK = registerBlock("raw_lithium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops()));


    public static final RegistryObject<Block> LITHIUM_ORE = registerBlock("lithium_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LITHIUM_DEEPSLATE_ORE = registerBlock("lithium_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.of()
                    .strength(5f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));


    public static final RegistryObject<Block> FORCE_FIELD_BLOCK =
            registerBlock("force_field_block",
                    () -> new ForceFieldBlock(BlockBehaviour.Properties.of().noOcclusion().strength(10f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> FORCE_FIELD_BLOCK_ATTRACT =
            registerBlock("force_field_block_attract",
                    () -> new AttractFieldBlock(BlockBehaviour.Properties.of().noOcclusion().strength(10f).requiresCorrectToolForDrops()));




    public static final RegistryObject<RotatedPillarBlock> ASH_LOG = registerBlock("ash_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> ASH_WOOD = registerBlock("ash_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_ASH_LOG = registerBlock("stripped_ash_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_ASH_WOOD = registerBlock("stripped_ash_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));


    public static final RegistryObject<Block> BL_PORTAL = registerBlock("bl_portal",
            () -> new ModPortalBlock(BlockBehaviour.Properties.of().noLootTable().noOcclusion().noCollission()));

    public static final RegistryObject<Block> PEPPER_CROP = BLOCKS.register("pepper_crop",
            () -> new PepperCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static final RegistryObject<Block> ASH_PLANKS = registerBlock("ash_planks",
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

    public static final RegistryObject<Block> ASH_LEAVES = registerBlock("ash_leaves",
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

    public static final RegistryObject<Block> ASH_SAPLING = registerBlock("ash_sapling",
            () -> new SaplingBlock(ModTreeGrowers.ASH, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));


    public static final RegistryObject<Block> GNEISS = registerBlock("gneiss",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.5f, 6f)
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.STONE)
                    .sound(SoundType.STONE)));

    public static final RegistryObject<Block> ASH_BLOCK = registerBlock("ash_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.7f, 0.2f)
                    .noLootTable()
                    .sound(SoundType.SAND)
            ));

    public static final RegistryObject<Block> LAB_BLOCK = registerBlock("lab_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.8f, 8f)
                    .sound(SoundType.BASALT)
                    .requiresCorrectToolForDrops()
            ));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}