package dev.maksiks.amaranth.worldgen.tree.trunk_placer.custom_placer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.maksiks.amaranth.worldgen.tree.trunk_placer.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static dev.maksiks.amaranth.worldgen.tree.TreeUtils.isAdjacentToAny;
import static dev.maksiks.twigonometry.api.LeafPlacerContextKt.DIAGONALS;

public class SatistreeTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<SatistreeTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            p_70261_ -> trunkPlacerParts(p_70261_).apply(p_70261_, SatistreeTrunkPlacer::new)
    );

    public SatistreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.SATISTREE_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(
            LevelSimulatedReader level,
            BiConsumer<BlockPos, BlockState> blockSetter,
            RandomSource random,
            int freeTreeHeight,
            BlockPos pos,
            TreeConfiguration config
    ) {
        setDirtAt(level, blockSetter, random, pos.below(), config);

        freeTreeHeight = 8 + random.nextInt(3);
        for (int i = 0; i < freeTreeHeight; i++) {
            this.placeLog(level, blockSetter, random, pos.above(i), config);
        }

        BlockPos startPos = pos.above(freeTreeHeight - random.nextInt(2) - 5);
        List<FoliagePlacer.FoliageAttachment> attachments = new ArrayList<>();
        List<BlockPos> adjacent = new ArrayList<>();

        int branchCount =
                random.nextInt(4) != 0 ? 2 :
                        random.nextInt(10) == 0 ? 3 : 1;
        for (int i = 0; i < branchCount; i++) {
            boolean diagonal = random.nextBoolean();
            BlockPos branchingPos;
            Direction xwise;
            BlockPos upperPos;

            int attempts = 0;
            int maxAttempts = 20;

            do {
                if (diagonal) {
                    int roll = random.nextInt(4) * 2;
                    branchingPos = startPos.relative(DIAGONALS[roll]).relative(DIAGONALS[roll + 1]);
                    xwise = random.nextBoolean() ? DIAGONALS[roll] : DIAGONALS[roll + 1];
                    upperPos = branchingPos.relative(xwise).above();
                } else {
                    Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                    branchingPos = startPos.relative(dir);
                    xwise = random.nextBoolean() ? dir.getClockWise() : dir.getCounterClockWise();
                    upperPos = branchingPos.relative(dir).relative(xwise).above();
                }
                attempts++;
            } while (isAdjacentToAny(branchingPos, adjacent) && attempts < maxAttempts);

            adjacent.add(branchingPos);

            this.placeLog(level, blockSetter, random, branchingPos, config);
            this.placeLog(level, blockSetter, random, upperPos, config);
            this.placeLog(level, blockSetter, random, upperPos.above(), config);
            boolean isHigher = random.nextBoolean();
            if (isHigher) {
                this.placeLog(level, blockSetter, random, upperPos.above().above(), config);
            }

            if (branchCount == 3) {
                startPos = startPos.above();
            } else {
                startPos = startPos.above(1 + random.nextInt(10) == 0 ? 1 : 0);
            }

            if (isHigher) {
                attachments.add(new FoliagePlacer.FoliageAttachment(upperPos, 2, false));
            } else {
                attachments.add(new FoliagePlacer.FoliageAttachment(upperPos, 1, false));
            }
        }

        //
        // encoding stuff into radiusOffset again too
        //
        attachments.add(new FoliagePlacer.FoliageAttachment(pos.above(freeTreeHeight), 0, false));
        return attachments;
    }
}
