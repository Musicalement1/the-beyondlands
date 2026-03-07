package net.beyondLands.tbl.event;

import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.entity.ModEntities;
import net.beyondLands.tbl.entity.client.AshZombieModel;
import net.beyondLands.tbl.entity.client.AshlingModel;
import net.beyondLands.tbl.entity.custom.AshZombie;
import net.beyondLands.tbl.entity.custom.AshlingEntity;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TBL.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AshlingModel.LAYER_LOCATION, AshlingModel::createBodyLayer);
        event.registerLayerDefinition(AshZombieModel.LAYER_LOCATION, AshZombieModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ASHLING.get(), AshlingEntity.createAttributes().build());
        event.put(ModEntities.ASH_ZOMBIE.get(), AshZombie.createAttributes().build());
    }
}