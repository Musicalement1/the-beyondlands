package net.musicalement.tbl.item;

import net.musicalement.tbl.util.TBlTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class TBlToolTiers {
    public static final Tier STEEL = new SimpleTier(
            TBlTags.Blocks.INCORRECT_FOR_STEEL_TOOL,

            1400, 6, 2f, 5,
            () -> Ingredient.of(TBlItems.STEEL_INGOT.get())
    );
}