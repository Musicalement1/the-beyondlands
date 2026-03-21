package net.beyondlands.tbl.datagen;

import net.beyondlands.tbl.entity.ModEntities;
import net.beyondlands.tbl.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import java.util.stream.Stream;

public class ModEntityLootProvider extends EntityLootSubProvider {
    protected ModEntityLootProvider(HolderLookup.Provider pRegistries) {
        super(FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    public void generate() {
        this.add(
                ModEntities.ASHLING.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                LootItem.lootTableItem(ModItems.ASH.get())
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                                        )
                                        //.when(LootItemKilledByPlayerCondition.killedByPlayer())
                        )
        );

        this.add(
                ModEntities.ASH_ZOMBIE.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                LootItem.lootTableItem(ModItems.ASH.get())
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                                        )
                                //.when(LootItemKilledByPlayerCondition.killedByPlayer())
                        )
        );

        this.add(
                ModEntities.NUKER.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                LootItem.lootTableItem(ModItems.ASH.get())
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                                        )
                                //.when(LootItemKilledByPlayerCondition.killedByPlayer())
                        )
        );
    }


    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return ModEntities.ENTITY_TYPES.getEntries()
                .stream()
                .map(e -> (EntityType<?>) e.value());
    }
}
