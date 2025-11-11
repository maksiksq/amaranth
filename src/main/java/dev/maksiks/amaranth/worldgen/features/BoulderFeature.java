package dev.maksiks.amaranth.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.List;

public class BoulderFeature extends Feature<NoneFeatureConfiguration> {

    public BoulderFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        BlockPos origin = ctx.origin();
        RandomSource random = ctx.random();
        int r = 1;

        List<BlockPos> positions = new ArrayList<>();

        // making rock
        for (int x = -r; x <= r; x++) {
            for (int z = -r; z <= r; z++) {
                positions.add(origin.offset(x, 0, z));
            }
        }
        for (int x = -r; x <= r; x++) {
            for (int y = -r; y <= r; y++) {
                positions.add(origin.offset(x, y, 0));
            }
        }
        for (int y = -r; y <= r; y++) {
            for (int z = -r; z <= r; z++) {
                positions.add(origin.offset(0, y, z));
            }
        }

        // shmooving rock so it's always grounded
        BlockPos curBase = origin;
        int limit = 4;
        while (hasNonSolidBelow(level, positions)) {
            curBase = curBase.below();
            List<BlockPos> shifted = new ArrayList<>(positions.size());
            for (BlockPos pos : positions) {
                shifted.add(pos.below());
            }
            positions = shifted;

            // im sorry for all the cool worldgen glitches
            // but it just sometimes looks bad on big cliffs which is kind of sad
            limit--;
            if (limit < 0) return false;
            if (curBase.getY() < level.getMinBuildHeight() + 2) return false;
        }

        // placing
        for (BlockPos pos : positions) {
            setRock(level, pos, random);
        }

        // replacing with dirt below
        for (BlockPos pos : positions) {
            BlockPos below = pos.below();
            if (!positions.contains(below)) {
                BlockState belowState = level.getBlockState(below);
                if (belowState.is(Blocks.GRASS_BLOCK)
                        || belowState.is(Blocks.MYCELIUM)
                        || belowState.is(Blocks.PODZOL)
                        || belowState.is(Blocks.DIRT_PATH)) {
                    level.setBlock(below, Blocks.DIRT.defaultBlockState(), 2);
                }
            }
        }

        return true;
    }

    private void setRock(WorldGenLevel level, BlockPos pos, RandomSource random) {
        boolean roll = random.nextBoolean();
        level.setBlock(pos, roll
                        ? Blocks.TUFF.defaultBlockState()
                        : random.nextBoolean()
                            ? Blocks.ANDESITE.defaultBlockState()
                            : Blocks.STONE.defaultBlockState(),
                2);
    }

    private boolean hasNonSolidBelow(WorldGenLevel level, List<BlockPos> positions) {
        for (BlockPos pos : positions) {
            BlockPos below = pos.below();
            BlockState belowState = level.getBlockState(below);

            if (!belowState.isFaceSturdy(level, below, Direction.UP)) {
                return true;
            }
        }
        return false;
    }
}