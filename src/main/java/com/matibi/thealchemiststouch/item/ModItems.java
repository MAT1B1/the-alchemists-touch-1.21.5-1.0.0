package com.matibi.thealchemiststouch.item;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import com.matibi.thealchemiststouch.effect.ModEffects;
import com.matibi.thealchemiststouch.effect.TerrainApplicableEffect;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ModItems {
    public static final ConsumableComponent POISON_FOOD_CONSUMABLE_COMPONENT = ConsumableComponent.builder()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.POISON, 6 * 20, 1), 0.6f))
            .build();

    public static final FoodComponent POISON_FOOD_COMPONENT = new FoodComponent.Builder()
            .nutrition(1)
            .saturationModifier(0.2f)
            .alwaysEdible()
            .build();

    public static final Item POISONOUS_CARROT = register("poisonous_carrot", new Item(new Item.Settings()
            .food(POISON_FOOD_COMPONENT, POISON_FOOD_CONSUMABLE_COMPONENT)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TheAlchemistsTouch.MOD_ID,"poisonous_carrot")))
    ));

    public static final Item POISONOUS_BEETROOT = register("poisonous_beetroot", new Item(new Item.Settings()
            .food(POISON_FOOD_COMPONENT, POISON_FOOD_CONSUMABLE_COMPONENT)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TheAlchemistsTouch.MOD_ID,"poisonous_beetroot")))
    ));

    public static final Item ALCHEMIST_CORE = register("alchemist_core", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TheAlchemistsTouch.MOD_ID,"alchemist_core")))
            .rarity(Rarity.UNCOMMON)
            .component(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT)
    ) {
        @Override
        public boolean hasGlint(ItemStack stack) {
            return true;
        }
    });

    // Runes
    public static final Item RUNE = register("rune",
            new AlchemicalRuneItem(new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TheAlchemistsTouch.MOD_ID,"rune")))
                    .maxCount(16)
            ));

    private static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TheAlchemistsTouch.MOD_ID, id), item);
    }

    private static void customFood(FabricItemGroupEntries entries) {
        entries.add(POISONOUS_CARROT);
        entries.add(POISONOUS_BEETROOT);
    }

    private static void customItem(FabricItemGroupEntries entries) {
        entries.add(ModItems.ALCHEMIST_CORE);
        entries.add(createBaseRune());
        entries.add(createRunes(ModEffects.ACID, 0));
        entries.add(createRunes(ModEffects.ACID, 1));
        entries.add(createRunes(ModEffects.ALCHEMIST, 0));
        entries.add(createRunes(ModEffects.PETRIFICATION, 0));
        entries.add(createRunes(ModEffects.PETRIFICATION, 1));
    }

    public static ItemStack createBaseRune() {
        ItemStack stack = new ItemStack(ModItems.RUNE);
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
            ItemStack rune = new ItemStack(ModItems.RUNE);

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
        TheAlchemistsTouch.LOGGER.info("Registering mod items for " + TheAlchemistsTouch.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::customFood);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::customItem);
    }
}