package dev.maksiks.amaranth.worldgen.tree.trunk_placer.custom_placer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.maksiks.amaranth.worldgen.tree.trunk_placer.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class AlienFencePlantTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<AlienFencePlantTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            p_70261_ -> trunkPlacerParts(p_70261_).apply(p_70261_, AlienFencePlantTrunkPlacer::new)
    );

    public AlienFencePlantTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    private static final Map<Direction, BooleanProperty> DIR_TO_PROP = Map.of(
            Direction.NORTH, CrossCollisionBlock.NORTH,
            Direction.SOUTH, CrossCollisionBlock.SOUTH,
            Direction.EAST, CrossCollisionBlock.EAST,
            Direction.WEST, CrossCollisionBlock.WEST
    );

    protected boolean placeFenceWithDirections(
            LevelSimulatedReader level,
            BiConsumer<BlockPos, BlockState> blockSetter,
            RandomSource random,
            BlockPos pos,
            TreeConfiguration config,
            Function<BlockState, BlockState> propertySetter,
            Direction... dirs
    ) {
        if (this.validTreePos(level, pos)) {
            BlockState state = config.trunkProvider.getState(random, pos);

            for (Direction dir : dirs) {
                BooleanProperty prop = DIR_TO_PROP.get(dir);
                state = state.setValue(prop, true);
            }

            blockSetter.accept(pos, propertySetter.apply(state));
            return true;
        } else {
            return false;
        }
    }

    protected boolean placeFenceWithDirections(
            LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource
                    random, BlockPos pos, TreeConfiguration config, Direction... dir
    ) {
        return this.placeFenceWithDirections(level, blockSetter, random, pos, config, Function.identity(), dir);
    }


    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.ALIEN_FENCE_PLANT_TRUNK_PLACER.get();
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
        List<FoliagePlacer.FoliageAttachment> attachments = new java.util.ArrayList<>();

        int branchCount = random.nextInt(3) == 1 ? 2 : 1;
        boolean roll = random.nextBoolean();
        int pipeHeight = branchCount == 2
                ? roll ? 3 : 4
                : roll && random.nextBoolean() ? 3 : 2;

        for (int i = 0; i < pipeHeight; i++) {
            if (i != pipeHeight - 1 && (branchCount != 2 || i != pipeHeight - 2)) {
                this.placeLog(level, blockSetter, random, pos.above(i), config);
            }
            if (i == pipeHeight - 1) {
                Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                this.placeFenceWithDirections(level, blockSetter, random, pos.above(i), config, dir);
                BlockPos tip = pos.above(i + 1).relative(dir);
                this.placeFenceWithDirections(level, blockSetter, random, pos.above(i).relative(dir), config, dir.getOpposite());
                this.placeLog(level, blockSetter, random, tip, config);
                attachments.add(new FoliagePlacer.FoliageAttachment(tip, 0, false));
                if (branchCount == 2) {
                    Direction opposite = dir.getOpposite();
                    this.placeFenceWithDirections(level, blockSetter, random, pos.above(i - 1), config, opposite);
                    Direction xwise = random.nextBoolean() ? dir.getCounterClockWise() : dir.getClockWise();
                    BlockPos lowTip = pos.above(i).relative(opposite).relative(xwise);
                    this.placeFenceWithDirections(level, blockSetter, random, pos.above(i - 1).relative(opposite), config, dir, xwise);
                    this.placeFenceWithDirections(level, blockSetter, random, pos.above(i - 1).relative(opposite).relative(xwise), config, xwise.getOpposite());
                    this.placeLog(level, blockSetter, random, lowTip, config);
                    attachments.add(new FoliagePlacer.FoliageAttachment(lowTip, 0, false));
                }
            }
        }

        return attachments;
    }
}
