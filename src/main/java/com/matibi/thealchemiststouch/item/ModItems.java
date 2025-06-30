package com.matibi.thealchemiststouch.item;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import com.matibi.thealchemiststouch.effect.ModEffects;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
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
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

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

    public static final Item ACID_RUNE = register("acid_rune",
            new AlchemicalRuneItem(new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TheAlchemistsTouch.MOD_ID,"acid_rune")))
                    .maxCount(16),
                    new StatusEffectInstance(ModEffects.ACID, 1, 1)
            ));

    public static final Item PETRIFICATION_RUNE = register("petrification_rune",
            new AlchemicalRuneItem(new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TheAlchemistsTouch.MOD_ID,"petrification_rune")))
                    .maxCount(16),
                    new StatusEffectInstance(ModEffects.PETRIFICATION, 1, 0)
            ));

    public static final Item ALCHEMIST_RUNE = register("alchemist_rune",
            new AlchemicalRuneItem(new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TheAlchemistsTouch.MOD_ID,"alchemist_rune")))
                    .maxCount(16),
                    new StatusEffectInstance(ModEffects.ALCHEMIST, 1, 0)
            ));

    // Pour la rune de base, pas d'effet
    public static final Item RUNE = register("rune",
            new AlchemicalRuneItem(new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TheAlchemistsTouch.MOD_ID,"rune")))
                    .maxCount(16),
                    null
            ));

    private static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TheAlchemistsTouch.MOD_ID, id), item);
    }

    private static void customFood(FabricItemGroupEntries entries) {
        entries.add(POISONOUS_CARROT);
        entries.add(POISONOUS_BEETROOT);
    }

    private static void customItem(FabricItemGroupEntries entries) {
        entries.add(RUNE);
        entries.add(ACID_RUNE);
        entries.add(PETRIFICATION_RUNE);
        entries.add(ALCHEMIST_RUNE);
        entries.add(ALCHEMIST_CORE);
    }

    public static void register() {
        TheAlchemistsTouch.LOGGER.info("Registering mod items for " + TheAlchemistsTouch.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::customFood);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::customItem);
    }
}