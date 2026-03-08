package net.beyondLands.tbl.worldgen.portal;

import net.beyondLands.tbl.block.ModBlocks;
import net.beyondLands.tbl.block.ModPortalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;

import java.util.function.Function;

public class ModTeleporter {

    public static DimensionTransition createTransition(Entity entity, ServerLevel dest, BlockPos pos, boolean forcedEntry) {

        dest.setBlock(pos.below(), ModBlocks.GATE_OPENER.get().defaultBlockState(), 3);
        dest.setBlock(pos, ModBlocks.BL_PORTAL.get().defaultBlockState(), 3);
        dest.setBlock(pos.above(), Blocks.AIR.defaultBlockState(), 3);
        dest.setBlock(pos.above(2), Blocks.AIR.defaultBlockState(), 3);
        return new DimensionTransition(
                dest,
                Vec3.atCenterOf(pos.above()),
                Vec3.ZERO,
                entity.getYRot(),
                entity.getXRot(),

                (e) -> {
                    dest.playSound(
                            null,
                            pos,
                            SoundEvents.ENDERMAN_TELEPORT,
                            SoundSource.PLAYERS,
                            1.0F,
                            1.0F
                    );
                }

        );
    }
}
