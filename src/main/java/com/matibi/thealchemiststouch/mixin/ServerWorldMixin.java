package com.matibi.thealchemiststouch.mixin;

import com.matibi.thealchemiststouch.effect.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @Inject(method = "playSoundFromEntity", at = @At("HEAD"), cancellable = true)
    private void cancelSoundIfSilenced(PlayerEntity source, Entity entity, RegistryEntry<SoundEvent> sound, SoundCategory category, float volume, float pitch, long seed, CallbackInfo ci) {
        if (entity instanceof LivingEntity living && living.hasStatusEffect(ModEffects.SILENCE)) {
            ci.cancel();
        }
    }
}

