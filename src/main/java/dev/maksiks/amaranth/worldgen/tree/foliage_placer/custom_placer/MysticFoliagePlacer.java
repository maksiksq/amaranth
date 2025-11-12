package dev.maksiks.amaranth.worldgen.tree.foliage_placer.custom_placer;

import com.mojang.datafixers.Products.P3;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.codecs.RecordCodecBuilder.Instance;
import com.mojang.serialization.codecs.RecordCodecBuilder.Mu;
import dev.maksiks.amaranth.worldgen.tree.foliage_placer.ModFoliagePlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.concurrent.atomic.AtomicInteger;
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
        AtomicInteger currentY = new AtomicInteger(3);
        BlockPos trunkPos = attachment.pos();

        // FIX SLOPPINESS

        BiConsumer<Integer, Integer> placeGenericRow = (range, y) -> this.placeLeavesRow(level, blockSetter, random, config, attachment.pos(), range, y, attachment.doubleTrunk());

        Runnable bump = () -> {
            if (random.nextInt(5) != 0) {
                currentY.getAndDecrement();
            }
        };

        // top 1
        bump.run();
        tryPlaceLeaf(level, blockSetter, random, config, trunkPos.above(currentY.get()));

        // 3 x5
        while (currentY.get() >= 0) {
            bump.run();

            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.above(currentY.get()));

            for (Direction dir : Direction.Plane.HORIZONTAL) {
                tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(dir, 1).above(currentY.get()));
            }
        }

        // fancy rhombusish
        bump.run();
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(dir, 2).above(currentY.get()));
        }

        // fancy rhombusish w diagonals
        bump.run();
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(dir, 2).above(currentY.get()));
        }

        for (int i = 0; i < diagonals.length; i += 2) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 1).relative(diagonals[i+1], 1).above(currentY.get()));
        }

        // 1wide 5x + fancy rhombusish w diagonals w 2wide diagonals
        bump.run();
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(dir, 1).above(currentY.get()));
        }

        for (Direction dir : Direction.Plane.HORIZONTAL) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(dir, 2).above(currentY.get()));
        }

        for (int i = 0; i < diagonals.length; i += 2) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 1).relative(diagonals[i+1], 1).above(currentY.get()));
        }

        for (int i = 0; i < diagonals.length; i += 2) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 2).relative(diagonals[i+1], 2).above(currentY.get()));
        }

        // 1wide pancake w 2wide corners
        bump.run();
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(dir, 1).above(currentY.get()));
        }

        for (int i = 0; i < diagonals.length; i += 2) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 1).relative(diagonals[i+1], 1).above(currentY.get()));
        }

        for (int i = 0; i < diagonals.length; i += 2) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 2).relative(diagonals[i+1], 1).above(currentY.get()));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 2).relative(diagonals[i+1], -1).above(currentY.get()));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i+1], 2).relative(diagonals[i], 1).above(currentY.get()));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i+1], 2).relative(diagonals[i], -1).above(currentY.get()));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 2).relative(diagonals[i+1], 2).above(currentY.get()));
        }

        // 1wide pancake
        bump.run();
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(dir, 1).above(currentY.get()));
        }

        for (int i = 0; i < diagonals.length; i += 2) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 1).relative(diagonals[i+1], 1).above(currentY.get()));
        }

        // 2wide round pancake
        bump.run();
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(dir, 1).above(currentY.get()));
        }

        for (int i = 0; i < diagonals.length; i += 2) {
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 2).above(currentY.get()));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i+1], 2).above(currentY.get()));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 2).relative(diagonals[i+1], 1).above(currentY.get()));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 2).relative(diagonals[i+1], -1).above(currentY.get()));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i+1], 2).relative(diagonals[i], 1).above(currentY.get()));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i+1], 2).relative(diagonals[i], -1).above(currentY.get()));
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.relative(diagonals[i], 1).relative(diagonals[i+1], 1).above(currentY.get()));
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
