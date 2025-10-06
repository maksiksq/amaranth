package dev.maksiks.amaranth.worldgen.biome.regions;

import net.minecraft.world.level.biome.Climate;

public class ModRegionHelpers {
    // it's not surface because that gets eaten by vanilla a few blocks up too often if we don't fill the whole region
    public static final Climate.Parameter NORMAL_REGION_DEPTH = Climate.Parameter.span(-0.25F, 0);
}
