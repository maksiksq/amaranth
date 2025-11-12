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

import static dev.maksiks.amaranth.worldgen.tree.LeafPlacerContextKt.diagonals;

public class WitchyTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<WitchyTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> trunkPlacerParts(instance).apply(instance, WitchyTrunkPlacer::new)
    );

    public WitchyTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.WITCHY_TRUNK_PLACER.get();
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

        List<FoliagePlacer.FoliageAttachment> foliage = new ArrayList<>();
        int height = random.nextInt(2) + 6;
        // a bit off but oh well
        int maxBranchCount = random.nextInt(1) + 1;
        int branchCount = 0;
        int lastBranchHeight = -2;

        for (int i = 0; i < height; i++) {
            this.placeLog(level, blockSetter, random, pos.above(i), config);

            if (i > 0 && i < height - 2 && i > lastBranchHeight + 1) {
                BlockPos branchTip = placeBranch(level, blockSetter, random, pos.above(i), config);
                foliage.add(new FoliagePlacer.FoliageAttachment(branchTip, 1, false));
                branchCount++;
                lastBranchHeight = i;
            }

            if (i == height - 1 && branchCount < maxBranchCount) {
                i = 0;
                lastBranchHeight = -2;
            }
        }

        foliage.add(new FoliagePlacer.FoliageAttachment(pos.above(height), 0, false));
        return foliage;
    }

    private BlockPos placeBranch(
            LevelSimulatedReader level,
            BiConsumer<BlockPos, BlockState> blockSetter,
            RandomSource random,
            BlockPos startPos,
            TreeConfiguration config
    ) {
        boolean isDiagonal = random.nextBoolean();
        boolean isLonger = random.nextInt(3) == 0;

        BlockPos branchPos;

        if (isDiagonal) {
            branchPos = placeDiagonalBranch(level, blockSetter, random, startPos, config, isLonger);
        } else {
            branchPos = placeCardinalBranch(level, blockSetter, random, startPos, config, isLonger);
        }

        return branchPos;
    }

    private BlockPos placeCardinalBranch(
            LevelSimulatedReader level,
            BiConsumer<BlockPos, BlockState> blockSetter,
            RandomSource random,
            BlockPos startPos,
            TreeConfiguration config,
            boolean isLonger
    ) {
        Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos branchPos = startPos.relative(dir);
        this.placeLog(level, blockSetter, random, branchPos, config);

        if (isLonger) {
            BlockPos tipPos = branchPos.relative(dir).above();
            this.placeLog(level, blockSetter, random, tipPos, config);
            return tipPos;
        }

        return branchPos;
    }

    private BlockPos placeDiagonalBranch(
            LevelSimulatedReader level,
            BiConsumer<BlockPos, BlockState> blockSetter,
            RandomSource random,
            BlockPos startPos,
            TreeConfiguration config,
            boolean isLonger
    ) {
        int ix = random.nextInt(4) * 2;
        Direction[] dirs = {diagonals[ix], diagonals[ix + 1]};
        BlockPos branchPos = startPos.relative(dirs[0]).relative(dirs[1]);
        this.placeLog(level, blockSetter, random, branchPos, config);

        if (isLonger) {
            Direction extendDir = random.nextBoolean() ? dirs[0] : dirs[1];
            BlockPos tipPos = branchPos.relative(extendDir).above();
            this.placeLog(level, blockSetter, random, tipPos, config);
            return tipPos;
        }

        return branchPos;
    }
}