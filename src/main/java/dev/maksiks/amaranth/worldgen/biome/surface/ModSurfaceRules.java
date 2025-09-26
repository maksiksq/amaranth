package dev.maksiks.amaranth.worldgen.biome.surface;

import dev.maksiks.amaranth.worldgen.biome.ModBiomes;
import dev.maksiks.amaranth.worldgen.levelgen.noise.ModNoises;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

import java.util.ArrayList;
import java.util.List;

import static dev.maksiks.amaranth.worldgen.levelgen.noise.ModNoises.SILVER_NOISE;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource RED_TERRACOTTA = makeStateRule(Blocks.RED_TERRACOTTA);
    private static final SurfaceRules.RuleSource SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);
    private static final SurfaceRules.RuleSource YELLOW_TERRACOTTA = makeStateRule(Blocks.YELLOW_TERRACOTTA);
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource POWDER_SNOW = makeStateRule(Blocks.POWDER_SNOW);
    private static final SurfaceRules.RuleSource DIORITE = makeStateRule(Blocks.DIORITE);
    private static final SurfaceRules.RuleSource SNOW = makeStateRule(Blocks.SNOW);
    private static final SurfaceRules.RuleSource LIME_TERRACOTTA = makeStateRule(Blocks.LIME_TERRACOTTA);
    private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource MYCELIUM = makeStateRule(Blocks.MYCELIUM);

    private static SurfaceRules.RuleSource silverLayerRule(int layerY) {
        return SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.SILVER_BIRCH_FOREST),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.ifTrue(
                                SurfaceRules.noiseCondition(SILVER_NOISE, -0.1D, 0.1D),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.yBlockCheck(VerticalAnchor.absolute(layerY), 0),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(layerY + 1), 0)),
                                                YELLOW_TERRACOTTA
                                        )
                                )
                        )
                )
        );
    }

    private static SurfaceRules.RuleSource powderSnowBlobsLayerRule() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.noiseCondition(SILVER_NOISE, -0.1D, 0.1D),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, POWDER_SNOW),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, POWDER_SNOW)
                        )
                )

        );
    }

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurfaceAndStoneBelow = SurfaceRules.sequence(
                // Top block: grass (if above water), else dirt
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK)
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.ifTrue(SurfaceRules.not(isAtOrAboveWaterLevel), DIRT)
                ),

                SurfaceRules.ifTrue(
                        SurfaceRules.UNDER_FLOOR,
                        DIRT
                ),

                STONE
        );
        RandomSource random = RandomSource.create();

        List<SurfaceRules.RuleSource> rules = new ArrayList<>();

        int[] silverLayers = {
                61 + random.nextInt(2),
                64 - random.nextInt(2),
                69 + random.nextInt(2),
                74 - random.nextInt(2),
                78 + random.nextInt(2),
                84 + random.nextInt(3),
                96 + random.nextInt(5),
                102 + random.nextInt(5),
                120 + random.nextInt(5),
                145 + random.nextInt(5)
        };

        for (int layer : silverLayers) {
            rules.add(silverLayerRule(layer));
        }

        // desolate
        rules.add(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.DESOLATE_ICE_FIELDS), powderSnowBlobsLayerRule()));

        rules.add(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.DESOLATE_ICE_FIELDS),
                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel,
                                SNOW_BLOCK)));

        // orderly
        rules.add(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.ORDERLY_COURTS),
                SurfaceRules.ifTrue(isAtOrAboveWaterLevel,
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                SurfaceRules.ifTrue(
                                        SurfaceRules.noiseCondition(ModNoises.STRIPE_ATTEMPT_NOISE, -0.88, 0.05),
                                        LIME_TERRACOTTA
                                )
                        )
                )
        ));
        // orderly ruins
        rules.add(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.ORDERLY_COURTS_RUINS),
                SurfaceRules.ifTrue(isAtOrAboveWaterLevel,
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.noiseCondition(ModNoises.STRIPE_ATTEMPT_NOISE, -0.88, 0.05),
                                                LIME_TERRACOTTA
                                        )
                                )
                        )
                )
        ));

        // shroomlands
        rules.add(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.SHROOMLANDS),
                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel,
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.ON_FLOOR,
                                                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, MYCELIUM)
                                        ),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.ON_FLOOR,
                                                SurfaceRules.ifTrue(SurfaceRules.not(isAtOrAboveWaterLevel), DIRT)
                                        ),

                                        SurfaceRules.ifTrue(
                                                SurfaceRules.UNDER_FLOOR,
                                                DIRT
                                        ),

                                        STONE
                                ))));

        // Default to a grass and dirt surface
        rules.add(SurfaceRules.ifTrue(
                        SurfaceRules.not(SurfaceRules.isBiome(ModBiomes.DESOLATE_ICE_FIELDS)),
                        SurfaceRules.ifTrue(
                                SurfaceRules.not(SurfaceRules.isBiome(ModBiomes.SHROOMLANDS)),
                                grassSurfaceAndStoneBelow
                        )
                )
        );

        return SurfaceRules.sequence(
                rules.toArray(SurfaceRules.RuleSource[]::new)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}