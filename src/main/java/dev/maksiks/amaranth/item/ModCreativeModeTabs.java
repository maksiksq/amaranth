package dev.maksiks.amaranth.item;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Amaranth.MOD_ID);

    public static final Supplier<CreativeModeTab> AMARANTH_TAB = CREATIVE_MODE_TAB.register("amaranth_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.MYSTIC_LEAVES_BLOCK))
                    .title(Component.translatable("creativetab.amaranth.amaranth"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.MAFIA_BLOB);
                        output.accept(ModItems.BEANIE_BLOB);
                        output.accept(ModItems.HEXFRUIT);

                        output.accept(ModBlocks.MYSTIC_SAPLING_BLOCK);
                        output.accept(ModBlocks.MYSTIC_LEAVES_BLOCK);

                        output.accept(ModBlocks.MYSTIC_LOG_BLOCK);
                        output.accept(ModBlocks.STRIPPED_MYSTIC_LOG_BLOCK);
                        output.accept(ModBlocks.MYSTIC_PLANKS_BLOCK);
//                        output.accept(ModBlocks.MYSTIC_WOOD_BLOCK);
//                        output.accept(ModBlocks.STRIPPED_MYSTIC_WOOD_BLOCK);
                    }).build());

    // can't forget
    // .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "amaranth_tab"))
    // to have tabs ordered if i add more


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
