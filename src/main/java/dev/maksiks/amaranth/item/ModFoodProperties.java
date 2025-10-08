package dev.maksiks.amaranth.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    // image eating this before a boss intentionally and the 0.9 chance does not proc lmao
    // evil hehehe
    public static final FoodProperties HEXFRUIT = new FoodProperties.Builder().nutrition(3).saturationModifier(5)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 700), 0.9F)
            .alwaysEdible()
            .build();

    public static final FoodProperties MUSHROOM_TEA = new FoodProperties.Builder().nutrition(9).saturationModifier(12)
            .build();



}
