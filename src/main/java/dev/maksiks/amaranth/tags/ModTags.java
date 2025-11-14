package dev.maksiks.amaranth.tags;

import dev.maksiks.amaranth.Amaranth;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        /**
        * separate tag from vanilla because the vanilla one
        * would allow placing bamboo on top of this block and vice versa
         **/
        public static final TagKey<Block> ALIEN_PHYLLOSTACHYS_PLANTABLE_ON =
                tag("alien_phyllostachys_plantable_on");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, name));
        }
    }
}