package dev.maksiks.amaranth.worldgen.biome;

import dev.maksiks.amaranth.Amaranth;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "overworld"), 2));
    }
}
