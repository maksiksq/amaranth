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

import static dev.maksiks.amaranth.worldgen.tree.foliage_placer.FoliageHelpers.*;

public class AnthocyaninFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<AnthocyaninFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance -> blobParts(instance).apply(instance, AnthocyaninFoliagePlacer::new));
    protected final int height;

    protected static <P extends AnthocyaninFoliagePlacer> P3<Mu<P>, IntProvider, IntProvider, Integer> blobParts(Instance<P> instance) {
        return foliagePlacerParts(instance).and(Codec.intRange(0, 16).fieldOf("height").forGetter(p_68412_ -> p_68412_.height));
    }

    public AnthocyaninFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacerTypes.ANTHOCYANIN_FOLIAGE_PLACER.get();
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
        int r = 2;

        // css grid wait no
        boolean[][] hasLeafBelow = new boolean[2 * r + 1][2 * r + 1];

        for (int dy = -r; dy <= r; dy++) {
            for (int dx = -r; dx <= r; dx++) {
                for (int dz = -r; dz <= r; dz++) {
                    int dist = Math.abs(dx) + Math.abs(dy) + Math.abs(dz);
                    if (dist > r) continue;

                    double skipChance = dist == r ? 0.6 : (dist == r - 1 ? 0.3 : 0.0);
                    boolean skip = random.nextDouble() < skipChance;

                    int gridX = dx + r;
                    int gridZ = dz + r;

                    if (hasLeafBelow[gridX][gridZ]) {
                        skip = false;
                    }

                    if (!skip) {
                        BlockPos leafPos = trunkPos.offset(dx, dy, dz);
                        tryPlaceLeaf(level, blockSetter, random, config, leafPos);

                        hasLeafBelow[gridX][gridZ] = true;
                    }
                }
            }
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
