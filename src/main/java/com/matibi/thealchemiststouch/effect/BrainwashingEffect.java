package com.matibi.thealchemiststouch.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class BrainwashingEffect extends StatusEffect {
    public BrainwashingEffect() {
        super(StatusEffectCategory.HARMFUL, 0x9933FF);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        // Log de debug
        if (!entity.getWorld().isClient) {
            System.out.println("[BrainwashingEffect] Applied to " + entity.getName().getString());
        }
    }
}
