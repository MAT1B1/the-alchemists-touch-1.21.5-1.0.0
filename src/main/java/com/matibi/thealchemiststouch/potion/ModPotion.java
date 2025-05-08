package com.matibi.thealchemiststouch.potion;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModPotion {
    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(TheAlchemistsTouch.MOD_ID, name), potion);
    }

    public static void register() {
        TheAlchemistsTouch.LOGGER.info("Registering potions for " + TheAlchemistsTouch.MOD_ID);
        FabricBrewingRecipeRegistryBuilder.BUILD.register( builder -> {
            // changement de base, mais moins efficace que de farmer des nether wart
            builder.registerPotionRecipe(Potions.WATER, Items.INK_SAC, Potions.AWKWARD);

            // potions accessible early
            builder.registerPotionRecipe(Potions.AWKWARD, Items.MAGMA_BLOCK, Potions.FIRE_RESISTANCE);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.GOLDEN_APPLE, Potions.REGENERATION);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.GLOW_BERRIES, Potions.STRENGTH);
        });
    }
}
