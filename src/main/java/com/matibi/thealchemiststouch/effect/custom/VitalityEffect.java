package com.matibi.thealchemiststouch.effect.custom;

import com.matibi.thealchemiststouch.util.HealthUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class VitalityEffect extends StatusEffect {
    public VitalityEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xf00000);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void applyInstantEffect(ServerWorld world, @Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        if (target instanceof PlayerEntity player)
            HealthUtils.changeHealthBy(player, 2.0f);
    }
}
