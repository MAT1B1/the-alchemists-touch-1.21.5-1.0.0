package com.matibi.thealchemiststouch.effect.custom;

import com.matibi.thealchemiststouch.effect.ModEffects;
import com.matibi.thealchemiststouch.effect.TerrainApplicableEffect;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.Map;

public class IgnitionEffect extends StatusEffect implements TerrainApplicableEffect {
    private static final Map<Block, Block> SMELTABLES = new HashMap<>();

    static {
        SMELTABLES.put(Blocks.SAND, Blocks.GLASS);
        SMELTABLES.put(Blocks.RED_SAND, Blocks.GLASS);
        SMELTABLES.put(Blocks.COBBLESTONE, Blocks.STONE);
        SMELTABLES.put(Blocks.STONE, Blocks.SMOOTH_STONE);
        SMELTABLES.put(Blocks.NETHERRACK, Blocks.NETHER_BRICKS);
        SMELTABLES.put(Blocks.CLAY, Blocks.BRICKS);
        SMELTABLES.put(Blocks.WET_SPONGE, Blocks.SPONGE);
        SMELTABLES.put(Blocks.RAW_IRON_BLOCK, Blocks.IRON_BLOCK);
        SMELTABLES.put(Blocks.RAW_COPPER_BLOCK, Blocks.COPPER_BLOCK);
        SMELTABLES.put(Blocks.RAW_GOLD_BLOCK, Blocks.GOLD_BLOCK);
        SMELTABLES.put(Blocks.SHORT_GRASS, Blocks.SHORT_DRY_GRASS);
        SMELTABLES.put(Blocks.TALL_GRASS, Blocks.TALL_DRY_GRASS);
    }

    public IgnitionEffect() {
        super(StatusEffectCategory.HARMFUL, 0xFF4500);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (!entity.isOnFire() && !entity.isInFluid() && !entity.hasStatusEffect(ModEffects.FROST))
            entity.setOnFireFor(4);
        return super.applyUpdateEffect(world, entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onEntityRemoval(ServerWorld world, LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
        entity.setOnFire(false);
        super.onEntityRemoval(world, entity, amplifier, reason);
    }

    @Override
    public void applyOnBlock(ServerWorld world, BlockPos blockPos, int duration, int amplifier) {
        BlockState state = world.getBlockState(blockPos);
        Block block = state.getBlock();

        if (SMELTABLES.containsKey(block))
            world.setBlockState(blockPos, SMELTABLES.get(block).getDefaultState(), 3);
        else
            world.setBlockState(blockPos, Blocks.COAL_BLOCK.getDefaultState());

        world.spawnParticles(
                ParticleTypes.FLAME,
                blockPos.getX() + 0.5,
                blockPos.getY() + 0.5,
                blockPos.getZ() + 0.5,
                5, 0.4, 0.4, 0.4, 0.01
        );
    }

    @Override
    public boolean isBlockApplicable(ServerWorld world, BlockPos blockPos) {
        BlockState state = world.getBlockState(blockPos);
        Block block = state.getBlock();
        return SMELTABLES.containsKey(block) || block.getDefaultState().isIn(BlockTags.LOGS);
    }
}