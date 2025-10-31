package dev.maksiks.amaranth.worldgen.terrain;

import dev.maksiks.amaranth.worldgen.biome.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.Function;

public class MushlandTerrain {
    public static void processMushlandTerrain(Function<BlockPos, Holder<Biome>> biomeGetter,
                                              ChunkAccess chunk) {
        ChunkPos pos = chunk.getPos();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = pos.getBlockX(x);
                int worldZ = pos.getBlockZ(z);

                int currentHeight = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, worldX, worldZ);

                mutable.set(worldX, currentHeight, worldZ);
                Holder<Biome> biome = biomeGetter.apply(mutable);

                if (!biome.is(ModBiomes.MUSHLAND)) {
                    continue;
                }

                int targetHeight = 64;

                if (currentHeight > targetHeight) {
                    for (int y = targetHeight + 1; y <= currentHeight; y++) {
                        chunk.setBlockState(mutable.setY(y), Blocks.AIR.defaultBlockState(), false);
                    }
                } else if (currentHeight < targetHeight) {
                    for (int y = currentHeight; y < targetHeight; y++) {
                        chunk.setBlockState(mutable.setY(y), Blocks.STONE.defaultBlockState(), false);
                    }
                }

                chunk.setBlockState(mutable.setY(targetHeight), Blocks.GRASS_BLOCK.defaultBlockState(), false);
            }
        }
    }
}