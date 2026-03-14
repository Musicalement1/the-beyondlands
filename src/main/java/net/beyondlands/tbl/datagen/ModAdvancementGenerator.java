package net.beyondlands.tbl.datagen;

import net.beyondlands.tbl.block.ModBlocks;
import net.beyondlands.tbl.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.internal.NeoForgeAdvancementProvider;

import java.util.List;
import java.util.function.Consumer;

public class ModAdvancementGenerator implements NeoForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider registries,
                         Consumer<AdvancementHolder> saver,
                         ExistingFileHelper existingFileHelper) {

        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        new ItemStack(ModItems.LITHIUM.get()),
                        Component.translatable("advancement.tbl.story.root.title"),
                        Component.translatable("advancement.tbl.story.root.description"),
                        ResourceLocation.fromNamespaceAndPath("tbl", "textures/block/lithium_ore.png"),
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("pickup_crafting",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRAFTING_TABLE))
                .requirements(AdvancementRequirements.allOf(List.of("pickup_crafting")))
                .save(saver, ResourceLocation.fromNamespaceAndPath("tbl", "story/root"), existingFileHelper);
        AdvancementHolder obtain_raw_lithium = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        new ItemStack(ModBlocks.LITHIUM_ORE.get()),
                        Component.translatable("advancement.tbl.story.obtain_raw_lithium.title"),
                        Component.translatable("advancement.tbl.story.obtain_raw_lithium.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("pickup_raw_li",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_LITHIUM.get()))
                .requirements(AdvancementRequirements.allOf(List.of("pickup_raw_li")))
                .save(saver, ResourceLocation.fromNamespaceAndPath("tbl", "story/obtain_raw_lithium"), existingFileHelper);

        AdvancementHolder smelt_lithium = Advancement.Builder.advancement()
                .parent(obtain_raw_lithium)
                .display(
                        new ItemStack(ModItems.LITHIUM.get()),
                        Component.translatable("advancement.tbl.story.smelt_lithium.title"),
                        Component.translatable("advancement.tbl.story.smelt_lithium.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("pickup_li",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LITHIUM.get()))
                .requirements(AdvancementRequirements.allOf(List.of("pickup_li")))
                .save(saver, ResourceLocation.fromNamespaceAndPath("tbl","story/smelt_lithium"), existingFileHelper);

        AdvancementHolder obtain_steel = Advancement.Builder.advancement()
                .parent(smelt_lithium)
                .display(
                        new ItemStack(ModItems.STEEL_INGOT.get()),
                        Component.translatable("advancement.tbl.story.obtain_steel.title"),
                        Component.translatable("advancement.tbl.story.obtain_steel.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("steel",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_INGOT.get())
                )
                .requirements(AdvancementRequirements.allOf(List.of("steel")))
                .save(saver, ResourceLocation.fromNamespaceAndPath("tbl","story/obtain_steel"), existingFileHelper);

        AdvancementHolder make_battery = Advancement.Builder.advancement()
                .parent(obtain_steel)
                .display(
                        new ItemStack(ModItems.LITHIUM_BATTERY.get()),
                        Component.translatable("advancement.tbl.story.make_battery.title"),
                        Component.translatable("advancement.tbl.story.make_battery.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("bat", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LITHIUM_BATTERY))
                .addCriterion("sbat", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LITHIUM_BATTERY_STACK))
                .addCriterion("batb", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LITHIUM_BATTERY_BOOSTED))
                .addCriterion("sbatb", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LITHIUM_BATTERY_STACK_BOOSTED))
                .save(saver, ResourceLocation.fromNamespaceAndPath("tbl","story/make_battery"), existingFileHelper);
    }
}