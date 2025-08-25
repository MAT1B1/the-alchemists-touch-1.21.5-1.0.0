package com.matibi.thealchemiststouch.util;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> DETECTABLE_ORES = createTag("detectable_ores");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(TheAlchemistsTouch.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> IMBUEABLE_WEAPONS = createTag("imbuable_weapons");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(TheAlchemistsTouch.MOD_ID, name));
        }
    }
}
