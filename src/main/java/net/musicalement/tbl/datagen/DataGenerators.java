package net.musicalement.tbl.datagen;

import net.musicalement.tbl.TBL;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = TBL.MOD_ID)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {

        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        DatapackBuiltinEntriesProvider datapackProvider = new RegistryDataGenerator(packOutput, event.getLookupProvider());
        CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();

        // Loot tables
        generator.addProvider(event.includeServer(),
                new LootTableProvider(
                        packOutput,
                        Collections.emptySet(),
                        List.of(
                                new LootTableProvider.SubProviderEntry(TBlBlockLootTableProvider::new, LootContextParamSets.BLOCK),
                                new LootTableProvider.SubProviderEntry(TBlEntityLootProvider::new, LootContextParamSets.ENTITY),
                                new LootTableProvider.SubProviderEntry(TBlChestLootProvider::new, LootContextParamSets.CHEST)
                        ),
                        lookupProvider
                )
        );


        generator.addProvider(
                event.includeServer(),
                new TBlDatapackEntries(packOutput, lookupProvider)
        );
        // Recipes
        generator.addProvider(event.includeServer(),
                new TBlRecipeProvider(packOutput, lookupProvider)
        );

        // Advancements
        generator.addProvider(event.includeServer(),
                new TBlAdvancementProvider(packOutput, lookupProvider, existingFileHelper)
        );

        // Block tags
        BlockTagsProvider blockTagsProvider =
                new TBlBlockTagProvider(packOutput, lookupProvider, existingFileHelper);

        generator.addProvider(event.includeServer(), blockTagsProvider);

        // Item tags
        generator.addProvider(event.includeServer(),
                new TBlItemTagProvider(
                        packOutput,
                        lookupProvider,
                        blockTagsProvider.contentsGetter(),
                        existingFileHelper
                )
        );

        // Biome tags
        generator.addProvider(event.includeServer(),
                new TBlBiomeTagGenerator(packOutput, lookupProvider, existingFileHelper)
        );

        // Client models
        generator.addProvider(event.includeClient(),
                new TBlItemModelProvider(packOutput, existingFileHelper)
        );

        generator.addProvider(event.includeClient(),
                new TBlBlockStateProvider(packOutput, existingFileHelper)
        );
    }
}