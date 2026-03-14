package net.beyondlands.tbl.worldgen.biome;



import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.entity.ModEntities;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;

public class ModBiomes {

    public static final ResourceKey<Biome> ASHLAND = ResourceKey.create(
            Registries.BIOME,
            TBL.prefix("ashland")
    );

    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(ASHLAND, createAshland(context));
    }

    private static Biome createAshland(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE,
             new MobSpawnSettings.SpawnerData(ModEntities.ASHLING.get(), 8, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(ModEntities.ASH_ZOMBIE.get(), 2, 1, 4));
        BiomeGenerationSettings.Builder generationBuilder =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER)
                );

        BiomeDefaultFeatures.addDefaultOres(generationBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.8f)
                .downfall(0.4f)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .grassColorOverride(0x4A4A4A)
                        .waterColor(0xF54927)
                        .waterFogColor(0x4A4A4A)
                        .fogColor(0x7F3729)
                        .skyColor(0x7F3729)
                        .build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(generationBuilder.build())
                .build();
    }
}