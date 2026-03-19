package net.beyondlands.tbl.datagen;

import com.google.common.collect.ImmutableList;
import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import java.util.List;

public class ModProcessorsList {

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
                                                        new RandomBlockMatchTest(ModBlocks.LAB_BLOCK.get(), 0.1F),
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
                                                        new RandomBlockMatchTest(ModBlocks.LAB_BLOCK.get(), 0.1F),
                                                        AlwaysTrueTest.INSTANCE,
                                                        Blocks.POLISHED_DIORITE.defaultBlockState()
                                                ),
                                                new ProcessorRule(
                                                        new RandomBlockMatchTest(ModBlocks.LAB_BLOCK.get(), 0.1F),
                                                        AlwaysTrueTest.INSTANCE,
                                                        Blocks.NETHERRACK.defaultBlockState()
                                                ),
                                                new ProcessorRule(
                                                        new RandomBlockMatchTest(ModBlocks.LAB_BLOCK.get(), 0.1F),
                                                        AlwaysTrueTest.INSTANCE,
                                                        Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.defaultBlockState()
                                                ),
                                                new ProcessorRule(
                                                        new RandomBlockMatchTest(Blocks.AIR, 0.02F),
                                                        AlwaysTrueTest.INSTANCE,
                                                        ModBlocks.CORIUM.get().defaultBlockState()
                                                )
                                        )
                                )
                        )
                )
        );

    }
}