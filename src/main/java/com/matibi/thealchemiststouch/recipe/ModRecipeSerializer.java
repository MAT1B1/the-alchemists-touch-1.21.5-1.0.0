package com.matibi.thealchemiststouch.recipe;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeSerializer {
    public static final SpecialCraftingRecipe.SpecialRecipeSerializer<RuneRecipe> RUNE_FROM_POTION =
            new SpecialCraftingRecipe.SpecialRecipeSerializer<>(RuneRecipe::new);

    public static final SpecialCraftingRecipe.SpecialRecipeSerializer<CombinationRecipe> COMBINATION =
            new SpecialCraftingRecipe.SpecialRecipeSerializer<>(CombinationRecipe::new);

    public static void register() {
        Registry.register(Registries.RECIPE_SERIALIZER,
                Identifier.of(TheAlchemistsTouch.MOD_ID, "rune_from_potion"),
                ModRecipeSerializer.RUNE_FROM_POTION);

        Registry.register(Registries.RECIPE_SERIALIZER,
                Identifier.of(TheAlchemistsTouch.MOD_ID, "combination"),
                ModRecipeSerializer.COMBINATION);

    }
}
