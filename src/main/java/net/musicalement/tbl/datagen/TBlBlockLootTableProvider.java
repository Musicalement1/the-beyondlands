package net.musicalement.tbl.datagen;

import net.musicalement.tbl.block.crop.PepperCropBlock;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.musicalement.tbl.item.TBlItems;
import net.musicalement.tbl.block.TBlBlocks;
import java.util.Set;

public class TBlBlockLootTableProvider extends BlockLootSubProvider {
    protected TBlBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        dropSelf(TBlBlocks.LITHIUM_BLOCK.get());
        dropSelf(TBlBlocks.RAW_LITHIUM_BLOCK.get());
        dropSelf(TBlBlocks.FORCE_FIELD_BLOCK.get());
        dropSelf(TBlBlocks.FORCE_FIELD_BLOCK_ATTRACT.get());
        dropSelf(TBlBlocks.GNEISS.get());
        dropSelf(TBlBlocks.LAB_BLOCK.get());
        dropSelf(TBlBlocks.GATE_OPENER.get());
        dropSelf(TBlBlocks.CORIUM.get());
        dropSelf(TBlBlocks.GREEN_MUSHROOM.get());
        dropSelf(TBlBlocks.BOOSTING_TABLE.get());
        dropSelf(TBlBlocks.PROPULSOR.get());

        this.add(TBlBlocks.LITHIUM_ORE.get(),
                block -> createOreDrop(TBlBlocks.LITHIUM_ORE.get(), TBlItems.RAW_LITHIUM.get()));
        this.add(TBlBlocks.LITHIUM_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(TBlBlocks.LITHIUM_DEEPSLATE_ORE.get(), TBlItems.RAW_LITHIUM.get(), 2, 6));


        this.dropSelf(TBlBlocks.ASH_LOG.get());
        this.dropSelf(TBlBlocks.ASH_WOOD.get());
        this.dropSelf(TBlBlocks.STRIPPED_ASH_LOG.get());
        this.dropSelf(TBlBlocks.STRIPPED_ASH_WOOD.get());
        this.dropSelf(TBlBlocks.ASH_PLANKS.get());
        this.dropSelf(TBlBlocks.ASH_SAPLING.get());

        this.add(TBlBlocks.GREEN_MUSHROOM_BLOCK.get(), block ->
                this.createMushroomBlockDrop(block, TBlBlocks.GREEN_MUSHROOM_BLOCK)
                );

        this.add(TBlBlocks.ASH_LEAVES.get(), block ->
                createLeavesDrops(block, TBlBlocks.ASH_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));


        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TBlBlocks.PEPPER_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PepperCropBlock.AGE, PepperCropBlock.MAX_AGE));

        this.add(TBlBlocks.PEPPER_CROP.get(), this.createCropDrops(TBlBlocks.PEPPER_CROP.get(),
                TBlItems.PEPPER.get(), TBlItems.PEPPER_SEEDS.get(), lootItemConditionBuilder));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock, this.applyExplosionDecay(
                        pBlock, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }



    @Override
    protected Iterable<Block> getKnownBlocks() {
        return TBlBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}