package net.beyondlands.tbl;

import net.beyondlands.tbl.block.ModBlocks;
import net.beyondlands.tbl.entity.ModEntities;
import net.beyondlands.tbl.entity.client.AshZombieRenderer;
import net.beyondlands.tbl.entity.client.AshlingRenderer;
import net.beyondlands.tbl.entity.client.NukerRenderer;
import net.beyondlands.tbl.item.ModCreativeTabs;
import net.beyondlands.tbl.item.ModItems;
import net.beyondlands.tbl.block.entity.ModBlockEntities;

import com.mojang.logging.LogUtils;
import net.beyondlands.tbl.screen.ModMenuTypes;
import net.beyondlands.tbl.screen.custom.GateScreen;
import net.beyondlands.tbl.worldgen.ModFeatures;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import org.slf4j.Logger;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.util.Locale;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(TBL.MOD_ID)
public class TBL
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "tbl";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public TBL(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);



        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }


    public static ResourceLocation prefix(String name) {
        return ResourceLocation.fromNamespaceAndPath(TBL.MOD_ID, name.toLowerCase(Locale.ROOT));
    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.ASHLING.get(), AshlingRenderer::new);
            EntityRenderers.register(ModEntities.ASH_ZOMBIE.get(), AshZombieRenderer::new);
            EntityRenderers.register(ModEntities.NUKER.get(), NukerRenderer::new);
        }

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.GATE_MENU.get(), GateScreen::new);
        }
    }
}
