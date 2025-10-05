package dev.maksiks.amaranth.worldgen.tree.trunk_placer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
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

public class AnthocyaninTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<AnthocyaninTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> trunkPlacerParts(instance).apply(instance, AnthocyaninTrunkPlacer::new)
    );

    public AnthocyaninTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.ANTHOCYANIN_TRUNK_PLACER.get();
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

        for (int i = 0; i < freeTreeHeight; i++) {
            this.placeLog(level, blockSetter, random, pos.above(i), config);
        }

        List<FoliagePlacer.FoliageAttachment> foliage = new ArrayList<>();
        foliage.add(new FoliagePlacer.FoliageAttachment(pos.above(freeTreeHeight), 0, false));

        BlockPos tip = pos.above(freeTreeHeight);

        for (int i = 0; i < 2 + random.nextInt(2); i++) {
            double horizontalAngle = random.nextDouble() * Math.PI * 2.0D;
            int length = 2 + random.nextInt(3);
            double tilt = Math.toRadians(random.nextDouble() * 50.0D + 15.0D);

            double xDir = Math.cos(horizontalAngle) * Math.cos(tilt);
            double yDir = Math.sin(tilt);
            double zDir = Math.cos(horizontalAngle) * Math.sin(tilt);

            for (int j = 0; j < length; j++) {
                int x = tip.getX() + (int) Math.round(xDir * j);
                int y = tip.getY() + (int) Math.round(yDir * j);
                int z = tip.getZ() + (int) Math.round(zDir * j);
                this.placeLog(level, blockSetter, random, new BlockPos(x, y, z), config);
            }

            BlockPos branchEnd = new BlockPos(
                    tip.getX() + (int) Math.round(xDir * (length - 1)),
                    tip.getY() + (int) Math.round(yDir * (length - 1)),
                    tip.getZ() + (int) Math.round(zDir * (length - 1))
            );

            foliage.add(new FoliagePlacer.FoliageAttachment(branchEnd, 0, false));
        }

        return foliage;
    }
}
