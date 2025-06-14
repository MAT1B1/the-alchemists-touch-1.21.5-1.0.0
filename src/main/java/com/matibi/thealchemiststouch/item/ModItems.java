package com.matibi.thealchemiststouch.item;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item MIXED_POTION = register("mixed_potion", new MixedPotionItem(new Item.Settings()));

    private static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TheAlchemistsTouch.MOD_ID, id), item);
    }

    public static void registerItems() {
        // For now just triggers static init
    }
}