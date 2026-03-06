package net.beyondLands.tbl.block;

import net.beyondLands.tbl.worldgen.dimension.ModDimensions;
import net.beyondLands.tbl.worldgen.portal.ModTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ModPortalBlock extends Block implements Portal {
    public ModPortalBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity.canUsePortal(false)) {
            pEntity.setAsInsidePortal(this, pPos);
        }
    }


    @Nullable
    @Override
    public DimensionTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {
        ResourceKey<Level> newDimension = level.dimension() == Level.OVERWORLD ? ModDimensions.BLDIM_LEVEL_KEY : Level.OVERWORLD;
        ServerLevel serverlevel = level.getServer().getLevel(newDimension);
        if (serverlevel == null) {
            return null;
        } else {
            WorldBorder worldborder = serverlevel.getWorldBorder();
            double d0 = DimensionType.getTeleportationScale(level.dimensionType(), serverlevel.dimensionType());
            BlockPos horizontalPos = worldborder.clampToBounds(pos.getX() * d0, pos.getY(), pos.getZ() * d0);

            ChunkPos chunkPos = new ChunkPos(horizontalPos.getX() >> 4, horizontalPos.getZ() >> 4);
            ChunkAccess chunk = serverlevel.getChunkSource().getChunk(
                    chunkPos.x,
                    chunkPos.z,
                    ChunkStatus.FULL,
                    true  //force gen
            );

            int surfaceY = chunk.getHeight(Heightmap.Types.WORLD_SURFACE,
                    horizontalPos.getX() & 15,
                    horizontalPos.getZ() & 15);
            BlockPos newPos = horizontalPos.atY(Math.max(surfaceY, serverlevel.getMinBuildHeight()));

            return ModTeleporter.createTransition(entity, serverlevel, newPos, false);
        }
    }

}