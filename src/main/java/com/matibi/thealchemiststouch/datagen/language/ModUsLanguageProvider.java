package com.matibi.thealchemiststouch.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModUsLanguageProvider extends FabricLanguageProvider {
    TranslationBuilder t;

    public ModUsLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder t_param) {
        t = t_param;
        // Vanilla effects
        registerVanilla("levitation", "Levitation");
        registerVanilla("glowing", "Glowing");
        registerVanilla("darkness", "Darkness");
        registerVanilla("haste", "Haste");
        registerVanilla("mining_fatigue", "Mining Fatigue");
        t.add("item.minecraft.potion.effect.alcohol", "Alcohol");
        t.add("item.minecraft.splash_potion.effect.alcohol", "Splash Alcohol");
        t.add("item.minecraft.lingering_potion.effect.alcohol", "Lingering Alcohol");
        t.add("item.minecraft.tipped_arrow.effect.alcohol", "Arrow of Alcohol");

        // Custom effects without rune
        register("saturation", "Saturation");
        register("long_leg", "Long legs");
        register("liquid_walker", "Liquid Walker");
        register("ore_sense", "Ore Sense");
        register("resonance", "Resonance");
        register("reactivation", "Reactivation");
        register("purification", "Purification");
        register("teleportation", "Teleportation");
        register("thorns", "Thorns");
        register("brain_washing", "Brain Washing");
        register("frost", "Frost");
        register("death", "Death");
        register("double_health", "Double Health");
        register("resurrection", "Resurrection");
        register("infinity", "Infinity");
        register("vitality", "Vitality");
        register("long_cooldown", "Long Cooldown");
        register("short_cooldown", "Short Cooldown");
        register("masking", "Hidden Effect");

        // Custom effects with rune
        registerWithRune("petrification", "Petrification");                       // -> Rune of Petrification
        registerWithRune("acid", "Acidity");
        registerWithRune("ignition", "Ignition");
        registerWithRune("alchemist", "Alchemist");

        // ---- Other non-effect items ----
        t.add("item.the-alchemists-touch.poisonous_carrot", "Poisonous Carrot");
        t.add("item.the-alchemists-touch.poisonous_beetroot", "Poisonous Beetroot");
        t.add("item.the-alchemists-touch.alchemist_core", "Alchemist Core");
        t.add("item.the-alchemists-touch.rune.effect.empty", "Rune");
        t.add("item.the-alchemists-touch.rune.effect.mixed", "Multi-effect Rune");
        t.add("item.minecraft.potion.effect.mixed", "Multi-effect Potion");
        t.add("item.minecraft.splash_potion.effect.mixed", "Multi-effect Splash Potion");
        t.add("item.minecraft.lingering_potion.effect.mixed", "Multi-effect Lingering Potion");
        t.add("item.minecraft.tipped_arrow.effect.mixed", "Multi-effect Arrow");

        // Special messages
        t.add("item.the-alchemists-touch.rune.block_only", "Runes can only be used on blocks");
        t.add("item.the-alchemists-touch.rune.block_not_good", "The block is not compatible");
        t.add("itemGroup.the-alchemists-touch.alchemy", "Alchemy");
        t.add("splash.the-alchemists-touch.magic", "Alchemy is power !!!");
        t.add("splash.the-alchemists-touch.thanks", "Thank you for supporting me LivelyBadGood");
    }

    private void registerWithRune(String id, String name) {
        register(id, name);
        t.add("item.the-alchemists-touch.rune.effect." + id, "Rune of " + name);
    }

    private void registerVanilla(String id, String name) {
        t.add("item.minecraft.potion.effect." + id, "Potion of " + name);
        t.add("item.minecraft.splash_potion.effect." + id, "Splash Potion of " + name);
        t.add("item.minecraft.lingering_potion.effect." + id, "Lingering Potion of " + name);
        t.add("item.minecraft.tipped_arrow.effect." + id, "Arrow of " + name);
    }

    private void register(String id, String name) {
        registerVanilla(id, name);
        t.add("effect.the-alchemists-touch." + id, name);
    }
}
