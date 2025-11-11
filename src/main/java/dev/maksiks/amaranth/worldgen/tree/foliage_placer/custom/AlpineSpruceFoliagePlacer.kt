package dev.maksiks.amaranth.worldgen.tree.foliage_placer.custom;

import com.mojang.datafixers.Products.P3
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.mojang.serialization.codecs.RecordCodecBuilder.Instance
import com.mojang.serialization.codecs.RecordCodecBuilder.Mu
import dev.maksiks.amaranth.Amaranth
import dev.maksiks.amaranth.worldgen.tree.LeafPlacerContext
import dev.maksiks.amaranth.worldgen.tree.LeafPlacerContext.Companion.empty
import dev.maksiks.amaranth.worldgen.tree.LeafPlacerContext.Companion.simpleGuaranteed
import dev.maksiks.amaranth.worldgen.tree.LeafPlacerContext.Companion.skipDiagonalSectors
import dev.maksiks.amaranth.worldgen.tree.foliage_placer.ModFoliagePlacerTypes
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType

class AlpineSpruceFoliagePlacer(
    radius: IntProvider,
    offset: IntProvider,
    val height: Int
) : FoliagePlacer(radius, offset) {
    companion object {
        @JvmStatic
        var CODEC: MapCodec<AlpineSpruceFoliagePlacer> =
            RecordCodecBuilder.mapCodec { instance ->
                blobParts(instance).apply(instance, ::AlpineSpruceFoliagePlacer)
            }

        @JvmStatic
        private fun blobParts(instance: Instance<AlpineSpruceFoliagePlacer>): P3<
                Mu<AlpineSpruceFoliagePlacer>,
                IntProvider,
                IntProvider,
                Int
                > {
            return foliagePlacerParts(instance).and(
                Codec.intRange(0, 16).fieldOf("height").forGetter { it.height }
            )
        }
    }

    protected override fun type(): FoliagePlacerType<*> = ModFoliagePlacerTypes.ALPINE_SPRUCE_FOLIAGE_PLACER.get()

    private enum class Variant {
        NORMAL,
        ROUND,
        SMOL
    }

    override fun createFoliage(
        level: LevelSimulatedReader,
        blockSetter: FoliageSetter,
        random: RandomSource,
        config: TreeConfiguration,
        maxFreeTreeHeight: Int,
        attachment: FoliageAttachment,
        foliageHeight: Int,
        foliageRadius: Int,
        offset: Int
    ) {
        val trunkPos = attachment.pos().below()
        val ctx = LeafPlacerContext.ctx(level, blockSetter, random, config, debug = false);

        fun at(height: Int): BlockPos = trunkPos.above(height)
        var curY = 0;
        fun bump() {
            curY += 1
        }

        fun lower() {
            curY -= 1
        }

        val variant = when {
            attachment.radiusOffset() == 2 -> Variant.ROUND
            attachment.radiusOffset() == 1 -> Variant.SMOL
            attachment.radiusOffset() == 0 -> Variant.NORMAL
            else -> Variant.NORMAL
        }

        if (variant == Variant.NORMAL || variant == Variant.ROUND) {
            // ABOVE
            run {
                bump()
                ctx.disc(at(curY), 1)

                bump()
                bump()
                ctx.incDisc(
                    listOf(at(curY - 1)), 100, true,
                    LeafPlacerContext.HorizontalLayer(25, 0, 25)
                )
                ctx.incDisc(
                    listOf(at(curY - 1), at(curY)), 100, true,
                    LeafPlacerContext.HorizontalLayer(100, 25, 25)
                )

                repeat(random.nextInt(2) + 2) {
                    bump()
                    ctx.placeLeaf(at(curY));
                }
            }
            // BELOW
            run {
                curY = 1
                run {
                    val height = when
                                         (maxFreeTreeHeight >= 12
                                && random.nextBoolean()
                                && variant != Variant.ROUND
                    ) {
                        true -> 4
                        false -> 3
                    }
                    repeat(height) {
                        lower()
                        ctx.incDisc(
                            at(curY), 0, true,
                            simpleGuaranteed
                        )
                        ctx.incSquare(
                            at(curY), 0,
                            LeafPlacerContext.HorizontalLayer(
                                25,
                                0,
                                50,
                                null,
                                true,
                                LeafPlacerContext.LayerPattern.X_SHAPE
                            )
                        )
                    }
                }

                lower()
                ctx.disc(at(curY), 1)
                ctx.incSquare(
                    at(curY), 0,
                    LeafPlacerContext.HorizontalLayer(50, 50, 75, null, false, LeafPlacerContext.LayerPattern.X_SHAPE)
                )
                val keepNSOrNW = random.nextBoolean()
                run {
                    val offset = when (random.nextBoolean()) {
                        false -> -2
                        true -> 2
                    }
                    val offsetDir = when (keepNSOrNW) {
                        true -> Direction.NORTH
                        false -> Direction.EAST
                    }
                    ctx.placeLeaf(at(curY).relative(offsetDir, offset))
                }

                lower()
                ctx.disc(at(curY), 1)
                ctx.incSquare(
                    at(curY), 0,
                    LeafPlacerContext.HorizontalLayer(50, 50, 100, null, false, LeafPlacerContext.LayerPattern.X_SHAPE)
                )
                val cardinalAlternatingLayer: LeafPlacerContext.HorizontalLayer = when (keepNSOrNW) {
                    true -> LeafPlacerContext.HorizontalLayer(
                        100, 50, 100, null, true, null,
                        LeafPlacerContext.Sector.E.skip or LeafPlacerContext.Sector.W.skip or skipDiagonalSectors
                    )

                    false -> LeafPlacerContext.HorizontalLayer(
                        100, 50, 100, null, true, null,
                        LeafPlacerContext.Sector.N.skip or LeafPlacerContext.Sector.S.skip or skipDiagonalSectors
                    )
                }
                ctx.incDiamond(at(curY), 0, empty, cardinalAlternatingLayer)

                repeat(2) {
                    lower()
                    val guaranteedCrossLayer =
                        LeafPlacerContext.HorizontalLayer(
                            100,
                            0,
                            100,
                            null,
                            false,
                            LeafPlacerContext.LayerPattern.CROSS
                        );
                    ctx.incDiamond(
                        at(curY), 0,
                        guaranteedCrossLayer,
                        guaranteedCrossLayer
                    )
                    ctx.incSquare(
                        at(curY), 0,
                        LeafPlacerContext.HorizontalLayer(
                            75,
                            75,
                            100,
                            null,
                            false,
                            LeafPlacerContext.LayerPattern.X_SHAPE
                        )
                    )
                }

                lower()
                ctx.incDisc(
                    at(curY), 0, true,
                    *Array(2) { simpleGuaranteed }
                )

                lower()
                if (variant == Variant.NORMAL) {
                    ctx.incDisc(
                        at(curY), 0, true,
                        *Array(2) { simpleGuaranteed },
                        LeafPlacerContext.HorizontalLayer(50, 80, 100)
                    )
                }
                if (variant == Variant.ROUND) {
                    ctx.incDisc(
                        at(curY), 0, true,
                        simpleGuaranteed,
                        LeafPlacerContext.HorizontalLayer(70, 80, 100)
                    )
                }


                lower()
                if (variant == Variant.NORMAL) {
                    ctx.disc(at(curY), 1)
                }
                if (variant == Variant.ROUND && random.nextBoolean()) {
                    ctx.square(at(curY), 1)
                    lower()
                    ctx.disc(at(curY), 1)
                }
            }
        } else {
            // SMOL
            // ABOVE
            run {
                curY = 0

                bump()
                bump()
                ctx.incDisc(
                    listOf(at(curY - 1)), 100, true,
                    LeafPlacerContext.HorizontalLayer(25, 0, 25)
                )
                ctx.incDisc(
                    listOf(at(curY - 1), at(curY)), 100, true,
                    LeafPlacerContext.HorizontalLayer(100, 25, 25)
                )

                repeat(2) {
                    bump()
                    ctx.placeLeaf(at(curY));
                }
            }

            // BELOW
            run {
                curY = 1

                Amaranth.LOGGER.info("Smol variant $maxFreeTreeHeight")

                run {
                    repeat(5) {
                        lower()
                        ctx.incDisc(
                            at(curY), 0, true,
                            simpleGuaranteed
                        )
                        ctx.incSquare(
                            at(curY), 0,
                            LeafPlacerContext.HorizontalLayer(
                                25,
                                0,
                                50,
                                null,
                                true,
                                LeafPlacerContext.LayerPattern.X_SHAPE
                            )
                        )
                    }
                }

                lower()
                ctx.square(at(curY), 1, 0)
                ctx.incDiamond(at(curY), 0, empty, LeafPlacerContext.HorizontalLayer(50, 25, 100))

                lower()
                ctx.incDisc(
                    at(curY), 0, true,
                    simpleGuaranteed,
                    LeafPlacerContext.HorizontalLayer(70, 90, 100)
                )

                lower()
                ctx.disc(at(curY), 1)
            }
        }
    }

    override fun foliageHeight(random: RandomSource, height: Int, config: TreeConfiguration): Int =
        this.height

    override fun shouldSkipLocation(
        random: RandomSource,
        localX: Int,
        localY: Int,
        localZ: Int,
        range: Int,
        large: Boolean
    ): Boolean {
        return localX == range && localZ == range && (random.nextInt(2) == 0 || localY == 0)
    }
}
