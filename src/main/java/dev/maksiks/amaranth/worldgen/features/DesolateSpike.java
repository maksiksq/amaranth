package dev.maksiks.amaranth.worldgen.features;

import com.mojang.serialization.Codec;
import dev.maksiks.amaranth.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DesolateSpike extends Feature<NoneFeatureConfiguration> {

    public DesolateSpike(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        BlockPos pos = ctx.origin();
        RandomSource random = ctx.random();

        int height = 5 + random.nextInt(8);

        double angle = random.nextDouble() * 2 * Math.PI;
        double tiltStrength = 0.3 + random.nextDouble() * 0.7;
        double dxTilt = Math.cos(angle) * tiltStrength;
        double dzTilt = Math.sin(angle) * tiltStrength;

        for (int y = 0; y < height; y++) {
            int radius = Math.max(0, (height - y) / 3);

            int offsetX = (int) Math.round(dxTilt * y);
            int offsetZ = (int) Math.round(dzTilt * y);
            BlockPos layerCenter = pos.offset(offsetX, y, offsetZ);

            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    if (dx * dx + dz * dz <= radius * radius && random.nextFloat() > 0.2f) {
                        BlockPos placePos = layerCenter.offset(dx, 0, dz);

                        if (random.nextFloat() < 0.7f) {
                            level.setBlock(placePos, ModBlocks.MIASMA_ICE.get().defaultBlockState(), 2);
                        } else {
                            level.setBlock(placePos, ModBlocks.DENSE_MIASMA_ICE.get().defaultBlockState(), 2);
                        }
                    }
                }
            }
        }

        return true;
    }
}
