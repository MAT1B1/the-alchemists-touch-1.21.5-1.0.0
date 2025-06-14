package com.matibi.thealchemiststouch.datagen;


import com.matibi.thealchemiststouch.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                createShapeless(RecipeCategory.BREWING, ModItems.SUSPICIOUS_STEW, 8)
                        .input(Items.POISONOUS_POTATO)
                        .input(ModItems.POISONOUS_CARROT)
                        .input(ModItems.POISONOUS_BEETROOT)
                        .input(Items.BROWN_MUSHROOM)
                        .input(Items.RED_MUSHROOM)
                        .input(Items.FERMENTED_SPIDER_EYE)
                        .input(Items.BONE_MEAL)
                        .input(Items.RAW_COPPER)
                        .input(Items.BOWL, 8)
                        .criterion(hasItem(ModItems.POISONOUS_BEETROOT), conditionsFromItem(ModItems.POISONOUS_BEETROOT))
                        .criterion(hasItem(ModItems.POISONOUS_CARROT), conditionsFromItem(ModItems.POISONOUS_CARROT))
                        .criterion(hasItem(Items.POISONOUS_POTATO), conditionsFromItem(Items.POISONOUS_POTATO))
                        .offerTo(exporter);

            }
        };
    }
    @Override
    public String getName() {
        return "recipe";
    }
}
