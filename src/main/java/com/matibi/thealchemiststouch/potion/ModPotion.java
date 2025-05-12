package com.matibi.thealchemiststouch.potion;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import com.matibi.thealchemiststouch.effect.ModEffects;
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
            StatusEffects.LEVITATION, 20 * 10, 0);

    public static RegistryEntry<Potion> GLOWING = registerPotion("glowing",
            StatusEffects.GLOWING, 20 * 60, 0);

    public static RegistryEntry<Potion> ALCOHOL = registerPotion("alcohol",
            StatusEffects.NAUSEA, 20 * 60, 0);

    public static RegistryEntry<Potion> DARKNESS = registerPotion("darkness",
            StatusEffects.DARKNESS, 20 * 30, 0);

    public static RegistryEntry<Potion> LONG_LEG = registerPotion("long_leg",
            ModEffects.LONG_LEG, 20 * 60 * 3, 0);

    public static RegistryEntry<Potion> LIQUID_WALKER = registerPotion("liquid_walker",
            ModEffects.LIQUID_WALKER, 20 * 60 * 3, 0);

    public static RegistryEntry<Potion> ORE_SENSE = registerPotion("ore_sense",
            ModEffects.ORE_SENSE, 20 * 60 * 3, 0);

    public static RegistryEntry<Potion> RESONANCE = registerPotion("resonance",
            ModEffects.RESONANCE, 20 * 60 * 3, 0);

    public static RegistryEntry<Potion> REACTIVATION = registerPotion("reactivation",
            ModEffects.REACTIVATION, 20 * 60 * 3, 0);

    public static RegistryEntry<Potion> PURIFICATION = registerPotion("purification",
            ModEffects.PURIFICATION, 20 * 60 * 3, 0);

    public static RegistryEntry<Potion> PETRIFICATION = registerPotion("petrification",
            ModEffects.PETRIFICATION, 20 * 60, 0);

    public static RegistryEntry<Potion> ACID = registerPotion("acid",
            ModEffects.ACID, 20 * 45, 0);

    public static RegistryEntry<Potion> IGNITION = registerPotion("ignition",
            ModEffects.IGNITION, 20 * 45, 0);

    public static RegistryEntry<Potion> TELEPORTATION = registerPotion("teleportation",
            ModEffects.TELEPORTAION, 1, 0);

    public static RegistryEntry<Potion> THORNS = registerPotion("thorns",
            ModEffects.THORNS, 20 * 60 * 3 , 0);

    public static RegistryEntry<Potion> BRAIN_WASHING = registerPotion("brain_washing",
            ModEffects.BRAIN_WASHING, 20 * 60 * 3 , 0);

    public static void register() {
        TheAlchemistsTouch.LOGGER.info("Registering mod potions for " + TheAlchemistsTouch.MOD_ID);

        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            // changement de base, mais moins efficace que de farmer des nether wart
            builder.registerPotionRecipe(Potions.WATER, Items.INK_SAC, Potions.AWKWARD);
            // TODO : trouver un meilleur ingredient

            // potions accessible early
            builder.registerPotionRecipe(Potions.AWKWARD, Items.MAGMA_BLOCK, Potions.FIRE_RESISTANCE);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.GOLDEN_APPLE, Potions.REGENERATION);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.FIREWORK_STAR, Potions.STRENGTH);

            // potion custom
            builder.registerPotionRecipe(Potions.SLOW_FALLING, Items.FEATHER, ModPotion.LEVITATION);
            builder.registerPotionRecipe(Potions.NIGHT_VISION, Items.GLOW_BERRIES, ModPotion.GLOWING);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.SWEET_BERRIES, ModPotion.ALCOHOL);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.INK_SAC, ModPotion.DARKNESS);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.BAMBOO, ModPotion.LONG_LEG);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.LILY_PAD, ModPotion.LIQUID_WALKER);
            builder.registerPotionRecipe(Potions.NIGHT_VISION, Items.IRON_ORE, ModPotion.ORE_SENSE);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.ECHO_SHARD, ModPotion.RESONANCE);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.CLOCK, ModPotion.REACTIVATION);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.MILK_BUCKET, ModPotion.PURIFICATION);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.OBSIDIAN, ModPotion.PETRIFICATION);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.ROTTEN_FLESH, ModPotion.ACID);
            builder.registerPotionRecipe(Potions.FIRE_RESISTANCE, Items.FERMENTED_SPIDER_EYE, ModPotion.IGNITION);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.ENDER_PEARL, ModPotion.TELEPORTATION);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.CACTUS, ModPotion.THORNS);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.CACTUS, ModPotion.BRAIN_WASHING);
        });
    }

    private static RegistryEntry<Potion> registerPotion(String name,
                                                        RegistryEntry<net.minecraft.entity.effect.StatusEffect> effect,
                                                        int duration,
                                                        int amplifier) {
        return Registry.registerReference(Registries.POTION, Identifier.of(TheAlchemistsTouch.MOD_ID, name),
                new Potion(name, new StatusEffectInstance(effect, duration, amplifier)));
    }
}
