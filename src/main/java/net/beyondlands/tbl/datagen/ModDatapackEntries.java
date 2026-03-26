package net.beyondlands.tbl.datagen;


import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.worldgen.ModBiomeModifiers;
import net.beyondlands.tbl.worldgen.ModConfiguredFeatures;
import net.beyondlands.tbl.worldgen.ModPlacedFeatures;
import net.beyondlands.tbl.worldgen.biome.ModBiomes;
import net.beyondlands.tbl.worldgen.dimension.ModDimensions;
import net.beyondlands.tbl.worldgen.structure.ModStructureSets;
import net.beyondlands.tbl.worldgen.structure.ModStructures;
import net.beyondlands.tbl.worldgen.structure.ModTemplatePools;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackEntries extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            //.add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap)
            //.add(Registries.TRIM_PATTERN, ModTrimPatterns::bootstrap)
            //.add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)
            //.add(Registries.NOISE_SETTINGS, ModNoiseGeneratorSettings::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(Registries.BIOME, ModBiomes::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            .add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType)
            .add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem)
            .add(Registries.PROCESSOR_LIST, ModProcessorsList::bootstrap)
            .add(Registries.STRUCTURE, ModStructures::bootstrap)
            .add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap)
            .add(Registries.TEMPLATE_POOL, ModTemplatePools::bootstrap);

    public ModDatapackEntries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(TBL.MOD_ID));
    }
}