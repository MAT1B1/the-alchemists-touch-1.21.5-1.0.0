package com.matibi.thealchemiststouch.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import org.jetbrains.annotations.Nullable;

public class TeleportationEffect extends StatusEffect {
    // TODO : working tipped arrow

    public TeleportationEffect() {
        super(StatusEffectCategory.NEUTRAL, 0x8833cc);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void applyInstantEffect(
            ServerWorld world,
            @Nullable Entity effectSource,
            @Nullable Entity attacker,
            LivingEntity target,
            int amplifier,
            double proximity
    ) {
        double radius = 8.0 + world.random.nextDouble() * 8.0;
        double angle = world.random.nextDouble() * 2 * Math.PI;
        double dx = Math.cos(angle) * radius;
        double dz = Math.sin(angle) * radius;

        double newX = target.getX() + dx;
        double newZ = target.getZ() + dz;
        BlockPos pos = new BlockPos((int)newX, 0, (int)newZ);
        double newY = world.getTopY(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos) + 1;

        target.teleport(newX, newY, newZ, true);
    }
}