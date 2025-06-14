package com.matibi.thealchemiststouch.effect.custom;

import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class SedativeEffect extends StatusEffect {
    public SedativeEffect() {
        super(StatusEffectCategory.HARMFUL, 0x2e1a47);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (!(entity instanceof ServerPlayerEntity player)) return false;
        player.setPose(EntityPose.SLEEPING);
        return super.applyUpdateEffect(world, entity, amplifier);
    }
}
