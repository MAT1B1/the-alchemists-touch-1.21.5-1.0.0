package com.matibi.thealchemiststouch.mixin.brewingNpotion;

import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BrewingStandBlockEntity.class)
public interface BrewingStandAccessor {
    @Accessor("fuel")
    int getFuel();

    @Accessor("fuel")
    void setFuel(int value);

    @Accessor("inventory")
    DefaultedList<ItemStack> getInventory();
}
