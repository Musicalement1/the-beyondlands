package net.musicalement.tbl.datagen;


import net.musicalement.tbl.TBL;
import net.musicalement.tbl.worldgen.TBlBiomeModifiers;
import net.musicalement.tbl.worldgen.TBlConfiguredFeatures;
import net.musicalement.tbl.worldgen.TBlPlacedFeatures;
import net.musicalement.tbl.worldgen.biome.TBlBiomes;
import net.musicalement.tbl.worldgen.dimension.TBlDimensions;
import net.musicalement.tbl.worldgen.structure.TBlStructureSets;
import net.musicalement.tbl.worldgen.structure.TBlStructures;
import net.musicalement.tbl.worldgen.structure.TBlTemplatePools;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TBlDatapackEntries extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            //.add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap)
            //.add(Registries.TRIM_PATTERN, ModTrimPatterns::bootstrap)
            //.add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)
            //.add(Registries.NOISE_SETTINGS, ModNoiseGeneratorSettings::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, TBlConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, TBlPlacedFeatures::bootstrap)
            .add(Registries.BIOME, TBlBiomes::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, TBlBiomeModifiers::bootstrap)
            .add(Registries.DIMENSION_TYPE, TBlDimensions::bootstrapType)
            .add(Registries.LEVEL_STEM, TBlDimensions::bootstrapStem)
            .add(Registries.PROCESSOR_LIST, TBlProcessorsList::bootstrap)
            .add(Registries.STRUCTURE, TBlStructures::bootstrap)
            .add(Registries.STRUCTURE_SET, TBlStructureSets::bootstrap)
            .add(Registries.TEMPLATE_POOL, TBlTemplatePools::bootstrap);

    public TBlDatapackEntries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(TBL.MOD_ID));
    }
}