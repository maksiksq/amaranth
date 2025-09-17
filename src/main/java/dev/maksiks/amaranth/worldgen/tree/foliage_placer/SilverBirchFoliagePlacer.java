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

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

import static dev.maksiks.amaranth.worldgen.tree.foliage_placer.FoliageHelpers.*;

public class SilverBirchFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<SilverBirchFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance -> blobParts(instance).apply(instance, SilverBirchFoliagePlacer::new));
    protected final int height;

    protected static <P extends SilverBirchFoliagePlacer> P3<Mu<P>, IntProvider, IntProvider, Integer> blobParts(Instance<P> instance) {
        return foliagePlacerParts(instance).and(Codec.intRange(0, 16).fieldOf("height").forGetter(p_68412_ -> p_68412_.height));
    }

    public SilverBirchFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacerTypes.SILVER_BIRCH_FOLIAGE_PLACER.get();
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
        AtomicInteger currentY = new AtomicInteger(3);
        BlockPos trunkPos = attachment.pos();

        Runnable bump = () -> {
            currentY.getAndDecrement();
        };

//        Uninmplemented
//        String variant = switch (random.nextInt(6)) {
//            case 0, 1, 2, 3 -> "thin";
//            case 4 -> "thick";
//            case 5 -> "chonky";
//            default -> "thin";
//        };

        // top 1
        bump.run();
        tryPlaceLeaf(level, blockSetter, random, config, trunkPos.above(currentY.get()));

        // 1 splat 50/50
        bump.run();
        tryPlaceLeaf(level, blockSetter, random, config, trunkPos.above(currentY.get()));

        splat(random, (pos, rand) -> tryPlaceLeaf(level, blockSetter, rand, config, pos), trunkPos, currentY, 50).place();


        // 2 splat 75/25 + 50/50 diagonals
        for (int i = 0; i <= 1; ++i) {
            bump.run();
            tryPlaceLeaf(level, blockSetter, random, config, trunkPos.above(currentY.get()));

            splat(random, (pos, rand) -> tryPlaceLeaf(level, blockSetter, rand, config, pos), trunkPos, currentY, 75, true, 50).place();
        }


        // 6 5x5 with 38% edges and 62% mid ring and no corners
        for (int i = 0; i <= 5; ++i) {
            bump.run();
            bigSplatDistributed(random, (pos, rand) -> tryPlaceLeaf(level, blockSetter, rand, config, pos), trunkPos, currentY, 62, 38, level, true).place();
        }

        // 1 5x5 with 15% edges and 50% mid ring and no corners to round out the bottom
        bump.run();
        bigSplat(random, (pos, rand) -> tryPlaceLeaf(level, blockSetter, rand, config, pos), trunkPos, currentY, 50, 15).place();

        // 1 75% splat
        bump.run();
        splat(random, (pos, rand) -> tryPlaceLeaf(level, blockSetter, rand, config, pos), trunkPos, currentY, 75).place();
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
