package net.beyondLands.tbl.screen;

import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.screen.custom.GateMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, TBL.MOD_ID);

    public static final RegistryObject<MenuType<GateMenu>> GATE_MENU =
            MENUS.register("gate_menu", () -> IForgeMenuType.create(GateMenu::new));


    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}