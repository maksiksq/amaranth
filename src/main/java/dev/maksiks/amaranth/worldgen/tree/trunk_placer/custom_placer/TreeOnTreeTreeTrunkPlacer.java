package dev.maksiks.amaranth.worldgen.tree.trunk_placer.custom_placer;

import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.maksiks.amaranth.worldgen.tree.trunk_placer.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class TreeOnTreeTreeTrunkPlacer extends TrunkPlacer {

    public static final MapCodec<TreeOnTreeTreeTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> trunkPlacerParts(instance).apply(instance, TreeOnTreeTreeTrunkPlacer::new)
    );

    public TreeOnTreeTreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.TREE_ON_TREE_TREE_TRUNK_PLACER.get();
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

        List<FoliagePlacer.FoliageAttachment> foliageAttachments = Lists.newArrayList();

        BlockPos mainTrunkTop = pos.above(freeTreeHeight);
        foliageAttachments.add(new FoliagePlacer.FoliageAttachment(mainTrunkTop, 0, false));

        List<BlockPos> currentLayer = Lists.newArrayList();
        currentLayer.add(mainTrunkTop);

        int maxLayers = random.nextInt(10) == 0 ? 6 + random.nextInt(3) : 1 + random.nextInt(3); // Usually 1-3, rarely 6-8

        for (int layer = 0; layer < maxLayers; layer++) {
            List<BlockPos> nextLayer = Lists.newArrayList();

            for (BlockPos currentTrunkTop : currentLayer) {
                int treesOnThisTrunk = random.nextInt(3);
                for (int i = 0; i < treesOnThisTrunk; i++) {
                    int offsetX = random.nextInt(3) - 1;
                    int offsetZ = random.nextInt(3) - 1;

                    if (offsetX == 0 && offsetZ == 0 && treesOnThisTrunk > 1) {
                        offsetX = random.nextBoolean() ? 1 : -1;
                    }

                    BlockPos newTrunkStart = currentTrunkTop.offset(offsetX, 0, offsetZ);
                    int newTrunkHeight = 2 + random.nextInt(4);

                    for (int j = 0; j < newTrunkHeight; j++) {
                        this.placeLog(level, blockSetter, random, newTrunkStart.above(j), config);
                    }

                    BlockPos newTrunkTop = newTrunkStart.above(newTrunkHeight);

                    foliageAttachments.add(new FoliagePlacer.FoliageAttachment(newTrunkTop, 0, false));

                    nextLayer.add(newTrunkTop);
                }
            }

            if (nextLayer.isEmpty()) {
                break;
            }

            currentLayer = nextLayer;

            if (random.nextInt(layer + 2) == 0) {
                break;
            }
        }

        return foliageAttachments;
    }
}