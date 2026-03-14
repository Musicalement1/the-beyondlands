package net.beyondlands.tbl.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.entity.custom.AshlingEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AshlingRenderer extends MobRenderer<AshlingEntity, AshlingModel<AshlingEntity>> {
    public AshlingRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AshlingModel<>(pContext.bakeLayer(AshlingModel.LAYER_LOCATION)), 0.85f);
    }

    @Override
    public ResourceLocation getTextureLocation(AshlingEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(TBL.MOD_ID, "textures/entity/ashling/ashling_gray.png");
    }

    @Override
    public void render(AshlingEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            pPoseStack.scale(1.5f, 1.5f, 1.5f);//1, 1 ,1
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
