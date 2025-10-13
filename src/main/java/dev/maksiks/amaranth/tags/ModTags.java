package dev.maksiks.amaranth.tags;

import dev.maksiks.amaranth.Amaranth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Blocks {
    }

    public static class Items {
        public static final TagKey<Item> THORN_REPAIR = createTag("thorn_repair");
    }

    private static TagKey<Item> createTag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, name));
    }
}
