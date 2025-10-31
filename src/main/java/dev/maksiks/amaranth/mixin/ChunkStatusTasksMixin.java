package dev.maksiks.amaranth.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.maksiks.amaranth.worldgen.terrain.MushlandTerrain;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.GenerationChunkHolder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.StaticCache2D;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.status.ChunkStatusTasks;
import net.minecraft.world.level.chunk.status.ChunkStep;
import net.minecraft.world.level.chunk.status.WorldGenContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Mixin(ChunkStatusTasks.class)
public abstract class ChunkStatusTasksMixin {
    @Inject(method = "generateSurface", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/chunk/ChunkGenerator;buildSurface(Lnet/minecraft/server/level/WorldGenRegion;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/levelgen/RandomState;Lnet/minecraft/world/level/chunk/ChunkAccess;)V"))
    private static void injectBiomeTerrain(WorldGenContext worldGenContext, ChunkStep step, StaticCache2D<GenerationChunkHolder> cache, ChunkAccess chunk, CallbackInfoReturnable<CompletableFuture<ChunkAccess>> cir, @Local ServerLevel serverLevel, @Local WorldGenRegion worldGenRegion) {
        BiomeManager biomeManager = worldGenRegion.getBiomeManager()
                .withDifferentSource((x, y, z) -> worldGenContext.generator().getBiomeSource().getNoiseBiome(x, y, z, worldGenContext.level()
                        .getChunkSource().randomState().sampler()));
        Long2ObjectOpenHashMap<Long2ObjectOpenHashMap<Holder<Biome>>> biomeCache = new Long2ObjectOpenHashMap<>();

        Function<BlockPos, Holder<Biome>> biomeGetter =
                pos -> biomeCache.computeIfAbsent(ChunkPos.asLong(pos),
                key0 -> new Long2ObjectOpenHashMap<>()).computeIfAbsent(ChunkPos.asLong(pos.getX(), pos.getZ()),
                key1 -> biomeManager.getBiome(pos));

        // mush
        MushlandTerrain.processMushlandTerrain(biomeGetter, chunk);
    }
}