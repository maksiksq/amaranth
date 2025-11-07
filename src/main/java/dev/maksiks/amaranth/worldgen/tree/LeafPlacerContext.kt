package dev.maksiks.amaranth.worldgen.tree;

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;

@JvmField
val diagonals: Array<Direction> = arrayOf(
    Direction.NORTH, Direction.EAST,
    Direction.NORTH, Direction.WEST,
    Direction.SOUTH, Direction.EAST,
    Direction.SOUTH, Direction.WEST
)

class LeafPlacerContext(
    var level: LevelSimulatedReader,
    var blockSetter: FoliagePlacer.FoliageSetter,
    var random: RandomSource,
    var config: TreeConfiguration) {
    companion object {
        @JvmStatic
        fun ctx(
            level: LevelSimulatedReader,
            blockSetter: FoliagePlacer.FoliageSetter,
            random: RandomSource,
            config: TreeConfiguration
        ): LeafPlacerContext {
            return LeafPlacerContext(level, blockSetter, random, config);
        }
    }

    fun interface LeafPlacer {
        fun place();
    }

    fun placeLeaf(pos: BlockPos) {
        // cutting out the try part just to separate it tho it does the same thing
        FoliagePlacer.tryPlaceLeaf(level, blockSetter, random, config, pos);
    }

    /**
     *  Makes a square, as shrimple as that
     * */
    fun square(
        radius: Int,
        trunkPos: BlockPos,
        chance: Int
    ): LeafPlacer {
        return square(100, trunkPos,*IntArray(radius) {100})
    }

    fun square(
        radius: Int,
        trunkPos: BlockPos,
        vararg layerChances: Int
    ): LeafPlacer {
        return LeafPlacer {
            for (x in -radius..radius) {

            }
        }
    }
}
