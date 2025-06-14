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
public abstract class PlayerEntityMixin {

    @Inject(method = "dropInventory", at = @At("HEAD"), cancellable = true)
    private void preventDropAndClearInventoryIfAcidOrPetrified(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        if ((player.hasStatusEffect(ModEffects.ACID) && player.isDead()) || player.hasStatusEffect(ModEffects.PETRIFICATION)) {
            player.getInventory().clear();
            ci.cancel();
        }
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity)(Object)this;

        if (player.hasStatusEffect(ModEffects.RESURRECTION)) {
            if (amount >= player.getHealth()) {
                // Simule l’effet du totem
                player.setHealth(1.0f);
                player.clearStatusEffects();
                player.addStatusEffect(new StatusEffectInstance(REGENERATION, 20 * 45, 1));
                player.addStatusEffect(new StatusEffectInstance(ABSORPTION, 20 * 5, 1));
                player.addStatusEffect(new StatusEffectInstance(FIRE_RESISTANCE, 20 * 40, 0));

                // Son et particules de totem
                world.syncWorldEvent(1033, player.getBlockPos(), 0);
                player.playSound(SoundEvents.ITEM_TOTEM_USE, 1.0F, 1.0F);
                player.emitGameEvent(GameEvent.ENTITY_INTERACT);

                if (player instanceof ServerPlayerEntity serverPlayer) {
                    serverPlayer.networkHandler.sendPacket(new EntityStatusS2CPacket(player, (byte) 35));
                }

                cir.setReturnValue(false);
            }
        }
    }
}