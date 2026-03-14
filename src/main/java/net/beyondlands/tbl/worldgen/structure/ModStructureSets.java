package net.beyondlands.tbl.worldgen.structure;


import net.beyondlands.tbl.TBL;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.core.registries.Registries;

import java.util.List;

public class ModStructureSets {

    public static final ResourceKey<StructureSet> ASHLING_RUINS_SET =
            ResourceKey.create(
                    Registries.STRUCTURE_SET,
                    TBL.prefix("ashling_ruins")
            );

    public static void bootstrap(BootstrapContext<StructureSet> context) {

        Holder<Structure> structure =
                context.lookup(Registries.STRUCTURE)
                        .getOrThrow(ModStructures.ASHLING_RUINS);

        context.register(
                ASHLING_RUINS_SET,
                new StructureSet(
                        List.of(new StructureSet.StructureSelectionEntry(structure, 1)),
                        new RandomSpreadStructurePlacement(
                                32,
                                8,
                                RandomSpreadType.LINEAR,
                                13307654
                        )
                )
        );
    }
}