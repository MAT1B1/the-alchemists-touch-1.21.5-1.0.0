package com.matibi.thealchemiststouch.effect.custom;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class MaskingEffect extends StatusEffect {
    public MaskingEffect() {super(StatusEffectCategory.BENEFICIAL, 0x444444);}

    @Override
    public boolean isInstant() {return true;}
}
