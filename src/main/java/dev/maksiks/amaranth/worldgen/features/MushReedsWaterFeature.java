package dev.maksiks.amaranth.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import static dev.maksiks.amaranth.block.custom.ReedsBlock.isValidReedSoil;

public class MushReedsWaterFeature extends Feature<NoneFeatureConfiguration> {
    // basically random patch but 1 block underwater
    // but with a 50/50 chance
    // + spawns less if it's not adjacent to coast

    public MushReedsWaterFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        BlockPos pos = ctx.origin();
        RandomSource random = ctx.random();

        // random patch settings
        int tries = 512;
        int xzSpread = 16;
        int ySpread = 3;

        int placed = 0;

        for (int i = 0; i < tries; i++) {
            int xOffset = random.nextInt(xzSpread) - random.nextInt(xzSpread);
            int yOffset = random.nextInt(ySpread) - random.nextInt(ySpread);
            int zOffset = random.nextInt(xzSpread) - random.nextInt(xzSpread);

            BlockPos attemptPos = pos.offset(xOffset, yOffset, zOffset);

            BlockPos waterSurface = findWaterSurface(level, attemptPos);

            if (waterSurface != null && canPlaceReedAt(level, waterSurface)) {
                // spawning only 50% of the time
                if (random.nextBoolean()) {
                    continue;
                }
                // if far from coast spawning rarely
                if (!isNearCoast(level, waterSurface) && random.nextInt(5) != 0) {
                    continue;
                }
                placeReed(level, waterSurface);
                placed++;
            }
        }

        return placed > 0;
    }

    private BlockPos findWaterSurface(WorldGenLevel level, BlockPos startPos) {
        int floorY = level.getHeight(Heightmap.Types.OCEAN_FLOOR, startPos.getX(), startPos.getZ());

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos(startPos.getX(), floorY, startPos.getZ());

        int maxY = Math.min(level.getMaxBuildHeight(), startPos.getY() + 10);

        while (mutable.getY() < maxY) {
            BlockState current = level.getBlockState(mutable);
            BlockState above = level.getBlockState(mutable.above());

            if (current.getFluidState().is(FluidTags.WATER) &&
                    !above.getFluidState().is(FluidTags.WATER) &&
                    (above.isAir() || above.canBeReplaced())) {
                return mutable.immutable();
            }

            mutable.move(0, 1, 0);
        }

        return null;
    }

    private boolean canPlaceReedAt(WorldGenLevel level, BlockPos waterPos) {
        BlockPos abovePos = waterPos.above();
        BlockPos belowPos = waterPos.below();
        BlockState belowState = level.getBlockState(belowPos);

        if (!level.getBlockState(waterPos).getFluidState().is(FluidTags.WATER)) {
            return false;
        }

        BlockState aboveState = level.getBlockState(abovePos);
        if (!aboveState.isAir() && !aboveState.canBeReplaced()) {
            return false;
        }

        BlockState belowWater = level.getBlockState(waterPos.below());
        return !belowWater.isAir() && !belowWater.canBeReplaced() && isValidReedSoil(belowState);
    }

    private void placeReed(WorldGenLevel level, BlockPos waterPos) {
        BlockState reedBlock = dev.maksiks.amaranth.block.ModBlocks.REEDS.get().defaultBlockState();

        level.setBlock(waterPos, reedBlock
                .setValue(net.minecraft.world.level.block.DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
                .setValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED, true), 2);

        level.setBlock(waterPos.above(), reedBlock
                .setValue(net.minecraft.world.level.block.DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
                .setValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED, false), 2);
    }

    private boolean isNearCoast(WorldGenLevel level, BlockPos waterPos) {
        // coast v
        int range = 2;

        for (int dx = -range; dx <= range; dx++) {
            for (int dz = -range; dz <= range; dz++) {
                if (dx == 0 && dz == 0) continue;

                BlockPos checkPos = waterPos.offset(dx, 0, dz);
                BlockState state = level.getBlockState(checkPos);

                if (!state.getFluidState().is(FluidTags.WATER) &&
                        state.isSolid()) {
                    return true;
                }
            }
        }

        return false;
    }
}