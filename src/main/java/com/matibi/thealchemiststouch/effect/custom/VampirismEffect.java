package com.matibi.thealchemiststouch.effect.custom;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class VampirismEffect extends StatusEffect {
    public VampirismEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x8B0000);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {return true;}

}
