package com.matibi.thealchemiststouch.effect.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UnstableEffect extends StatusEffect {
    private static final List<RegistryEntry<StatusEffect>> NEGATIVE_POOL = List.of(
            StatusEffects.POISON,
            StatusEffects.WEAKNESS,
            StatusEffects.SLOWNESS,
            StatusEffects.MINING_FATIGUE,
            StatusEffects.NAUSEA,
            StatusEffects.BLINDNESS,
            StatusEffects.HUNGER,
            StatusEffects.WITHER,
            StatusEffects.BAD_OMEN
    );

    public UnstableEffect() {
        super(StatusEffectCategory.HARMFUL, 0x000000);
    }

    @Override
    public boolean isInstant() {return true;}

    @Override
    public void applyInstantEffect(ServerWorld world, @Nullable Entity effectEntity, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        Random r = world.getRandom();

        float explodeChance = Math.min(0.1f + 0.10f * amplifier, 0.60f);

        if (r.nextFloat() < explodeChance) {
            float power = 2.5f + amplifier;
            boolean createFire = false;
            world.createExplosion(
                    target,
                    target.getX(), target.getY(), target.getZ(),
                    power,
                    createFire,
                    ServerWorld.ExplosionSourceType.MOB
            );

            world.playSound(null, target.getBlockPos(), SoundEvents.ENTITY_CREEPER_PRIMED,
                    SoundCategory.HOSTILE, 0.9f, 0.7f + r.nextFloat() * 0.4f);
            world.spawnParticles(ParticleTypes.EXPLOSION, target.getX(), target.getBodyY(0.5), target.getZ(),
                    1, 0.0, 0.0, 0.0, 0.0);
        } else {

            RegistryEntry<StatusEffect> pick = NEGATIVE_POOL.get(r.nextInt(NEGATIVE_POOL.size()));

            int seconds = 8 + amplifier * 6;
            int level = (pick == StatusEffects.WITHER) ? Math.max(0, amplifier - 1) : amplifier;

            target.addStatusEffect(new StatusEffectInstance(pick, seconds * 20, level, false, true, true));

            world.spawnParticles(ParticleTypes.SMOKE, target.getX(), target.getBodyY(0.6), target.getZ(),
                    8 + amplifier * 4, 0.4, 0.2, 0.4, 0.01);
            world.playSound(null, target.getBlockPos(), SoundEvents.BLOCK_BREWING_STAND_BREW,
                    SoundCategory.PLAYERS, 0.7f, 0.9f + r.nextFloat() * 0.2f);
        }

        super.applyInstantEffect(world, effectEntity, attacker, target, amplifier, proximity);
    }
}
