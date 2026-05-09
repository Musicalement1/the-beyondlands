package net.musicalement.tbl.item;

import net.musicalement.tbl.TBL;
import net.musicalement.tbl.block.TBlBlocks;
import net.musicalement.tbl.entity.TBlEntities;
import net.musicalement.tbl.item.battery.BatteryItem;
import net.musicalement.tbl.item.battery.HighVoltageBatteryItem;
import net.musicalement.tbl.item.bow.Prototype_002;
import net.musicalement.tbl.item.fuel.FuelItem;
import net.musicalement.tbl.item.hammer.HammerItem;
import net.musicalement.tbl.item.hydroreactive.LithiumReact;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;




public class TBlItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(TBL.MOD_ID);



    public static final DeferredItem<Item> LITHIUM = ITEMS.register("lithium",
            () -> new LithiumReact(new Item.Properties()));
    public static final DeferredItem<Item> RAW_LITHIUM = ITEMS.register("raw_lithium",
            () -> new LithiumReact(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LITHIUM_BATTERY = ITEMS.register("lithium_battery",
            () -> new BatteryItem(new Item.Properties()
                    .stacksTo(1)
                    .durability(1000)
                    .attributes(
                            ItemAttributeModifiers.builder()
                                    .add(
                                            Attributes.MOVEMENT_SPEED,
                                            new AttributeModifier(
                                                    ResourceLocation.fromNamespaceAndPath("tbl", "battery_speed_bonus"),
                                                    0.5,//+50%
                                                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                                            ),
                                            EquipmentSlotGroup.HAND
                                    )
                                    .build()
                    )
            ));

    public static final DeferredItem<Item> LITHIUM_BATTERY_STACK = ITEMS.register("lithium_battery_stack",
            () -> new BatteryItem(new Item.Properties()
                    .stacksTo(1)
                    .durability(10000)
                    .attributes(
                            ItemAttributeModifiers.builder()
                                    .add(
                                            Attributes.MOVEMENT_SPEED,
                                            new AttributeModifier(
                                                    ResourceLocation.fromNamespaceAndPath("tbl", "battery_speed_bonus"),
                                                    0.5,//+50%
                                                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                                            ),
                                            EquipmentSlotGroup.HAND
                                    )
                                    .build()
                    )
            ));

    public static final DeferredItem<Item> LITHIUM_BATTERY_BOOSTED = ITEMS.register("lithium_battery_boosted",
            () -> new HighVoltageBatteryItem(new Item.Properties()
                    .stacksTo(1)
                    .durability(1000)
                    .attributes(
                            ItemAttributeModifiers.builder()
                                    .add(
                                            Attributes.MOVEMENT_SPEED,
                                            new AttributeModifier(
                                                    ResourceLocation.fromNamespaceAndPath("tbl", "battery_speed_bonus"),
                                                    1.5,//+150%
                                                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                                            ),
                                            EquipmentSlotGroup.HAND
                                    )
                                    .build()
                    )
            ));

    public static final DeferredItem<Item> LITHIUM_BATTERY_STACK_BOOSTED = ITEMS.register("lithium_battery_stack_boosted",
            () -> new HighVoltageBatteryItem(new Item.Properties()
                    .stacksTo(1)
                    .durability(10000)
                    .attributes(
                            ItemAttributeModifiers.builder()
                                    .add(
                                            Attributes.MOVEMENT_SPEED,
                                            new AttributeModifier(
                                                    ResourceLocation.fromNamespaceAndPath("tbl", "battery_speed_bonus"),
                                                    1.5,//+150%
                                                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                                            ),
                                            EquipmentSlotGroup.HAND
                                    )
                                    .build()
                    )
            ));

    public static final DeferredItem<Item> PEPPER = ITEMS.register("pepper",
            () -> new Item(new Item.Properties().food(TBlFoodProperties.PEPPER)));
    public static final DeferredItem<Item> ASH = ITEMS.register("ash",
            () -> new FuelItem(new Item.Properties(), 1200));

    public static final DeferredItem<Item> ASHLING_SPAWN_EGG = ITEMS.register("ashling_spawn_egg",
            () -> new DeferredSpawnEggItem(TBlEntities.ASHLING, 0x474747, 0x2dbccc, new Item.Properties()));
    public static final DeferredItem<Item> ASH_ZOMBIE_SPAWN_EGG = ITEMS.register("ash_zombie_spawn_egg",
            () -> new DeferredSpawnEggItem(TBlEntities.ASH_ZOMBIE, 0x38D1CB, 0xCE38D1, new Item.Properties()));
    public static final DeferredItem<Item> NUKER_SPAWN_EGG = ITEMS.register("nuker_spawn_egg",
            () -> new DeferredSpawnEggItem(TBlEntities.NUKER, 0xF72800, 0x000000, new Item.Properties()));

    public static final DeferredItem<Item> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(TBlToolTiers.STEEL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(TBlToolTiers.STEEL, 3.5f, -2.7f))));
    public static final DeferredItem<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(TBlToolTiers.STEEL, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(TBlToolTiers.STEEL, 1, -2.8f))));
    public static final DeferredItem<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(TBlToolTiers.STEEL, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(TBlToolTiers.STEEL, 1.5f, -3.0f))));
    public static final DeferredItem<Item> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(TBlToolTiers.STEEL, new Item.Properties()
                    .attributes(AxeItem.createAttributes(TBlToolTiers.STEEL, 6.5f, -3.2f))));
    public static final DeferredItem<Item> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(TBlToolTiers.STEEL, new Item.Properties()
                    .attributes(HoeItem.createAttributes(TBlToolTiers.STEEL, 0, -3.0f))));
    public static final DeferredItem<Item> STEEL_KNIFE = ITEMS.register("steel_knife",
            () -> new SwordItem(TBlToolTiers.STEEL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(TBlToolTiers.STEEL, 1.5f, 1.0f))));

    public static final DeferredItem<Item> STEEL_HAMMER = ITEMS.register("steel_hammer",
            () -> new HammerItem(TBlToolTiers.STEEL, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(TBlToolTiers.STEEL, 7.0f, -3.5f))));


    public static final DeferredItem<ArmorItem> STEEL_HELMET = ITEMS.register("steel_helmet",
            () -> new ArmorItem(TBlArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(25))));
    public static final DeferredItem<ArmorItem> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new ArmorItem(TBlArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(25))));
    public static final DeferredItem<ArmorItem> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
            () -> new ArmorItem(TBlArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(25))));
    public static final DeferredItem<ArmorItem> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new ArmorItem(TBlArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(25))));



    public static final DeferredItem<Item> STEEL_HORSE_ARMOR = ITEMS.register("steel_horse_armor",
            () -> new AnimalArmorItem(TBlArmorMaterials.STEEL_ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN,
                    false, new Item.Properties().stacksTo(1)));


    public static final DeferredItem<Item> PEPPER_SEEDS = ITEMS.register("pepper_seeds",
            () -> new ItemNameBlockItem(TBlBlocks.PEPPER_CROP.get(), new Item.Properties()));


    public static final DeferredItem<Item> PROTOTYPE_002 = ITEMS.register("prototype_002",
            () -> new Prototype_002(new Item.Properties().durability(500)));

    public static final DeferredItem<Item> ENCHANTITE_SHARD = ITEMS.register("enchantite_shard",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true))
            );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
