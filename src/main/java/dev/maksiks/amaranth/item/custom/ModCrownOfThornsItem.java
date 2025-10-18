package dev.maksiks.amaranth.item.custom;

import com.mojang.datafixers.types.Type;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.List;
import java.util.function.Consumer;

public class ModCrownOfThornsItem extends Item {
    public ModCrownOfThornsItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> components, TooltipFlag tooltipFlag) {
        components.accept(Component.translatable("tooltip.amaranth.crown_of_thorns.tooltip"));
        super.appendHoverText(stack, context, display, components, tooltipFlag);
    }
}