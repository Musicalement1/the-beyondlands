package net.beyondLands.tbl.datagen;

import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.block.ModBlocks;
import net.beyondLands.tbl.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.List;
import java.util.function.Consumer;

public class ModAdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator {{

}

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {

        AdvancementHolder root = Advancement.Builder.advancement()
            .display(
                new ItemStack(ModItems.LITHIUM.get()),
                Component.translatable("advancement.tbl.story.root.title"),
                Component.translatable("advancement.tbl.story.root.description"),
                ResourceLocation.fromNamespaceAndPath("tbl","textures/block/lithium_ore.png"),
                AdvancementType.TASK,
                true,
                false,
                false
             )
            .addCriterion("pickup_crafting", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRAFTING_TABLE))
            .requirements(AdvancementRequirements.allOf(List.of("pickup_crafting")))
            .save(saver, ResourceLocation.fromNamespaceAndPath("tbl", "story/root"));


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
                .addCriterion("pickup_raw_li", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_LITHIUM.get()))
                .requirements(AdvancementRequirements.allOf(List.of("pickup_raw_li")))
                .save(saver, ResourceLocation.fromNamespaceAndPath("tbl", "story/obtain_raw_lithium"));

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
                .addCriterion("pickup_li", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LITHIUM.get()))
                .requirements(AdvancementRequirements.allOf(List.of("pickup_li")))
                .save(saver, ResourceLocation.fromNamespaceAndPath("tbl", "story/smelt_lithium"));
    }

}
