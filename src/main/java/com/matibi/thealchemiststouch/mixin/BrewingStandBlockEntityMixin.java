package com.matibi.thealchemiststouch.mixin;

import com.matibi.thealchemiststouch.item.AlchemicalRuneItem;
import com.matibi.thealchemiststouch.item.ModItems;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

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