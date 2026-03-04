package net.beyondLands.tbl.datagen;


import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TBL.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.LITHIUM.get());
        basicItem(ModItems.RAW_LITHIUM.get());

        basicItem(ModItems.STEEL_INGOT.get());
        basicItem(ModItems.LITHIUM_BATTERY.get());
        basicItem(ModItems.PEPPER.get());
        basicItem(ModItems.ASH.get());

        withExistingParent(ModItems.ASHLING_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }
}