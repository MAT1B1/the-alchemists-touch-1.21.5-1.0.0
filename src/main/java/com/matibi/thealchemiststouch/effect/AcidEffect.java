package com.matibi.thealchemiststouch.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

public class AcidEffect extends StatusEffect {
    public AcidEffect() {
        super(StatusEffectCategory.HARMFUL, 0x7FFF00);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        entity.damage(world, entity.getDamageSources().magic(),1.0F + amplifier);
        return super.applyUpdateEffect(world, entity, amplifier);
    }
}
