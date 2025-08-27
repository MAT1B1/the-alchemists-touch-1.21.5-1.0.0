package com.matibi.thealchemiststouch.effect.custom;

import com.matibi.thealchemiststouch.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;

public class FrostEffect extends StatusEffect {
    public FrostEffect() {
        super(StatusEffectCategory.HARMFUL, 0x80D8FF);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (entity.hasStatusEffect(ModEffects.IGNITION)) return super.applyUpdateEffect(world, entity, amplifier);

        entity.addStatusEffect(new StatusEffectInstance(
                StatusEffects.SLOWNESS, 5 * 20, 3, false, false));

        if (entity.age % 40 == 0 && entity.canFreeze())
            entity.damage(world, entity.getDamageSources().freeze(), 1.0f);
        return super.applyUpdateEffect(world, entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
