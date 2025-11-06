package dev.maksiks.amaranth.worldgen.features;

import com.mojang.serialization.Codec;
import dev.maksiks.amaranth.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DesolateSpikeFeature extends Feature<NoneFeatureConfiguration> {

    public DesolateSpikeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        BlockPos pos = ctx.origin();
        RandomSource random = ctx.random();

        int height = 5 + random.nextInt(8);

        double angle = random.nextDouble() * 2 * Math.PI;
        double tiltStrength = 0.3 + random.nextDouble() * 0.7;
        double dxTilt = Math.cos(angle) * tiltStrength;
        double dzTilt = Math.sin(angle) * tiltStrength;

        List<LayerInfo> upwardLayers = generateSpikePart(level, pos, random, height, dxTilt, dzTilt, true);
        List<LayerInfo> downwardLayers = generateSpikePart(level, pos, random, height, dxTilt, dzTilt, false);

        placeWithConnectivity(level, random, upwardLayers, downwardLayers, pos);

        return true;
    }

    private static class LayerInfo {
        final BlockPos center;
        final int radius;
        final int layerIndex;
        final Set<BlockPos> potentialBlocks;

        LayerInfo(BlockPos center, int radius, int layerIndex) {
            this.center = center;
            this.radius = radius;
            this.layerIndex = layerIndex;
            this.potentialBlocks = new HashSet<>();
        }
    }

    private List<LayerInfo> generateSpikePart(WorldGenLevel level, BlockPos centerPos, RandomSource random,
                                              int height, double dxTilt, double dzTilt, boolean upward) {
        List<LayerInfo> layers = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            int radius = Math.max(0, (height - y - 1) / 3);

            int offsetX = (int) Math.round(dxTilt * y);
            int offsetZ = (int) Math.round(dzTilt * y);

            int actualY = upward ? y : -y;
            int actualOffsetX = upward ? offsetX : -offsetX;
            int actualOffsetZ = upward ? offsetZ : -offsetZ;

            BlockPos layerCenter = centerPos.offset(actualOffsetX, actualY, actualOffsetZ);
            LayerInfo layer = new LayerInfo(layerCenter, radius, y);

            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    if (dx * dx + dz * dz <= radius * radius) {
                        BlockPos placePos = layerCenter.offset(dx, 0, dz);
                        if (canPlaceBlockAt(level, placePos)) {
                            layer.potentialBlocks.add(placePos);
                        }
                    }
                }
            }

            if (!layer.potentialBlocks.isEmpty()) {
                layers.add(layer);
            }
        }

        return layers;
    }

    private void placeWithConnectivity(WorldGenLevel level, RandomSource random,
                                       List<LayerInfo> upwardLayers, List<LayerInfo> downwardLayers, BlockPos origin) {
        Set<BlockPos> placedBlocks = new HashSet<>();

        if (canPlaceBlockAt(level, origin)) {
            placedBlocks.add(origin);
        }

        processLayers(upwardLayers, placedBlocks, random, true);

        processLayers(downwardLayers, placedBlocks, random, false);

        for (BlockPos pos : placedBlocks) {
            if (random.nextFloat() < 0.7f) {
                level.setBlock(pos, ModBlocks.SORROW_ICE.get().defaultBlockState(), 2);
            } else {
                level.setBlock(pos, ModBlocks.REMNANT_SORROW_ICE.get().defaultBlockState(), 2);
            }
        }
    }

    private void processLayers(List<LayerInfo> layers, Set<BlockPos> placedBlocks, RandomSource random, boolean upward) {
        for (LayerInfo layer : layers) {
            Set<BlockPos> layerCandidates = new HashSet<>();

            for (BlockPos candidate : layer.potentialBlocks) {
                if (hasAdjacentPlacedBlock(candidate, placedBlocks)) {
                    layerCandidates.add(candidate);
                }
            }

            if (layerCandidates.isEmpty() && !placedBlocks.isEmpty()) {
                BlockPos closest = findClosestToPlaced(layer.potentialBlocks, placedBlocks);
                if (closest != null) {
                    layerCandidates.add(closest);
                }
            }

            if (layer.radius > 0) {
                for (BlockPos candidate : layer.potentialBlocks) {
                    if (!layerCandidates.contains(candidate) && hasAdjacentInSet(candidate, layerCandidates)) {
                        // Use distance-based probability - closer to tip = less likely to place
                        float distanceFactor = (float) layer.layerIndex / layers.size();
                        float placeProbability = layer.radius > 1 ? 0.6f - (distanceFactor * 0.4f) : 0.3f - (distanceFactor * 0.2f);

                        if (random.nextFloat() < placeProbability) {
                            layerCandidates.add(candidate);
                        }
                    }
                }
            }

            placedBlocks.addAll(layerCandidates);
        }
    }

    private boolean hasAdjacentPlacedBlock(BlockPos pos, Set<BlockPos> placedBlocks) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (dx == 0 && dy == 0 && dz == 0) continue;
                    if (placedBlocks.contains(pos.offset(dx, dy, dz))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean hasAdjacentInSet(BlockPos pos, Set<BlockPos> blockSet) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (dx == 0 && dy == 0 && dz == 0) continue;
                    if (blockSet.contains(pos.offset(dx, dy, dz))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private BlockPos findClosestToPlaced(Set<BlockPos> candidates, Set<BlockPos> placedBlocks) {
        BlockPos closest = null;
        double minDistance = Double.MAX_VALUE;

        for (BlockPos candidate : candidates) {
            for (BlockPos placed : placedBlocks) {
                double distance = candidate.distSqr(placed);
                if (distance < minDistance) {
                    minDistance = distance;
                    closest = candidate;
                }
            }
        }

        return closest;
    }

    private boolean canPlaceBlockAt(WorldGenLevel level, BlockPos pos) {
        if (pos.getY() < level.getMinBuildHeight()) {
            return false;
        }
        if (pos.getY() >= level.getMaxBuildHeight()) {
            return false;
        }

        BlockState existingBlock = level.getBlockState(pos);
        Block block = existingBlock.getBlock();

        // not replacing the ghost-post structure bits
        if (block == Blocks.SPRUCE_FENCE || block == Blocks.SPRUCE_HANGING_SIGN || block == Blocks.BARREL) {
            return false;
        }

        return true;
    }

}