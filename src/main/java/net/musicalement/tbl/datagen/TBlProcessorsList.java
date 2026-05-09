package net.musicalement.tbl.datagen;

import com.google.common.collect.ImmutableList;
import net.musicalement.tbl.TBL;
import net.musicalement.tbl.block.TBlBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

public class TBlProcessorsList {

    public static final ResourceKey<StructureProcessorList> LAB_RUINS = ResourceKey.create(
            Registries.PROCESSOR_LIST,
            TBL.prefix("lab_ruins")
    );
    public static final ResourceKey<StructureProcessorList> LAB_RUINS_REACTOR = ResourceKey.create(
            Registries.PROCESSOR_LIST,
            TBL.prefix("lab_ruins_reactor")
    );

    public static void bootstrap(BootstrapContext<StructureProcessorList> context) {

        context.register(
                LAB_RUINS,
                new StructureProcessorList(
                        ImmutableList.of(
                                new RuleProcessor(
                                        ImmutableList.of(
                                                new ProcessorRule(
                                                        new RandomBlockMatchTest(TBlBlocks.LAB_BLOCK.get(), 0.1F),
                                                        AlwaysTrueTest.INSTANCE,
                                                        Blocks.POLISHED_DIORITE.defaultBlockState()
                                                ),
                                                new ProcessorRule(
                                                        new RandomBlockMatchTest(Blocks.AIR, 0.01F),
                                                        AlwaysTrueTest.INSTANCE,
                                                        Blocks.COBWEB.defaultBlockState()
                                                )
                                        )
                                )
                        )
                )
        );

        context.register(
                LAB_RUINS_REACTOR,
                new StructureProcessorList(
                        ImmutableList.of(
                                new RuleProcessor(
                                        ImmutableList.of(
                                                new ProcessorRule(
                                                        new RandomBlockMatchTest(TBlBlocks.LAB_BLOCK.get(), 0.1F),
                                                        AlwaysTrueTest.INSTANCE,
                                                        Blocks.POLISHED_DIORITE.defaultBlockState()
                                                ),
                                                new ProcessorRule(
                                                        new RandomBlockMatchTest(TBlBlocks.LAB_BLOCK.get(), 0.1F),
                                                        AlwaysTrueTest.INSTANCE,
                                                        Blocks.NETHERRACK.defaultBlockState()
                                                ),
                                                new ProcessorRule(
                                                        new RandomBlockMatchTest(TBlBlocks.LAB_BLOCK.get(), 0.1F),
                                                        AlwaysTrueTest.INSTANCE,
                                                        Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.defaultBlockState()
                                                ),
                                                new ProcessorRule(
                                                        new RandomBlockMatchTest(Blocks.AIR, 0.005F),
                                                        AlwaysTrueTest.INSTANCE,
                                                        TBlBlocks.CORIUM.get().defaultBlockState()
                                                )
                                                /*new ProcessorRule(
                                                        new BlockMatchTest(ModBlocks.GNEISS.get()),//anti corium feature
                                                        AlwaysTrueTest.INSTANCE,
                                                        Blocks.AIR.defaultBlockState()
                                                )*/
                                        )
                                )
                        )
                )
        );

    }
}