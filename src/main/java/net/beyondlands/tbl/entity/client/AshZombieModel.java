package net.beyondlands.tbl.entity.client;

import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.entity.custom.AshZombie;
import net.minecraft.client.model.AbstractZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class AshZombieModel<T extends AshZombie> extends AbstractZombieModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TBL.MOD_ID, "ash_zombie"), "main");

    public AshZombieModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = AbstractZombieModel.createMesh(CubeDeformation.NONE, 0.0F);
        //PartDefinition partdefinition = mesh.getRoot();

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public boolean isAggressive(T entity) {
        return entity.isAggressive();
    }
}