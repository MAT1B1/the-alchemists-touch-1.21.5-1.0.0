package com.matibi.thealchemiststouch.mixin;


import com.matibi.thealchemiststouch.effect.ModEffects;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.entity.effect.StatusEffects.*;

@Mixin(PlayerEntity.class)
public abstract class AcidMixin {

    @Inject(method = "dropInventory", at = @At("HEAD"), cancellable = true)
    private void preventDropAndClearInventoryIfAcidOrPetrified(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (player.hasStatusEffect(ModEffects.ACID) && player.isDead()) {
            player.getInventory().clear();
            ci.cancel();
        }
    }
}