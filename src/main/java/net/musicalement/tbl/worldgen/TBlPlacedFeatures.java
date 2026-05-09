package net.musicalement.tbl.worldgen;

import net.musicalement.tbl.TBL;
import net.musicalement.tbl.block.TBlBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class TBlPlacedFeatures {
    public static final ResourceKey<PlacedFeature> LITHIUM_ORE_PLACED_KEY = registerKey("lithium_ore_placed");
    public static final ResourceKey<PlacedFeature> ASH_PLACED_KEY = registerKey("ash_placed");
    public static final ResourceKey<PlacedFeature> CORIUM_PLACED_KEY = registerKey("corium_placed");
    public static final ResourceKey<PlacedFeature> HUGE_GREEN_MUSHROOM_PLACED_KEY = registerKey("huge_green_mushroom_placed");
    public static final ResourceKey<PlacedFeature> GREEN_MUSHROOM_PLACED_KEY = registerKey("green_mushroom_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, LITHIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(TBlConfiguredFeatures.OVERWORLD_LITHIUM_ORE_KEY),
                TBlOrePlacement.commonOrePlacement(12,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, ASH_PLACED_KEY, configuredFeatures.getOrThrow(TBlConfiguredFeatures.ASH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        TBlBlocks.ASH_SAPLING.get()));

        register(
                context,
                CORIUM_PLACED_KEY,
                configuredFeatures.getOrThrow(TBlConfiguredFeatures.CORIUM_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(5),
                        CountPlacement.of(2),//spawn 2 of them when its placed
                        InSquarePlacement.spread(),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                        BiomeFilter.biome()
                )
        );

        register(
                context,
                HUGE_GREEN_MUSHROOM_PLACED_KEY,
                configuredFeatures.getOrThrow(TBlConfiguredFeatures.HUGE_GREEN_MUSHROOM),
                List.of(
                        RarityFilter.onAverageOnceEvery(7),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()
                )
        );

        register(
                context,
                GREEN_MUSHROOM_PLACED_KEY,
                configuredFeatures.getOrThrow(TBlConfiguredFeatures.GREEN_MUSHROOM),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()
                )
        );
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TBL.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}