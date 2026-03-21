package net.beyondlands.tbl.block;

import net.beyondlands.tbl.worldgen.dimension.ModDimensions;
import net.beyondlands.tbl.worldgen.portal.ModTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.DimensionTransition;
import org.jetbrains.annotations.Nullable;

public class ModPortalBlock extends Block implements Portal {
    public ModPortalBlock(Properties pProperties) {


        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ALWAYS_STAY, false));
    }
    public static final BooleanProperty ALWAYS_STAY = BooleanProperty.create("always_stay");

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ALWAYS_STAY);
    }


    @Override
    protected void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity.canUsePortal(false)) {
            pEntity.setAsInsidePortal(this, pPos);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {


        //else place another portal

        BlockPos above = pos.above();

        if (level.getBlockState(above).isAir() && !(above.getY() >= level.getMaxBuildHeight())) {

            level.setBlock(above, ModBlocks.BL_PORTAL.get().defaultBlockState(), 3);

            level.scheduleTick(above, this, 2);

            if (level instanceof ServerLevel serverLevel) {
                spawnPortalWave(serverLevel, above);
            }
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {

        if (level.isClientSide) return;


        BlockPos below = pos.below();
        BlockState belowState = level.getBlockState(below);

        if (!belowState.is(ModBlocks.BL_PORTAL.get()) && !belowState.is(ModBlocks.GATE_OPENER.get()) && !state.getValue(ALWAYS_STAY)) {
            level.removeBlock(pos, false);
            if (level instanceof ServerLevel serverLevel) {
                spawnPortalWave(serverLevel, pos);
            }
        }

        if (fromPos.equals(pos.above())  && !state.getValue(ALWAYS_STAY)) {

            BlockState aboveState = level.getBlockState(fromPos);

            if (!aboveState.is(ModBlocks.BL_PORTAL.get())) {
                level.removeBlock(pos, false);
                if (level instanceof ServerLevel serverLevel) {
                    spawnPortalWave(serverLevel, pos);
                }
            }
        }
        if (!level.isClientSide) {//re-force ticking if not ticking already lol
            level.scheduleTick(pos, this, 2);
        }
    }


    public static void spawnPortalWave(ServerLevel level, BlockPos center) {

        double cx = center.getX() + 0.5;
        double cy = center.getY() + 0.5;
        double cz = center.getZ() + 0.5;

        int points = 80;
        double speed = 0.3;

        for (int i = 0; i < points; i++) {

            double angle = 2 * Math.PI * i / points;

            double x = Math.cos(angle);
            double z = Math.sin(angle);

            level.sendParticles(
                    ParticleTypes.END_ROD,
                    cx, cy, cz,
                    0,
                    x * speed,
                    0,
                    z * speed,
                    1
            );
        }
    }

    /* Not ignoring BL_PORTALS
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
    }*/

    @Nullable
    @Override
    public DimensionTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {

        ResourceKey<Level> newDimension = level.dimension() == Level.OVERWORLD
                ? ModDimensions.BLDIM_LEVEL_KEY
                : Level.OVERWORLD;

        ServerLevel serverlevel = level.getServer().getLevel(newDimension);
        if (serverlevel == null) {
            return null;
        }

        WorldBorder worldborder = serverlevel.getWorldBorder();
        double scale = DimensionType.getTeleportationScale(level.dimensionType(), serverlevel.dimensionType());

        BlockPos horizontalPos = worldborder.clampToBounds(
                pos.getX() * scale,
                pos.getY(),
                pos.getZ() * scale
        );

        ChunkPos chunkPos = new ChunkPos(horizontalPos.getX() >> 4, horizontalPos.getZ() >> 4);
        ChunkAccess chunk = serverlevel.getChunkSource().getChunk(
                chunkPos.x,
                chunkPos.z,
                ChunkStatus.FULL,
                true
        );

        int surfaceY = chunk.getHeight(
                Heightmap.Types.WORLD_SURFACE,
                horizontalPos.getX() & 15,
                horizontalPos.getZ() & 15
        );

        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(
                horizontalPos.getX(),
                Math.max(surfaceY, serverlevel.getMinBuildHeight()),
                horizontalPos.getZ()
        );

        //int maxDrop = 318;
        while (mutablePos.getY() > serverlevel.getMinBuildHeight()/* && maxDrop-- > 0*/) {

            BlockState state = serverlevel.getBlockState(mutablePos);

            if (!state.isAir() && !state.is(ModBlocks.BL_PORTAL.get())) {
                break;
            }

            mutablePos.move(0, -1, 0);
        }

        BlockPos newPos = mutablePos.immutable();

        return ModTeleporter.createTransition(entity, serverlevel, newPos, false);
    }

}