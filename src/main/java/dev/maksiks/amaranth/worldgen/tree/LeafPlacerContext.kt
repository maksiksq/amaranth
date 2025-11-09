package dev.maksiks.amaranth.worldgen.tree;

import dev.maksiks.amaranth.Amaranth
import net.minecraft.commands.arguments.coordinates.BlockPosArgument.blockPos
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.LevelWriter
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import java.util.function.Predicate
import kotlin.Boolean
import kotlin.math.abs
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

    val debug = true;

    fun placeLeaf(pos: BlockPos) {
        if (debug) {
            blockSetter.set(pos, Blocks.GREEN_TERRACOTTA.defaultBlockState())
        }
        // cutting out the try part just to separate it tho it does the same thing
        FoliagePlacer.tryPlaceLeaf(level, blockSetter, random, config, pos)
    }

    /**
     * A horizontal layer
     *
     * @param chance chance of spawning a block in this layer, 1-100%.
     * @param guaranteed guaranteed percentage of blocks to spawn in this layer.
     * @param cap maximum percentage of blocks to spawn in this layer.
     * @param centricFactor biases blocks to spawn near the middle/corners.
     * - 1.0 - higher chance towards the middle (axes);
     * - 0.5 normal;
     * - 0.0 higher chance towards the corners (sides).
     * @param connected if true, will ensure that each leaf block in the layer is connected to another
     *  (in any direction) to prevent leaf decay. If it didn't proc, another block will be substituted elsewhere
     *  so the distribution remains the same. Might make the tree slightly more "solid" depending on other settings.
     * @param custom you can pass in your own code to be executed instead of the leaf placing function.
     * Can be useful if you want to add custom conditions or place different blocks.
     *
     * E.g., place a lantern below every other block in horizontal layer 2:
     * TODO: java examples for everything
     * ```
     * LeafPlacerContext.HrLayer(100, custom = { ctx, pos, x, z, dist ->
     *     if (dist == 2 && ctx.random.nextBoolean()) {
     *         ctx.placeLeaf(pos)
     *         ctx.blockSetter.set(pos.below(), Blocks.LANTERN.defaultBlockState())
     *     }
     * })
     * ```
     * If you don't care about the horizontal layer, it's likely better to make a separate for loop instead.
     *
     *  @see incSquare
     *  @see incDiamond
     *  @see incDisc
     * */
    data class HrLayer(
        val chance: Int,
        val guaranteed: Int = 0,
        val centricFactor: Double? = null,
        val connected: Boolean = false,
        val custom: ((LeafPlacerContext, BlockPos, Int, Int, Int) -> Unit)? = null
    )

    data class Candidate(
        val pos: BlockPos,
        val dist: Int,
        val x: Int,
        val z: Int
    )

    private fun checkIfConnected(pos: BlockPos): Boolean {
        for (dir in Direction.entries) {
            if (level.isStateAtPosition(pos.relative(dir), Predicate( BlockTags.LEAVES::equals))) return true
        }
        return false
    }

    private enum class ShapeType {
        SQUARE,
        DIAMOND,
//        DISC
    }

    private fun calculateDist(shapeType: ShapeType, x: Int, z: Int): Int {
        return when (shapeType) {
            ShapeType.SQUARE -> maxOf(abs(x), abs(z))
            ShapeType.DIAMOND -> abs(x) + abs(z)
        }
    }

    /**
     * Internal shape building math, use [incSquare], [incDiamond], [incDisc] instead.
     *
     * @see incSquare
     * @see incDiamond
     * @see incDisc
     * */
    private fun incShape(
        pos: BlockPos,
        centerChance: Int = 100,
        shapeType: ShapeType,
        vararg layers: HrLayer
    ) {
        val radius = layers.size

        val candidates = mutableListOf<Candidate>()
        // skeleton
        for (x in -radius..radius) {
            for (z in -radius..radius) {
                val dist = calculateDist(shapeType, x, z)
                // irrelevant for square
                if (dist > radius) continue

                val placePos = pos.offset(x, 0, z)

                val chance = if (dist == 0) {
                    centerChance
                } else {
                    val layer = layers[dist - 1]
                    if (layer.centricFactor != null) {
                        val gradient = when (shapeType) {
                            ShapeType.SQUARE -> {
                                // 1.0 = center
                                // 0.0 = corners
                                val layerEdgeDist = dist - (abs(x).coerceAtMost(abs(z)))
                                1.0 - (layerEdgeDist.toDouble() / dist)
                            }
                            ShapeType.DIAMOND -> {
                                // 1.0 = axes
                                // 0.0 = sides
                                1.0 - abs(abs(x) - abs(z)).toDouble() / dist
                            }
                        }

                        val factor = layer.centricFactor
                        val bias = (0.5 - factor) * 2.0

                        val weighted = gradient * (1 + bias) + (1 - gradient) * (1 - bias)

                        val modifiedChance = (layer.chance * weighted).coerceIn(0.0, 100.0)
                        modifiedChance.toInt()
                    } else {
                        layer.chance
                    }
                }

                if (debug) {
                    // spawning an armor stand showing the percentage
                    val armorStand = EntityType.ARMOR_STAND.create(level as Level)
                    armorStand?.let {
                        it.setPos(placePos.x + 0.5, placePos.y + 0.5, placePos.z + 0.5)
                        it.isInvisible = true
                        it.isMarker = true
                        it.customName = Component.literal("$chance%")
                        it.isCustomNameVisible = true
                        (level as LevelWriter).addFreshEntity(it)
                    }
                }

                if ((random.nextInt(100) < chance)) {
                    candidates.add(Candidate(placePos, dist, x, z))
                }
            }

            // placing
            for (candidate in candidates) {
                val pos = candidate.pos
                val dist = candidate.dist
                if (dist == 0) {
                    placeLeaf(pos)
                    continue
                }
                val layer = layers[dist - 1]
                if (layer.connected && !checkIfConnected(pos)) continue
                layers[dist - 1].custom?.invoke(this, pos, candidate.x, candidate.z, dist) ?: placeLeaf(pos)
            }
        }
    }

    /**
     *  Makes a square with of a certain radius at a certain position, as shrimple as that.
     *
     *  @param radius radius.
     *  @param pos the position from which placing starts. Usu. above/at/below trunk attachment position.
     *   a chance to place the middle block,
     *   and a chance to place each block applied individually per block,
     *  @param centerChance chance specifically for the center block,
     *   you might want to alternate it quite often, 1-100%.
     *  @param chance chance of spawning each individual block, 1-100%.
     *
     *  See [incSquare] and [incShape] for more details
     *
     *  @see incSquare
     *  @see incShape
     * */
    fun square(
        radius: Int,
        pos: BlockPos,
        centerChance: Int = 100,
        chance: Int = 100
    ) {
        incSquare(pos, centerChance, *Array(radius) { HrLayer(chance) })
    }

    /**
     * Makes a square with incremental chances for each layer.
     *
     * For example (Java):
     * ```
     * ctx.incSquare(pos, 100, new HrLayer(50), new HrLayer(25));
     * ```
     * (Kotlin):
     * ```
     * ctx.incSquare(pos, 100, HrLayer(50), HrLayer(25))
     * ```
     * Would result in:
     *  ```
     *  🟥 🟥 🟥 🟥 🟥 ️
     *  🟥 🟦 🟦 🟦 🟥
     *  🟥 🟦 🟨 🟦 🟥
     *  🟥 🟦 🟦 🟦 🟥
     *  🟥 🟥 🟥 🟥 🟥
     *  ```
     *  Where the chances of spawning a block are:
     *  🟨 - 100%; 🟦 - 50%; 🟥 - 25%;
     *
     *  @param pos the position from which placing starts. Usu. above/below trunk attachment position.
     *  @param centerChance chance specifically for the center block,
     *   separate from [layers] because all other [HrLayer] params don't really apply to it,
     *   and you might want to alternate it quite often.
     *  @param layers an array of [HrLayer], each defines settings for a single incremented outward layer,
     *   and the size of the array defines the radius of the resulting shape.
     *
     *  @see square
     *  @see incShape
     * */
    fun incSquare(pos: BlockPos, centerChance: Int = 100, vararg layers: HrLayer) =
        incShape(pos, centerChance, ShapeType.SQUARE, *layers);

    /**
     *  Makes a diamond with of a certain radius at a certain position, as shrimple as that.
     *
     *  @param radius radius.
     *  @param pos the position from which placing starts. Usu. above/at/below trunk attachment position.
     *   a chance to place the middle block,
     *   and a chance to place each block applied individually per block,
     *  @param centerChance chance specifically for the center block,
     *   you might want to alternate it quite often, 1-100%.
     *  @param chance chance of spawning each individual block, 1-100%.
     *
     *  See [incDiamond] and [incShape] for more details
     *
     *  @see incDiamond
     *  @see incShape
     * */
    fun diamond(
        radius: Int,
        pos: BlockPos,
        centerChance: Int = 100,
        chance: Int = 100
    ) {
        incDiamond(pos, centerChance, *Array(radius) { HrLayer(chance) })
    }

    /**
     * Makes a diamond with incremental chances for each layer.
     *
     * For example (Java):
     * ```
     * ctx.incDiamond(pos, 100, new HrLayer(50), new HrLayer(25));
     * ```
     * (Kotlin):
     * ```
     * ctx.incDiamond(pos, 100, HrLayer(50), HrLayer(25))
     * ```
     * Would result in:
     *  ```
     *  🆓 🆓 🟥 🆓 🆓 ️
     *  🆓 🟥 🟦 🟥 🆓
     *  🟥 🟦 🟨 🟦 🟥
     *  🆓 🟥 🟦 🟥 🆓
     *  🆓 🆓 🟥 🆓 🆓
     *  ```
     *  Where the chances of spawning a block are:
     *  🟨 - 100%; 🟦 - 50%; 🟥 - 25%;
     *
     *  @param pos the position from which placing starts. Usu. above/below trunk attachment position.
     *  @param centerChance chance specifically for the center block,
     *   separate from [layers] because all other [HrLayer] params don't really apply to it,
     *   and you might want to alternate it quite often.
     *  @param layers an array of [HrLayer], each defines settings for a single incremented outward layer,
     *   and the size of the array defines the radius of the resulting shape.
     *
     *  @see diamond
     *  @see incShape
     * */
    fun incDiamond(pos: BlockPos, centerChance: Int = 100, vararg layers: HrLayer) =
        incShape(pos, centerChance, ShapeType.DIAMOND, *layers);

    /**
     *  Makes a disc with a chance to add a middle
     *  and a chance to place for all blocks
     * */
    fun disc(
        radius: Int,
        smooth: Boolean = true,
        trunkPos: BlockPos,
        centerChance: Int = 100,
        chance: Int = 100
    ) {
        incDisc(trunkPos, smooth, centerChance, *IntArray(radius) { chance })
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
     *
     *  @param smooth removes the 1 block off the cardinal sides of the disc,
     *  makes it less like a star and more circular. Usually what you want.
     * */
    fun incDisc(
        trunkPos: BlockPos,
        smooth: Boolean = true,
        centerChance: Int = 100,
        vararg layerChances: Int
    ) {
        val radius = layerChances.size
        val outerCutoff = radius + 0.4

        for (x in -radius..radius) {
            for (z in -radius..radius) {
                val distSq = x * x + z * z
                if (smooth && distSq > radius * radius) continue
                if (!smooth && distSq > outerCutoff * outerCutoff) continue

                // integer distance in layers
                val ld = sqrt((x * x + z * z).toDouble()).toInt()
                val pos = trunkPos.offset(x, 0, z)

                val chance = when (ld) {
                    0 -> centerChance
                    in 1..radius -> layerChances[ld - 1]
                    else -> continue
                }

                if (random.nextInt(100) < chance) {
                    placeLeaf(pos)
                }
            }
        }
    }
}