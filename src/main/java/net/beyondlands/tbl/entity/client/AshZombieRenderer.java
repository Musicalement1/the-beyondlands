package net.beyondlands.tbl.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.entity.custom.AshZombie;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AshZombieRenderer extends MobRenderer<AshZombie, AshZombieModel<AshZombie>> {
    public AshZombieRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AshZombieModel<>(pContext.bakeLayer(AshZombieModel.LAYER_LOCATION)), 0.85f);
    }

    @Override
    public ResourceLocation getTextureLocation(AshZombie pEntity) {
        return ResourceLocation.fromNamespaceAndPath(TBL.MOD_ID, "textures/entity/ash_zombie.png");
    }

    @Override
    public void render(AshZombie pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            pPoseStack.scale(1.3f, 1.3f, 1.3f);//1, 1 ,1
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
