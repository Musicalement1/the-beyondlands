/*package net.beyondlands.tbl.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ChunkEvent;

@EventBusSubscriber(modid = "tbl")
public class SurfacePostProcessing {

    private static final ResourceKey<Level> TARGET_DIM =
            ResourceKey.create(Registries.DIMENSION, ResourceLocation.tryParse("tbl:bl_dim"));

    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent.Load event) {

        if (!event.isNewChunk()) return;

        if (!(event.getChunk() instanceof LevelChunk chunk)) return;

        Level level = chunk.getLevel();

        if (level.isClientSide) return;

        if (!level.dimension().equals(TARGET_DIM)) return;

        int minX = chunk.getPos().x << 4;
        int minZ = chunk.getPos().z << 4;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {

                int worldX = minX + x;
                int worldZ = minZ + z;

                for (int y = 100; y >= 50; y--) {

                    BlockPos pos = new BlockPos(worldX, y, worldZ);
                    BlockState state = level.getBlockState(pos);

                    if (state.isAir()) continue;

                    BlockState above = level.getBlockState(pos.above());

                    if (state.is(Blocks.DIRT) && above.isAir()) {
                        level.setBlock(pos, Blocks.GRASS_BLOCK.defaultBlockState(), 2);
                    }

                    break;
                }
            }
        }
    }
}*/