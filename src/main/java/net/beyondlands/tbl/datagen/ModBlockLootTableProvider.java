package net.beyondlands.tbl.datagen;

import net.beyondlands.tbl.block.crop.PepperCropBlock;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.beyondlands.tbl.item.ModItems;
import net.beyondlands.tbl.block.ModBlocks;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.LITHIUM_BLOCK.get());
        dropSelf(ModBlocks.RAW_LITHIUM_BLOCK.get());
        dropSelf(ModBlocks.FORCE_FIELD_BLOCK.get());
        dropSelf(ModBlocks.FORCE_FIELD_BLOCK_ATTRACT.get());
        dropSelf(ModBlocks.GNEISS.get());
        dropSelf(ModBlocks.LAB_BLOCK.get());
        dropSelf(ModBlocks.GATE_OPENER.get());
        dropSelf(ModBlocks.CORIUM.get());
        dropSelf(ModBlocks.GREEN_MUSHROOM.get());

        this.add(ModBlocks.LITHIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.LITHIUM_ORE.get(), ModItems.RAW_LITHIUM.get()));
        this.add(ModBlocks.LITHIUM_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.LITHIUM_DEEPSLATE_ORE.get(), ModItems.RAW_LITHIUM.get(), 2, 6));


        this.dropSelf(ModBlocks.ASH_LOG.get());
        this.dropSelf(ModBlocks.ASH_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_ASH_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_ASH_WOOD.get());
        this.dropSelf(ModBlocks.ASH_PLANKS.get());
        this.dropSelf(ModBlocks.ASH_SAPLING.get());

        this.add(ModBlocks.GREEN_MUSHROOM_BLOCK.get(), block ->
                this.createMushroomBlockDrop(block, ModBlocks.GREEN_MUSHROOM_BLOCK)
                );

        this.add(ModBlocks.ASH_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.ASH_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));


        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PEPPER_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PepperCropBlock.AGE, PepperCropBlock.MAX_AGE));

        this.add(ModBlocks.PEPPER_CROP.get(), this.createCropDrops(ModBlocks.PEPPER_CROP.get(),
                ModItems.PEPPER.get(), ModItems.PEPPER_SEEDS.get(), lootItemConditionBuilder));
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
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}