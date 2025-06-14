package com.matibi.thealchemiststouch.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

public class IgnitionEffect extends StatusEffect {

    public IgnitionEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xFF4500); // Couleur orangée
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (!entity.isOnFire() && !entity.isInFluid() && !entity.hasStatusEffect(ModEffects.FROST))
            entity.setOnFireFor(4);
        return super.applyUpdateEffect(world, entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onEntityRemoval(ServerWorld world, LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
        entity.setOnFire(false);
        super.onEntityRemoval(world, entity, amplifier, reason);
    }
}
