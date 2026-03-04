package net.beyondLands.tbl.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties PEPPER = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(1.0f)
            .build();
}
