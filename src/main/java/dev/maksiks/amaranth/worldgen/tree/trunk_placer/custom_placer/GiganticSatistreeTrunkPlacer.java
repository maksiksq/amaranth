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
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.List;
import java.util.function.BiConsumer;

public class GiganticSatistreeTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<GiganticSatistreeTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            p_70261_ -> trunkPlacerParts(p_70261_).apply(p_70261_, GiganticSatistreeTrunkPlacer::new)
    );

    public GiganticSatistreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.GIGANTIC_SATISTREE_TRUNK_PLACER.get();
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

        freeTreeHeight = 22 + random.nextInt(4);
        int limit = 6;
        Direction highDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockPos placePos = pos.relative(dir);
            this.placeLog(level, blockSetter, random, placePos, config);
            this.placeLog(level, blockSetter, random, placePos.above(), config);
            BlockPos maybeAirPos = placePos.below();
            limit--;
            int airIx = 0;
            while (!level.isStateAtPosition(maybeAirPos, BlockBehaviour.BlockStateBase::isSolid)) {
                airIx++;
                this.placeLog(level, blockSetter, random, maybeAirPos, config);
                maybeAirPos = maybeAirPos.below();
                if (airIx > limit) break;
            }
            if (level.isStateAtPosition(maybeAirPos, Blocks.GRASS_BLOCK.defaultBlockState()::equals)) {
                setDirtAt(level, blockSetter, random, maybeAirPos, config);
            }
            if (level.isStateAtPosition(placePos.below(), Blocks.GRASS_BLOCK.defaultBlockState()::equals)) {
                setDirtAt(level, blockSetter, random, placePos.below(), config);
            }
            if (dir==highDir) {
                this.placeLog(level, blockSetter, random, pos.relative(highDir.getClockWise()).above(2), config);
                this.placeLog(level, blockSetter, random, pos.relative(highDir).above(2), config);
                this.placeLog(level, blockSetter, random, pos.relative(highDir).above(3), config);
            }
        }
        for (int i = 0; i < freeTreeHeight; i++) {
            this.placeLog(level, blockSetter, random, pos.above(i), config);
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.above(freeTreeHeight), 0, false));
    }
}
