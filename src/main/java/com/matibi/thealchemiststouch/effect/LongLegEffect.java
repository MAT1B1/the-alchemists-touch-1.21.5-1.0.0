package com.matibi.thealchemiststouch.effect;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;

public class LongLegEffect extends StatusEffect {
    // TODO : actually change the player to lengthen the legs too

    public LongLegEffect() {
        super(StatusEffectCategory.NEUTRAL, 0x89D964);

        this.addAttributeModifier(
                EntityAttributes.STEP_HEIGHT,
                Identifier.of(TheAlchemistsTouch.MOD_ID, "long_legs_step"),
                0.4D,
                EntityAttributeModifier.Operation.ADD_VALUE
        );

        this.addAttributeModifier(
                EntityAttributes.MOVEMENT_SPEED,
                Identifier.of(TheAlchemistsTouch.MOD_ID, "long_legs_speed"),
                0.1D,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
        );
    }
}
