package net.beyondlands.tbl.datagen;

import net.beyondlands.tbl.worldgen.biome.ModBiomes;
import net.beyondlands.tbl.worldgen.structure.ModStructureSets;
import net.beyondlands.tbl.worldgen.structure.ModStructures;
import net.beyondlands.tbl.worldgen.structure.ModTemplatePools;
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
                        .add(Registries.BIOME, ModBiomes::bootstrap)
                        .add(Registries.STRUCTURE, ModStructures::bootstrap)
                        .add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap)
                        .add(Registries.TEMPLATE_POOL, ModTemplatePools::bootstrap)
                        .add(Registries.PROCESSOR_LIST, ModProcessorsList::bootstrap),
                Set.of("tbl")
        );
    }
}