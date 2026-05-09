package net.musicalement.tbl.worldgen.tree;


import net.musicalement.tbl.TBL;
import net.musicalement.tbl.worldgen.TBlConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class TBlTreeGrowers {
    public static final TreeGrower ASH = new TreeGrower(TBL.MOD_ID + ":ash",
            Optional.empty(), Optional.of(TBlConfiguredFeatures.ASH_KEY), Optional.empty());
}