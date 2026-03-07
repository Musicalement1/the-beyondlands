package net.beyondLands.tbl.datagen;

import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagGenerator extends BiomeTagsProvider {
    public static final TagKey<Biome> VALID_LAB_RUINS_BIOMES = TagKey.create(Registries.BIOME, TBL.prefix("valid_lab_ruins_biomes"));
    public static final TagKey<Biome> IS_BL = TagKey.create(Registries.BIOME, TBL.prefix("is_bl"));
    public static final TagKey<Biome> IS_BL_OR_OVERWORLD = TagKey.create(Registries.BIOME, TBL.prefix("is_bl_or_overworld"));
    public static final  TagKey<Biome> ASH_RUINS_SPAWN_IN = TagKey.create(Registries.BIOME, TBL.prefix("ash_ruins_spawn_in"));


    public ModBiomeTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, TBL.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        provider.lookupOrThrow(Registries.BIOME)
                .getOrThrow(ModBiomes.ASHLAND);
        this.tag(VALID_LAB_RUINS_BIOMES).add(
                Biomes.PLAINS,
                Biomes.FOREST
        );
        this.tag(IS_BL).add(
                ModBiomes.ASHLAND
        );
        this.tag(IS_BL_OR_OVERWORLD)
                .addTag(BiomeTags.IS_OVERWORLD)
                .addTag(ModBiomeTagGenerator.IS_BL);

        this.tag(ASH_RUINS_SPAWN_IN).add(
                ModBiomes.ASHLAND
        );
    }
}

