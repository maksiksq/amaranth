package dev.maksiks.amaranth.item;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.entity.ModEntities;
import dev.maksiks.amaranth.item.custom.CrownOfThornsItem;
import dev.maksiks.amaranth.item.custom.MelonHelmetItem;
import dev.maksiks.amaranth.item.custom.WisteriaJuiceItem;
import dev.maksiks.amaranth.sound.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Amaranth.MOD_ID);

    // misc
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

    public static final DeferredItem<ArmorItem> MELON_HELMET = ITEMS.register("melon_helmet",
            () -> new MelonHelmetItem(ModArmorMaterials.MELON_HELMET_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(5))));

    // discs
    public static final DeferredItem<Item> PALETTE_OVERLOAD_MUSIC_DISC = ITEMS.register("palette_overload_music_disc",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).jukeboxPlayable(ModSounds.PALETTE_OVERLOAD_KEY).stacksTo(1)));

    // pain
    public static final DeferredItem<Item> THORN = ITEMS.register("thorn",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<ArmorItem> CROWN_OF_THORNS = ITEMS.register("crown_of_thorns",
            () -> new CrownOfThornsItem(ModArmorMaterials.CROWN_OF_THORNS_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(9))));

    // pastel
    public static final DeferredItem<Item> WISTERIA_JUICE = ITEMS.register("wisteria_juice",
            () -> new WisteriaJuiceItem(new Item.Properties().food(ModFoodProperties.WISTERIA_JUICE)));

    // mush
    public static final DeferredItem<Item> REED_BAR = ITEMS.register("reed_bar",
            () -> new Item(new Item.Properties().food(ModFoodProperties.REED_BAR)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
