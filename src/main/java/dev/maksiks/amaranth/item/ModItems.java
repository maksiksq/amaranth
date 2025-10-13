package dev.maksiks.amaranth.item;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.entity.ModEntities;
import dev.maksiks.amaranth.item.custom.ModCrownOfThornsItem;
import dev.maksiks.amaranth.sound.ModSounds;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

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
            () -> new SpawnEggItem(ModEntities.SHROOM_BOI.get(), 0xf75d57, 0xf1f1f1,
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
    public static final DeferredItem<ArmorItem> CROWN_OF_THORNS = ITEMS.register("crown_of_thorns",
            () -> new ModCrownOfThornsItem(ModArmorMaterials.CROWN_OF_THORNS_MATERIAL, ArmorType.HELMET,
                    new Item.Properties()));

    @FunctionalInterface
    private interface ItemFactory<T extends Item> {
        T create(Item.Properties properties);
    }

    private static <T extends Item> DeferredItem<T> registerItem(String name, ItemFactory<T> factory) {
        return ITEMS.register(name, () -> factory.create(
                new Item.Properties().setId(
                        ResourceKey.create(Registries.ITEM,
                                ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, name))
                )
        ));
    }

    // without seId
    private static <T extends Item> DeferredItem<T> registerItem(String name, Supplier<T> supplier) {
        return ITEMS.register(name, supplier);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
