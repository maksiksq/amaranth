package dev.maksiks.amaranth.datagen;

import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // temp
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MAFIA_BLOB.get(), 1)
                .requires(ModItems.HEXFRUIT.get())
                .unlockedBy("has_hexfruit", has(ModItems.HEXFRUIT.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MAFIA_BLOB.get(), 1)
                .requires(ModBlocks.MYSTIC_LEAVES.get())
                .unlockedBy("has_hexfruit", has(ModBlocks.MYSTIC_LEAVES.get()))
                .save(recipeOutput, "amaranth:mafia_blob_from_leaves");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BEANIE_BLOB.get())
                .pattern("HHH")
                .pattern("HHH")
                .pattern("HHH")
                .define('H', ModItems.HEXFRUIT.get())
                .unlockedBy("has_hexfruit", has(ModItems.HEXFRUIT.get()))
                .save(recipeOutput);

        // yoink a custom method for smelting later so it's inside neo's folder because it's hardcoded to be in mc

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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.ORNAMENTED_ANTHOCYANIN_TRAPDOOR.get(), 1)
                .requires(ModBlocks.ANTHOCYANIN_TRAPDOOR.get())
                .requires(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES.get())
                .unlockedBy("has_anthocyanin_trapdoor", has(ModBlocks.ANTHOCYANIN_TRAPDOOR.get()))
                .unlockedBy("has_anthocyanin_trapdoor", has(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES.get()))
                .save(recipeOutput);
    }
}
