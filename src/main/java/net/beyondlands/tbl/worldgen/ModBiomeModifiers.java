package net.beyondlands.tbl.worldgen;



import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.datagen.ModBiomeTagGenerator;
import net.beyondlands.tbl.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_LITHIUM_ORE = registerKey("add_lithium_ore");
    public static final ResourceKey<BiomeModifier> ADD_ASH_TREE = registerKey("add_tree_ash");
    public static final ResourceKey<BiomeModifier> ADD_CORIUM = registerKey("add_corium");
    public static final ResourceKey<BiomeModifier> ADD_HUGE_GREEN_MUSHROOM = registerKey("add_huge_green_mushroom");
    public static final ResourceKey<BiomeModifier> ADD_GREEN_MUSHROOM = registerKey("add_green_mushroom");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeature = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_LITHIUM_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModBiomeTagGenerator.IS_BL_OR_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.LITHIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        // Individual Biomes
        // context.register(ADD_ALEXANDRITE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
        //         HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.BAMBOO_JUNGLE)),
        //         HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.ALEXANDRITE_ORE_PLACED_KEY)),
        //         GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_ASH_TREE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.ASHLAND)/*, biomes.getOrThrow(Biomes.SAVANNA)*/),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.ASH_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(
                ADD_CORIUM,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        context.lookup(Registries.BIOME)
                                .getOrThrow(ModBiomeTagGenerator.CORIUM_FEATURES_SPAWN_IN),
                        HolderSet.direct(
                                placedFeature.getOrThrow(ModPlacedFeatures.CORIUM_PLACED_KEY)
                        ),
                        GenerationStep.Decoration.SURFACE_STRUCTURES
                )
        );

        context.register(
                ADD_HUGE_GREEN_MUSHROOM,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        context.lookup(Registries.BIOME)
                                .getOrThrow(ModBiomeTagGenerator.CORIUM_FEATURES_SPAWN_IN),
                        HolderSet.direct(
                                placedFeature.getOrThrow(ModPlacedFeatures.HUGE_GREEN_MUSHROOM_PLACED_KEY)
                        ),
                        GenerationStep.Decoration.SURFACE_STRUCTURES
                )
        );

        context.register(
                ADD_GREEN_MUSHROOM,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        context.lookup(Registries.BIOME)
                                .getOrThrow(ModBiomeTagGenerator.CORIUM_FEATURES_SPAWN_IN),
                        HolderSet.direct(
                                placedFeature.getOrThrow(ModPlacedFeatures.GREEN_MUSHROOM_PLACED_KEY)
                        ),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(TBL.MOD_ID, name));
    }
}