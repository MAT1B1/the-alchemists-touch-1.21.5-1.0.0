package com.matibi.thealchemiststouch.effect.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
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
    public void applyInstantEffect(ServerWorld world, @Nullable Entity effectEntity, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {

        if (target.isAlive()) {
            if (target.getType().toString().contains("wither")
                || target.getType().toString().contains("ender_dragon")
                || target.getType().toString().contains("warden")
                || target.getType().toString().contains("elder_guardian")) {
                float maxHealth = target.getMaxHealth();
                float currentHealth = target.getHealth();

                if (currentHealth > maxHealth / 2) {
                    if (attacker != null) {
                        float dmg = Objects.requireNonNull(attacker.getControllingPassenger()).getMaxHealth();
                        attacker.damage(world, world.getDamageSources().magic(), dmg);
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
        super.applyInstantEffect(world, effectEntity, attacker, target, amplifier, proximity);
    }
}
