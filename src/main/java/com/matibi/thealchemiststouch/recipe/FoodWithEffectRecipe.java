package com.matibi.thealchemiststouch.recipe;

import com.matibi.thealchemiststouch.effect.ModEffects;
import it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
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
import java.util.concurrent.atomic.AtomicBoolean;

public class FoodWithEffectRecipe extends SpecialCraftingRecipe {
    public FoodWithEffectRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        int potionCount = 0;
        int foodCount   = 0;

        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                ItemStack stack = input.getStackInSlot(x, y);
                if (stack.isEmpty()) continue;

                if (isPotionItem(stack)) {
                    PotionContentsComponent c = stack.get(DataComponentTypes.POTION_CONTENTS);
                    if (c == null) return false;
                    potionCount++;
                } else if (isFood(stack))
                    foodCount++;
                else
                    return false;
                if (foodCount > 1) return false;
            }
        }
        return potionCount >= 1 && foodCount == 1;
    }

    private boolean isPotionItem(ItemStack stack) {
        return stack.isOf(Items.POTION)
                || stack.isOf(Items.SPLASH_POTION)
                || stack.isOf(Items.LINGERING_POTION);
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        ItemStack food = ItemStack.EMPTY;
        AtomicBoolean has_masking = new AtomicBoolean(false);

        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                ItemStack s = input.getStackInSlot(x, y);
                if (!s.isEmpty() && isFood(s)) { food = s; break; }
            }
            if (!food.isEmpty()) break;
        }
        if (food.isEmpty()) return ItemStack.EMPTY;

        List<StatusEffectInstance> effects = new ArrayList<>();
        PotionContentsComponent existing = food.getOrDefault(
                DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT
        );

        existing.getEffects().forEach(e -> {
            if (e.equals(ModEffects.MASKING)) has_masking.set(true);
            effects.add(e);
        });

        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                ItemStack s = input.getStackInSlot(x, y);
                if (s.isEmpty() || !isPotionItem(s)) continue;

                PotionContentsComponent incoming = s.getOrDefault(
                        DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT
                );

                incoming.getEffects().forEach(e -> {
                    if (e.equals(ModEffects.MASKING)) has_masking.set(true);
                    effects.add(e);
                });
            }
        }

        PotionContentsComponent merged = new PotionContentsComponent(
                Optional.empty(), // Pas de potion vanilla
                Optional.empty(), // Pas de couleur personnalisée
                effects,          // Liste des effets combinés
                Optional.empty() // Pas de nom personnalisé
        );

        ItemStack result = food.copyWithCount(1);
        result.set(DataComponentTypes.POTION_CONTENTS, merged);

        if (has_masking.get()) {
            TooltipDisplayComponent tdc = result.getOrDefault(
                    DataComponentTypes.TOOLTIP_DISPLAY,
                    TooltipDisplayComponent.DEFAULT
            );
            var hidden = new ReferenceLinkedOpenHashSet<>(tdc.hiddenComponents());
            hidden.add(DataComponentTypes.POTION_CONTENTS);

            result.set(DataComponentTypes.TOOLTIP_DISPLAY, new TooltipDisplayComponent(false, hidden));
        }

        FoodComponent baseFood = food.get(DataComponentTypes.FOOD);
        if (baseFood != null)
            result.set(DataComponentTypes.FOOD,
                new FoodComponent(baseFood.nutrition(), baseFood.saturation(), true));

        var consumable = food.get(DataComponentTypes.CONSUMABLE);
        if (consumable != null) result.set(DataComponentTypes.CONSUMABLE, consumable);

        return result;
    }

    private boolean isFood(ItemStack stack) {
        return stack.getItem().getComponents().contains(DataComponentTypes.FOOD);
    }

    @Override
    public RecipeSerializer<FoodWithEffectRecipe> getSerializer() {
        return ModRecipeSerializer.FOOD_WITH_EFFECT;
    }
}
