package dev.maksiks.amaranth.item;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.tags.ModTags;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.EnumMap;

public class ModArmorMaterials {
    public static final ArmorMaterial CROWN_OF_THORNS_MATERIAL = new ArmorMaterial(
            109,
            Util.make(new EnumMap<>(ArmorType.class), attribute -> {
                attribute.put(ArmorType.HELMET, 2);
            }),
            16,
            BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.GRASS_BREAK),
            0f,
            0.05f,
            ModTags.Items.THORN_REPAIR,
            ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "crown_of_thorns"));
}
