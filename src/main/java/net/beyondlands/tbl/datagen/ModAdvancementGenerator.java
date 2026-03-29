package net.beyondlands.tbl.datagen;

import net.beyondlands.tbl.block.ModBlocks;
import net.beyondlands.tbl.entity.ModEntities;
import net.beyondlands.tbl.item.ModItems;
import net.beyondlands.tbl.worldgen.biome.ModBiomes;
import net.beyondlands.tbl.worldgen.dimension.ModDimensions;
import net.beyondlands.tbl.worldgen.structure.ModStructures;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.internal.NeoForgeAdvancementProvider;
import net.neoforged.neoforge.common.util.Lazy;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModAdvancementGenerator implements NeoForgeAdvancementProvider.AdvancementGenerator {


    private static final Supplier<EntityType<?>[]> BL_KILLABLE = Lazy.of(() -> new EntityType<?>[]{
            ModEntities.ASH_ZOMBIE.get(),
            ModEntities.NUKER.get()
    });
    private static final Supplier<List<ResourceKey<Biome>>> BL_BIOMES = Lazy.of(() -> List.of(
            ModBiomes.ASHLAND,
            ModBiomes.WASTELAND
    ));

    @Override
    public void generate(HolderLookup.Provider registries,
                         Consumer<AdvancementHolder> saver,
                         ExistingFileHelper existingFileHelper) {

        HolderLookup.RegistryLookup<Biome> biomes = registries.lookupOrThrow(Registries.BIOME);
        HolderLookup.RegistryLookup<Structure> structures = registries.lookupOrThrow(Registries.STRUCTURE);

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

        AdvancementHolder make_steel_armor = Advancement.Builder.advancement()
                .parent(obtain_steel)
                .display(
                        new ItemStack(ModItems.STEEL_CHESTPLATE.get()),
                        Component.translatable("advancement.tbl.story.make_steel_armor.title"),
                        Component.translatable("advancement.tbl.story.make_steel_armor.description"),
                        null,
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("h", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_HELMET))
                .addCriterion("c", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_CHESTPLATE))
                .addCriterion("l", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_LEGGINGS))
                .addCriterion("f", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_BOOTS))
                .save(saver, ResourceLocation.fromNamespaceAndPath("tbl","story/make_steel_armor"), existingFileHelper);


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

        AdvancementHolder find_lab = Advancement.Builder.advancement()
                .parent(make_battery)
                .display(
                        new ItemStack(ModBlocks.LAB_BLOCK.get()),
                        Component.translatable("advancement.tbl.story.find_lab.title"),
                        Component.translatable("advancement.tbl.story.find_lab.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion(
                        "lab",
                        PlayerTrigger.TriggerInstance.located(
                                LocationPredicate.Builder.inStructure(structures.getOrThrow(ModStructures.LAB_RUINS))
                        )
                )
                .save(saver, ResourceLocation.fromNamespaceAndPath("tbl","story/find_lab"), existingFileHelper);


        AdvancementHolder enter_bl = Advancement.Builder.advancement()
                .parent(find_lab)
                .display(
                        new ItemStack(ModBlocks.BL_PORTAL.get()),
                        Component.translatable("advancement.tbl.story.enter_bl.title"),
                        Component.translatable("advancement.tbl.story.enter_bl.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion(
                        "bl",
                        PlayerTrigger.TriggerInstance.located(
                                LocationPredicate.Builder.inDimension(ModDimensions.BLDIM_LEVEL_KEY)
                        )
                )
                .save(saver, ResourceLocation.fromNamespaceAndPath("tbl","story/enter_bl"), existingFileHelper);

        AdvancementHolder kill_bl_monster = this.addBLKillable(registries, Advancement.Builder.advancement()
                .parent(enter_bl)
                .display(
                        new ItemStack(ModItems.STEEL_SWORD.get()),
                        Component.translatable("advancement.tbl.story.kill_bl_monster.title"),
                        Component.translatable("advancement.tbl.story.kill_bl_monster.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
        )
                .requirements(AdvancementRequirements.Strategy.OR)
                .save(saver, ResourceLocation.fromNamespaceAndPath("tbl","story/kill_bl_monster"), existingFileHelper);


        AdvancementHolder bl_hunter = this.addBLKillable(registries, Advancement.Builder.advancement()
                        .parent(kill_bl_monster)
                        .display(
                                new ItemStack(ModItems.STEEL_AXE.get()),
                                Component.translatable("advancement.tbl.story.bl_hunter.title"),
                                Component.translatable("advancement.tbl.story.bl_hunter.description"),
                                null,
                                AdvancementType.CHALLENGE,
                                true,
                                true,
                                false
                        )
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .save(saver, ResourceLocation.fromNamespaceAndPath("tbl","story/bl_hunter"), existingFileHelper);

        AdvancementHolder bl_explorer = this.addBLBiome(registries, Advancement.Builder.advancement()
                        .parent(enter_bl)
                        .display(
                                new ItemStack(ModItems.STEEL_BOOTS.get()),
                                Component.translatable("advancement.tbl.story.bl_explorer.title"),
                                Component.translatable("advancement.tbl.story.bl_explorer.description"),
                                null,
                                AdvancementType.CHALLENGE,
                                true,
                                true,
                                false
                        )
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .save(saver, ResourceLocation.fromNamespaceAndPath("tbl","story/bl_explorer"), existingFileHelper);


    }

    //next time kill a certain number of mobs?
    private Advancement.Builder addBLKillable(HolderLookup.Provider registries, Advancement.Builder builder) {
        for (EntityType<?> entity : BL_KILLABLE.get()) {
            builder.addCriterion(EntityType.getKey(entity).getPath(),
                    KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity()
                            .of(entity)
                    ));
        }
        return builder;
    }

    private Advancement.Builder addBLBiome(HolderLookup.Provider registries, Advancement.Builder builder) {
        HolderLookup<Biome> biomeLookup = registries.lookupOrThrow(Registries.BIOME);
        for (ResourceKey<Biome> biomeKey : BL_BIOMES.get()) {
            Holder<Biome> holder = biomeLookup.getOrThrow(biomeKey);
            HolderSet<Biome> set = HolderSet.direct(holder);
            builder.addCriterion("in_" + biomeKey.location().getPath(),
                    PlayerTrigger.TriggerInstance.located(
                            LocationPredicate.Builder.location()
                                    .setBiomes(set)
                    )
            );
        }
        //builder.requirements(AdvancementRequirements.Strategy.AND);//put this whenn we save the adv. its better
        return builder;
    }

}