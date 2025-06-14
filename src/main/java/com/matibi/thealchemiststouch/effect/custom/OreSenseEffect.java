package com.matibi.thealchemiststouch.effect.custom;

import com.matibi.thealchemiststouch.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class OreSenseEffect extends StatusEffect {

    private int tick = 0;

    public OreSenseEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xF0C838);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        int radius = 2 + amplifier * 2;
        BlockPos origin = entity.getBlockPos();
        BlockPos.Mutable pos = new BlockPos.Mutable();

        tick++;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    pos.set(origin.getX() + x, origin.getY() + y, origin.getZ() + z);
                    BlockState state = world.getBlockState(pos);
                    Block block = state.getBlock();

                    if (block.getDefaultState().isIn(ModTags.Blocks.DETECTABLE_ORES) && tick >= 10) {
                        Vec3d look = entity.getRotationVector().normalize();
                        Vec3d particlePos = entity.getPos().add(look.multiply(0.6)).add(0, 1.5, 0);


                        world.spawnParticles(
                                ParticleTypes.ELECTRIC_SPARK,
                                particlePos.x, particlePos.y, particlePos.z,
                                3, 0.2, 0.1, 0.2, 0.01
                        );

                        tick = 0;
                        return super.applyUpdateEffect(world, entity, amplifier);
                    }
                }
            }
        }

        return super.applyUpdateEffect(world, entity, amplifier);
    }
}
