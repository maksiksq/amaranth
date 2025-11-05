package dev.maksiks.amaranth;

import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Amaranth.MOD_ID);

    public static final Supplier<CreativeModeTab> AMARANTH_TAB = CREATIVE_MODE_TAB.register("amaranth_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.MYSTIC_LEAVES))
                    .title(Component.translatable("creativetab.amaranth.amaranth"))
                    .displayItems((itemDisplayParameters, output) -> {
                        // misc
                        output.accept(ModItems.MAFIA_BLOB);
                        output.accept(ModItems.BEANIE_BLOB);
                        output.accept(ModItems.EMPTY_TEA_CUP);

                        // biome items
                        output.accept(ModItems.THORN);
                        output.accept(ModItems.CROWN_OF_THORNS);

                        // food
                        output.accept(ModItems.HEXFRUIT);
                        output.accept(ModItems.MUSHROOM_TEA);
                        output.accept(ModItems.WISTERIA_JUICE);
                        output.accept(ModItems.REED_BAR);

                        // spawn eggs
                        output.accept(ModItems.SHROOM_BOI_SPAWN_EGG);

                        // leaves
                        output.accept(ModBlocks.MYSTIC_LEAVES);
                        output.accept(ModBlocks.SILVERY_SILVER_BIRCH_LEAVES);
                        output.accept(ModBlocks.LIGHT_SILVER_BIRCH_LEAVES);
                        output.accept(ModBlocks.DARK_SILVER_BIRCH_LEAVES);
                        output.accept(ModBlocks.PURPLE_MIXED_OAK_LEAVES);
                        output.accept(ModBlocks.RED_MIXED_OAK_LEAVES);
                        output.accept(ModBlocks.YELLOW_MIXED_OAK_LEAVES);
                        output.accept(ModBlocks.ANTHOCYANIN_LEAVES);
                        output.accept(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES);
                        output.accept(ModBlocks.WISTERIA_LEAVES);

                        // saplings
                        output.accept(ModBlocks.MYSTIC_SAPLING);
                        output.accept(ModBlocks.STUBBY_SAPLING);
                        output.accept(ModBlocks.SILVER_BIRCH_SAPLING);
                        output.accept(ModBlocks.PURPLE_MIXED_OAK_SAPLING);
                        output.accept(ModBlocks.RED_MIXED_OAK_SAPLING);
                        output.accept(ModBlocks.YELLOW_MIXED_OAK_SAPLING);
                        output.accept(ModBlocks.TRIMMED_TREE_SAPLING);
                        output.accept(ModBlocks.ANTHOCYANIN_SAPLING);
                        output.accept(ModBlocks.SPEARY_SAPLING);
                        output.accept(ModBlocks.WISTERIA_SAPLING);
                        output.accept(ModBlocks.RED_MINI_SHROOM_SPORELING);
                        output.accept(ModBlocks.BROWN_MINI_SHROOM_SPORELING);
                        output.accept(ModBlocks.WITCHY_SAPLING);

                        // biome decor
                        output.accept(ModBlocks.GOLDEN_LEAF_LITTER);
                        output.accept(ModBlocks.SPIKY_ARCHES);
                        output.accept(ModBlocks.PHLOX);
                        output.accept(ModBlocks.REEDS);

                        // flowers
                        output.accept(ModBlocks.MALACHITE_VIPERS_BUGLOSS);

                        // full biome vegetation blocks
                        output.accept(ModBlocks.THICK_PUMPKIN);

                        // woods and stuff
                        output.accept(ModBlocks.MYSTIC_LOG);
                        output.accept(ModBlocks.ANTHOCYANIN_LOG);
                        output.accept(ModBlocks.JUICY_WISTERIA_LOG);
                        output.accept(ModBlocks.WISTERIA_LOG);
                        output.accept(ModBlocks.MYSTIC_WOOD);
                        output.accept(ModBlocks.ANTHOCYANIN_WOOD);
                        output.accept(ModBlocks.WISTERIA_WOOD);
                        output.accept(ModBlocks.STRIPPED_MYSTIC_LOG);
                        output.accept(ModBlocks.STRIPPED_ANTHOCYANIN_LOG);
                        output.accept(ModBlocks.STRIPPED_WISTERIA_LOG);
                        output.accept(ModBlocks.STRIPPED_MYSTIC_WOOD);
                        output.accept(ModBlocks.STRIPPED_ANTHOCYANIN_WOOD);
                        output.accept(ModBlocks.STRIPPED_WISTERIA_WOOD);

                        output.accept(ModBlocks.MYSTIC_PLANKS);
                        output.accept(ModBlocks.ANTHOCYANIN_PLANKS);
                        output.accept(ModBlocks.WISTERIA_PLANKS);

                        output.accept(ModBlocks.MYSTIC_STAIRS);
                        output.accept(ModBlocks.ANTHOCYANIN_STAIRS);
                        output.accept(ModBlocks.WISTERIA_STAIRS);
                        output.accept(ModBlocks.MYSTIC_SLAB);
                        output.accept(ModBlocks.ANTHOCYANIN_SLAB);
                        output.accept(ModBlocks.WISTERIA_SLAB);

                        output.accept(ModBlocks.MYSTIC_PRESSURE_PLATE);
                        output.accept(ModBlocks.ANTHOCYANIN_PRESSURE_PLATE);
                        output.accept(ModBlocks.WISTERIA_PRESSURE_PLATE);
                        output.accept(ModBlocks.MYSTIC_BUTTON);
                        output.accept(ModBlocks.ANTHOCYANIN_BUTTON);
                        output.accept(ModBlocks.WISTERIA_BUTTON);

                        output.accept(ModBlocks.MYSTIC_FENCE);
                        output.accept(ModBlocks.ANTHOCYANIN_FENCE);
                        output.accept(ModBlocks.WISTERIA_FENCE);
                        output.accept(ModBlocks.MYSTIC_FENCE_GATE);
                        output.accept(ModBlocks.ANTHOCYANIN_FENCE_GATE);
                        output.accept(ModBlocks.WISTERIA_FENCE_GATE);

                        output.accept(ModBlocks.MYSTIC_DOOR);
                        output.accept(ModBlocks.ANTHOCYANIN_DOOR);
                        output.accept(ModBlocks.ORNAMENTED_ANTHOCYANIN_DOOR);
                        output.accept(ModBlocks.WISTERIA_DOOR);
                        output.accept(ModBlocks.MYSTIC_TRAPDOOR);
                        output.accept(ModBlocks.ANTHOCYANIN_TRAPDOOR);
                        output.accept(ModBlocks.ORNAMENTED_ANTHOCYANIN_TRAPDOOR);
                        output.accept(ModBlocks.WISTERIA_TRAPDOOR);

                        // rocks
                        output.accept(ModBlocks.SORROW_ICE);
                        output.accept(ModBlocks.REMNANT_SORROW_ICE);
                        output.accept(ModBlocks.MARBLE);

                        // music discs
                        output.accept(ModItems.PALETTE_OVERLOAD_MUSIC_DISC);
                    }).build());

    // can't forget
    // .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "amaranth_tab"))
    // to have tabs ordered if i add more


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
