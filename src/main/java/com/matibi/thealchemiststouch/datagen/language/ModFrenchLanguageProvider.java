package com.matibi.thealchemiststouch.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModFrenchLanguageProvider extends FabricLanguageProvider {
    TranslationBuilder t;

    public ModFrenchLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "fr_fr", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder t_param) {
        t = t_param;
        // Effets vanilla déjà existants
        registerVanilla("levitation", "de Lévitation");
        registerVanilla("glowing", "de Surbrillance");
        registerVanilla("darkness", "de Obscurité");
        registerVanilla("haste", "de Célérité");
        registerVanilla("mining_fatigue", "de Fatigue de minage");
        t.add("item.minecraft.potion.effect.alcohol", "Alcool");
        t.add("item.minecraft.splash_potion.effect.alcohol", "Alcohol jetable");
        t.add("item.minecraft.lingering_potion.effect.alcohol", "Alcool persistant");
        t.add("item.minecraft.tipped_arrow.effect.alcohol", "Flèche d'Alcool");

        // Effets custom simples (pas de rune)
        register("saturation", "Saturation");
        register("long_leg", "Grandes jambes");
        register("liquid_walker", "Marche sur liquide");
        register("ore_sense", "Détection de minerais");
        register("resonance", "Résonance");
        register("reactivation", "Réactivation");
        register("purification", "Purification");
        register("teleportation", "Téléportation");
        register("thorns", "Épines", "d'Épines");
        register("brain_washing", "Lavage de cerveau");
        register("frost", "Givre");
        register("death", "Mort");
        register("double_health", "Double vie");
        register("resurrection", "Résurrection");
        register("infinity", "Infinité","d'Infinité");
        register("vitality", "Vitalité");
        register("long_cooldown", "Cooldown allongé");
        register("short_cooldown", "Cooldown réduit");
        register("masking", "Voile d'oublie");

        // Effets custom avec rune
        registerWithRune("petrification", "Pétrification");
        registerWithRune("acid", "Acidité", "d'Acidité");
        registerWithRune("ignition", "Ignition", "d'Ignition");
        registerWithRune("alchemist", "Alchimiste", "de l'Alchimiste");

        // ---- Autres items qui ne sont pas des effets ----
        t.add("item.the-alchemists-touch.poisonous_carrot", "Carotte empoisonnée");
        t.add("item.the-alchemists-touch.poisonous_beetroot", "Betterave empoisonnée");
        t.add("item.the-alchemists-touch.alchemist_core", "Noyau d'Alchimiste");
        t.add("item.the-alchemists-touch.rune.effect.empty", "Rune");
        t.add("item.the-alchemists-touch.rune.effect.mixed", "Rune multi-effest");
        registerVanilla("mixed", "multi-effets");

        // Messages spéciaux
        t.add("item.the-alchemists-touch.rune.block_only", "Les runes ne peuvent être utilisées que sur des blocs");
        t.add("item.the-alchemists-touch.rune.block_not_good", "Le bloc n'est pas compatible");
        t.add("itemGroup.the-alchemists-touch.alchemy", "Alchimie");
        t.add("splash.the-alchemists-touch.magic", "L'Alchimie c'est le pouvoir !!!");
        t.add("splash.the-alchemists-touch.thanks", "Merci de me supporter LivelyBadGood");
    }

    private void registerWithRune(String id, String name) {
        registerWithRune(id, name, "de " + name);
    }

    private void registerWithRune(String id, String effect_name, String name) {
        register(id, effect_name, name);
        t.add("item.the-alchemists-touch.rune.effect." + id, "Rune " + name);
    }

    private void registerVanilla(String id, String name) {
        t.add("item.minecraft.potion.effect." + id, "Potion " + name);
        t.add("item.minecraft.splash_potion.effect." + id, "Potion jetable " + name);
        t.add("item.minecraft.lingering_potion.effect." + id, "Potion persistante " + name);
        t.add("item.minecraft.tipped_arrow.effect." + id, "Flèche " + name);
    }

    private void register(String id, String name) {
        register(id, name, "de " + name);
    }

    private void register(String id, String effect_name, String name) {
        registerVanilla(id, name);
        t.add("effect.the-alchemists-touch." + id, effect_name);
    }
}
