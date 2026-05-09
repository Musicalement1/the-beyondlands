package net.musicalement.tbl.datagen;


import net.musicalement.tbl.TBL;
import net.musicalement.tbl.item.TBlItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;

import net.musicalement.tbl.block.TBlBlocks;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.LinkedHashMap;

public class TBlItemModelProvider extends ItemModelProvider {

    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public TBlItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TBL.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(TBlItems.LITHIUM.get());
        basicItem(TBlItems.RAW_LITHIUM.get());

        basicItem(TBlItems.STEEL_INGOT.get());
        basicItem(TBlItems.LITHIUM_BATTERY.get());
        basicItem(TBlItems.LITHIUM_BATTERY_BOOSTED.get());
        basicItem(TBlItems.PEPPER.get());
        basicItem(TBlItems.ASH.get());
        basicItem(TBlItems.LITHIUM_BATTERY_STACK.get());
        basicItem(TBlItems.LITHIUM_BATTERY_STACK_BOOSTED.get());
        basicItem(TBlItems.ENCHANTITE_SHARD.get());

        withExistingParent(TBlItems.ASHLING_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(TBlItems.ASH_ZOMBIE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(TBlItems.NUKER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        handheldItem(TBlItems.STEEL_SWORD);
        handheldItem(TBlItems.STEEL_PICKAXE);
        handheldItem(TBlItems.STEEL_SHOVEL);
        handheldItem(TBlItems.STEEL_AXE);
        handheldItem(TBlItems.STEEL_HOE);
        handheldItem(TBlItems.STEEL_HAMMER);
        handheldItem(TBlItems.STEEL_KNIFE);

        trimmedArmorItem(TBlItems.STEEL_HELMET);
        trimmedArmorItem(TBlItems.STEEL_CHESTPLATE);
        trimmedArmorItem(TBlItems.STEEL_LEGGINGS);
        trimmedArmorItem(TBlItems.STEEL_BOOTS);

        basicItem(TBlItems.STEEL_HORSE_ARMOR.get());

        saplingItem(TBlBlocks.ASH_SAPLING);
        saplingItem(TBlBlocks.GREEN_MUSHROOM);

        basicItem(TBlItems.PEPPER_SEEDS.get());
    }

    private ItemModelBuilder saplingItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(TBL.MOD_ID,"block/" + item.getId().getPath()));
    }
    // Shoutout to El_Redstoniano for making this
    private void trimmedArmorItem(DeferredItem<ArmorItem> itemRegistryObject) {
        final String MOD_ID = TBL.MOD_ID; // Change this to your mod id

        if (itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace() + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }
    private ItemModelBuilder handheldItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(TBL.MOD_ID,"item/" + item.getId().getPath()));
    }
}