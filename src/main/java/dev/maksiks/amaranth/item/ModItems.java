package dev.maksiks.amaranth.item;

import dev.maksiks.amaranth.Amaranth;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Amaranth.MOD_ID);

    public static final DeferredItem<Item> MAFIA_BLOB = ITEMS.register("mafia_blob", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BEANIE_BLOB = ITEMS.register("beanie_blob", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
