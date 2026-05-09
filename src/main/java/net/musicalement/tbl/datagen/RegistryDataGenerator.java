package net.musicalement.tbl.datagen;

import net.musicalement.tbl.worldgen.biome.TBlBiomes;
import net.musicalement.tbl.worldgen.structure.TBlStructureSets;
import net.musicalement.tbl.worldgen.structure.TBlStructures;
import net.musicalement.tbl.worldgen.structure.TBlTemplatePools;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
/*
* Everything added to registryDataGen is what is supposed to be injected into the datagen lookup so that
* it doesn't just take vanilla stuff and causes errors
*
* */
public class RegistryDataGenerator extends DatapackBuiltinEntriesProvider {

    public RegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(
                output,
                provider,
                new RegistrySetBuilder()
                        .add(Registries.BIOME, TBlBiomes::bootstrap)
                        .add(Registries.STRUCTURE, TBlStructures::bootstrap)
                        .add(Registries.STRUCTURE_SET, TBlStructureSets::bootstrap)
                        .add(Registries.TEMPLATE_POOL, TBlTemplatePools::bootstrap)
                        .add(Registries.PROCESSOR_LIST, TBlProcessorsList::bootstrap),
                Set.of("tbl")
        );
    }
}