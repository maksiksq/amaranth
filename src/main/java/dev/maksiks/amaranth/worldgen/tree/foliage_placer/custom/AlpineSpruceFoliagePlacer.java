package dev.maksiks.amaranth.worldgen.tree.foliage_placer.custom;

import com.mojang.datafixers.Products.P3;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.codecs.RecordCodecBuilder.Instance;
import com.mojang.serialization.codecs.RecordCodecBuilder.Mu;
import dev.maksiks.amaranth.worldgen.tree.LeafPlacerContext;
import dev.maksiks.amaranth.worldgen.tree.foliage_placer.ModFoliagePlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class AlpineSpruceFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<AlpineSpruceFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance -> blobParts(instance).apply(instance, AlpineSpruceFoliagePlacer::new));
    protected final int height;

    protected static <P extends AlpineSpruceFoliagePlacer> P3<Mu<P>, IntProvider, IntProvider, Integer> blobParts(Instance<P> instance) {
        return foliagePlacerParts(instance).and(Codec.intRange(0, 16).fieldOf("height").forGetter(p_68412_ -> p_68412_.height));
    }

    public AlpineSpruceFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacerTypes.ALPINE_SPRUCE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(
            LevelSimulatedReader level,
            FoliageSetter blockSetter,
            RandomSource random,
            TreeConfiguration config,
            int maxFreeTreeHeight,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset
    ) {
        BlockPos trunkPos = attachment.pos();
        LeafPlacerContext ctx = LeafPlacerContext.ctx(level, blockSetter, random, config);

//        blockSetter.set(trunkPos.below(), Blocks.COAL_BLOCK.defaultBlockState());

        record Group(int height) {}

        // ABOVE groups
        var above1 = new Group(3); {
            // core
            for (int i = 1; i < height; i++) {
                ctx.placeLeaf(trunkPos.above(i+above1.height));
            }

            ctx.cross(50, true, 50);
        }

        // 2-3 above all
        var lance = new Group(random.nextInt(2) + 2); {
            for (int i = 1; i < height; i++) {
                ctx.placeLeaf(trunkPos.above(i+above1.height));
            }
        }

        // BELOW groups
    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return this.height;
    }

    /**
     * Skips certain positions based on the provided shape, such as rounding corners randomly.
     * The coordinates are passed in as absolute value, and should be within [0, {@code range}].
     */
    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return localX == range && localZ == range && (random.nextInt(2) == 0 || localY == 0);
    }
}
