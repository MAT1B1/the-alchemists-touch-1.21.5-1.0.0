package com.matibi.thealchemiststouch.rune;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import com.matibi.thealchemiststouch.effect.ModEffects;
import com.matibi.thealchemiststouch.effect.TerrainApplicableEffect;
import com.matibi.thealchemiststouch.item.AlchemicalRuneItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ModRunes {
    public static final Item RUNE = Registry.register(Registries.ITEM,
            Identifier.of(TheAlchemistsTouch.MOD_ID, "rune"),
            new AlchemicalRuneItem(new Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TheAlchemistsTouch.MOD_ID,"rune")))
                .maxCount(16)
        ));

    private static void customItem(FabricItemGroupEntries entries) {
        entries.add(RUNE);
        entries.add(createBaseRune());
        entries.add(createRunes(ModEffects.ACID, 0));
        entries.add(createRunes(ModEffects.ACID, 1));
        entries.add(createRunes(ModEffects.ALCHEMIST, 0));
        entries.add(createRunes(ModEffects.PETRIFICATION, 0));
        entries.add(createRunes(ModEffects.PETRIFICATION, 1));
        entries.add(createRunes(ModEffects.IGNITION, 0));
    }

    public static ItemStack createBaseRune() {
        ItemStack stack = new ItemStack(ModRunes.RUNE);
        stack.set(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);
        String translationKey = "item.the-alchemists-touch.rune.effect.default";
        stack.set(DataComponentTypes.CUSTOM_NAME,
                Text.empty().append(Text.translatable(translationKey)).styled(style ->
                        style.withItalic(false)));
        return stack;
    }

    public static ItemStack createRunes(RegistryEntry<StatusEffect> entry, int amplifier) {
        StatusEffect effect = entry.value();
        if (effect instanceof TerrainApplicableEffect &&
                Objects.requireNonNull(Registries.STATUS_EFFECT.getId(effect)).getNamespace().equals(TheAlchemistsTouch.MOD_ID)) {
            ItemStack rune = new ItemStack(ModRunes.RUNE);

            rune.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(
                    Optional.empty(),
                    Optional.empty(),
                    List.of(new StatusEffectInstance(entry, 1, amplifier)),
                    Optional.empty()
            ));
            String effectId = Objects.requireNonNull(Registries.STATUS_EFFECT.getId(effect)).getPath();
            String translationKey = "item.the-alchemists-touch.rune.effect." + effectId;
            rune.set(DataComponentTypes.CUSTOM_NAME,
                    Text.empty().append(Text.translatable(translationKey)).styled(style ->
                            style.withItalic(false)));

            return rune;
        }
        return ItemStack.EMPTY;
    }

    public static void register() {
        TheAlchemistsTouch.LOGGER.info("Registering mod runes for " + TheAlchemistsTouch.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModRunes::customItem);
    }
}
