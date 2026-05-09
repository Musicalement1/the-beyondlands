package net.musicalement.tbl.worldgen.biome;



import net.musicalement.tbl.TBL;
import net.musicalement.tbl.entity.TBlEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;

public class TBlBiomes {

    public static final ResourceKey<Biome> ASHLAND = ResourceKey.create(
            Registries.BIOME,
            TBL.prefix("ashland")
    );

    public static final ResourceKey<Biome> WASTELAND = ResourceKey.create(
            Registries.BIOME,
            TBL.prefix("wasteland")
    );

    public static void bootstrap(BootstrapContext<Biome> context) {

        context.register(ASHLAND, createAshland(context));
        context.register(WASTELAND, createWasteland(context));

    }

    private static Biome createWasteland(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(TBlEntities.NUKER.get(), 2, 1, 2));
        BiomeGenerationSettings.Builder generationBuilder =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER)
                );

        BiomeDefaultFeatures.addDefaultOres(generationBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.4f)
                .downfall(0.7f)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .grassColorOverride(0x127334)
                        .waterColor(0x00FF56)
                        .waterFogColor(0x00FF56)
                        .fogColor(0x20B352)
                        .skyColor(0x0E4F25)
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.ITEM_SLIME, 0.003F))
                        .build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(generationBuilder.build())
                .build();
    }


    private static Biome createAshland(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE,
             new MobSpawnSettings.SpawnerData(TBlEntities.ASHLING.get(), 8, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(TBlEntities.ASH_ZOMBIE.get(), 2, 1, 4));
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
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.ASH, 0.00625F))
                        .build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(generationBuilder.build())
                .build();
    }
}