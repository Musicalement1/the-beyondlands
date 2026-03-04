package net.beyondLands.tbl.item;

import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.item.battery.BatteryItem;
import net.beyondLands.tbl.item.fuel.FuelItem;
import net.beyondLands.tbl.item.hydroreactive.LithiumReact;
import net.minecraft.world.item.Item;
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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
