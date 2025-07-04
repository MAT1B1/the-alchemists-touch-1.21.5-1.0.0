package com.matibi.thealchemiststouch.effect.custom;

import com.matibi.thealchemiststouch.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class InfinityEffect extends StatusEffect {
    private static final Set<StatusEffect> BLACKLISTED_EFFECTS = Set.of(
            ModEffects.REACTIVATION.value(),
            ModEffects.RESONANCE.value()
    );


    public InfinityEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x9f00ff);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void applyInstantEffect(ServerWorld world, @Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        for (StatusEffectInstance effect : target.getStatusEffects()) {
            if (BLACKLISTED_EFFECTS.contains(effect.getEffectType().value())) continue;
            target.damage(world, target.getDamageSources().magic(), 2.0f * 2);
            StatusEffectInstance infinite = new StatusEffectInstance(
                    effect.getEffectType(),
                    -1,
                    effect.getAmplifier(),
                    effect.isAmbient(),
                    effect.shouldShowParticles(),
                    effect.shouldShowIcon()
            );

            target.addStatusEffect(infinite);
        }
    }
}