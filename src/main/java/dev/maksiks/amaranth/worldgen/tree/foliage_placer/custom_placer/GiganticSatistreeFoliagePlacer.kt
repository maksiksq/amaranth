package dev.maksiks.amaranth.worldgen.tree.foliage_placer.custom_placer;

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

class GiganticSatistreeFoliagePlacer(
    radius: IntProvider,
    offset: IntProvider,
    val height: Int
) : FoliagePlacer(radius, offset) {
    companion object {
        @JvmStatic
        var CODEC: MapCodec<GiganticSatistreeFoliagePlacer> =
            RecordCodecBuilder.mapCodec { instance ->
                blobParts(instance).apply(instance, ::GiganticSatistreeFoliagePlacer)
            }

        @JvmStatic
        private fun blobParts(instance: Instance<GiganticSatistreeFoliagePlacer>): P3<
                Mu<GiganticSatistreeFoliagePlacer>,
                IntProvider,
                IntProvider,
                Int
                > {
            return foliagePlacerParts(instance).and(
                Codec.intRange(0, 16).fieldOf("height").forGetter { it.height }
            )
        }
    }

    protected override fun type(): FoliagePlacerType<*> = ModFoliagePlacerTypes.GIGANTIC_SATISTREE_FOLIAGE_PLACER.get()

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
