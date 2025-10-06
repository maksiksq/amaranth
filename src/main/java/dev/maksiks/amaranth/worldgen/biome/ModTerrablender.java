package dev.maksiks.amaranth.worldgen.biome;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.worldgen.biome.regions.ModMidRangeRegion;
import dev.maksiks.amaranth.worldgen.biome.regions.ModEasterEggRegion;
import dev.maksiks.amaranth.worldgen.biome.regions.ModRareRegion;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModMidRangeRegion(ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "common"), 10));
        Regions.register(new ModRareRegion(ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "rare"), 4));
        Regions.register(new ModEasterEggRegion(ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "easteregg"), 1));
    }
}
