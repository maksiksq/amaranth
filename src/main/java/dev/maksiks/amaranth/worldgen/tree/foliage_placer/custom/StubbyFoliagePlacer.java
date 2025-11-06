package dev.maksiks.amaranth.worldgen.tree.foliage_placer.custom;

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

public class StubbyFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<StubbyFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance -> blobParts(instance).apply(instance, StubbyFoliagePlacer::new));
    protected final int height;

    protected static <P extends StubbyFoliagePlacer> P3<Mu<P>, IntProvider, IntProvider, Integer> blobParts(Instance<P> instance) {
        return foliagePlacerParts(instance).and(Codec.intRange(0, 16).fieldOf("height").forGetter(p_68412_ -> p_68412_.height));
    }

    public StubbyFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
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
        return ModFoliagePlacerTypes.STUBBY_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(
            LevelSimulatedReader level,
            FoliageSetter blockSetter,
            RandomSource random,
            TreeConfiguration config,
            int maxFreeTreeHeight,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset
    ) {
        AtomicInteger currentY = new AtomicInteger(1);
        BlockPos trunkPos = attachment.pos();

        BlockPos center = trunkPos.above(currentY.get());

        Runnable smallLayer = () -> {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (Math.abs(dx) == 1 && Math.abs(dz) == 1) {
                        continue;
                    }

                    BlockPos leafPos = center.offset(dx, currentY.get(), dz);

                    if (random.nextFloat() < 0.3f) {continue;}
                    tryPlaceLeaf(level, blockSetter, random, config, leafPos);
                }
            }
        };

        Runnable layer = () -> {
            for (int dx = -2; dx <= 2; dx++) {
                for (int dz = -2; dz <= 2; dz++) {
                    if (Math.abs(dx) == 2 && Math.abs(dz) == 2) {
                        continue;
                    }

                    BlockPos leafPos = center.offset(dx, currentY.get(), dz);

                    if (random.nextFloat() < 0.3f) {continue;}
                    tryPlaceLeaf(level, blockSetter, random, config, leafPos);
                }
            }
        };
        currentY.getAndDecrement();
        smallLayer.run();
        currentY.getAndDecrement();
        layer.run();
        currentY.getAndDecrement();
        layer.run();
        currentY.getAndDecrement();
        layer.run();
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
