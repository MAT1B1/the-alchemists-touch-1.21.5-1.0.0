package com.matibi.thealchemiststouch.effect.custom;

import com.matibi.thealchemiststouch.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.world.ServerWorld;
import java.util.Collection;
import java.util.List;

public class ResonanceEffect extends StatusEffect {

    public ResonanceEffect() {
        super(StatusEffectCategory.NEUTRAL, 0xA0E8E0);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 40 == 0;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {

        Collection<StatusEffectInstance> effects = entity.getStatusEffects().stream()
                .filter(e -> !e.getEffectType().equals(ModEffects.RESONANCE))
                .toList();

        if (effects.isEmpty()) return super.applyUpdateEffect(world, entity, amplifier);

        double radius = 4.0 + amplifier * 2;
        List<LivingEntity> nearby = world.getEntitiesByClass(LivingEntity.class, entity.getBoundingBox().expand(radius),
                e -> e != entity && e.isAlive());

        for (LivingEntity target : nearby) {
            for (StatusEffectInstance effect : effects) {
                StatusEffectInstance copy = new StatusEffectInstance(effect.getEffectType(), effect.getDuration() / 2, effect.getAmplifier());
                target.addStatusEffect(copy);
            }
        }

        return super.applyUpdateEffect(world, entity, amplifier);
    }
}
