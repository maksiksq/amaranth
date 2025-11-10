package dev.maksiks.amaranth.worldgen.tree.foliage_placer.custom;

import com.mojang.datafixers.Products.P3
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.mojang.serialization.codecs.RecordCodecBuilder.Instance
import com.mojang.serialization.codecs.RecordCodecBuilder.Mu
import dev.maksiks.amaranth.worldgen.tree.LeafPlacerContext
import dev.maksiks.amaranth.worldgen.tree.ReferenceFoliage
import dev.maksiks.amaranth.worldgen.tree.foliage_placer.ModFoliagePlacerTypes
import net.minecraft.util.RandomSource
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.block.Blocks
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
        val ctx = LeafPlacerContext.ctx(level, blockSetter, random, config, debug = false);

        data class Group(val height: Int)

        // ABOVE groups
        val above1 = Group(3).apply {
            // core
            for (i in 1 until height) {
                ctx.placeLeaf(trunkPos.above(i + height))
            }

            ctx.square(2, trunkPos.above(1))
            val layers1 = arrayOf(
                LeafPlacerContext.HorizontalLayer(100, 30, removeIfDecays = true),
                LeafPlacerContext.HorizontalLayer(100, custom = { ctx, pos, x, z, dist ->
                    if (dist == 2 && ctx.random.nextBoolean()) {
                        ctx.placeLeaf(pos)
                        ctx.blockSetter.set(pos.below(), Blocks.LANTERN.defaultBlockState())
                    }
                }),
                LeafPlacerContext.HorizontalLayer(50, 30, removeIfDecays = true),
                LeafPlacerContext.HorizontalLayer(30, 30, removeIfDecays = true),
                LeafPlacerContext.HorizontalLayer(50, 0, 100, 0.9, removeIfDecays = true),
                LeafPlacerContext.HorizontalLayer(50, 0, 100, 0.8, removeIfDecays = true),
                LeafPlacerContext.HorizontalLayer(50, 0, 100, 0.7, removeIfDecays = true),
                LeafPlacerContext.HorizontalLayer(50, 0, 100, 0.6, removeIfDecays = true),
                LeafPlacerContext.HorizontalLayer(50, 0, 100, 0.5, removeIfDecays = true),
                LeafPlacerContext.HorizontalLayer(50, 0, 100, 0.4, removeIfDecays = true),
                LeafPlacerContext.HorizontalLayer(50, 0, 100, 0.3, removeIfDecays = true),
                LeafPlacerContext.HorizontalLayer(50, 0, 100, 0.2, removeIfDecays = true),
                LeafPlacerContext.HorizontalLayer(50, 0, 100, 0.1, removeIfDecays = true),
            )
            ctx.incSquare(trunkPos.above(2), 100, *layers1)
//            val layers = arrayOf(
//                LeafPlacerContext.HorizontalLayer(100),
//                LeafPlacerContext.HorizontalLayer(50),
//                LeafPlacerContext.HorizontalLayer(25),
//            )
//            ctx.incSquare(trunkPos.above(2), 100, *layers)

//            ctx.incSquare(trunkPos.above(2), 100, 50, 100);
//            ctx.incSquare(trunkPos.above(3), 100, 100, 75, 50);
        }

        // 2-3 above all
        val lance = Group(random.nextInt(2) + 2).apply {
            for (i in 1 until height) {
                ctx.placeLeaf(trunkPos.above(i + above1.height))
            }
            ctx.incDisc(trunkPos.above(height + above1.height + 5), false, 100, LeafPlacerContext.HorizontalLayer(100, 30), LeafPlacerContext.HorizontalLayer(100, 30), LeafPlacerContext.HorizontalLayer(100, 30), LeafPlacerContext.HorizontalLayer(100, 30), LeafPlacerContext.HorizontalLayer(100, 30), LeafPlacerContext.HorizontalLayer(100, 30), LeafPlacerContext.HorizontalLayer(100, 30), LeafPlacerContext.HorizontalLayer(100, 30), LeafPlacerContext.HorizontalLayer(100, 30))
            ctx.incDisc(trunkPos.above(height + above1.height + 6), true, 100, LeafPlacerContext.HorizontalLayer(100, 30), LeafPlacerContext.HorizontalLayer(100, 30), LeafPlacerContext.HorizontalLayer(100, 30), LeafPlacerContext.HorizontalLayer(100, 30), LeafPlacerContext.HorizontalLayer(100, 30), LeafPlacerContext.HorizontalLayer(100, 30), LeafPlacerContext.HorizontalLayer(100, 30), LeafPlacerContext.HorizontalLayer(100, 30), LeafPlacerContext.HorizontalLayer(100, 30))

            ctx.incDiamond(trunkPos.above(height + above1.height + 8), 100, LeafPlacerContext.HorizontalLayer(100, 30, removeIfDecays = true), LeafPlacerContext.HorizontalLayer(100, 30, removeIfDecays = true), LeafPlacerContext.HorizontalLayer(100, 30, removeIfDecays = true), LeafPlacerContext.HorizontalLayer(50, 30, removeIfDecays = true), LeafPlacerContext.HorizontalLayer(100, 30, removeIfDecays = true))
            ctx.incDiamond(trunkPos.below(1), 100, LeafPlacerContext.HorizontalLayer(100, 30, removeIfDecays = true, centricFactor = 0.9), LeafPlacerContext.HorizontalLayer(100, 30, removeIfDecays = true, centricFactor = 0.9), LeafPlacerContext.HorizontalLayer(70, 30, removeIfDecays = true, centricFactor = 0.9), LeafPlacerContext.HorizontalLayer(50, 60, removeIfDecays = true, centricFactor = 0.9), LeafPlacerContext.HorizontalLayer(30, 30, removeIfDecays = true, centricFactor = 0.9) )
            ctx.incDiamond(trunkPos.above(height + above1.height + 13), 100, LeafPlacerContext.HorizontalLayer(100, 30, removeIfDecays = true, centricFactor = 0.1), LeafPlacerContext.HorizontalLayer(100, 30, removeIfDecays = true, centricFactor = 0.1), LeafPlacerContext.HorizontalLayer(70, 30, removeIfDecays = true, centricFactor = 0.1), LeafPlacerContext.HorizontalLayer(50, 60, removeIfDecays = true, centricFactor = 0.1), LeafPlacerContext.HorizontalLayer(30, 30, removeIfDecays = true, centricFactor = 0.1) )
            ctx.incDisc(trunkPos.above(height + above1.height + 14), true, 100, LeafPlacerContext.HorizontalLayer(100), LeafPlacerContext.HorizontalLayer(100), LeafPlacerContext.HorizontalLayer(100), LeafPlacerContext.HorizontalLayer(100), LeafPlacerContext.HorizontalLayer(100))
            ctx.incDisc(trunkPos.above(height + above1.height + 15), false, 100, LeafPlacerContext.HorizontalLayer(100), LeafPlacerContext.HorizontalLayer(100), LeafPlacerContext.HorizontalLayer(100), LeafPlacerContext.HorizontalLayer(100), LeafPlacerContext.HorizontalLayer(100))
            ctx.incDisc(trunkPos.above(height + above1.height + 16), true, 100, LeafPlacerContext.HorizontalLayer(100), LeafPlacerContext.HorizontalLayer(100))
            ctx.incDisc(trunkPos.above(height + above1.height + 17), false, 100, LeafPlacerContext.HorizontalLayer(100), LeafPlacerContext.HorizontalLayer(100))
            ctx.incDisc(trunkPos.above(height + above1.height + 18), true, 100, LeafPlacerContext.HorizontalLayer(100), LeafPlacerContext.HorizontalLayer(100), LeafPlacerContext.HorizontalLayer(100))
            ctx.incDisc(trunkPos.above(height + above1.height + 19), false, 100, LeafPlacerContext.HorizontalLayer(100), LeafPlacerContext.HorizontalLayer(100), LeafPlacerContext.HorizontalLayer(100))

            val layers1 = arrayOf(
                LeafPlacerContext.HorizontalLayer(100, 30, removeIfDecays = false),
                LeafPlacerContext.HorizontalLayer(50, 30, removeIfDecays = false),
                LeafPlacerContext.HorizontalLayer(30, 30, removeIfDecays = false),
                LeafPlacerContext.HorizontalLayer(0, 0, 100, 0.9, removeIfDecays = false),
                LeafPlacerContext.HorizontalLayer(50, 0, 100, 0.8, removeIfDecays = false),
                LeafPlacerContext.HorizontalLayer(0, 0, 100, 0.7, removeIfDecays = false),
                LeafPlacerContext.HorizontalLayer(50, 0, 100, 0.6, removeIfDecays = false),
                LeafPlacerContext.HorizontalLayer(0, 0, 100, 0.5, removeIfDecays = false),
                LeafPlacerContext.HorizontalLayer(50, 0, 100, 0.4, removeIfDecays = false),
                LeafPlacerContext.HorizontalLayer(0, 0, 100, 0.3, removeIfDecays = false),
                LeafPlacerContext.HorizontalLayer(50, 0, 100, 0.2, removeIfDecays = false),
                LeafPlacerContext.HorizontalLayer(0, 0, 100, 0.1, removeIfDecays = false),
                LeafPlacerContext.HorizontalLayer(100, skipSector = LeafPlacerContext.Sector.N.skip or LeafPlacerContext.Sector.E.skip),
            )
            ctx.incDisc(trunkPos.above(height + above1.height + 20), true, 100, *layers1)


            val layers2 = arrayOf(
                LeafPlacerContext.HorizontalLayer(10, 90, 100),
                LeafPlacerContext.HorizontalLayer(10, 90, 100),
                LeafPlacerContext.HorizontalLayer(10, 90, 100),
                LeafPlacerContext.HorizontalLayer(10, 90, 100),
                LeafPlacerContext.HorizontalLayer(10, 90, 100),
                LeafPlacerContext.HorizontalLayer(90, 0, 40),
                LeafPlacerContext.HorizontalLayer(90, 0, 40),
                LeafPlacerContext.HorizontalLayer(90, 0, 40),
            )
            ctx.incDisc(trunkPos.above(height + above1.height + 24), true, 100, *layers2)


            val layers3 = arrayOf(
                LeafPlacerContext.HorizontalLayer(100, skipSector = LeafPlacerContext.Sector.N.skip or LeafPlacerContext.Sector.E.skip),
                LeafPlacerContext.HorizontalLayer(100, skipSector = LeafPlacerContext.Sector.N.skip or LeafPlacerContext.Sector.E.skip),
                LeafPlacerContext.HorizontalLayer(100, skipSector = LeafPlacerContext.Sector.N.skip or LeafPlacerContext.Sector.E.skip),
                LeafPlacerContext.HorizontalLayer(100, skipSector = LeafPlacerContext.Sector.N.skip or LeafPlacerContext.Sector.E.skip),
                LeafPlacerContext.HorizontalLayer(100, skipSector = LeafPlacerContext.Sector.N.skip or LeafPlacerContext.Sector.E.skip),
                LeafPlacerContext.HorizontalLayer(100, skipSector = LeafPlacerContext.Sector.N.skip or LeafPlacerContext.Sector.E.skip),
                LeafPlacerContext.HorizontalLayer(100, skipSector = LeafPlacerContext.Sector.N.skip or LeafPlacerContext.Sector.E.skip),
                LeafPlacerContext.HorizontalLayer(100, skipSector = LeafPlacerContext.Sector.N.skip or LeafPlacerContext.Sector.E.skip),
                LeafPlacerContext.HorizontalLayer(100, skipSector = LeafPlacerContext.Sector.N.skip or LeafPlacerContext.Sector.E.skip),
                LeafPlacerContext.HorizontalLayer(100, skipSector = LeafPlacerContext.Sector.N.skip or LeafPlacerContext.Sector.E.skip),
                LeafPlacerContext.HorizontalLayer(100, skipSector = LeafPlacerContext.Sector.N.skip or LeafPlacerContext.Sector.E.skip),
            )

            val layers4 = Array(6) {
                LeafPlacerContext.HorizontalLayer(100, pattern = LeafPlacerContext.LayerPattern.CROSS)
            }
            val layers5 = Array(6) {
                LeafPlacerContext.HorizontalLayer(100, pattern = LeafPlacerContext.LayerPattern.DIAGONALS)
            }
            ctx.incSquare(trunkPos.above(height + above1.height + 32),  100, *layers4)
            ctx.incSquare(trunkPos.above(height + above1.height + 34),  100, *layers5)
            ctx.incSquare(trunkPos.above(height + above1.height + 36),  100, *layers3)

            ReferenceFoliage.createReferenceFoliage(ctx, trunkPos.above(height + above1.height + 40), false)
            // BELOW groups
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
