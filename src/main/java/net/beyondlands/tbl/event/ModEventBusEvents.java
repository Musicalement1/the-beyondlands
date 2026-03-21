package net.beyondlands.tbl.event;

import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.entity.ModEntities;
import net.beyondlands.tbl.entity.client.AshZombieModel;
import net.beyondlands.tbl.entity.client.AshlingModel;
import net.beyondlands.tbl.entity.client.NukerModel;
import net.beyondlands.tbl.entity.custom.AshZombie;
import net.beyondlands.tbl.entity.custom.AshlingEntity;
import net.beyondlands.tbl.entity.custom.NukerEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = TBL.MOD_ID)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AshlingModel.LAYER_LOCATION, AshlingModel::createBodyLayer);
        event.registerLayerDefinition(AshZombieModel.LAYER_LOCATION, AshZombieModel::createBodyLayer);
        event.registerLayerDefinition(NukerModel.LAYER_LOCATION, NukerModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ASHLING.get(), AshlingEntity.createAttributes().build());
        event.put(ModEntities.ASH_ZOMBIE.get(), AshZombie.createAttributes().build());
        event.put(ModEntities.NUKER.get(), NukerEntity.createAttributes().build());
    }
}