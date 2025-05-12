package com.matibi.thealchemiststouch.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

public class ThornsEffect extends StatusEffect {
    public ThornsEffect() {
        super(StatusEffectCategory.HARMFUL, 0x55FF55);
    }

    @Override
    public void onEntityDamage(ServerWorld world, LivingEntity entity, int amplifier, DamageSource source, float amount) {
        if (source.getAttacker() instanceof LivingEntity attacker) {
            float reflected = amount * (0.2f + amplifier * 0.1f);
            attacker.damage(world, source, reflected);
        }
    }
}
