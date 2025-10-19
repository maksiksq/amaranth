package dev.maksiks.amaranth.worldgen.tree.foliage_placer;

import com.mojang.datafixers.Products.P3;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.codecs.RecordCodecBuilder.Instance;
import com.mojang.serialization.codecs.RecordCodecBuilder.Mu;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.concurrent.atomic.AtomicInteger;

import static dev.maksiks.amaranth.worldgen.tree.foliage_placer.FoliageHelpers.splat;

public class SpearyFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<SpearyFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance -> blobParts(instance).apply(instance, SpearyFoliagePlacer::new));
    protected final int height;

    protected static <P extends SpearyFoliagePlacer> P3<Mu<P>, IntProvider, IntProvider, Integer> blobParts(Instance<P> instance) {
        return foliagePlacerParts(instance).and(Codec.intRange(0, 16).fieldOf("height").forGetter(p_68412_ -> p_68412_.height));
    }

    public SpearyFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacerTypes.SPEARY_FOLIAGE_PLACER.get();
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
        BlockPos trunkPos = attachment.pos();
        BlockPos trunkTop = attachment.pos().below(1);

        int trunkTopExtent = random.nextInt(16) == 0 ? 3 : 2;
        int trunkBottomExtent = random.nextInt(3) == 0 || maxFreeTreeHeight == 3 ? 2 : 3;

        // top 1
        tryPlaceLeaf(level, blockSetter, random, config, trunkTop.above(trunkTopExtent));

        // 1-2 splat 43/57 + 7/93 diagonals
        for (int i = trunkTopExtent - 1; i > 0; i--) {
                tryPlaceLeaf(level, blockSetter, random, config, trunkTop.above(i));

                splat(random, (pos, rand) -> tryPlaceLeaf(level, blockSetter, rand, config, pos), trunkPos, new AtomicInteger(i-1), 43, true, 7).place();
        }

        // 1-2 splat 80/20 + 7/93 diagonals
        for (int i = trunkBottomExtent-1; i > 0; i--) {
            splat(random, (pos, rand) -> tryPlaceLeaf(level, blockSetter, rand, config, pos), trunkPos, new AtomicInteger(-i), 80, true, 7).place();
        }

        // 1 splat 43/57 + 7/93 diagonals
        splat(random, (pos, rand) -> tryPlaceLeaf(level, blockSetter, rand, config, pos), trunkPos, new AtomicInteger(-trunkBottomExtent), 43, true, 7).place();
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
