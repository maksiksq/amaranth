package dev.maksiks.amaranth.datagen;

import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.models.model.TextureMapping.pattern;

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
                .requires(ModBlocks.MYSTIC_LEAVES_BLOCK.get())
                .unlockedBy("has_hexfruit", has(ModBlocks.MYSTIC_LEAVES_BLOCK.get()))
                .save(recipeOutput, "amaranth:mafia_blob_from_leaves");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BEANIE_BLOB.get())
                .pattern("HHH")
                .pattern("HHH")
                .pattern("HHH")
                .define('H', ModItems.HEXFRUIT.get())
                .unlockedBy("has_hexfruit", has(ModItems.HEXFRUIT.get()))
                .save(recipeOutput);

        // yoink a custom method for smelting later so it's inside neo's folder because it's hardcoded to be in mc
    }
}
