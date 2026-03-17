package net.beyondlands.tbl.datagen;

import net.beyondlands.tbl.TBL;
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
                                new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK),
                                new LootTableProvider.SubProviderEntry(ModEntityLootProvider::new, LootContextParamSets.ENTITY),
                                new LootTableProvider.SubProviderEntry(ModChestLootProvider::new, LootContextParamSets.CHEST)
                        ),
                        lookupProvider
                )
        );


        generator.addProvider(
                event.includeServer(),
                new ModDatapackEntries(packOutput, lookupProvider)
        );
        // Recipes
        generator.addProvider(event.includeServer(),
                new ModRecipeProvider(packOutput, lookupProvider)
        );

        // Advancements
        generator.addProvider(event.includeServer(),
                new ModAdvancementProvider(packOutput, lookupProvider, existingFileHelper)
        );

        // Block tags
        BlockTagsProvider blockTagsProvider =
                new ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper);

        generator.addProvider(event.includeServer(), blockTagsProvider);

        // Item tags
        generator.addProvider(event.includeServer(),
                new ModItemTagProvider(
                        packOutput,
                        lookupProvider,
                        blockTagsProvider.contentsGetter(),
                        existingFileHelper
                )
        );

        // Biome tags
        generator.addProvider(event.includeServer(),
                new ModBiomeTagGenerator(packOutput, lookupProvider, existingFileHelper)
        );

        // Client models
        generator.addProvider(event.includeClient(),
                new ModItemModelProvider(packOutput, existingFileHelper)
        );

        generator.addProvider(event.includeClient(),
                new ModBlockStateProvider(packOutput, existingFileHelper)
        );
    }
}