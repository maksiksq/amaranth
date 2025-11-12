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

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static dev.maksiks.amaranth.worldgen.tree.LeafPlacerContextKt.diagonals;
import static dev.maksiks.amaranth.worldgen.tree.ModOldTreeHelpers.*;

public class WitchyFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<WitchyFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance -> blobParts(instance).apply(instance, WitchyFoliagePlacer::new));
    protected final int height;

    protected static <P extends WitchyFoliagePlacer> P3<Mu<P>, IntProvider, IntProvider, Integer> blobParts(Instance<P> instance) {
        return foliagePlacerParts(instance).and(Codec.intRange(0, 16).fieldOf("height").forGetter(p_68412_ -> p_68412_.height));
    }

    public WitchyFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacerTypes.WITCHY_FOLIAGE_PLACER.get();
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
        boolean branch = attachment.radiusOffset() == 1;

        if (!branch) {
            BlockPos trunkPos = attachment.pos().below();

            // 2-3 above
            int lanceHeight = random.nextInt(2) + 2;

            for (int i = lanceHeight; i > 0; i--) {
                tryPlaceLeaf(level, blockSetter, random, config, trunkPos.above(i));

                if (i == lanceHeight - 1) {
                    splat(random, (pos, rand) -> tryPlaceLeaf(level, blockSetter, rand, config, pos), trunkPos, new AtomicInteger(lanceHeight - 2), 12).place();
                }
                if (lanceHeight == 3 && i == 1) {
                    splat(random, (pos, rand) -> tryPlaceLeaf(level, blockSetter, rand, config, pos), trunkPos, new AtomicInteger(0), 75).place();
                }
            }

            // 1 splat + same diagonal
            ArrayList<BlockPos> placedAtTrunk0 = new ArrayList<>();
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                if (random.nextInt(100) >= 75) continue;
                BlockPos pos = trunkPos.relative(dir);
                placedAtTrunk0.add(pos);
                tryPlaceLeaf(level, blockSetter, random, config, pos);
            }

            for (int j = 0; j < diagonals.length; j += 2) {
                if (random.nextInt(100) >= 12) {
                    continue;
                }

                // placing if adjacent to cardinal
                BlockPos pos = trunkPos.relative(diagonals[j]).relative(diagonals[j + 1]);
                if (placedAtTrunk0.contains(trunkPos.relative(diagonals[j].getOpposite())) ||
                        placedAtTrunk0.contains(trunkPos.relative(diagonals[j + 1].getOpposite()))) {
                    tryPlaceLeaf(level, blockSetter, random, config, pos);
                }
            }

            // 2-3 of the same but may extend
            int depth = random.nextInt(2)+2;
            for (int i = 1; i <= depth; i++) {
                ArrayList<BlockPos> placedAtTrunkN = new ArrayList<>();
                for (Direction dir : Direction.Plane.HORIZONTAL) {
                    if (random.nextInt(100) >= 80) continue;
                    BlockPos pos = trunkPos.relative(dir).below(i);
                    placedAtTrunkN.add(pos);
                    tryPlaceLeaf(level, blockSetter, random, config, pos);

                    // extending with a 25% chance
                    if (random.nextInt(100) <= 25) {
                        tryPlaceLeaf(level, blockSetter, random, config, pos.relative(dir));
                    }
                }

                for (int j = 0; j < diagonals.length; j += 2) {
                    if (random.nextInt(100) >= 12) continue;

                    // placing if adjacent to cardinal and extending
                    BlockPos pos = trunkPos.relative(diagonals[j]).relative(diagonals[j + 1]).below(i);
                    if (placedAtTrunkN.contains(trunkPos.relative(diagonals[j].getOpposite()).below(i)) ||
                            placedAtTrunkN.contains(trunkPos.relative(diagonals[j + 1].getOpposite()).below(i))) {
                        tryPlaceLeaf(level, blockSetter, random, config, pos);

                        if (random.nextInt(100) <= 40) {
                            tryPlaceLeaf(level, blockSetter, random, config, pos.relative(diagonals[j]));
                        }
                        if (random.nextInt(100) <= 40) {
                            tryPlaceLeaf(level, blockSetter, random, config, pos.relative(diagonals[j + 1]));
                        }
                    }
                }
            }

            // 1 splat + diagonals
            splat(random, (pos, rand) -> tryPlaceLeaf(level, blockSetter, rand, config, pos), trunkPos, new AtomicInteger(-(depth+1)), 85, true, 12).place();
        } else {
            BlockPos trunkPos = attachment.pos();

            // 1 splat + same diagonal + verticals
            ArrayList<BlockPos> placedAtTrunk0 = new ArrayList<>();
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                if (random.nextInt(100) >= 95) continue;
                BlockPos pos = trunkPos.relative(dir);
                placedAtTrunk0.add(pos);
                tryPlaceLeaf(level, blockSetter, random, config, pos);
            }

            for (int j = 0; j < diagonals.length; j += 2) {
                if (random.nextInt(100) >= 12) {
                    continue;
                }

                // placing if adjacent to cardinal
                BlockPos pos = trunkPos.relative(diagonals[j]).relative(diagonals[j + 1]);
                if (placedAtTrunk0.contains(trunkPos.relative(diagonals[j].getOpposite())) ||
                        placedAtTrunk0.contains(trunkPos.relative(diagonals[j + 1].getOpposite()))) {
                    tryPlaceLeaf(level, blockSetter, random, config, pos);
                }
            }

            if (random.nextInt(100) <= 90) {
                tryPlaceLeaf(level, blockSetter, random, config, trunkPos.above());
            }
            if (random.nextInt(100) <= 9) {
                tryPlaceLeaf(level, blockSetter, random, config, trunkPos.below());
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
