package com.matibi.thealchemiststouch.potion;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModPotion {
    public static RegistryEntry<Potion> LEVITATION = registerPotion("levitation",
            new Potion("levitation",
                    new StatusEffectInstance(StatusEffects.LEVITATION, 20 * 10)));

    public static RegistryEntry<Potion> GLOWING = registerPotion("glowing",
            new Potion("glowing",
                    new StatusEffectInstance(StatusEffects.GLOWING, 20 * 60)));

    public static RegistryEntry<Potion> ALCHOOL = registerPotion("alchool",
            new Potion("alchool",
                    new StatusEffectInstance(StatusEffects.NAUSEA, 20 * 60)));

    public static RegistryEntry<Potion> DARKNESS = registerPotion("darkness",
            new Potion("darkness",
                    new StatusEffectInstance(StatusEffects.DARKNESS, 20 * 30)));

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
            builder.registerPotionRecipe(Potions.AWKWARD, Items.FIREWORK_STAR, Potions.STRENGTH);

            // potion custom
            builder.registerPotionRecipe(Potions.SLOW_FALLING, Items.FEATHER, ModPotion.LEVITATION);
            builder.registerPotionRecipe(Potions.NIGHT_VISION, Items.GLOW_BERRIES, ModPotion.GLOWING);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.SWEET_BERRIES, ModPotion.ALCHOOL);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.SCULK, ModPotion.DARKNESS);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.SCULK, ModPotion.DARKNESS);
        });
    }
}
