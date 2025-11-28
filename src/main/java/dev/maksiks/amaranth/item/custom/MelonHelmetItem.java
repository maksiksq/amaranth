package dev.maksiks.amaranth.item.custom;

import dev.maksiks.amaranth.item.ModArmorMaterials;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class MelonHelmetItem extends ArmorItem {
    public MelonHelmetItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.amaranth.melon_helmet.tooltip"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof Player player && !level.isClientSide()) {
            evaluateArmorEffects(player);
        }
    }

    private void evaluateArmorEffects(Player player) {
        Holder<ArmorMaterial> material = ModArmorMaterials.MELON_HELMET_MATERIAL;
        MobEffectInstance effect = new MobEffectInstance(MobEffects.REGENERATION, 1, 0, false, false);

        if (hasCorrectHelmetOn(material, player)) {
            addEffectToPlayer(player, effect);
        }
    }

    private void addEffectToPlayer(Player player, MobEffectInstance effect) {
        boolean hasPlayerEffect = player.hasEffect(effect.getEffect());

        if (!hasPlayerEffect) {
            player.addEffect(new MobEffectInstance(effect.getEffect(),
                    effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.isVisible()));
        }
    }

    private boolean hasCorrectHelmetOn(Holder<ArmorMaterial> material, Player player) {
        Item helmet = player.getInventory().getArmor(3).getItem();
        return helmet instanceof ArmorItem && ((ArmorItem) helmet).getMaterial() == material;
    }
}