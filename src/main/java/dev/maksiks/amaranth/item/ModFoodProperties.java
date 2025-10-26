package dev.maksiks.amaranth.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class ModFoodProperties {
    // image eating this before a boss intentionally and the 0.9 chance does not proc lmao
    // evil hehehe
    public static final FoodProperties HEXFRUIT = new FoodProperties.Builder().nutrition(3).saturationModifier(5)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 700), 0.9F)
            .alwaysEdible()
            .build();

    public static final FoodProperties MUSHROOM_TEA = new FoodProperties.Builder().nutrition(9).saturationModifier(12)
            .build();

    public static final FoodProperties WISTERIA_JUICE = new FoodProperties.Builder().nutrition(2).saturationModifier(2)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 3), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 160, 3), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 100, 3), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 400, 5), 0.9F)
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 800, 0), 1.0F)
            .alwaysEdible()
            .usingConvertsTo(Items.GLASS_BOTTLE)
            .build();



}
