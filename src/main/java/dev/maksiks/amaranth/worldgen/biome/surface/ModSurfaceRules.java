package dev.maksiks.amaranth.worldgen.biome.surface;

import dev.maksiks.amaranth.worldgen.biome.ModBiomes;
import dev.maksiks.amaranth.worldgen.noise.ModNoises;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

import java.util.ArrayList;
import java.util.List;

import static dev.maksiks.amaranth.worldgen.noise.ModNoises.*;
import static net.minecraft.world.level.levelgen.SurfaceRules.stoneDepthCheck;
import static net.minecraft.world.level.levelgen.SurfaceRules.yBlockCheck;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);
    private static final SurfaceRules.RuleSource YELLOW_TERRACOTTA = makeStateRule(Blocks.YELLOW_TERRACOTTA);
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource POWDER_SNOW = makeStateRule(Blocks.POWDER_SNOW);
    private static final SurfaceRules.RuleSource LIME_TERRACOTTA = makeStateRule(Blocks.LIME_TERRACOTTA);
    private static final SurfaceRules.RuleSource MYCELIUM = makeStateRule(Blocks.MYCELIUM);
    private static final SurfaceRules.RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final SurfaceRules.RuleSource DEAD_TUBE_CORAL_BLOCK = makeStateRule(Blocks.DEAD_TUBE_CORAL_BLOCK);
    private static final SurfaceRules.RuleSource DEAD_BRAIN_CORAL_BLOCK = makeStateRule(Blocks.DEAD_BRAIN_CORAL_BLOCK);
    private static final SurfaceRules.RuleSource DEAD_BUBBLE_CORAL_BLOCK = makeStateRule(Blocks.DEAD_BUBBLE_CORAL_BLOCK);
    private static final SurfaceRules.RuleSource SUSPICIOUS_GRAVEL = makeStateRule(Blocks.SUSPICIOUS_GRAVEL);
    private static final SurfaceRules.RuleSource PRISMARINE = makeStateRule(Blocks.PRISMARINE);
    private static final SurfaceRules.RuleSource CYAN_CONCRETE = makeStateRule(Blocks.CYAN_CONCRETE);
    private static final SurfaceRules.RuleSource DARK_PRISMARINE = makeStateRule(Blocks.DARK_PRISMARINE);
    private static final SurfaceRules.RuleSource WATER = makeStateRule(Blocks.WATER);
    private static final SurfaceRules.RuleSource MUD = makeStateRule(Blocks.MUD);

    private static SurfaceRules.RuleSource silverLayerRule(int layerY) {
        return SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.SILVER_BIRCH_FOREST),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.ifTrue(
                                SurfaceRules.noiseCondition(SILVER_NOISE, -0.1D, 0.1D),
                                SurfaceRules.ifTrue(
                                        yBlockCheck(VerticalAnchor.absolute(layerY), 0),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.not(yBlockCheck(VerticalAnchor.absolute(layerY + 1), 0)),
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

    private static SurfaceRules.RuleSource safeSurfaceFloorRule(SurfaceRules.RuleSource innerRule) {
        return SurfaceRules.ifTrue(
                stoneDepthCheck(0, false, 10, CaveSurface.FLOOR),
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, innerRule)
                )
        );
    }

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        RandomSource random = RandomSource.create();

        List<SurfaceRules.RuleSource> rules = new ArrayList<>();

        // silver
        int[] silverLayers = {
                62,
                64,
                70,
                73,
                80,
                85,
                98,
                105,
                125,
                146
        };

        for (int layer : silverLayers) {
            rules.add(silverLayerRule(layer));
        }

        // desolate
        rules.add(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.DESOLATE_ICE_FIELDS), powderSnowBlobsLayerRule()));
        rules.add(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.DESOLATE_ICE_FIELDS),
                        SurfaceRules.ifTrue(
                                // makes an illusion instead of cutoff underground i guess, even if it really corresponds to terrain above lol
                                stoneDepthCheck(0, false, 64, CaveSurface.FLOOR),
                                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, SNOW_BLOCK)
                        )
                )
        );

        // orderly (VARIANT BELOW)
        rules.add(SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.ORDERLY_COURTS),
                safeSurfaceFloorRule(
                        SurfaceRules.ifTrue(
                                SurfaceRules.noiseCondition(ModNoises.STRIPE_ATTEMPT_NOISE, -0.88, 0.05),
                                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, LIME_TERRACOTTA)
                        )
                )
        ));

        // orderly ruins (ORIGINAL ABOVE)
        rules.add(SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.ORDERLY_COURTS_RUINS),
                safeSurfaceFloorRule(
                        SurfaceRules.ifTrue(
                                SurfaceRules.noiseCondition(ModNoises.STRIPE_ATTEMPT_NOISE, -0.88, 0.05),
                                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, LIME_TERRACOTTA)
                        )
                )
        ));

        // shroom
        rules.add(SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.SHROOMLANDS),
                SurfaceRules.sequence(
                        safeSurfaceFloorRule(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.noiseCondition(PATCHY_NOISE, 0.2D, 2.0D),
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, MYCELIUM)
                                )
                        ),
                        safeSurfaceFloorRule(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.noiseCondition(SILVER_NOISE, -0.1D, 0.1D),
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, MYCELIUM)
                                )
                        ),
                        safeSurfaceFloorRule(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.noiseCondition(PATCHY_NOISE, -1.0D, -0.3D),
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK)
                                )
                        ),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.ifTrue(SurfaceRules.not(isAtOrAboveWaterLevel), DIRT)),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT),
                        STONE
                )
        ));

        // dusty
        rules.add(SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.DUSTY_FLATS),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                                stoneDepthCheck(0, false, 10, CaveSurface.FLOOR),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.abovePreliminarySurface(),
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.ON_FLOOR,
                                                        SurfaceRules.ifTrue(
                                                                SurfaceRules.noiseCondition(SILVER_NOISE, -0.20D, 0.20D),
                                                                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, DEAD_BUBBLE_CORAL_BLOCK)
                                                        )
                                                ),
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.UNDER_FLOOR,
                                                        SurfaceRules.ifTrue(
                                                                SurfaceRules.noiseCondition(SILVER_NOISE, -0.20D, 0.20D),
                                                                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, DEAD_BUBBLE_CORAL_BLOCK)
                                                        )
                                                )
                                        )
                                )
                        ),
                        safeSurfaceFloorRule(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.noiseCondition(SILVER_NOISE, -0.24D, 0.24D),
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, DEAD_BRAIN_CORAL_BLOCK)
                                )
                        ),
                        safeSurfaceFloorRule(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.noiseCondition(SILVER_NOISE, -0.28D, 0.28D),
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, DEAD_TUBE_CORAL_BLOCK)
                                )
                        ),
                        safeSurfaceFloorRule(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.noiseCondition(SILVER_NOISE, -0.30D, 0.30D),
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, SUSPICIOUS_GRAVEL)
                                )
                        ),
                        SurfaceRules.ifTrue(
                                stoneDepthCheck(0, false, 20, CaveSurface.FLOOR),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.abovePreliminarySurface(),
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRAVEL)),
                                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, GRAVEL),
                                                SurfaceRules.ifTrue(stoneDepthCheck(0, true, 10, CaveSurface.FLOOR), GRAVEL)
                                        )
                                )
                        )
                )
        ));

        // anthocyanin
        rules.add(SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.ANTHOCYANIN_FOREST),
                safeSurfaceFloorRule(
                        SurfaceRules.ifTrue(
                                SurfaceRules.noiseCondition(VEINY_NOISE, -0.06D, 0.06D),
                                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, DARK_PRISMARINE)
                        )
                )
        ));

        rules.add(SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.ANTHOCYANIN_FOREST),
                safeSurfaceFloorRule(
                        SurfaceRules.ifTrue(
                                SurfaceRules.noiseCondition(VEINY_NOISE, -0.11D, 0.11D),
                                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, CYAN_CONCRETE)
                        )
                )
        ));

        rules.add(SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.ANTHOCYANIN_FOREST),
                safeSurfaceFloorRule(
                        SurfaceRules.ifTrue(
                                SurfaceRules.noiseCondition(VEINY_NOISE, -0.16D, 0.16D),
                                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, PRISMARINE)
                        )
                )
        ));

        // mush
        rules.add(SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.MUSHLAND),
                SurfaceRules.ifTrue(
                        SurfaceRules.noiseCondition(SILVER_NOISE, -0.1D, 0.1D),
                        SurfaceRules.ifTrue(
                                yBlockCheck(VerticalAnchor.absolute(62), 0),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.not(yBlockCheck(VerticalAnchor.absolute(63), 0)),
                                        WATER
                                )
                        )
                )
        ));

        rules.add(SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.MUSHLAND),
                safeSurfaceFloorRule(
                        SurfaceRules.ifTrue(
                                SurfaceRules.noiseCondition(VEINY_NOISE, -0.12D, 0.12D),
                                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, MUD)
                        )
                )
        ));

        // vanilla default fallback
        SurfaceRuleData.overworld();

        return SurfaceRules.sequence(rules.toArray(SurfaceRules.RuleSource[]::new));
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}