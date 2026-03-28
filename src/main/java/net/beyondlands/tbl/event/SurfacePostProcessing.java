/*package net.beyondlands.tbl.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ChunkEvent;

@EventBusSubscriber(modid = "tbl")
public class SurfacePostProcessing {

    private static final ResourceKey<Level> TARGET_DIM =
            ResourceKey.create(Registries.DIMENSION, ResourceLocation.tryParse("tbl:bl_dim"));

    private static final int MARKER_Y = 6;//not elegant but welp

    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent.Load event) {
        ChunkAccess chunk = event.getChunk();
        LevelAccessor world = chunk.getLevel();
        if (!(world instanceof Level level)) return;

        if (!level.dimension().equals(TARGET_DIM)) return;

        int minX = chunk.getPos().x << 4;
        int minZ = chunk.getPos().z << 4;

        BlockPos markerPos = new BlockPos(minX, MARKER_Y, minZ);
        if (chunk.getBlockState(markerPos).is(Blocks.BEDROCK)) return;//already generated surface
        chunk.setBlockState(markerPos, Blocks.BEDROCK.defaultBlockState(), false);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 33; y < level.getMaxBuildHeight(); y++) {
                    BlockPos pos = new BlockPos(minX + x, y, minZ + z);
                    var state = chunk.getBlockState(pos);

                    if (state.is(Blocks.STONE)) {
                        var above = chunk.getBlockState(pos.above());
                        if (above.isAir()) {
                            chunk.setBlockState(pos, Blocks.DIRT.defaultBlockState(), false);
                            chunk.setBlockState(pos.below(1), Blocks.GRASS_BLOCK.defaultBlockState(), false);
                            break;
                        }
                    }
                }
            }
        }
    }
}*/