package dev.maksiks.amaranth.item;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.entity.ModEntities;
import dev.maksiks.amaranth.sound.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Amaranth.MOD_ID);

    public static final DeferredItem<Item> MAFIA_BLOB = ITEMS.register("mafia_blob",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.amaranth.mafia_blob.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<Item> BEANIE_BLOB = ITEMS.register("beanie_blob",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> HEXFRUIT = ITEMS.register("hexfruit",
            () -> new Item(new Item.Properties().food(ModFoodProperties.HEXFRUIT)));

    public static final DeferredItem<Item> SHROOM_BOI_SPAWN_EGG = ITEMS.register("shroom_boi_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SHROOM_BOI, 0xf75d57, 0xf1f1f1,
                    new Item.Properties()));

    public static final DeferredItem<Item> EMPTY_TEA_CUP = ITEMS.register("empty_tea_cup",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MUSHROOM_TEA = ITEMS.register("mushroom_tea",
            () -> new Item(new Item.Properties().food(ModFoodProperties.MUSHROOM_TEA)));

    // discs
    public static final DeferredItem<Item> PALETTE_OVERLOAD_MUSIC_DISC = ITEMS.register("palette_overload_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.PALETTE_OVERLOAD_KEY).stacksTo(1)));

    // pain
    public static final DeferredItem<Item> THORN = ITEMS.register("thorn",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
