package net.musicalement.tbl.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.musicalement.tbl.TBL;
import net.musicalement.tbl.entity.custom.NukeExplosionEntity;

public class NukeExplosionEntityRenderer extends EntityRenderer<NukeExplosionEntity> {

    public NukeExplosionEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(NukeExplosionEntity entity,
                       float entityYaw,
                       float partialTick,
                       PoseStack poseStack,
                       MultiBufferSource buffer,
                       int packedLight) {

    }

    @Override
    public ResourceLocation getTextureLocation(NukeExplosionEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(
                TBL.MOD_ID,
                "textures/entity/empty.png"
        );
    }
}