package net.musicalement.tbl.event;

import net.musicalement.tbl.TBL;
import net.musicalement.tbl.entity.TBlEntities;
import net.musicalement.tbl.entity.client.AshZombieModel;
import net.musicalement.tbl.entity.client.AshlingModel;
import net.musicalement.tbl.entity.client.NukerModel;
import net.musicalement.tbl.entity.custom.AshZombie;
import net.musicalement.tbl.entity.custom.AshlingEntity;
import net.musicalement.tbl.entity.custom.NukerEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = TBL.MOD_ID)
public class TBlEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AshlingModel.LAYER_LOCATION, AshlingModel::createBodyLayer);
        event.registerLayerDefinition(AshZombieModel.LAYER_LOCATION, AshZombieModel::createBodyLayer);
        event.registerLayerDefinition(NukerModel.LAYER_LOCATION, NukerModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(TBlEntities.ASHLING.get(), AshlingEntity.createAttributes().build());
        event.put(TBlEntities.ASH_ZOMBIE.get(), AshZombie.createAttributes().build());
        event.put(TBlEntities.NUKER.get(), NukerEntity.createAttributes().build());
    }
}