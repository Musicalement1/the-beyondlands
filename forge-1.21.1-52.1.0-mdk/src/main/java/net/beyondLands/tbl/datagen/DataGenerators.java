package net.beyondLands.tbl.datagen;

import net.beyondLands.tbl.TBL;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = TBL.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        //Vanilla (all mc here)
        CompletableFuture<HolderLookup.Provider> vanillaLookup = event.getLookupProvider();

        //Mod registries
        ModDatapackEntries datapackEntries = new ModDatapackEntries(packOutput, vanillaLookup);
        generator.addProvider(event.includeServer(), datapackEntries);

        CompletableFuture<HolderLookup.Provider> registryLookup = datapackEntries.getRegistryProvider();

        //loot tables etc
        generator.addProvider(event.includeServer(),
                new LootTableProvider(packOutput, Collections.emptySet(),
                        List.of(
                                new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK),
                                new LootTableProvider.SubProviderEntry(ModEntityLootProvider::new, LootContextParamSets.ENTITY),
                                new LootTableProvider.SubProviderEntry(ModChestLootProvider::new, LootContextParamSets.CHEST)
                        ),
                        vanillaLookup));

        generator.addProvider(event.includeServer(),
                new ModRecipeProvider(packOutput, vanillaLookup));

        generator.addProvider(event.includeServer(),
                new ModAdvancementProvider(packOutput, vanillaLookup, existingFileHelper));

        //tags that use modified registry
        BlockTagsProvider blockTagsProvider =
                new ModBlockTagProvider(packOutput, registryLookup, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);

        generator.addProvider(event.includeServer(),
                new ModItemTagProvider(packOutput, registryLookup, blockTagsProvider.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(),
                new ModBiomeTagGenerator(packOutput, registryLookup, existingFileHelper));

        //client side models
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));
    }
}