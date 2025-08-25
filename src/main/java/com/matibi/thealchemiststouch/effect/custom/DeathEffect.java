package com.matibi.thealchemiststouch.effect.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class DeathEffect extends StatusEffect {
    public DeathEffect() {
        super(StatusEffectCategory.HARMFUL, 0x000000);
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
        if (target.isAlive()) {
            if (isBoss(target)) {
                float maxHealth = target.getMaxHealth();
                float currentHealth = target.getHealth();

                if (currentHealth > maxHealth / 2) {
                    if (attacker != null) {
                        LivingEntity passenger = attacker.getControllingPassenger();
                        if (passenger != null) {
                            float dmg = passenger.getMaxHealth();
                            attacker.damage(world, world.getDamageSources().magic(), dmg);
                        }
                    }
                    target.setHealth(maxHealth / 2);
                }
            } else {
                if (attacker != null)
                    target.damage(world, target.getDamageSources().mobAttack((LivingEntity) attacker), target.getMaxHealth());
                else
                    target.damage(world, target.getDamageSources().magic(), target.getMaxHealth());
            }
        }
    }

    private static boolean isBoss(LivingEntity e) {
        var t = e.getType();
        return t == EntityType.WITHER
                || t == EntityType.ENDER_DRAGON
                || t == EntityType.WARDEN
                || t == EntityType.ELDER_GUARDIAN;
    }
}
