package com.matibi.thealchemiststouch.effect.custom;

import com.matibi.thealchemiststouch.effect.TerrainApplicableEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class AcidEffect extends StatusEffect implements TerrainApplicableEffect {
    public AcidEffect() {
        super(StatusEffectCategory.HARMFUL, 0x7FFF00);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        entity.damage(world, entity.getDamageSources().magic(),1.0F + amplifier);
        return super.applyUpdateEffect(world, entity, amplifier);
    }

    @Override
    public void applyOnBlock(ServerWorld world, BlockPos center, int duration, int amplifier) {
        int radius = 1 + amplifier;
        int depth = 1 + amplifier * 2;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                for (int dy = 0; dy > -depth; dy--) {
                    BlockPos target = center.add(dx, dy, dz);

                    if (!world.getBlockState(target).isAir()) {
                        world.breakBlock(target, false);
                    }
                }
            }
        }
    }
}
