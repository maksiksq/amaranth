package dev.maksiks.amaranth.worldgen.tree;

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.sqrt

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
    var config: TreeConfiguration
) {
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

    fun placeLeaf(pos: BlockPos) {
        // cutting out the try part just to separate it tho it does the same thing
        FoliagePlacer.tryPlaceLeaf(level, blockSetter, random, config, pos);
    }

    /**
     *  Makes a square with a chance to add a middle
     *  and a chance to place for all blocks, as shrimple as that
     * */
    fun square(
        radius: Int,
        trunkPos: BlockPos,
        centerChance: Int = 100,
        chance: Int = 100
    ) {
        incSquare(trunkPos, centerChance, *IntArray(radius) { chance })
    }

    /**
     *  Makes a square with incremental chances for each layer.
     *  In Java chances may be passed as an array.
     *
     *  For example, incSquare(pos, 100, 50, 25) would result in:
     *  🟥🟪🟪🟪🟥
     *  🟪🟦🟦🟦🟪
     *  🟪🟦🟨🟦🟪
     *  🟪🟦🟦🟦🟪
     *  🟥🟪🟪🟪🟥
     *  Where the chances of spawning a block are as follows:
     *  🟨 - 100%; 🟦 - 50%; 🟥 - 25;
     * */
    fun incSquare(
        trunkPos: BlockPos,
        centerChance: Int = 100,
        vararg layerChances: Int
    ) {
        val radius = layerChances.size

        for (x in -radius..radius) {
            for (z in -radius..radius) {
                val distance = maxOf(abs(x), abs(z))
                val pos = trunkPos.offset(x, 0, z);

                val chance =
                    if (distance == 0) centerChance
                    else layerChances[distance - 1]

                if (random.nextInt(100) < chance) {
                    placeLeaf(pos)
                }
            }
        }
    }

    /**
     *  Makes a diamond with a chance to add a middle
     *  and a chance to place for all blocks
     * */
    @JvmOverloads
    fun diamond(
        radius: Int,
        trunkPos: BlockPos,
        centerChance: Int = 100,
        chance: Int = 100
    ) {
        incDiamond(trunkPos, centerChance, *IntArray(radius) { chance })
    }


    /**
     *  Makes a diamond with incremental chances for each layer.
     *  In Java chances may be passed as an array.
     *
     *  For example, incDiamond(pos, 100, 50, 25) would result in:
     *  ☐☐🟪☐☐
     *  ☐🟪🟦🟪☐
     *  🟪🟦🟨🟦🟪
     *  ☐🟪🟦🟪☐
     *  ☐☐🟪☐☐
     *  Where the chances of spawning a block are as follows:
     *  🟨 - 100%; 🟦 - 50%; 🟥 - 25;
     * */
    fun incDiamond(
        trunkPos: BlockPos,
        centerChance: Int = 100,
        vararg layerChances: Int
    ) {
        val radius = layerChances.size

        for (x in -radius..radius) {
            for (z in -radius..radius) {
                val distance = abs(x) + abs(z)
                val pos = trunkPos.offset(x, 0, z)
                if (distance > radius) continue

                val chance =
                    if (distance == 0) centerChance
                    else layerChances[distance - 1]

                if (random.nextInt(100) < chance) {
                    placeLeaf(pos)
                }
            }
        }
    }


    /**
     *  Makes a disc with a chance to add a middle
     *  and a chance to place for all blocks
     * */
    fun disc(
        radius: Int,
        trunkPos: BlockPos,
        centerChance: Int = 100,
        chance: Int = 100
    ) {
        incDisc(trunkPos, centerChance, *IntArray(radius) { chance })
    }


    /**
     *  Makes a disc with incremental chances for each layer.
     *  In Java chances may be passed as an array.
     *
     *  For example, incDisc(pos, 100, 50, 25) would result in:
     *  ☐🟪🟪🟪☐
     *  🟪🟦🟦🟦🟪
     *  🟪🟦🟨🟦🟪
     *  🟪🟦🟦🟦🟪
     *  ☐🟪🟪🟪☐
     *  Where the chances of spawning a block are as follows:
     *  🟨 - 100%; 🟦 - 50%; 🟥 - 25;
     * */
    fun incDisc(
        trunkPos: BlockPos,
        centerChance: Int = 100,
        vararg layerChances: Int
    ) {
        val radius = layerChances.size

        for (x in -radius..radius) {
            for (z in -radius..radius) {
                val distanceSq = x * x + z * z
                if (distanceSq > radius * radius) continue

                // integer distance in layers
                val r = sqrt(distanceSq.toDouble()).toInt()
                val pos = trunkPos.offset(x, 0, z)

                val chance = when (r) {
                    0 -> centerChance
                    in 1..radius -> layerChances[r - 1]
                    else -> continue
                }

                if (random.nextInt(100) < chance) {
                    placeLeaf(pos)
                }
            }
        }
    }
}
