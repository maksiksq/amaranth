package dev.maksiks.amaranth.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.List;

import static dev.maksiks.amaranth.worldgen.features.ModFeatureUtils.hasNonSolidBelow;

public class VividShrublandRockFeature extends Feature<NoneFeatureConfiguration> {
    public VividShrublandRockFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        BlockPos origin = ctx.origin();
        RandomSource random = ctx.random();
        int sqrRadius = 2;

        List<BlockPos> positions = new ArrayList<>();
        int skipCorner = random.nextInt(4);

        for (int y = 0; y <= 1; y++) {
            for (int x = 1; x <= sqrRadius; x++) {
                for (int z = 1; z <= sqrRadius; z++) {
                    if (y == 1 &&
                            ((skipCorner == 0 && x == 1 && z == 1) ||
                                    (skipCorner == 1 && x == sqrRadius && z == 1) ||
                                    (skipCorner == 2 && x == 1 && z == sqrRadius) ||
                                    (skipCorner == 3 && x == sqrRadius && z == sqrRadius))) {
                        continue;
                    }
                    positions.add(origin.offset(x, y, z));
                }
            }
        }

        shiftAndPlace(positions, level, random);

        return false;
    }

    private void shiftAndPlace(List<BlockPos> positions, WorldGenLevel level, RandomSource random) {
        int i = 0;
        int limit = 7;
        List<BlockPos> bottomPositions = positions.subList(0, positions.size() / 2);
        while (hasNonSolidBelow(level, bottomPositions) && i<=limit) {
            i++;
            positions = positions.stream().map(BlockPos::below).toList();
            bottomPositions = positions.subList(0, positions.size() / 2);
        }
        if (i==limit) return;
        positions.forEach(pos -> {
            if (level.isStateAtPosition(pos.below(), state -> Blocks.GRASS_BLOCK.defaultBlockState().equals(state))) {
                level.setBlock(pos.below(), Blocks.DIRT.defaultBlockState(), 2);
            }
            setRock(level, pos, random);
        });
    }

    private void setRock(WorldGenLevel level, BlockPos pos, RandomSource random) {
        boolean roll = random.nextBoolean();
        level.setBlock(pos, roll
                        ? Blocks.STONE.defaultBlockState()
                        : Blocks.ANDESITE.defaultBlockState(),
                2);
    }
}
