package dev.maksiks.amaranth.worldgen.biome.terrain

import dev.maksiks.amaranth.worldgen.biome.ModBiomes
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.server.level.WorldGenRegion
import net.minecraft.util.Mth
import net.minecraft.world.level.ChunkPos
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.chunk.ChunkAccess
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.core.registries.Registries
import java.util.function.Function
import kotlin.math.min
import kotlin.math.round
import kotlin.math.sqrt

/**
/ note: it do be weird sometimes but this has gone through way too many iterations
/ my brain is too fried at this point
**/
class SteppedSpringsTerrain {
    companion object {
        private const val BLEND_RADIUS = 3
        private const val LAYER_HEIGHT = 5
        private const val LAYER_OFFSET = 0

        @JvmStatic
        fun processSteppedSpringsTerrain(
            biomeGetter: Function<BlockPos, Holder<Biome>>,
            chunk: ChunkAccess,
            region: WorldGenRegion
        ) {
            val biomeRegistry = region.registryAccess().registryOrThrow(Registries.BIOME)
            val biomeHolder = biomeRegistry.getHolderOrThrow(ModBiomes.STEPPED_SPRINGS)

            val chunkPos: ChunkPos = chunk.pos
            val worldBaseX = chunkPos.x shl 4
            val worldBaseZ = chunkPos.z shl 4
            val blockStateFill = Blocks.STONE.defaultBlockState()
            val airState = Blocks.AIR.defaultBlockState()

            for (localX in 0..15) {
                for (localZ in 0..15) {
                    val worldX = worldBaseX + localX
                    val worldZ = worldBaseZ + localZ

                    val surfY = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, localX, localZ)
                    val centerPos = BlockPos(worldX, surfY, worldZ)
                    val blend = calculateBlendFactor(biomeGetter, centerPos, BLEND_RADIUS, biomeHolder)

                    if (blend <= 0.05) continue

                    val quantizedYDouble = round((surfY - LAYER_OFFSET).toDouble() / LAYER_HEIGHT.toDouble()) * LAYER_HEIGHT.toDouble() + LAYER_OFFSET.toDouble()
                    val quantizedY = quantizedYDouble.toInt()
                    val steppedY = quantizedY.coerceIn(surfY - 4, surfY + 4).coerceAtMost(255)

                    val finalTop = Mth.lerp(blend.toFloat(), surfY.toFloat(), steppedY.toFloat()).toInt().coerceIn(0, 255)

                    if (finalTop > surfY) {
                        for (y in (surfY + 1)..finalTop) {
                            val pos = BlockPos(worldX, y, worldZ)
                            chunk.setBlockState(pos, blockStateFill, false)
                        }
                    } else if (finalTop < surfY) {
                        for (y in (finalTop + 1)..surfY) {
                            val pos = BlockPos(worldX, y, worldZ)
                            chunk.setBlockState(pos, airState, false)
                        }
                    }
                    val topPos = BlockPos(worldX, finalTop, worldZ)
                    chunk.setBlockState(topPos, blockStateFill, false)

                    if (finalTop < surfY) {
                        val tidyTop = min(255, finalTop + 1)
                        val tidyPos = BlockPos(worldX, tidyTop, worldZ)
                        if (!chunk.getBlockState(tidyPos).isAir) {
                            chunk.setBlockState(tidyPos, airState, false)
                        }
                    }
                }
            }
        }

        private fun calculateBlendFactor(
            biomeGetter: Function<BlockPos, Holder<Biome>>,
            center: BlockPos,
            radius: Int,
            targetBiome: Holder<Biome>
        ): Double {
            val centerX = center.x
            val centerZ = center.z
            val centerY = center.y

            var minDistanceToNonTarget = radius.toDouble()

            for (xOff in -radius..radius) {
                for (zOff in -radius..radius) {
                    val samplePos = BlockPos(centerX + xOff, centerY, centerZ + zOff)
                    val sampled = biomeGetter.apply(samplePos)
                    if (!sampled.`is`(targetBiome)) {
                        val dist = sqrt((xOff * xOff + zOff * zOff).toDouble())
                        if (dist < minDistanceToNonTarget) minDistanceToNonTarget = dist
                    }
                }
            }

            val normalized = Mth.clamp(minDistanceToNonTarget / radius.toDouble(), 0.0, 1.0)
            return smoothstep(normalized)
        }

        private fun smoothstep(x: Double): Double {
            return x * x * (3.0 - 2.0 * x)
        }
    }
}
