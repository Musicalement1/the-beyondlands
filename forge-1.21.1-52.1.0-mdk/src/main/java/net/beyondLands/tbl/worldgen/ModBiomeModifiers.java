package net.beyondLands.tbl.worldgen;



import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.datagen.ModBiomeTagGenerator;
import net.beyondLands.tbl.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_LITHIUM_ORE = registerKey("add_lithium_ore");
    public static final ResourceKey<BiomeModifier> ADD_ASH_TREE = registerKey("add_tree_ash");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeature = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_LITHIUM_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModBiomeTagGenerator.IS_BL_OR_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.LITHIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        // Individual Biomes
        // context.register(ADD_ALEXANDRITE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
        //         HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.BAMBOO_JUNGLE)),
        //         HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.ALEXANDRITE_ORE_PLACED_KEY)),
        //         GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_ASH_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.ASHLAND)/*, biomes.getOrThrow(Biomes.SAVANNA)*/),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.ASH_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(TBL.MOD_ID, name));
    }
}