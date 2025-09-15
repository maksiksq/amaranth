package dev.maksiks.amaranth.worldgen.biome.surface;

import dev.maksiks.amaranth.worldgen.biome.ModBiomes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

import static dev.maksiks.amaranth.worldgen.levelgen.noise.ModNoises.SILVER_NOISE;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource RED_TERRACOTTA = makeStateRule(Blocks.RED_TERRACOTTA);
    private static final SurfaceRules.RuleSource YELLOW_TERRACOTTA = makeStateRule(Blocks.YELLOW_TERRACOTTA);

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        RandomSource random = RandomSource.create();

        int silverLayer1 = 61+random.nextInt(2);
        int silverLayer2 = 64-random.nextInt(2);
        int silverLayer3 = 69+random.nextInt(2);
        int silverLayer4 = 74-random.nextInt(2);
        int silverLayer5 = 78+random.nextInt(2);

        // Default to a grass and dirt surface
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.SILVER_BIRCH_FOREST),
                        SurfaceRules.ifTrue(
                                SurfaceRules.ON_FLOOR,
                                SurfaceRules.ifTrue(
                                        SurfaceRules.noiseCondition(SILVER_NOISE, -0.1D, 0.1D),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.yBlockCheck(VerticalAnchor.absolute(silverLayer1), 0),
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(silverLayer1+1), 0)),
                                                        YELLOW_TERRACOTTA
                                                )
                                        )
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.SILVER_BIRCH_FOREST),
                        SurfaceRules.ifTrue(
                                SurfaceRules.ON_FLOOR,
                                SurfaceRules.ifTrue(
                                        SurfaceRules.noiseCondition(SILVER_NOISE, -0.1D, 0.1D),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.yBlockCheck(VerticalAnchor.absolute(silverLayer2), 0),
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(silverLayer2+1), 0)),
                                                        YELLOW_TERRACOTTA
                                                )
                                        )
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.SILVER_BIRCH_FOREST),
                        SurfaceRules.ifTrue(
                                SurfaceRules.ON_FLOOR,
                                SurfaceRules.ifTrue(
                                        SurfaceRules.noiseCondition(SILVER_NOISE, -0.1D, 0.1D),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.yBlockCheck(VerticalAnchor.absolute(silverLayer3), 0),
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(silverLayer3+1), 0)),
                                                        YELLOW_TERRACOTTA
                                                )
                                        )
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.SILVER_BIRCH_FOREST),
                        SurfaceRules.ifTrue(
                                SurfaceRules.ON_FLOOR,
                                SurfaceRules.ifTrue(
                                        SurfaceRules.noiseCondition(SILVER_NOISE, -0.1D, 0.1D),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.yBlockCheck(VerticalAnchor.absolute(silverLayer4), 0),
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(silverLayer4+1), 0)),
                                                        YELLOW_TERRACOTTA
                                                )
                                        )
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.SILVER_BIRCH_FOREST),
                        SurfaceRules.ifTrue(
                                SurfaceRules.ON_FLOOR,
                                SurfaceRules.ifTrue(
                                        SurfaceRules.noiseCondition(SILVER_NOISE, -0.1D, 0.1D),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.yBlockCheck(VerticalAnchor.absolute(silverLayer5), 0),
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(silverLayer5+1), 0)),
                                                        YELLOW_TERRACOTTA
                                                )
                                        )
                                )
                        )
                ),
                // Default to a grass and dirt surface
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}