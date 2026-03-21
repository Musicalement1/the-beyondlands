package net.beyondlands.tbl.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.beyondlands.tbl.entity.custom.NukerEntity;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Creeper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NukerRenderer extends MobRenderer<NukerEntity, NukerModel<NukerEntity>> {
    float scaleFactor = 1.3f;
    private static final ResourceLocation CREEPER_LOCATION = ResourceLocation.fromNamespaceAndPath("tbl","textures/entity/nuker.png");

    public NukerRenderer(EntityRendererProvider.Context p_173958_) {
        super(p_173958_, new NukerModel<>(p_173958_.bakeLayer(NukerModel.LAYER_LOCATION)), 0.5F);
    }

    protected void scale(NukerEntity livingEntity, PoseStack poseStack, float partialTickTime) {
        float f = livingEntity.getSwelling(partialTickTime);
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        poseStack.scale(f2 * scaleFactor, f3 * scaleFactor, f2 * scaleFactor);
    }

    protected float getWhiteOverlayProgress(NukerEntity livingEntity, float partialTicks) {
        float f = livingEntity.getSwelling(partialTicks);
        return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
    }

    public ResourceLocation getTextureLocation(NukerEntity entity) {
        return CREEPER_LOCATION;
    }
}

