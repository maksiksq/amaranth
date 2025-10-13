package dev.maksiks.amaranth.worldgen.features;

import com.mojang.serialization.Codec;
import dev.maksiks.amaranth.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.util.FakePlayerFactory;

import java.util.*;

import static dev.maksiks.amaranth.worldgen.features.ModFeatureUtils.canPlaceBigThingAt;
import static dev.maksiks.amaranth.worldgen.features.ModFeatureUtils.updateAround;

public class ThickPumpkinFeature extends Feature<NoneFeatureConfiguration> {

    public ThickPumpkinFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        BlockPos pos = ctx.origin();
        RandomSource random = ctx.random();

        int size = 2;
        if (random.nextInt(10) == 0) {
            size = 3;
        }

        placeCubeUpAndStem(size, level, pos, random);

        return true;
    }

    private void placeCubeUpAndStem(int size, LevelAccessor level, BlockPos pos, RandomSource random) {
        if (size <= 0) return;
        int startXZ = -(size / 2);
        int endXZ = startXZ + size - 1;
        int yStart = 0;
        int yEnd = size - 1;

        List<BlockPos> toPlace = new ArrayList<>();
        List<BlockPos> topLayer = new ArrayList<>();

        boolean buried = false;

        for (int x = startXZ; x <= endXZ; x++) {
            for (int y = yStart; y <= yEnd; y++) {
                for (int z = startXZ; z <= endXZ; z++) {
                    BlockPos placementPos = pos.offset(x, y, z);

                    if (!canPlaceBigThingAt(level, placementPos)) {
                        return;
                    }

                    toPlace.add(placementPos);
                    if (y == yStart) {
                        if (level.getBlockState(placementPos.below()).canBeReplaced()) {
                            buried = true;
                        }
                    }
                    if (y == yEnd) {
                        topLayer.add(placementPos);
                    }
                }
            }
        }

        stabilizeBase(level, pos, size);

        int buriedHowMuch = buried ? 1 : 0;
        // placing the pumpkins
        for (BlockPos p : toPlace) {
            BlockPos target = p.below(buriedHowMuch);
            level.setBlock(target, ModBlocks.THICK_PUMPKIN.get().defaultBlockState(), Block.UPDATE_NONE);
        }

        // doing 2 passes because updates in worldgen are weird i guess
        for (BlockPos p : toPlace) {
            updateAround(level, p, ModBlocks.THICK_PUMPKIN.get());
        }

        // placing the stem
        level.setBlock(topLayer.get(random.nextInt(topLayer.size())).above().below(buriedHowMuch), Blocks.PUMPKIN_STEM.defaultBlockState().setValue(StemBlock.AGE, random.nextInt(3)+1), 2);
    }

    private void stabilizeBase(LevelAccessor level, BlockPos origin, int size) {
        int startXZ = -(size / 2);
        int endXZ = startXZ + size - 1;

        for (int x = startXZ; x <= endXZ; x++) {
            for (int z = startXZ; z <= endXZ; z++) {
                BlockPos pos = origin.offset(x, 0, z);
                BlockPos below = pos.below();
                boolean topPlaced = false;

                while (below.getY() > level.getMinY() && level.getBlockState(below).canBeReplaced()) {
                    if (!topPlaced) {
                        level.setBlock(below, Blocks.GRASS_BLOCK.defaultBlockState(), 2);
                        topPlaced = true;
                    } else {
                        level.setBlock(below, Blocks.DIRT.defaultBlockState(), 2);
                    }
                    below = below.below();
                }
            }
        }
    }
}