package net.beyondLands.tbl.worldgen.structure;

import com.mojang.datafixers.util.Pair;
import net.beyondLands.tbl.TBL;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.List;

public class ModTemplatePools {

    public static final ResourceKey<StructureTemplatePool> ASHLING_RUINS_POOL =
            ResourceKey.create(
                    Registries.TEMPLATE_POOL,
                    TBL.prefix("ashling_ruins_pool")
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
    }
}