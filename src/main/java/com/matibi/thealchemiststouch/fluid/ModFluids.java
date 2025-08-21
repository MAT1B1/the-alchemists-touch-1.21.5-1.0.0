package com.matibi.thealchemiststouch.fluid;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


public class ModFluids {

    public static final FlowableFluid STILL_POTION = Registry.register(Registries.FLUID,
            Identifier.of(TheAlchemistsTouch.MOD_ID, "potion"), new PotionFluid.Still());
    public static final FlowableFluid FLOWING_POTION = Registry.register(Registries.FLUID,
            Identifier.of(TheAlchemistsTouch.MOD_ID, "flowing_potion"), new PotionFluid.Flowing());

    public static final FluidBlock POTION_BLOCK = Registry.register(Registries.BLOCK,
            Identifier.of(TheAlchemistsTouch.MOD_ID, "potion_block"),
            new FluidBlock(ModFluids.STILL_POTION, AbstractBlock.Settings.copy(Blocks.WATER)
                    .replaceable().liquid()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TheAlchemistsTouch.MOD_ID, "potion_block")))));
    public static final Item POTION_BUCKET = Registry.register(Registries.ITEM,
            Identifier.of(TheAlchemistsTouch.MOD_ID, "potion_bucket"),
            new BucketItem(ModFluids.STILL_POTION, new Item.Settings()
                    .recipeRemainder(Items.BUCKET)
                    .maxCount(1)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TheAlchemistsTouch.MOD_ID, "potion_bucket")))));

    public static void register() {
        TheAlchemistsTouch.LOGGER.info("Registering mod fluids for " + TheAlchemistsTouch.MOD_ID);
    }
}
