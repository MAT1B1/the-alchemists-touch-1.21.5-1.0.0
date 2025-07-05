package com.matibi.thealchemiststouch.recipe;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CombinationRecipe extends SpecialCraftingRecipe {
    public CombinationRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    public boolean matches(CraftingRecipeInput input, World world) {
        int validPotionCount = 0;
        Item basePotionItem = null;

        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                ItemStack stack = input.getStackInSlot(x, y);
                if (!stack.isEmpty()) {
                    if (!isPotionItem(stack)) return false;

                    PotionContentsComponent component = stack.get(DataComponentTypes.POTION_CONTENTS);
                    if (component == null || !component.hasEffects()) return false;

                    if (basePotionItem == null)
                        basePotionItem = stack.getItem();
                    else if (stack.getItem() != basePotionItem)
                        return false;

                    validPotionCount++;
                }
            }
        }

        return validPotionCount > 1;
    }

    private boolean isPotionItem(ItemStack stack) {
        return stack.isOf(Items.POTION)
                || stack.isOf(Items.SPLASH_POTION)
                || stack.isOf(Items.LINGERING_POTION);
    }

    public ItemStack craft(CraftingRecipeInput craftingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup) {
        Item firstPotion = null;
        List<StatusEffectInstance> effects = new ArrayList<>();

        // Parcourir toutes les potions dans la grille de craft
        for (int i = 0; i < craftingRecipeInput.getHeight(); i++) {
            for (int j = 0; j < craftingRecipeInput.getWidth(); j++) {
                ItemStack itemStack = craftingRecipeInput.getStackInSlot(j, i);
                if (!itemStack.isEmpty() && isPotionItem(itemStack)) {
                    if (firstPotion == null)
                        firstPotion = itemStack.getItem();

                    PotionContentsComponent dataComponent = itemStack.get(DataComponentTypes.POTION_CONTENTS);
                    if (dataComponent != null)
                        dataComponent.getEffects().forEach(effects::add);
                }
            }
        }
        if (firstPotion == null) return ItemStack.EMPTY;

        ItemStack resultStack = new ItemStack(firstPotion);

        PotionContentsComponent combinedComponent = new PotionContentsComponent(
                Optional.empty(), // Pas de potion vanilla
                Optional.empty(), // Pas de couleur personnalisée
                effects,          // Liste des effets combinés
                Optional.of("mixed")  // Pas de nom personnalisé
        );

        // Ajouter les effets à la potion résultante
        resultStack.set(DataComponentTypes.POTION_CONTENTS, combinedComponent);

        return resultStack.copyWithCount(craftingRecipeInput.getStackCount());
    }

    @Override
    public RecipeSerializer<CombinationRecipe> getSerializer() {
        return ModRecipeSerializer.COMBINATION;
    }
}
