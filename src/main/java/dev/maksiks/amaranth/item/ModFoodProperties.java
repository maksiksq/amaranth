package dev.maksiks.amaranth.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoodProperties {
    // image eating this before a boss intentionally and the 0.9 chance does not proc lmao
    // evil hehehe
    public static final FoodProperties HEXFRUIT = new FoodProperties.Builder().nutrition(3).saturationModifier(5)
            .alwaysEdible()
            .build();

    public static final FoodProperties MUSHROOM_TEA = new FoodProperties.Builder().nutrition(9).saturationModifier(12)
            .build();

    public static final Consumable HEXFRUIT_EFFECT = Consumables.defaultFood().onConsume(
            new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 700), 0.9f)).build();
}
