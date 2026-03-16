package net.beyondlands.tbl.worldgen.structure;

import com.mojang.datafixers.util.Pair;
import net.beyondlands.tbl.TBL;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

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

    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {

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
                                                "tbl:lab_ruins/entrance/entrance"
                                                //processors.getOrThrow(ProcessorLists.EMPTY)
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
                                        StructurePoolElement.single("tbl:lab_ruins/rooms/room1"),//chest with brew, 1 exit
                                        2//ça c'est le poids (weight)
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/rooms/room2"),//dim teleporter
                                        2
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/rooms/room3"),//attractor trap
                                        1
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/rooms/room4"),//repulsor trap
                                        1
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/rooms/room5"),//simple room, acts as a corridor
                                        4
                                ),
                                Pair.of(
                                        StructurePoolElement.single("tbl:lab_ruins/rooms/room6"),//escalator (9x<!6>x9)
                                        2
                                )
                        ),
                        StructureTemplatePool.Projection.RIGID
                )
        );
    }
}