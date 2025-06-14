package com.matibi.thealchemiststouch.effect.custom;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;

public class ActivationEffect extends StatusEffect {
    public ActivationEffect() {
        super(StatusEffectCategory.NEUTRAL, 0xbd0000);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 20 == 0; // une fois par seconde
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        BlockPos origin = entity.getBlockPos();
        int radius = 2 + amplifier;

        BlockPos.iterateOutwards(origin, radius, radius, radius).forEach(pos -> {
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();

            // 🧨 TNT
            else if (block instanceof TntBlock) {
                TntBlock.primeTnt(world, pos);
                world.removeBlock(pos, false);
            }

            // 💡 Lampes
            else if (block instanceof RedstoneLampBlock && !state.get(RedstoneLampBlock.LIT)) {
                world.setBlockState(pos, state.with(RedstoneLampBlock.LIT, true), 10);
            }

            // 🛤️ Rails alimentés
            else if (block instanceof PoweredRailBlock && state.contains(Properties.POWERED) && !state.get(Properties.POWERED)) {
                world.setBlockState(pos, state.with(Properties.POWERED, true), 10);
            }

            // 📦 Trappes
            else if (block instanceof TrapdoorBlock && state.contains(Properties.OPEN) && !state.get(Properties.OPEN)) {
                world.setBlockState(pos, state.with(Properties.OPEN, true), 10);
            }
        });

        return super.applyUpdateEffect(world, entity, amplifier);
    }
}