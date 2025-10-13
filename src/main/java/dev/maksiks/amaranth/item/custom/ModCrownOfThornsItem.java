package dev.maksiks.amaranth.item.custom;

import com.mojang.datafixers.types.Type;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.List;

public class ModCrownOfThornsItem extends ArmorItem {
    public ModCrownOfThornsItem(ArmorMaterial material, ArmorType type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.amaranth.crown_of_thorns.tooltip"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}