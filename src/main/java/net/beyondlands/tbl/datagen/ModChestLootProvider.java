package net.beyondlands.tbl.datagen;

import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.*;
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

    public static final ResourceKey<LootTable> LAB_RUINS =
            ResourceKey.create(
                    net.minecraft.core.registries.Registries.LOOT_TABLE,
                    TBL.prefix("chests/lab_ruins")
            );

    public ModChestLootProvider(HolderLookup.Provider registries) {
        this.registries = registries;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {

        HolderLookup.RegistryLookup<Enchantment> lookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        consumer.accept(
                ASHLING_RUINS,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(4))

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
                                                .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.5F, 1.0F)))
                                                .apply(SetItemCountFunction.setCount(
                                                        UniformGenerator.between(1, 2))))
                                        .add(LootItem.lootTableItem(ModItems.LITHIUM_BATTERY_STACK.get())
                                                .setWeight(5)
                                                .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.5F, 1.0F)))
                                                .apply(SetItemCountFunction.setCount(
                                                        UniformGenerator.between(1, 2))))
                                        .add(LootItem.lootTableItem(ModItems.PEPPER_SEEDS.get())
                                                .setWeight(25)
                                                .apply(SetItemCountFunction.setCount(
                                                        UniformGenerator.between(2, 9))))
                        )
        );



        consumer.accept(
                LAB_RUINS,
                LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                        .setRolls(UniformGenerator.between(2.0f, 5.0f))


                                .add(LootItem.lootTableItem(Items.GLASS_BOTTLE)
                                        .setWeight(20)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(2, 9))
                                        )
                                )


                                .add(LootItem.lootTableItem(Items.SUSPICIOUS_STEW)
                                        .setWeight(10)
                                        .apply(SetStewEffectFunction.stewEffect()
                                                .withEffect(MobEffects.BLINDNESS, UniformGenerator.between(5, 10))
                                                .withEffect(MobEffects.DARKNESS, UniformGenerator.between(5, 10))
                                                .withEffect(MobEffects.CONFUSION, UniformGenerator.between(5, 10))
                                                .withEffect(MobEffects.REGENERATION, UniformGenerator.between(5, 10))
                                        )
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(2, 3))
                                        )
                                )

                                .add(LootItem.lootTableItem(Items.POTION)
                                        .setWeight(5)
                                        .apply(SetPotionFunction.setPotion(Potions.STRONG_TURTLE_MASTER))
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1, 2))
                                        )
                                )

                                .add(LootItem.lootTableItem(Items.ENDER_PEARL)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1, 4))
                                        )
                                )

                                .add(LootItem.lootTableItem(Items.OBSIDIAN)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1, 4))
                                        )
                                )

                                .add(LootItem.lootTableItem(Items.END_CRYSTAL)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1, 2))
                                        )
                                )

                                .add(LootItem.lootTableItem(ModItems.STEEL_CHESTPLATE.get())
                                        .setWeight(5)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1))
                                        )
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(this.registries, UniformGenerator.between(5.0F, 15.0F)))
                                )

                                .add(LootItem.lootTableItem(Items.WRITABLE_BOOK)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1, 2))
                                        )
                                )
                                .add(LootItem.lootTableItem(ModItems.ENCHANTITE_SHARD.get())
                                        .setWeight(7)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1, 2))
                                        )
                                )

                )
        );



    }
}