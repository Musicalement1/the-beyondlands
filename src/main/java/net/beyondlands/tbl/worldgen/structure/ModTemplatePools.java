package net.beyondlands.tbl.worldgen.structure;

import com.mojang.datafixers.util.Pair;
import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.datagen.ModProcessorsList;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.List;
import java.util.Optional;

public class ModTemplatePools {


    public static final ResourceKey<StructureTemplatePool> ASHLING_RUINS_POOL =
            ResourceKey.create(
                    Registries.TEMPLATE_POOL,
                    TBL.prefix("ashling_ruins_pool")
            );

    public static final ResourceKey<StructureTemplatePool> LAB_RUINS_POOL =
            ResourceKey.create(
                    Registries.TEMPLATE_POOL,
                    TBL.prefix("lab_ruins/lab_ruins_pool")
            );

    public static final ResourceKey<StructureTemplatePool> LAB_RUINS_ROOMS =
            ResourceKey.create(
                    Registries.TEMPLATE_POOL,
                    TBL.prefix("lab_ruins/lab_ruins_rooms")
            );

    public static final ResourceKey<StructureTemplatePool> LAB_RUINS_CORRIDORS =
            ResourceKey.create(
                    Registries.TEMPLATE_POOL,
                    TBL.prefix("lab_ruins/lab_ruins_corridors")
            );

    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        HolderGetter<StructureProcessorList> processors = context.lookup(Registries.PROCESSOR_LIST);
        context.register(
                ASHLING_RUINS_POOL,
                new StructureTemplatePool(
                        context.lookup(Registries.TEMPLATE_POOL)
                                .getOrThrow(Pools.EMPTY),
                        List.of(
                                Pair.of(
                                        StructurePoolElement.single("tbl:ashling_ruins"),
                                        1
                                )
                        ),
                        StructureTemplatePool.Projection.RIGID
                )
        );

        context.register(
                LAB_RUINS_POOL,
                new StructureTemplatePool(
                        context.lookup(Registries.TEMPLATE_POOL)
                                .getOrThrow(Pools.EMPTY),
                        List.of(
                                Pair.of(
                                        StructurePoolElement.single(
                                                "tbl:lab_ruins/entrance/reactor",
                                                processors.getOrThrow(ModProcessorsList.LAB_RUINS_REACTOR)
                                        ),
                                        1
                                )
                        ),
                        StructureTemplatePool.Projection.RIGID
                )
        );

        context.register(
                LAB_RUINS_ROOMS,
                new StructureTemplatePool(
                        context.lookup(Registries.TEMPLATE_POOL)
                                .getOrThrow(Pools.EMPTY),
                        List.of(
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/rooms/room1", processors.getOrThrow(ModProcessorsList.LAB_RUINS)),//chest with brew, 1 exit
                                        2//ça c'est le poids (weight)
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/rooms/room2", processors.getOrThrow(ModProcessorsList.LAB_RUINS)),//dim teleporter
                                        1
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/rooms/room3", processors.getOrThrow(ModProcessorsList.LAB_RUINS)),//attractor trap
                                        1
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/rooms/room4", processors.getOrThrow(ModProcessorsList.LAB_RUINS)),//repulsor trap
                                        1
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/rooms/room5", processors.getOrThrow(ModProcessorsList.LAB_RUINS)),//simple room, acts as a corridor
                                        4
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/rooms/room6", processors.getOrThrow(ModProcessorsList.LAB_RUINS)),//escalator (9x<!6>x9)
                                        2
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/rooms/room7", processors.getOrThrow(ModProcessorsList.LAB_RUINS)),
                                        2
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/rooms/room8", processors.getOrThrow(ModProcessorsList.LAB_RUINS)),
                                        3
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/rooms/room9", processors.getOrThrow(ModProcessorsList.LAB_RUINS)),
                                        7//dead end so high weigth
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/rooms/room9", processors.getOrThrow(ModProcessorsList.LAB_RUINS_REACTOR)),
                                        1//dead end with corium
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/rooms/room10", processors.getOrThrow(ModProcessorsList.LAB_RUINS)),
                                        1//Redstone tests room
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/corridors/c1", processors.getOrThrow(ModProcessorsList.LAB_RUINS)),//sometimes a corridor makes another corrido
                                        1
                                ),
                                Pair.of(
                                        StructurePoolElement.empty(),
                                        1//rare case where corridor just stops
                                )
                        ),
                        StructureTemplatePool.Projection.RIGID
                )
        );

        context.register(
                LAB_RUINS_CORRIDORS,
                new StructureTemplatePool(
                        context.lookup(Registries.TEMPLATE_POOL)
                                .getOrThrow(Pools.EMPTY),
                        List.of(
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/corridors/c1", processors.getOrThrow(ModProcessorsList.LAB_RUINS)),
                                        20
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/corridors/c2", processors.getOrThrow(ModProcessorsList.LAB_RUINS)),
                                        3
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/corridors/c3", processors.getOrThrow(ModProcessorsList.LAB_RUINS)),
                                        19
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/corridors/c4", processors.getOrThrow(ModProcessorsList.LAB_RUINS)),
                                        1
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/corridors/c5", processors.getOrThrow(ModProcessorsList.LAB_RUINS)),
                                        1
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/corridors/c6", processors.getOrThrow(ModProcessorsList.LAB_RUINS)),
                                        2
                                ),
                                Pair.of(
                                        StructurePoolElement.empty(),
                                        4//meh, dead end but works
                                )
                        ),
                        StructureTemplatePool.Projection.RIGID
                )
        );
    }
}