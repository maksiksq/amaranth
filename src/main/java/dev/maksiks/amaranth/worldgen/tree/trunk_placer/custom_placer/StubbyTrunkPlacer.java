package dev.maksiks.amaranth.worldgen.tree.trunk_placer.custom_placer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.maksiks.amaranth.worldgen.tree.trunk_placer.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class StubbyTrunkPlacer extends TrunkPlacer {
    // same as vanilla straight trunk placer right now

    public static final MapCodec<StubbyTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> trunkPlacerParts(instance).apply(instance, StubbyTrunkPlacer::new)
    );

    public StubbyTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.STUBBY_TRUNK_PLACER.get();
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

        Consumer<BlockPos>[] fixAirAndDirt = new Consumer[1];
        fixAirAndDirt[0] = (checkPos) -> {
            BlockPos below = checkPos.below();
            if (level.isStateAtPosition(below, BlockBehaviour.BlockStateBase::canBeReplaced)) {
                this.placeLog(level, blockSetter, random, below, config);
                fixAirAndDirt[0].accept(below);
            }
            if (level.isStateAtPosition(below, state -> state.is(Blocks.GRASS_BLOCK))) {
                setDirtAt(level, blockSetter, random, below, config);
            }
        };

        for (int y = 0; y < freeTreeHeight; y++) {
            this.placeLog(level, blockSetter, random, pos.above(y), config);

            for (Direction dir : Direction.Plane.HORIZONTAL) {
                BlockPos offsetPos = pos.relative(dir).above(y);
                this.placeLog(level, blockSetter, random, offsetPos, config);
                fixAirAndDirt[0].accept(offsetPos);
            }
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.above(freeTreeHeight), 0, false));
    }
}
