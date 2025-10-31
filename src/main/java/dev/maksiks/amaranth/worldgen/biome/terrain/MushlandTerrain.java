package dev.maksiks.amaranth.worldgen.biome.terrain;

import dev.maksiks.amaranth.worldgen.biome.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.Function;

public class MushlandTerrain {
    // I can comfortably hardcode it to y64 because sea level
    // plus my brain is already too fried to make it actually dynamic
    private static final int TARGET_HEIGHT = 64;
    // higher = not steeper
    // lower = steeper
    private static final int BLEND_RADIUS = 5;

    public static void processMushlandTerrain(Function<BlockPos, Holder<Biome>> biomeGetter,
                                              ChunkAccess chunk,
                                              WorldGenRegion region) {
        ChunkPos pos = chunk.getPos();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = pos.getBlockX(x);
                int worldZ = pos.getBlockZ(z);

                int vanillaHeight = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, worldX, worldZ);

                mutable.set(worldX, vanillaHeight, worldZ);
                Holder<Biome> biome = biomeGetter.apply(mutable);

                if (!biome.is(ModBiomes.MUSHLAND)) {
                    continue;
                }

                double blendFactor = calculateBlendFactor(biomeGetter, mutable, BLEND_RADIUS);

                int finalHeight = (int) Mth.lerp(blendFactor, vanillaHeight, TARGET_HEIGHT);

                applyTerrainShape(chunk, mutable, worldX, worldZ, vanillaHeight, finalHeight);
            }
        }
    }

    // determines how far it is from other biomes
    private static double calculateBlendFactor(Function<BlockPos, Holder<Biome>> biomeGetter,
                                               BlockPos.MutableBlockPos pos,
                                               int radius) {
        int centerX = pos.getX();
        int centerZ = pos.getZ();
        int centerY = pos.getY();

        double minDistance = radius;

        // sampling in a square pattern around the position
        for (int xOff = -radius; xOff <= radius; xOff++) {
            for (int zOff = -radius; zOff <= radius; zOff++) {
                pos.set(centerX + xOff, centerY, centerZ + zOff);
                Holder<Biome> sampledBiome = biomeGetter.apply(pos);

                if (!sampledBiome.is(ModBiomes.MUSHLAND)) {
                    // finding a non-mushland biome and mathing distance
                    double distance = Math.sqrt(xOff * xOff + zOff * zOff);
                    minDistance = Math.min(minDistance, distance);
                }
            }
        }

        pos.set(centerX, centerY, centerZ);

        double normalized = Mth.clamp(minDistance / radius, 0.0, 1.0);
        return smoothstep(normalized);
    }

    private static double smoothstep(double x) {
        return x * x * (3.0 - 2.0 * x);
    }

    // carving and filling with stone
    // surface rules should do the rest
    private static void applyTerrainShape(ChunkAccess chunk,
                                          BlockPos.MutableBlockPos mutable,
                                          int worldX, int worldZ,
                                          int currentHeight,
                                          int targetHeight) {
        int seaLevel = chunk.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, worldX, worldZ);

        if (currentHeight <= seaLevel && targetHeight > seaLevel) {
            targetHeight = seaLevel; // capping it at sea level to avoid overhangs
        }

        if (currentHeight > targetHeight) {
            for (int y = targetHeight + 1; y <= currentHeight; y++) {
                chunk.setBlockState(mutable.set(worldX, y, worldZ),
                        Blocks.AIR.defaultBlockState(), false);
            }
        } else if (currentHeight < targetHeight) {
            for (int y = currentHeight + 1; y <= targetHeight; y++) {
                if (y > seaLevel) break;
                chunk.setBlockState(mutable.set(worldX, y, worldZ),
                        Blocks.STONE.defaultBlockState(), false);
            }
        }
    }

}