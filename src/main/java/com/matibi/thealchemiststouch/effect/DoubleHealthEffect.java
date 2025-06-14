package com.matibi.thealchemiststouch.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
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
    public void applyInstantEffect(ServerWorld world, @Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        float baseHealth = target.getMaxHealth();
        float extraAbsorption = baseHealth * (1 + amplifier);

        target.setAbsorptionAmount(extraAbsorption);
    }
}

