package com.matibi.thealchemiststouch.mixin;

import com.matibi.thealchemiststouch.effect.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "emitGameEvent(Lnet/minecraft/registry/entry/RegistryEntry;Lnet/minecraft/entity/Entity;)V",
            at = @At("HEAD"), cancellable = true)
    private void cancelGameEventIfSilenced(RegistryEntry<GameEvent> event, @Nullable Entity entity, CallbackInfo ci) {
        if (entity instanceof LivingEntity living && living.hasStatusEffect(ModEffects.SILENCE)) {
            ci.cancel();
        }
    }
}
