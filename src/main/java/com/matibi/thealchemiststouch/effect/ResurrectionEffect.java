package com.matibi.thealchemiststouch.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.event.GameEvent;

import static net.minecraft.entity.effect.StatusEffects.*;

public class ResurrectionEffect extends StatusEffect {
    public ResurrectionEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xffcc66);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        entity.isDead();
        if (entity.getHealth() <= 1.0f) {
            // Simule l’effet du totem
            entity.setHealth(1.0f);
            entity.clearStatusEffects();
            entity.addStatusEffect(new StatusEffectInstance(REGENERATION, 20 * 45, 1));
            entity.addStatusEffect(new StatusEffectInstance(ABSORPTION, 20 * 5, 1));
            entity.addStatusEffect(new StatusEffectInstance(FIRE_RESISTANCE, 20 * 40, 0));

            // Son et particules de totem
            world.syncWorldEvent(1033, entity.getBlockPos(), 0);
            entity.playSound(SoundEvents.ITEM_TOTEM_USE, 1.0F, 1.0F);
            entity.emitGameEvent(GameEvent.ENTITY_INTERACT);

            // Retirer l'effet de Résurrection
            entity.removeStatusEffect(ModEffects.RESURRECTION);
        }

        return super.applyUpdateEffect(world, entity, amplifier);
    }
}
