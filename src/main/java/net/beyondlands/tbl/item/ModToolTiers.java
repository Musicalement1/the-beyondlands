package net.beyondlands.tbl.item;

import net.beyondlands.tbl.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier STEEL = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL,

            1400, 6, 2f, 5,
            () -> Ingredient.of(ModItems.STEEL_INGOT.get())
    );
}