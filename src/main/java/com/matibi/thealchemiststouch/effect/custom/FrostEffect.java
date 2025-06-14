package com.matibi.thealchemiststouch.effect.custom;

import com.matibi.thealchemiststouch.effect.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class FrostEffect extends StatusEffect {
    public FrostEffect() {
        super(StatusEffectCategory.HARMFUL, 0x80D8FF);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (entity.hasStatusEffect(ModEffects.IGNITION)) return super.applyUpdateEffect(world, entity, amplifier);

        Vec3d velocity = entity.getVelocity();
        double slowFactor = 0.05; // très lent
        entity.setVelocity(velocity.x * slowFactor, Math.min(velocity.y, 0), velocity.z * slowFactor);

        entity.setInPowderSnow(true);
        entity.setJumping(false);
        if (entity.age % 40 == 0 && entity.canFreeze()) {
            entity.damage(world, entity.getDamageSources().freeze(), 1.0f);
        }
        return super.applyUpdateEffect(world, entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onEntityRemoval(ServerWorld world, LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
        entity.setInPowderSnow(false);
        super.onEntityRemoval(world, entity, amplifier, reason);
    }
}
