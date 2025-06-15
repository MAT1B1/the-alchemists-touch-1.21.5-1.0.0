package com.matibi.thealchemiststouch.mixin;

import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingStandBlockEntity.class)
public abstract class BrewingStandBlockEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private static void injectCustomFuel(World world, BlockPos pos, BlockState state, BrewingStandBlockEntity blockEntity, CallbackInfo ci) {
        BrewingStandAccessor accessor = (BrewingStandAccessor) blockEntity;

        ItemStack fuelStack = accessor.getInventory().get(4);

        if (accessor.getFuel() < 20 && fuelStack.getItem() == Items.LAVA_BUCKET) {
            accessor.setFuel(accessor.getFuel() + 1);
            accessor.getInventory().set(4, new ItemStack(Items.BUCKET));
            world.updateListeners(pos, state, state, 3);
        }
    }
}