package dev.maksiks.amaranth.worldgen.tree.foliage_placer.custom_placer;

import com.mojang.datafixers.Products.P3
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.mojang.serialization.codecs.RecordCodecBuilder.Instance
import com.mojang.serialization.codecs.RecordCodecBuilder.Mu
import dev.maksiks.amaranth.worldgen.tree.foliage_placer.ModFoliagePlacerTypes
import dev.maksiks.twigonometry.api.LeafPlacerContext
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType

class AlienFencePlantFoliagePlacer(
    radius: IntProvider,
    offset: IntProvider,
    val height: Int
) : FoliagePlacer(radius, offset) {
    companion object {
        @JvmStatic
        var CODEC: MapCodec<AlienFencePlantFoliagePlacer> =
            RecordCodecBuilder.mapCodec { instance ->
                blobParts(instance).apply(instance, ::AlienFencePlantFoliagePlacer)
            }

        @JvmStatic
        private fun blobParts(instance: Instance<AlienFencePlantFoliagePlacer>): P3<
                Mu<AlienFencePlantFoliagePlacer>,
                IntProvider,
                IntProvider,
                Int
                > {
            return foliagePlacerParts(instance).and(
                Codec.intRange(0, 16).fieldOf("height").forGetter { it.height }
            )
        }
    }

    protected override fun type(): FoliagePlacerType<*> = ModFoliagePlacerTypes.ALIEN_FENCE_PLANT_FOLIAGE_PLACER.get()

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

        bump()
        // low thingie
        if (random.nextInt(4) <= 2) {
            ctx.incDiamond(
                at(curY), 0,
                LeafPlacerContext.HorizontalLayer(95, 75, 100)
            )
            bump()
            ctx.placeLeaf(at(curY))
            return;
        }
        // high thingie
        ctx.disc(at(curY), 1, 0)
        bump()
        ctx.placeLeaf(at(curY))
        ctx.incDiamond(
            at(curY), 0,
            LeafPlacerContext.HorizontalLayer(100, 25, 25)
        )
        bump()
        ctx.placeLeaf(at(curY))
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
