package net.beyondLands.tbl.item;

import net.beyondLands.tbl.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers {
    public static final Tier STEEL = new ForgeTier(1400, 6, 2f, 5,
            ModTags.Blocks.NEEDS_STEEL_TOOL, () -> Ingredient.of(ModItems.STEEL_INGOT.get()),
            ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL);
}