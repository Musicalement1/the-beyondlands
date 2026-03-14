package net.beyondlands.tbl.screen;

import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.screen.custom.GateMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, TBL.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<GateMenu>> GATE_MENU =
            registerMenuType("gate_menu", GateMenu::new);


    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name,
                                                                                                              IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));

    }
    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }

}