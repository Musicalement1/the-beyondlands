package net.beyondLands.tbl.item;

import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.entity.ModEntities;
import net.beyondLands.tbl.item.battery.BatteryItem;
import net.beyondLands.tbl.item.fuel.FuelItem;
import net.beyondLands.tbl.item.hammer.HammerItem;
import net.beyondLands.tbl.item.hydroreactive.LithiumReact;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TBL.MOD_ID);



    public static final RegistryObject<Item> LITHIUM = ITEMS.register("lithium",
            () -> new LithiumReact(new Item.Properties()));
    public static final RegistryObject<Item> RAW_LITHIUM = ITEMS.register("raw_lithium",
            () -> new LithiumReact(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LITHIUM_BATTERY = ITEMS.register("lithium_battery",
            () -> new BatteryItem(new Item.Properties()
                    .stacksTo(1)
                    .durability(1000)
            ));

    public static final RegistryObject<Item> PEPPER = ITEMS.register("pepper",
            () -> new Item(new Item.Properties().food(ModFoodProperties.PEPPER)));
    public static final RegistryObject<Item> ASH = ITEMS.register("ash",
            () -> new FuelItem(new Item.Properties(), 1200));

    public static final RegistryObject<Item> ASHLING_SPAWN_EGG = ITEMS.register("ashling_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ASHLING, 0x474747, 0x2dbccc, new Item.Properties()));

    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.STEEL, 5, -2.7f))));
    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.STEEL, 1, -2.8f))));
    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.STEEL, 1.5f, -3.0f))));
    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.STEEL, 8.5f, -3.5f))));
    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.STEEL, 0, -3.0f))));

    public static final RegistryObject<Item> STEEL_HAMMER = ITEMS.register("steel_hammer",
            () -> new HammerItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.STEEL, 8.5f, -3.9f))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
