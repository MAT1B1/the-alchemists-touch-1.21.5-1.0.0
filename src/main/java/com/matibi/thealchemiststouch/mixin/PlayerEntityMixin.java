package com.matibi.thealchemiststouch.mixin;


import com.matibi.thealchemiststouch.effect.ModEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void cancelAttackIfPetrified(Entity target, CallbackInfo ci) {
        PlayerEntity self = (PlayerEntity)(Object)this;
        if (self.hasStatusEffect(ModEffects.PETRIFICATION)) {
            ci.cancel();
        }
    }

    @Inject(method = "shouldCancelInteraction", at = @At("HEAD"), cancellable = true)
    private void cancelInteractIfPetrified(CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity self = (PlayerEntity)(Object)this;
        if (self.hasStatusEffect(ModEffects.PETRIFICATION))
            cir.setReturnValue(false);
    }

    @Inject(method = "canInteractWithEntity", at = @At("HEAD"), cancellable = true)
    private void cancelEntityInteractIfPetrified(Entity entity, double additionalRange, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity self = (PlayerEntity)(Object)this;
        if (self.hasStatusEffect(ModEffects.PETRIFICATION))
            cir.setReturnValue(false);
    }

    @Inject(method = "canPlaceOn", at = @At("HEAD"), cancellable = true)
    private void preventBlockPLacingWhenPetrified(BlockPos pos, Direction facing, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        if (player.hasStatusEffect(ModEffects.PETRIFICATION))
            cir.setReturnValue(false);
    }

    @Inject(method = "dropInventory", at = @At("HEAD"), cancellable = true)
    private void preventDropAndClearInventoryIfAcid(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        if (player.hasStatusEffect(ModEffects.ACID) && player.isDead()) {
            player.getInventory().clear();
            ci.cancel();
        }
    }
}