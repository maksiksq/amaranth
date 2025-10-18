package dev.maksiks.amaranth.event;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobDespawnEvent;

@EventBusSubscriber(modid = Amaranth.MOD_ID)
public class ModCrownOfThornsDamageHandler {
    @SubscribeEvent
    public static void onLivingIncomingDamageEvent(LivingIncomingDamageEvent event) {
        LivingEntity target = event.getEntity();
        var source = event.getSource();

        ItemStack headItem = target.getItemBySlot(EquipmentSlot.HEAD);
        if (headItem.getItem() == ModItems.CROWN_OF_THORNS.asItem()) {
            if (source.getEntity() instanceof LivingEntity attacker && attacker.isAlive()) {
                // essentially thorns 3 sorta
                if (target.getRandom().nextFloat() < 0.45f) {
                    float thornsDamage = 1.0F + target.getRandom().nextInt(4);
                    attacker.hurt(target.damageSources().thorns(target), thornsDamage);
                }

                attacker.level().levelEvent(2001, attacker.blockPosition(), 0);
            }
        }
    }
}