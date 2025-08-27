package com.matibi.thealchemiststouch.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;
import net.minecraft.entity.Entity;

public class DoubleHealthEffect extends StatusEffect {
    public DoubleHealthEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xf4c542);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        applyEffect(world, null, null, entity, amplifier, 1.0D);
        return super.applyUpdateEffect(world, entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {return true;}

    @Override
    public void applyInstantEffect(ServerWorld world, @Nullable Entity effectEntity, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        applyEffect(world, effectEntity, attacker, target, amplifier, proximity);
        super.applyInstantEffect(world, effectEntity, attacker, target, amplifier, proximity);
    }

    private static void applyEffect(ServerWorld world, @Nullable Entity effectEntity, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        float heart = target.getMaxHealth() / 2;
        int life = (int)((heart * (amplifier + 1)) / 2) - 1;
        target.addStatusEffect(
                new StatusEffectInstance(StatusEffects.ABSORPTION, -1, life, true, false),
                attacker
        );
    }
}

