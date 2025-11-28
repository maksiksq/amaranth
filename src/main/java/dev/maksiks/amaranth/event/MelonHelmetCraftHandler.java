package dev.maksiks.amaranth.event;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.item.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = Amaranth.MOD_ID)
public class MelonHelmetCraftHandler {
    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent e) {
        if (!e.getCrafting().is(ModItems.MELON_HELMET.get())) return;

        LivingEntity entity = (e.getEntity());
        Level level = entity.level();

        if (!level.isClientSide() && entity instanceof Player) {
            ServerPlayer player = (ServerPlayer) entity;
            FoodData foodData = player.getFoodData();

            foodData.eat(12, 8.5F);

            level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.ITEM_BREAK,
                    SoundSource.PLAYERS,
                    0.5F,
                    1.0F
            );

            player.server.tell(new net.minecraft.server.TickTask(
                    player.server.getTickCount() + 4,
                    () -> {
                        level.playSound(
                                null,
                                player.getX(),
                                player.getY(),
                                player.getZ(),
                                SoundEvents.PLAYER_BURP,
                                SoundSource.PLAYERS,
                                0.5F,
                                level.random.nextFloat() * 0.1F + 0.9F
                        );
                    }
            ));
        }

    }

}
