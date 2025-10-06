package dev.maksiks.amaranth.worldgen.biome.regions;

import net.minecraft.world.level.biome.Climate;

public class ModRegionHelpers {
    // it's not surface because that gets eaten by vanilla a few blocks up too often
    // and setting it too high makes it generate in the air
    public static final Climate.Parameter NORMAL_BIOME_DEPTH = Climate.Parameter.span(-0.25F, 0);
}
