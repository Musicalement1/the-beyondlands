package net.musicalement.tbl.worldgen;

import net.musicalement.tbl.TBL;
import net.musicalement.tbl.worldgen.mushroom.HugeGreenMushroomFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class TBlFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, TBL.MOD_ID);

    public static final DeferredHolder<Feature<?>, Feature<HugeMushroomFeatureConfiguration>> HUGE_GREEN_MUSHROOM =
            FEATURES.register("huge_green_mushroom",
                    () -> new HugeGreenMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));
}
