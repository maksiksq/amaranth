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
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Amaranth.MOD_ID);

    public static final DeferredItem<Item> MAFIA_BLOB = registerItem("mafia_blob",
            props -> new Item(props) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> components, TooltipFlag tooltipFlag) {
                    components.accept(Component.translatable("tooltip.amaranth.mafia_blob.tooltip"));
                    super.appendHoverText(stack, context, display, components, tooltipFlag);
                }
            });
    public static final DeferredItem<Item> BEANIE_BLOB = registerItem("beanie_blob",
            Item::new);
    public static final DeferredItem<Item> HEXFRUIT = registerItem("hexfruit",
            props -> new Item(props.food(ModFoodProperties.HEXFRUIT, ModFoodProperties.HEXFRUIT_EFFECT)));

    // maybe TODO: egg temp
    public static final DeferredItem<Item> SHROOM_BOI_SPAWN_EGG = registerItem("shroom_boi_spawn_egg",
            (props) -> new SpawnEggItem(props.spawnEgg(ModEntities.SHROOM_BOI.get())));

    public static final DeferredItem<Item> EMPTY_TEA_CUP = registerItem("empty_tea_cup",
            Item::new);
    public static final DeferredItem<Item> MUSHROOM_TEA = registerItem("mushroom_tea",
            props -> new Item(props.food(ModFoodProperties.MUSHROOM_TEA)));

    // discs
    public static final DeferredItem<Item> PALETTE_OVERLOAD_MUSIC_DISC = registerItem("palette_overload_music_disc",
            props -> new Item(props.jukeboxPlayable(ModSounds.PALETTE_OVERLOAD_KEY).stacksTo(1)));

    // pain
    public static final DeferredItem<Item> THORN = registerItem("thorn",
            Item::new);
    public static final DeferredItem<Item> CROWN_OF_THORNS = registerItem("crown_of_thorns",
            props -> new ModCrownOfThornsItem(props.humanoidArmor(ModArmorMaterials.CROWN_OF_THORNS_MATERIAL, ArmorType.HELMET)));

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

    // without setId if i need it
    private static <T extends Item> DeferredItem<T> registerItem(String name, Supplier<T> supplier) {
        return ITEMS.register(name, supplier);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
