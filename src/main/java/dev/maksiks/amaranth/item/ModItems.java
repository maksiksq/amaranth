package dev.maksiks.amaranth.item;

import dev.maksiks.amaranth.Amaranth;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
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



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
