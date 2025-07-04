package com.matibi.thealchemiststouch.recipe;

import net.minecraft.recipe.SpecialCraftingRecipe;

public class ModRecipeSerializer {
    public static final SpecialCraftingRecipe.SpecialRecipeSerializer<RuneRecipe> RUNE_FROM_POTION =
            new SpecialCraftingRecipe.SpecialRecipeSerializer<>(RuneRecipe::new);
}
