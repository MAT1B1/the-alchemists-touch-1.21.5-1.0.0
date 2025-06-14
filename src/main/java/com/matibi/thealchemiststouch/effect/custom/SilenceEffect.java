package com.matibi.thealchemiststouch.effect.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

public class SilenceEffect extends StatusEffect {
    public SilenceEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x222244);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        entity.setSilent(true);
        return super.applyUpdateEffect(world, entity, amplifier);
    }

    @Override
    public void onEntityRemoval(ServerWorld world, LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
        entity.setSilent(false);
        super.onEntityRemoval(world, entity, amplifier, reason);
    }
}
