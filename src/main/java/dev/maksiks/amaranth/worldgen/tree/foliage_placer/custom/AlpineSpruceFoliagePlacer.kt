package dev.maksiks.amaranth.worldgen.tree.foliage_placer.custom;

import com.mojang.datafixers.Products.P3
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.mojang.serialization.codecs.RecordCodecBuilder.Instance
import com.mojang.serialization.codecs.RecordCodecBuilder.Mu
import dev.maksiks.amaranth.worldgen.tree.LeafPlacerContext
import dev.maksiks.amaranth.worldgen.tree.foliage_placer.ModFoliagePlacerTypes
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
        val trunkPos = attachment.pos().below();
        val ctx = LeafPlacerContext.ctx(level, blockSetter, random, config);

        data class Group(val height: Int)

        // ABOVE groups
        val above1 = Group(3).apply {
            // core
            for (i in 1 until height) {
                ctx.placeLeaf(trunkPos.above(i + height))
            }

            ctx.square(2, trunkPos.above(1));
            ctx.incSquare(trunkPos.above(2), 0, 50, 100);
            ctx.incSquare(trunkPos.above(3), 0, 100, 75, 50);
        }

        // 2-3 above all
        val lance = Group(random.nextInt(2) + 2).apply {
            for (i in 1 until height) {
                ctx.placeLeaf(trunkPos.above(i + above1.height))
            }
            ctx.incDiamond(trunkPos.above(height + above1.height + 1), 100, 75, 60, 50, 40, 30, 20, 10, 1)
            ctx.incDiamond(trunkPos.above(height + above1.height + 2), 100, 100)
            ctx.incDiamond(trunkPos.above(height + above1.height + 3), 100, *IntArray(5) {100})
            ctx.incDiamond(trunkPos.above(height + above1.height + 3), 100, *IntArray(7) {50})
            ctx.incDisc(trunkPos.above(height + above1.height + 3), 100, *IntArray(7) {50})
        }

        // BELOW groups
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
