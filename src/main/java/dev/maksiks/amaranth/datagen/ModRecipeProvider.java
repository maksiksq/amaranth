package dev.maksiks.amaranth.datagen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // misc
        // temp
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MAFIA_BLOB.get(), 1)
                .requires(ModItems.HEXFRUIT.get())
                .unlockedBy("has_hexfruit", has(ModItems.HEXFRUIT.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MAFIA_BLOB.get(), 1)
                .requires(ModBlocks.MYSTIC_LEAVES.get())
                .unlockedBy("has_mystic_leaves", has(ModBlocks.MYSTIC_LEAVES.get()))
                .save(recipeOutput, "amaranth:mafia_blob_from_leaves");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BEANIE_BLOB.get())
                .pattern("HHH")
                .pattern("HHH")
                .pattern("HHH")
                .define('H', ModItems.HEXFRUIT.get())
                .unlockedBy("has_hexfruit", has(ModItems.HEXFRUIT.get()))
                .save(recipeOutput);

        // not temp

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EMPTY_TEA_CUP.get(), 1)
                .pattern("M M")
                .pattern(" M ")
                .pattern("   ")
                .define('M', ModBlocks.MARBLE.get().asItem())
                .unlockedBy("has_marble", has(ModBlocks.MARBLE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "empty_tea_cup_from_marble"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EMPTY_TEA_CUP.get(), 1)
                .pattern("D D")
                .pattern(" D ")
                .pattern("   ")
                .define('D', Blocks.POLISHED_DIORITE.asItem())
                .unlockedBy("has_diorite", has(Blocks.DIORITE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "empty_tea_cup_from_polished_diorite"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MUSHROOM_TEA.get(), 1)
                .requires(ModItems.EMPTY_TEA_CUP.get())
                .requires(Items.RED_MUSHROOM_BLOCK)
                .requires(Items.BROWN_MUSHROOM_BLOCK)
                .unlockedBy("has_tea_cup", has(ModItems.EMPTY_TEA_CUP.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.SPIKY_ARCHES.asItem(), 1)
                .requires(ModItems.THORN.get())
                .requires(ModItems.THORN.get())
                .requires(ModItems.THORN.get())
                .requires(Items.VINE)
                .unlockedBy("has_thorn", has(ModItems.THORN.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.CROWN_OF_THORNS, 1)
                .pattern("TTT")
                .pattern("T T")
                .pattern("   ")
                .define('T', ModItems.THORN.get())
                .unlockedBy("has_thorn", has(ModItems.THORN.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.THICK_PUMPKIN.asItem(), 1)
                .requires(Blocks.PUMPKIN)
                .requires(Blocks.PUMPKIN)
                .requires(Blocks.PUMPKIN)
                .requires(Blocks.PUMPKIN)
                .unlockedBy("has_pumpkin", has(Blocks.PUMPKIN.asItem()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.PUMPKIN_SEEDS, 12)
                .requires(ModBlocks.THICK_PUMPKIN.asItem())
                .unlockedBy("has_thick_pumpkin", has(ModBlocks.THICK_PUMPKIN.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "pumpkin_seeds_from_thick_pumpkin"));

        // who in the world will ever need this
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.PUMPKIN_PIE, 5)
                .requires(ModBlocks.THICK_PUMPKIN.asItem())
                .requires(Items.SUGAR)
                .requires(Items.SUGAR)
                .requires(Items.SUGAR)
                .requires(Items.EGG)
                .requires(Items.EGG)
                .requires(Items.EGG)
                .unlockedBy("has_thick_pumpkin", has(ModBlocks.THICK_PUMPKIN.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "pumpkin_pie_from_thick_pumpkin"));

        // yoink a custom method for smelting later so it's inside neo's folder because it's hardcoded to be in mc
        // mystic
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MYSTIC_PLANKS.asItem(), 4)
                .requires(ModBlocks.MYSTIC_LOG.get())
                .unlockedBy("has_mystic_log", has(ModBlocks.MYSTIC_LOG.asItem()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MYSTIC_WOOD.asItem(), 3)
                .requires(ModBlocks.MYSTIC_LOG.get())
                .requires(ModBlocks.MYSTIC_LOG.get())
                .requires(ModBlocks.MYSTIC_LOG.get())
                .requires(ModBlocks.MYSTIC_LOG.get())
                .unlockedBy("has_mystic_log", has(ModBlocks.MYSTIC_LOG.asItem()))
                .save(recipeOutput);

        stairBuilder(ModBlocks.MYSTIC_STAIRS.get(), Ingredient.of(ModBlocks.MYSTIC_PLANKS.get())).group("mystic")
                .unlockedBy("has_mystic_planks", has(ModBlocks.MYSTIC_PLANKS.get()))
                .save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MYSTIC_SLAB.get(), ModBlocks.MYSTIC_PLANKS.get());

        buttonBuilder(ModBlocks.MYSTIC_BUTTON.get(), Ingredient.of(ModBlocks.MYSTIC_PLANKS.get())).group("mystic")
                .unlockedBy("has_mystic_planks", has(ModBlocks.MYSTIC_PLANKS.get())).save(recipeOutput);
        pressurePlate(recipeOutput, ModBlocks.MYSTIC_PRESSURE_PLATE.get(), ModBlocks.MYSTIC_PLANKS.get());

        fenceBuilder(ModBlocks.MYSTIC_FENCE.get(), Ingredient.of(ModBlocks.MYSTIC_PLANKS.get())).group("mystic")
                .unlockedBy("has_mystic_planks", has(ModBlocks.MYSTIC_PLANKS.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.MYSTIC_FENCE_GATE.get(), Ingredient.of(ModBlocks.MYSTIC_PLANKS.get())).group("mystic")
                .unlockedBy("has_mystic_planks", has(ModBlocks.MYSTIC_PLANKS.get())).save(recipeOutput);

        doorBuilder(ModBlocks.MYSTIC_DOOR.get(), Ingredient.of(ModBlocks.MYSTIC_PLANKS.get())).group("mystic")
                .unlockedBy("has_mystic_planks", has(ModBlocks.MYSTIC_PLANKS.get())).save(recipeOutput);
        trapdoorBuilder(ModBlocks.MYSTIC_TRAPDOOR.get(), Ingredient.of(ModBlocks.MYSTIC_PLANKS.get())).group("mystic")
                .unlockedBy("has_mystic_planks", has(ModBlocks.MYSTIC_PLANKS.get())).save(recipeOutput);

        // anthocyanin
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ANTHOCYANIN_PLANKS.asItem(), 4)
                .requires(ModBlocks.ANTHOCYANIN_LOG.get())
                .unlockedBy("has_anthocyanin_log", has(ModBlocks.ANTHOCYANIN_LOG.asItem()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ANTHOCYANIN_WOOD.asItem(), 3)
                .requires(ModBlocks.ANTHOCYANIN_LOG.get())
                .requires(ModBlocks.ANTHOCYANIN_LOG.get())
                .requires(ModBlocks.ANTHOCYANIN_LOG.get())
                .requires(ModBlocks.ANTHOCYANIN_LOG.get())
                .unlockedBy("has_anthocyanin_log", has(ModBlocks.ANTHOCYANIN_LOG.asItem()))
                .save(recipeOutput);

        stairBuilder(ModBlocks.ANTHOCYANIN_STAIRS.get(), Ingredient.of(ModBlocks.ANTHOCYANIN_PLANKS.get())).group("anthocyanin")
                .unlockedBy("has_anthocyanin_planks", has(ModBlocks.ANTHOCYANIN_PLANKS.get()))
                .save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ANTHOCYANIN_SLAB.get(), ModBlocks.ANTHOCYANIN_PLANKS.get());

        buttonBuilder(ModBlocks.ANTHOCYANIN_BUTTON.get(), Ingredient.of(ModBlocks.ANTHOCYANIN_PLANKS.get())).group("anthocyanin")
                .unlockedBy("has_anthocyanin_planks", has(ModBlocks.ANTHOCYANIN_PLANKS.get())).save(recipeOutput);
        pressurePlate(recipeOutput, ModBlocks.ANTHOCYANIN_PRESSURE_PLATE.get(), ModBlocks.ANTHOCYANIN_PLANKS.get());

        fenceBuilder(ModBlocks.ANTHOCYANIN_FENCE.get(), Ingredient.of(ModBlocks.ANTHOCYANIN_PLANKS.get())).group("anthocyanin")
                .unlockedBy("has_anthocyanin_planks", has(ModBlocks.ANTHOCYANIN_PLANKS.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.ANTHOCYANIN_FENCE_GATE.get(), Ingredient.of(ModBlocks.ANTHOCYANIN_PLANKS.get())).group("anthocyanin")
                .unlockedBy("has_anthocyanin_planks", has(ModBlocks.ANTHOCYANIN_PLANKS.get())).save(recipeOutput);

        doorBuilder(ModBlocks.ANTHOCYANIN_DOOR.get(), Ingredient.of(ModBlocks.ANTHOCYANIN_PLANKS.get())).group("anthocyanin")
                .unlockedBy("has_anthocyanin_planks", has(ModBlocks.ANTHOCYANIN_PLANKS.get())).save(recipeOutput);
        trapdoorBuilder(ModBlocks.ANTHOCYANIN_TRAPDOOR.get(), Ingredient.of(ModBlocks.ANTHOCYANIN_PLANKS.get())).group("anthocyanin")
                .unlockedBy("has_anthocyanin_planks", has(ModBlocks.ANTHOCYANIN_PLANKS.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.ORNAMENTED_ANTHOCYANIN_TRAPDOOR.get(), 1)
                .requires(ModBlocks.ANTHOCYANIN_TRAPDOOR.get())
                .requires(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES.get())
                .unlockedBy("has_anthocyanin_trapdoor", has(ModBlocks.ANTHOCYANIN_TRAPDOOR.get()))
                .unlockedBy("has_blooming_anthocyanin_leaves", has(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.ORNAMENTED_ANTHOCYANIN_DOOR.get(), 1)
                .requires(ModBlocks.ANTHOCYANIN_DOOR.get())
                .requires(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES.get())
                .unlockedBy("has_anthocyanin_door", has(ModBlocks.ANTHOCYANIN_DOOR.get()))
                .unlockedBy("has_blooming_anthocyanin_leaves", has(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES.get()))
                .save(recipeOutput);
        
        // pastel
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WISTERIA_PLANKS.asItem(), 4)
                .requires(ModBlocks.WISTERIA_LOG.get())
                .unlockedBy("has_wisteria_log", has(ModBlocks.WISTERIA_LOG.asItem()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WISTERIA_WOOD.asItem(), 3)
                .requires(ModBlocks.WISTERIA_LOG.get())
                .requires(ModBlocks.WISTERIA_LOG.get())
                .requires(ModBlocks.WISTERIA_LOG.get())
                .requires(ModBlocks.WISTERIA_LOG.get())
                .unlockedBy("has_wisteria_log", has(ModBlocks.WISTERIA_LOG.asItem()))
                .save(recipeOutput);

        stairBuilder(ModBlocks.WISTERIA_STAIRS.get(), Ingredient.of(ModBlocks.WISTERIA_PLANKS.get())).group("wisteria")
                .unlockedBy("has_wisteria_planks", has(ModBlocks.WISTERIA_PLANKS.get()))
                .save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WISTERIA_SLAB.get(), ModBlocks.WISTERIA_PLANKS.get());

        buttonBuilder(ModBlocks.WISTERIA_BUTTON.get(), Ingredient.of(ModBlocks.WISTERIA_PLANKS.get())).group("wisteria")
                .unlockedBy("has_wisteria_planks", has(ModBlocks.WISTERIA_PLANKS.get())).save(recipeOutput);
        pressurePlate(recipeOutput, ModBlocks.WISTERIA_PRESSURE_PLATE.get(), ModBlocks.WISTERIA_PLANKS.get());

        fenceBuilder(ModBlocks.WISTERIA_FENCE.get(), Ingredient.of(ModBlocks.WISTERIA_PLANKS.get())).group("wisteria")
                .unlockedBy("has_wisteria_planks", has(ModBlocks.WISTERIA_PLANKS.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.WISTERIA_FENCE_GATE.get(), Ingredient.of(ModBlocks.WISTERIA_PLANKS.get())).group("wisteria")
                .unlockedBy("has_wisteria_planks", has(ModBlocks.WISTERIA_PLANKS.get())).save(recipeOutput);

        doorBuilder(ModBlocks.WISTERIA_DOOR.get(), Ingredient.of(ModBlocks.WISTERIA_PLANKS.get())).group("wisteria")
                .unlockedBy("has_wisteria_planks", has(ModBlocks.WISTERIA_PLANKS.get())).save(recipeOutput);
        trapdoorBuilder(ModBlocks.WISTERIA_TRAPDOOR.get(), Ingredient.of(ModBlocks.WISTERIA_PLANKS.get())).group("wisteria")
                .unlockedBy("has_wisteria_planks", has(ModBlocks.WISTERIA_PLANKS.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.ORNAMENTED_WISTERIA_TRAPDOOR.get(), 1)
                .requires(ModBlocks.WISTERIA_TRAPDOOR.get())
                .requires(ModBlocks.WISTERIA_LEAVES.get())
                .unlockedBy("has_wisteria_trapdoor", has(ModBlocks.WISTERIA_TRAPDOOR.get()))
                .unlockedBy("has_wisteria_leaves", has(ModBlocks.WISTERIA_LEAVES.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.ORNAMENTED_WISTERIA_DOOR.get(), 1)
                .requires(ModBlocks.WISTERIA_DOOR.get())
                .requires(ModBlocks.WISTERIA_LEAVES.get())
                .unlockedBy("has_wisteria_door", has(ModBlocks.WISTERIA_DOOR.get()))
                .unlockedBy("has_wisteria_leaves", has(ModBlocks.WISTERIA_LEAVES.get()))
                .save(recipeOutput);
    }
}
