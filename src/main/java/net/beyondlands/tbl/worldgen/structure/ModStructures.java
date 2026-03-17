package net.beyondlands.tbl.worldgen.structure;


import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.datagen.ModBiomeTagGenerator;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.core.registries.Registries;

import java.util.Map;

public class ModStructures {

    public static final ResourceKey<Structure> ASHLING_RUINS =
            ResourceKey.create(Registries.STRUCTURE, TBL.prefix("ashling_ruins"));

    public static final ResourceKey<Structure> LAB_RUINS =
            ResourceKey.create(Registries.STRUCTURE, TBL.prefix("lab_ruins"));


    public static void bootstrap(BootstrapContext<Structure> context) {

        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);

        context.register(
                ASHLING_RUINS,
                new JigsawStructure(
                        new Structure.StructureSettings.Builder(
                                biomes.getOrThrow(ModBiomeTagGenerator.ASH_RUINS_SPAWN_IN)
                        )
                                .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
                                .terrainAdapation(TerrainAdjustment.BEARD_THIN)
                                .build(),

                        pools.getOrThrow(ModTemplatePools.ASHLING_RUINS_POOL),

                        1, //size basically
                        ConstantHeight.of(VerticalAnchor.absolute(0)),
                        false,
                        Heightmap.Types.WORLD_SURFACE_WG
                )
        );

        context.register(
                LAB_RUINS,
                new JigsawStructure(
                        new Structure.StructureSettings(
                                biomes.getOrThrow(ModBiomeTagGenerator.VALID_LAB_RUINS_BIOMES),
                                Map.of(),
                                GenerationStep.Decoration.SURFACE_STRUCTURES,
                                TerrainAdjustment.BEARD_THIN
                        ),
                        pools.getOrThrow(ModTemplatePools.LAB_RUINS_POOL),
                        8,
                        ConstantHeight.of(VerticalAnchor.absolute(0)),
                        false,
                        Heightmap.Types.WORLD_SURFACE_WG
                )
        );

    }
}