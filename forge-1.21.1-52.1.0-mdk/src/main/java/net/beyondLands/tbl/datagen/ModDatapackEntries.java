package net.beyondLands.tbl.datagen;


import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.worldgen.ModBiomeModifiers;
import net.beyondLands.tbl.worldgen.ModConfiguredFeatures;
import net.beyondLands.tbl.worldgen.ModPlacedFeatures;
import net.beyondLands.tbl.worldgen.biome.ModBiomes;
import net.beyondLands.tbl.worldgen.dimension.ModDimensions;
import net.beyondLands.tbl.worldgen.structure.ModStructureSets;
import net.beyondLands.tbl.worldgen.structure.ModStructures;
import net.beyondLands.tbl.worldgen.structure.ModTemplatePools;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackEntries extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            //.add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap)
            //.add(Registries.TRIM_PATTERN, ModTrimPatterns::bootstrap)
            //.add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)

            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            .add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType)
            .add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem)
            .add(Registries.BIOME, ModBiomes::bootstrap)
            .add(Registries.STRUCTURE, ModStructures::bootstrap)
            .add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap)
            .add(Registries.TEMPLATE_POOL, ModTemplatePools::bootstrap);

    public ModDatapackEntries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(TBL.MOD_ID));
    }
}