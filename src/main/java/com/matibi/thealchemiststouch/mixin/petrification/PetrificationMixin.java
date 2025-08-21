package com.matibi.thealchemiststouch.mixin.petrification;

import com.matibi.thealchemiststouch.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class PetrificationMixin {

    @Inject(method = "jump", at = @At("HEAD"), cancellable = true)
    private void cancelJumpIfPetrified(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object)this;
        if (entity.hasStatusEffect(ModEffects.PETRIFICATION))
            ci.cancel();
    }

    @Inject(method = "tickActiveItemStack", at = @At("HEAD"), cancellable = true)
    private void stopUsingItemIfPetrified(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object)this;
        if (entity.hasStatusEffect(ModEffects.PETRIFICATION)) {
            entity.clearActiveItem();
            ci.cancel();
        }
    }

    @Inject(method = "dropInventory", at = @At("HEAD"), cancellable = true)
    private void preventDropIfPetrified(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object)this;
        if (entity.hasStatusEffect(ModEffects.PETRIFICATION))
            ci.cancel();
    }
}

