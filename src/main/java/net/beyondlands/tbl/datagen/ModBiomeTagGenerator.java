package net.beyondlands.tbl.datagen;

import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBiomeTagGenerator extends BiomeTagsProvider {
    public static final TagKey<Biome> VALID_LAB_RUINS_BIOMES = TagKey.create(Registries.BIOME, TBL.prefix("valid_lab_ruins_biomes"));
    public static final TagKey<Biome> IS_BL = TagKey.create(Registries.BIOME, TBL.prefix("is_bl"));
    public static final TagKey<Biome> IS_BL_OR_OVERWORLD = TagKey.create(Registries.BIOME, TBL.prefix("is_bl_or_overworld"));
    public static final  TagKey<Biome> ASH_RUINS_SPAWN_IN = TagKey.create(Registries.BIOME, TBL.prefix("ash_ruins_spawn_in"));
    public static final TagKey<Biome> CORIUM_FEATURES_SPAWN_IN = TagKey.create(Registries.BIOME, TBL.prefix("corium_features_spawn_in"));


    public ModBiomeTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, TBL.MOD_ID, helper);
    }
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(VALID_LAB_RUINS_BIOMES).add(
                Biomes.DARK_FOREST,
                Biomes.OLD_GROWTH_PINE_TAIGA,
                Biomes.OLD_GROWTH_SPRUCE_TAIGA,
                Biomes.SNOWY_TAIGA,
                Biomes.ICE_SPIKES,
                Biomes.DESERT,
                Biomes.MUSHROOM_FIELDS,
                Biomes.SWAMP,
                Biomes.MANGROVE_SWAMP,
                Biomes.FROZEN_PEAKS
        );
        this.tag(IS_BL).add(
                ModBiomes.ASHLAND,
                ModBiomes.WASTELAND
        );
        this.tag(IS_BL_OR_OVERWORLD)
                .addTag(BiomeTags.IS_OVERWORLD)
                .addTag(ModBiomeTagGenerator.IS_BL);

        this.tag(ASH_RUINS_SPAWN_IN).add(
                ModBiomes.ASHLAND
        );

        this.tag(CORIUM_FEATURES_SPAWN_IN).add(
                ModBiomes.WASTELAND
        );
    }
}

