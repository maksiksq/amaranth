package dev.maksiks.amaranth.worldgen.tree.foliage_placer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class FoliageHelpers {
    @FunctionalInterface
    public interface LeafPlacer {
        void place();
    }

    public static final Direction[] diagonals = {
            Direction.NORTH, Direction.EAST,
            Direction.NORTH, Direction.WEST,
            Direction.SOUTH, Direction.EAST,
            Direction.SOUTH, Direction.WEST
    };

    // checks if there is a neighbouring leaf or log block
    public static boolean checkIfDetached(LevelSimulatedReader level, BlockPos pos) {
        for (Direction dir : Direction.values()) {
            BlockPos neighbor = pos.relative(dir);
            if (level.isStateAtPosition(neighbor, state -> state.is(BlockTags.LEAVES)) || level.isStateAtPosition(neighbor, state -> state.is(BlockTags.LOGS_THAT_BURN))) {
                return false;
            }
        }
        return true;
    }

    // 3x3
    // 🟥🟦🟥
    // 🟦🟨🟦
    // 🟥🟦🟥
    public static LeafPlacer splat(
            RandomSource random,
            BiConsumer<BlockPos, RandomSource> tryPlaceLeaf,
            BlockPos trunkPos,
            AtomicInteger currentY,
            int chance,
            boolean corners,
            int cornersChance
    ) {
        return () -> {
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                if (random.nextInt(100) >= chance) continue;
                tryPlaceLeaf.accept(trunkPos.relative(dir, 1).above(currentY.get()), random);
            }

            if (!corners) {
                return;
            }

            for (int j = 0; j < diagonals.length; j += 2) {
                if (random.nextInt(100) >= cornersChance) {
                    continue;
                }
                tryPlaceLeaf.accept(trunkPos.relative(diagonals[j], 1).relative(diagonals[j + 1], 1).above(currentY.get()), random);
            }
        };
    }

    public static LeafPlacer splat(
            RandomSource random,
            BiConsumer<BlockPos, RandomSource> tryPlaceLeaf,
            BlockPos trunkPos,
            AtomicInteger currentY,
            int chance
    ) {
        return () -> {
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                if (random.nextInt(100) >= chance) continue;
                tryPlaceLeaf.accept(trunkPos.relative(dir, 1).above(currentY.get()), random);
            }
        };
    }


    // 5x5
    // 🟥🟪🟪🟪🟥
    // 🟪🟦🟦🟦🟪
    // 🟪🟦🟨🟦🟪
    // 🟪🟦🟦🟦🟪
    // 🟥🟪🟪🟪🟥
    public static LeafPlacer bigSplat(
            RandomSource random,
            BiConsumer<BlockPos, RandomSource> tryPlaceLeaf,
            BlockPos trunkPos,
            AtomicInteger currentY,
            int chance,
            int outerChance,
            boolean corners,
            int cornersChance
    ) {
        return () -> {
            for (int dx = -2; dx <= 2; dx++) {
                for (int dz = -2; dz <= 2; dz++) {
                    // skipping mid
                    if (dx == 0 && dz == 0) continue;

                    int distance = Math.max(Math.abs(dx), Math.abs(dz));
                    int curChance;

                    if (distance == 1) {
                        curChance = chance;
                    } else {
                        if (Math.abs(dx) == 2 && Math.abs(dz) == 2) {
                            curChance = corners ? cornersChance : -1;
                        } else {
                            curChance = outerChance;
                        }
                    }

                    if (random.nextInt(100) < curChance) {
                        tryPlaceLeaf.accept(trunkPos.offset(dx, currentY.get(), dz), random);
                    }
                }
            }
        };
    }

    public static LeafPlacer bigSplat(
            RandomSource random,
            BiConsumer<BlockPos, RandomSource> tryPlaceLeaf,
            BlockPos trunkPos,
            AtomicInteger currentY,
            int chance,
            int outerChance
    ) {
        return () -> {
            for (int dx = -2; dx <= 2; dx++) {
                for (int dz = -2; dz <= 2; dz++) {
                    // skipping mid
                    if (dx == 0 && dz == 0) continue;

                    int distance = Math.max(Math.abs(dx), Math.abs(dz));
                    int curChance;

                    if (distance == 1) {
                        curChance = chance;
                    } else {
                        if (Math.abs(dx) == 2 && Math.abs(dz) == 2) {
                            curChance = -1;
                        } else {
                            curChance = outerChance;
                        }
                    }

                    if (random.nextInt(100) < curChance) {
                        tryPlaceLeaf.accept(trunkPos.offset(dx, currentY.get(), dz), random);
                    }
                }
            }
        };
    }

    public static LeafPlacer bigSplatDiamond(
            RandomSource random,
            BiConsumer<BlockPos, RandomSource> tryPlaceLeaf,
            BlockPos trunkPos,
            AtomicInteger currentY,
            int innerChance,
            int outerChance
            // doesn't have corners inherently
    ) {
        return () -> {
            for (int dx = -2; dx <= 2; dx++) {
                for (int dz = -2; dz <= 2; dz++) {
                    if (dx == 0 && dz == 0) continue; // skip trunk

                    int manhattan = Math.abs(dx) + Math.abs(dz);
                    if (manhattan > 2) continue; // corners bye

                    int curChance;
                    if (manhattan == 1) {
                        curChance = innerChance;
                    } else if (manhattan == 2) {
                        curChance = outerChance;
                    } else {
                        continue; // should never hit
                    }

                    if (random.nextInt(100) < curChance) {
                        tryPlaceLeaf.accept(trunkPos.offset(dx, currentY.get(), dz), random);
                    }
                }
            }
        };
    }
    public static LeafPlacer bigSplatDistributed(
            RandomSource random,
            BiConsumer<BlockPos, RandomSource> tryPlaceLeaf,
            BlockPos trunkPos,
            AtomicInteger currentY,
            int innerPercent,
            int outerChance,
            boolean corners,
            int cornersChance
    ) {
        return () -> {
            List<BlockPos> innerPositions = new ArrayList<>();

            for (int dx = -2; dx <= 2; dx++) {
                for (int dz = -2; dz <= 2; dz++) {
                    if (dx == 0 && dz == 0) continue; // skip trunk

                    int distance = Math.max(Math.abs(dx), Math.abs(dz));

                    if (distance == 1) {
                        // collecting inner ring cells
                        innerPositions.add(trunkPos.offset(dx, currentY.get(), dz));
                    } else if (distance == 2) {
                        if (Math.abs(dx) == 2 && Math.abs(dz) == 2) {
                            if (corners && random.nextInt(100) < cornersChance) {
                                tryPlaceLeaf.accept(trunkPos.offset(dx, currentY.get(), dz), random);
                            }
                        } else {
                            if (random.nextInt(100) < outerChance) {
                                tryPlaceLeaf.accept(trunkPos.offset(dx, currentY.get(), dz), random);
                            }
                        }
                    }
                }
            }

            // shuffling inner positions
            Collections.shuffle(innerPositions, new Random(random.nextLong()));

            // picking the exact number based on percentage
            int toFill = (int) Math.ceil(innerPositions.size() * (innerPercent / 100.0));

            for (int i = 0; i < toFill; i++) {
                tryPlaceLeaf.accept(innerPositions.get(i), random);
            }
        };
    }

    // TODO: add detached check to everything else
    public static LeafPlacer bigSplatDistributed(
            RandomSource random,
            BiConsumer<BlockPos, RandomSource> tryPlaceLeaf,
            BlockPos trunkPos,
            AtomicInteger currentY,
            int innerPercent,
            int outerChance,
            LevelSimulatedReader level,
            boolean checkDetached
    ) {
        return () -> {
            List<BlockPos> innerPositions = new ArrayList<>();
            List<BlockPos> outerPositions = new ArrayList<>();

            for (int dx = -2; dx <= 2; dx++) {
                for (int dz = -2; dz <= 2; dz++) {
                    if (dx == 0 && dz == 0) continue; // skip trunk

                    int distance = Math.max(Math.abs(dx), Math.abs(dz));

                    if (distance == 1) {
                        // collecting inner ring cells
                        innerPositions.add(trunkPos.offset(dx, currentY.get(), dz));
                    } else if (distance == 2) {
                        if (random.nextInt(100) < outerChance) {
                            outerPositions.add(trunkPos.offset(dx, currentY.get(), dz));
                            tryPlaceLeaf.accept(trunkPos.offset(dx, currentY.get(), dz), random);
                        }
                    }
                }
            }

            // shuffling inner positions
            Collections.shuffle(innerPositions, new Random(random.nextLong()));

            // picking the exact number based on percentage
            int toFill = (int) Math.ceil(innerPositions.size() * (innerPercent / 100.0));

            for (int i = 0; i < toFill; i++) {
                tryPlaceLeaf.accept(innerPositions.get(i), random);
            }

            // checking for detached
            if (checkDetached) {
                for (int i = 0; i < outerPositions.size(); i++) {
                    BlockPos pos = outerPositions.get(i);

                    if (checkIfDetached(level, pos)) {
                        BlockPos cursor = pos;

                        int stepX = Integer.signum(trunkPos.getX() - pos.getX());
                        int stepZ = Integer.signum(trunkPos.getZ() - pos.getZ());

                        int maxSteps = 5;

                        while (checkIfDetached(level, cursor) && maxSteps-- > 0) {
                            cursor = cursor.offset(stepX, 0, stepZ);

                            tryPlaceLeaf.accept(cursor, random);
                        }
                    }
                }
            }
        };
    }
}
