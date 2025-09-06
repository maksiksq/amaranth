package dev.maksiks.amaranth.worldgen.tree.foliage_placer;

import com.mojang.datafixers.Products.P3;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.codecs.RecordCodecBuilder.Instance;
import com.mojang.serialization.codecs.RecordCodecBuilder.Mu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.function.BiConsumer;

public class MysticFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<MysticFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance -> blobParts(instance).apply(instance, MysticFoliagePlacer::new));
    protected final int height;

    protected static <P extends MysticFoliagePlacer> P3<Mu<P>, IntProvider, IntProvider, Integer> blobParts(Instance<P> instance) {
        return foliagePlacerParts(instance).and(Codec.intRange(0, 16).fieldOf("height").forGetter(p_68412_ -> p_68412_.height));
    }

    public MysticFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    private static final Direction[] diagonals = {
            Direction.NORTH, Direction.EAST,
            Direction.NORTH, Direction.WEST,
            Direction.SOUTH, Direction.EAST,
            Direction.SOUTH, Direction.WEST
    };

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacerTypes.MYSTIC_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(
            LevelSimulatedReader level,
            FoliagePlacer.FoliageSetter blockSetter,
            RandomSource random,
            TreeConfiguration config,
            int maxFreeTreeHeight,
            FoliagePlacer.FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset
    ) {
        int currentY = 3;
        BlockPos trunkPos = attachment.pos();

        // FIX SLOPPINESS

        BiConsumer<Integer, Integer> placeGenericRow = (range, y) -> this.placeLeavesRow(level, blockSetter, random, config, attachment.pos(), range, y, attachment.doubleTrunk());

        // top 1
        currentY--;
        tryPlaceLeaf(level, blockSetter, random, config, trunkPos.above(currentY));

        // 3 x5
        while (currentY >= 0) {
            currentY--;

            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.above(currentY));

            for (Direction dir : Direction.Plane.HORIZONTAL) {
                tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(dir, 1).above(currentY));
            }
        }

        // fancy rhombusish
        currentY--;
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(dir, 2).above(currentY));
        }

        // fancy rhombusish w diagonals
        currentY--;
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(dir, 2).above(currentY));
        }

        for (int i = 0; i < diagonals.length; i += 2) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 1).relative(diagonals[i+1], 1).above(currentY));
        }

        // 1wide 5x + fancy rhombusish w diagonals w 2wide diagonals
        currentY--;
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(dir, 1).above(currentY));
        }

        for (Direction dir : Direction.Plane.HORIZONTAL) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(dir, 2).above(currentY));
        }

        for (int i = 0; i < diagonals.length; i += 2) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 1).relative(diagonals[i+1], 1).above(currentY));
        }

        for (int i = 0; i < diagonals.length; i += 2) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 2).relative(diagonals[i+1], 2).above(currentY));
        }

        // 1wide pancake w 2wide corners
        currentY--;
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(dir, 1).above(currentY));
        }

        for (int i = 0; i < diagonals.length; i += 2) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 1).relative(diagonals[i+1], 1).above(currentY));
        }

        for (int i = 0; i < diagonals.length; i += 2) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 2).relative(diagonals[i+1], 1).above(currentY));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 2).relative(diagonals[i+1], -1).above(currentY));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i+1], 2).relative(diagonals[i], 1).above(currentY));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i+1], 2).relative(diagonals[i], -1).above(currentY));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 2).relative(diagonals[i+1], 2).above(currentY));
        }

        // 1wide pancake
        currentY--;
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(dir, 1).above(currentY));
        }

        for (int i = 0; i < diagonals.length; i += 2) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 1).relative(diagonals[i+1], 1).above(currentY));
        }

        // 2wide round pancake
        currentY--;
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(dir, 1).above(currentY));
        }

        for (int i = 0; i < diagonals.length; i += 2) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 2).above(currentY));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i+1], 2).above(currentY));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 2).relative(diagonals[i+1], 1).above(currentY));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 2).relative(diagonals[i+1], -1).above(currentY));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i+1], 2).relative(diagonals[i], 1).above(currentY));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i+1], 2).relative(diagonals[i], -1).above(currentY));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 1).relative(diagonals[i+1], 1).above(currentY));
        }

    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return this.height;
    }

    /**
     * Skips certain positions based on the provided shape, such as rounding corners randomly.
     * The coordinates are passed in as absolute value, and should be within [0, {@code range}].
     */
    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return localX == range && localZ == range && (random.nextInt(2) == 0 || localY == 0);
    }
}
