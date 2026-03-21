package net.beyondlands.tbl.item;

import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.block.ModBlocks;
import net.beyondlands.tbl.entity.ModEntities;
import net.beyondlands.tbl.item.battery.BatteryItem;
import net.beyondlands.tbl.item.battery.HighVoltageBatteryItem;
import net.beyondlands.tbl.item.fuel.FuelItem;
import net.beyondlands.tbl.item.hammer.HammerItem;
import net.beyondlands.tbl.item.hydroreactive.LithiumReact;
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




public class ModItems {
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
            () -> new Item(new Item.Properties().food(ModFoodProperties.PEPPER)));
    public static final DeferredItem<Item> ASH = ITEMS.register("ash",
            () -> new FuelItem(new Item.Properties(), 1200));

    public static final DeferredItem<Item> ASHLING_SPAWN_EGG = ITEMS.register("ashling_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.ASHLING, 0x474747, 0x2dbccc, new Item.Properties()));
    public static final DeferredItem<Item> ASH_ZOMBIE_SPAWN_EGG = ITEMS.register("ash_zombie_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.ASH_ZOMBIE, 0x38D1CB, 0xCE38D1, new Item.Properties()));
    public static final DeferredItem<Item> NUKER_SPAWN_EGG = ITEMS.register("nuker_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.NUKER, 0xF72800, 0x000000, new Item.Properties()));

    public static final DeferredItem<Item> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.STEEL, 3.5f, -2.7f))));
    public static final DeferredItem<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.STEEL, 1, -2.8f))));
    public static final DeferredItem<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.STEEL, 1.5f, -3.0f))));
    public static final DeferredItem<Item> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.STEEL, 6.5f, -3.2f))));
    public static final DeferredItem<Item> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.STEEL, 0, -3.0f))));
    public static final DeferredItem<Item> STEEL_KNIFE = ITEMS.register("steel_knife",
            () -> new SwordItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.STEEL, 1.5f, -0.2f))));

    public static final DeferredItem<Item> STEEL_HAMMER = ITEMS.register("steel_hammer",
            () -> new HammerItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.STEEL, 7.0f, -3.5f))));


    public static final DeferredItem<ArmorItem> STEEL_HELMET = ITEMS.register("steel_helmet",
            () -> new ArmorItem(ModArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(25))));
    public static final DeferredItem<ArmorItem> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new ArmorItem(ModArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(25))));
    public static final DeferredItem<ArmorItem> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
            () -> new ArmorItem(ModArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(25))));
    public static final DeferredItem<ArmorItem> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new ArmorItem(ModArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(25))));



    public static final DeferredItem<Item> STEEL_HORSE_ARMOR = ITEMS.register("steel_horse_armor",
            () -> new AnimalArmorItem(ModArmorMaterials.STEEL_ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN,
                    false, new Item.Properties().stacksTo(1)));


    public static final DeferredItem<Item> PEPPER_SEEDS = ITEMS.register("pepper_seeds",
            () -> new ItemNameBlockItem(ModBlocks.PEPPER_CROP.get(), new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
