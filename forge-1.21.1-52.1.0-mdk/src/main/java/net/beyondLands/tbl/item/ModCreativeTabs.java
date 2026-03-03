package net.beyondLands.tbl.item;


import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TBL.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TBL_STUFF_TAB = CREATIVE_MODE_TABS.register("tbl_stuff_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LITHIUM.get()))
                    .title(Component.translatable("creativetab.tbl.tbl_stuff"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.LITHIUM.get());
                        output.accept(ModItems.RAW_LITHIUM.get());
                        output.accept(ModBlocks.LITHIUM_BLOCK.get());
                        output.accept(ModBlocks.RAW_LITHIUM_BLOCK.get());
                        output.accept(ModBlocks.LITHIUM_ORE.get());
                        output.accept(ModBlocks.LITHIUM_DEEPSLATE_ORE.get());
                        output.accept(ModItems.STEEL_INGOT.get());
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
