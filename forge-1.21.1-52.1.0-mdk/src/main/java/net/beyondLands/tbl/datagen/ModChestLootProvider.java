package net.beyondLands.tbl.datagen;

import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class ModChestLootProvider implements LootTableSubProvider {

    private final HolderLookup.Provider registries;

    public static final ResourceKey<LootTable> ASHLING_RUINS =
            ResourceKey.create(
                    net.minecraft.core.registries.Registries.LOOT_TABLE,
                    TBL.prefix("chests/ashling_ruins")
            );

    public ModChestLootProvider(HolderLookup.Provider registries) {
        this.registries = registries;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {

        consumer.accept(
                ASHLING_RUINS,

                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(3))

                                        .add(LootItem.lootTableItem(Items.DIAMOND)
                                                .setWeight(5)
                                                .apply(SetItemCountFunction.setCount(
                                                        UniformGenerator.between(1, 2))))

                                        .add(LootItem.lootTableItem(Items.IRON_INGOT)
                                                .setWeight(15)
                                                .apply(SetItemCountFunction.setCount(
                                                        UniformGenerator.between(2, 6))))

                                        .add(LootItem.lootTableItem(Items.BREAD)
                                                .setWeight(20)
                                                .apply(SetItemCountFunction.setCount(
                                                        UniformGenerator.between(1, 4))))
                                        .add(LootItem.lootTableItem(ModItems.STEEL_INGOT.get())
                                                .setWeight(20)
                                                .apply(SetItemCountFunction.setCount(
                                                        UniformGenerator.between(1, 4))))
                                        .add(LootItem.lootTableItem(ModItems.LITHIUM_BATTERY.get())
                                                .setWeight(20)
                                                .apply(SetItemCountFunction.setCount(
                                                        UniformGenerator.between(1, 2))))
                                        .add(LootItem.lootTableItem(ModItems.LITHIUM_BATTERY_STACK.get())
                                                .setWeight(5)
                                                .apply(SetItemCountFunction.setCount(
                                                        UniformGenerator.between(1, 2))))
                        )
        );
    }
}