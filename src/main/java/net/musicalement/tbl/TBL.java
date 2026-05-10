package net.musicalement.tbl;

import net.musicalement.tbl.block.TBlBlocks;
import net.musicalement.tbl.entity.TBlEntities;
import net.musicalement.tbl.entity.client.AshZombieRenderer;
import net.musicalement.tbl.entity.client.AshlingRenderer;
import net.musicalement.tbl.entity.client.NukeExplosionEntityRenderer;
import net.musicalement.tbl.entity.client.NukerRenderer;
import net.musicalement.tbl.item.TBlCreativeTabs;
import net.musicalement.tbl.item.TBlItems;
import net.musicalement.tbl.block.entity.TBlBlockEntities;

import com.mojang.logging.LogUtils;
import net.musicalement.tbl.screen.TBlMenuTypes;
import net.musicalement.tbl.screen.custom.BoostingTableScreen;
import net.musicalement.tbl.screen.custom.GateScreen;
import net.musicalement.tbl.util.TBlItemProperties;
import net.musicalement.tbl.worldgen.TBlFeatures;
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



        TBlItems.register(modEventBus);
        TBlBlocks.register(modEventBus);
        TBlCreativeTabs.register(modEventBus);
        TBlBlockEntities.register(modEventBus);
        TBlEntities.register(modEventBus);
        TBlMenuTypes.register(modEventBus);
        TBlFeatures.FEATURES.register(modEventBus);
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
            EntityRenderers.register(TBlEntities.ASHLING.get(), AshlingRenderer::new);
            EntityRenderers.register(TBlEntities.ASH_ZOMBIE.get(), AshZombieRenderer::new);
            EntityRenderers.register(TBlEntities.NUKER.get(), NukerRenderer::new);
            EntityRenderers.register(TBlEntities.NUKE_EXPLOSION.get(), NukeExplosionEntityRenderer::new);
            TBlItemProperties.addCustomItemProperties();
        }

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(TBlMenuTypes.GATE_MENU.get(), GateScreen::new);
            event.register(TBlMenuTypes.BOOSTING_TABLE_MENU.get(), BoostingTableScreen::new);
        }
    }
}
