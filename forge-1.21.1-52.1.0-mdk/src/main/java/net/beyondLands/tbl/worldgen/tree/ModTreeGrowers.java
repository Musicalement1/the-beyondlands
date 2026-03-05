package net.beyondLands.tbl.worldgen.tree;


import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower ASH = new TreeGrower(TBL.MOD_ID + ":ash",
            Optional.empty(), Optional.of(ModConfiguredFeatures.ASH_KEY), Optional.empty());
}