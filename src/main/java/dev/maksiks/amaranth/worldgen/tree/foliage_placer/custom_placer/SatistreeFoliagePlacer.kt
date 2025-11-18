package dev.maksiks.amaranth.worldgen.tree.foliage_placer.custom_placer;

import com.google.common.collect.ImmutableList
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
import dev.maksiks.amaranth.worldgen.tree.foliage_placer.custom_placer.AlpineSpruceFoliagePlacer.Variant
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType

class SatistreeFoliagePlacer(
    radius: IntProvider,
    offset: IntProvider,
    val height: Int
) : FoliagePlacer(radius, offset) {
    companion object {
        @JvmStatic
        var CODEC: MapCodec<SatistreeFoliagePlacer> =
            RecordCodecBuilder.mapCodec { instance ->
                blobParts(instance).apply(instance, ::SatistreeFoliagePlacer)
            }

        @JvmStatic
        private fun blobParts(instance: Instance<SatistreeFoliagePlacer>): P3<
                Mu<SatistreeFoliagePlacer>,
                IntProvider,
                IntProvider,
                Int
                > {
            return foliagePlacerParts(instance).and(
                Codec.intRange(0, 16).fieldOf("height").forGetter { it.height }
            )
        }
    }

    private enum class Variant {
        TIP,
        HIGH_BRANCH,
        LOW_BRANCH
    }

    protected override fun type(): FoliagePlacerType<*> = ModFoliagePlacerTypes.SATISTREE_FOLIAGE_PLACER.get()

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
            attachment.radiusOffset() == 2 -> Variant.HIGH_BRANCH
            attachment.radiusOffset() == 1 -> Variant.LOW_BRANCH
            attachment.radiusOffset() == 0 -> Variant.TIP
            else -> Variant.TIP
        }

        if (variant == Variant.TIP) {
            curY = -4;
            bump()
            ctx.incDiamond(at(curY), 0,
                LeafPlacerContext.HorizontalLayer(25, 50, 50))
            for(i in 1..3) {
                bump()
                ctx.diamond(at(curY), 1)
                if (i==1) {
                    ctx.incSquare(
                        ImmutableList.of(at(curY), at(curY+1)), 0,
                        LeafPlacerContext.HorizontalLayer(25, 25, 25, null, false, LeafPlacerContext.LayerPattern.X_SHAPE)
                    )
                    val cornerHeight = when (random.nextBoolean()) {
                        true -> 3
                        false -> 2
                    }
                    ctx.incSquare(
                        List(cornerHeight) { at(curY + it) }, 0,
                        LeafPlacerContext.HorizontalLayer(25, 25, 25, null, false, LeafPlacerContext.LayerPattern.X_SHAPE)
                    )
                }
            }
            bump()
            ctx.incDiamond(at(curY), 100,
                LeafPlacerContext.HorizontalLayer(25, 50, 50))
            bump()
            ctx.placeLeaf(at(curY))
        }
        if (variant == Variant.HIGH_BRANCH) {
            curY = 0;
            bump()
            ctx.diamond(at(curY), 1, 0)
            bump()
            ctx.diamond(at(curY), 1, 0)
            ctx.incSquare(at(curY), 0,
                LeafPlacerContext.HorizontalLayer(15, 0, 25, null, false, LeafPlacerContext.LayerPattern.X_SHAPE)
            )
            bump()
            ctx.diamond(at(curY), 1, 100)
            ctx.incSquare(at(curY), 0,
                LeafPlacerContext.HorizontalLayer(2, 0, 25, null, false, LeafPlacerContext.LayerPattern.X_SHAPE)
            )
            bump()
            ctx.placeLeaf(at(curY))
        }
        if (variant == Variant.LOW_BRANCH) {
            curY = 1;
            bump()
            ctx.diamond(at(curY), 1, 0)
            bump()
            ctx.diamond(at(curY), 1, 100)
            ctx.incSquare(at(curY), 0,
                LeafPlacerContext.HorizontalLayer(15, 0, 25, null, false, LeafPlacerContext.LayerPattern.X_SHAPE)
            )
            bump()
            ctx.placeLeaf(at(curY))
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
