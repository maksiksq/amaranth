package dev.maksiks.amaranth.worldgen.tree;

import net.minecraft.core.BlockPos;

import java.util.List;

public class TreeUtils {
    /**
     * checks if two positions are adjacent within 1 block in any direction, including diagonals
     */
    public static boolean isAdjacent(BlockPos pos1, BlockPos pos2) {
        if (pos1.equals(pos2)) {
            return false;
        }

        int deltaX = Math.abs(pos1.getX() - pos2.getX());
        int deltaY = Math.abs(pos1.getY() - pos2.getY());
        int deltaZ = Math.abs(pos1.getZ() - pos2.getZ());

        return deltaX <= 1 && deltaY <= 1 && deltaZ <= 1;
    }

    public static boolean isAdjacentToAny(BlockPos pos, List<BlockPos> positions) {
        for (BlockPos adjacent : positions) {
            if (isAdjacent(pos, adjacent)) {
                return true;
            }
        }
        return false;
    }
}
