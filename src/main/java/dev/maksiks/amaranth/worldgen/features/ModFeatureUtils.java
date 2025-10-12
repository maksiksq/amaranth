package dev.maksiks.amaranth.worldgen.features;

import dev.maksiks.amaranth.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;

public class ModFeatureUtils {
    public static boolean canPlaceBigThingAt(LevelAccessor level, BlockPos pos) {
        BlockPos surfacePos = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, pos);

        // above bow'lho'volher check
        if (level.getFluidState(surfacePos).is(FluidTags.WATER) ||
                level.getFluidState(surfacePos.above()).is(FluidTags.WATER)) {
            return false;
        }

        // near bow'lho'volher check
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos checkPos = surfacePos.offset(x, 0, z);
                if (level.getFluidState(checkPos).is(FluidTags.WATER)) {
                    return false;
                }
            }
        }

        // sea level and space check

        // .getSeaLevel() is deprecated but i have no idea what to replace it with
        // maybe it's just a solid 63? Either way let it be for now
        int y = surfacePos.getY();
        if (y < level.getSeaLevel() - 10 || y > level.getSeaLevel() + 100) {
            return false;
        }

        // slope check
        int minHeight = Integer.MAX_VALUE;
        int maxHeight = Integer.MIN_VALUE;

        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                BlockPos checkPos = pos.offset(x, 0, z);
                int height = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, checkPos).getY();
                minHeight = Math.min(minHeight, height);
                maxHeight = Math.max(maxHeight, height);
            }
        }

        // (arbitrary)
        if (maxHeight - minHeight > 8) {
            return false;
        }

        // above trees check
        BlockPos surfaceBelowPos = surfacePos.below();
        BlockState groundBlock = level.getBlockState(surfaceBelowPos);
        if (!groundBlock.isFaceSturdy(level, surfaceBelowPos, Direction.UP, SupportType.FULL) || groundBlock.is(BlockTags.LEAVES)) {
            return false;
        }

        // air above check
        for (int i = 1; i <= 10; i++) {
            BlockPos airPos = surfacePos.above(i);
            BlockState state = level.getBlockState(airPos);
            if (!state.isAir() && !state.is(BlockTags.REPLACEABLE)) {
                return false;
            }
        }

        // floatie check
        for (int i = 1; i <= 3; i++) {
            BlockPos belowPos = surfacePos.below(i);
            if (level.getBlockState(belowPos).isAir()) {
                return false;
            }
        }

        return true;
    }

    public static void updateAround(LevelAccessor level, BlockPos pos, Block block) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() != block) return;

        for (Direction dir : Direction.values()) {
            BlockPos adj = pos.relative(dir);
            BlockState adjState = level.getBlockState(adj);

            BlockState newState = state.updateShape(dir, adjState, level, pos, adj);
            if (newState != state) {
                state = newState;
                level.setBlock(pos, state, Block.UPDATE_CLIENTS);
            }

            if (adjState.getBlock() == block) {
                BlockState newAdj = adjState.updateShape(dir.getOpposite(), state, level, adj, pos);
                if (newAdj != adjState) {
                    level.setBlock(adj, newAdj, Block.UPDATE_CLIENTS);
                }
            }
        }
    }

}
