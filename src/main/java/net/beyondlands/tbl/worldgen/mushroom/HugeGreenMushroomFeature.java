package net.beyondlands.tbl.worldgen.mushroom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

public class HugeGreenMushroomFeature extends Feature<HugeMushroomFeatureConfiguration> {

    public HugeGreenMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<HugeMushroomFeatureConfiguration> context) {

        LevelAccessor level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();
        HugeMushroomFeatureConfiguration config = context.config();

        int height = 5 + random.nextInt(3);

        for (int y = 0; y < height; y++) {
            level.setBlock(pos.above(y), config.stemProvider.getState(random, pos), 3);
        }

        int coneHeight = 4;

        for (int y = 0; y < coneHeight; y++) {
            int radius = coneHeight - y;

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {

                    if (Math.abs(x) + Math.abs(z) <= radius + 1) {
                        BlockPos capPos = pos.offset(x, height + y, z);

                        level.setBlock(capPos,
                                config.capProvider.getState(random, capPos),
                                3);
                    }
                }
            }
        }

        return true;
    }
}
