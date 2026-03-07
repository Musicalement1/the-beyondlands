package net.beyondLands.tbl.worldgen.biome;



import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.entity.ModEntities;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
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
        // SPAWNS
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE,
             new MobSpawnSettings.SpawnerData(ModEntities.ASHLING.get(), 8, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(ModEntities.ASH_ZOMBIE.get(), 2, 1, 4));
        // GENERATION
        BiomeGenerationSettings.Builder generationBuilder =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER)
                );

        // Exemple d'ajout de features vanilla
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