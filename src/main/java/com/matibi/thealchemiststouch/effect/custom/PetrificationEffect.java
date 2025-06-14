package com.matibi.thealchemiststouch.effect.custom;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public class PetrificationEffect extends StatusEffect {
    public PetrificationEffect() {
        super(StatusEffectCategory.HARMFUL, 0xA8A8A8);

        this.addAttributeModifier(
                EntityAttributes.MOVEMENT_SPEED,
                Identifier.of(TheAlchemistsTouch.MOD_ID, "petrification_movment"),
                -1.0D,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                EntityAttributes.KNOCKBACK_RESISTANCE,
                Identifier.of(TheAlchemistsTouch.MOD_ID, "petrification_knockback"),
                1.0D,
                EntityAttributeModifier.Operation.ADD_VALUE
        );
        this.addAttributeModifier(
                EntityAttributes.ATTACK_DAMAGE,
                Identifier.of(TheAlchemistsTouch.MOD_ID, "petrification_knockback"),
                -1.0D,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        entity.setInvulnerable(true);
        super.onApplied(entity, amplifier);
    }

    @Override
    public void onEntityRemoval(ServerWorld world, LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
        entity.setInvulnerable(false);
        super.onEntityRemoval(world, entity, amplifier, reason);
    }
}
