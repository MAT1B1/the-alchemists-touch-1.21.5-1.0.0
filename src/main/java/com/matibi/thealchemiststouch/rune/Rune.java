package com.matibi.thealchemiststouch.rune;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import com.matibi.thealchemiststouch.effect.TerrainApplicableEffect;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.*;

public record Rune(Identifier id, RegistryEntry<StatusEffect> effect, int amplifier) {

    public static ItemStack getItemStack(RegistryEntry<Rune> entry) {
        Rune rune = entry.value();

        return getItemStack(rune.effect(), rune.amplifier());
    }

    public static ItemStack getItemStack(PotionContentsComponent contents, int number) {

        if (contents == null || !contents.hasEffects())
            return ItemStack.EMPTY;

        for (StatusEffectInstance effect : contents.getEffects()) {
            RegistryEntry<StatusEffect> effectType = effect.getEffectType();
            int amplifier = effect.getAmplifier();
            return getItemStack(effectType, amplifier, number);
        }

        return ItemStack.EMPTY;
    }

    public static ItemStack getItemStack(PotionContentsComponent contents) {
        return  getItemStack(contents, 1);
    }

    public static ItemStack getItemStack(RegistryEntry<StatusEffect> effect, int amplifier) {
        return getItemStack(effect, amplifier, 1);
    }

    public static ItemStack getItemStack(RegistryEntry<StatusEffect> effect, int amplifier, int number) {
        if (effect.value() instanceof TerrainApplicableEffect &&
                Objects.requireNonNull(Registries.STATUS_EFFECT.getId(effect.value())).getNamespace()
                        .equals(TheAlchemistsTouch.MOD_ID)) {

            ItemStack stack = new ItemStack(ModRunes.RUNE, number);

            // Applique l'effet de potion
            stack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(
                    Optional.empty(),
                    Optional.empty(),
                    List.of(new StatusEffectInstance(effect, 1, amplifier)),
                    Optional.empty()
            ));

            // Nom personnalisé basé sur l'effet
            String effectId = Objects.requireNonNull(Registries.STATUS_EFFECT.getId(effect.value())).getPath();
            String translationKey = "item.the-alchemists-touch.rune.effect." + effectId;
            stack.set(DataComponentTypes.CUSTOM_NAME,
                    Text.empty().append(Text.translatable(translationKey)).styled(style -> style.withItalic(false)));

            // Couleur selon l'effet
            stack.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(effect.value().getColor()));

            // Cache certains composants dans le tooltip
            SequencedSet<ComponentType<?>> hidden = new LinkedHashSet<>();
            hidden.add(DataComponentTypes.ATTRIBUTE_MODIFIERS);
            hidden.add(DataComponentTypes.DYED_COLOR);
            stack.set(DataComponentTypes.TOOLTIP_DISPLAY, new TooltipDisplayComponent(false, hidden));

            return stack;
        }
        return ItemStack.EMPTY;
    }
}
