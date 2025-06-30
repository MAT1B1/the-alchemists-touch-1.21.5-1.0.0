package com.matibi.thealchemiststouch.effect;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public interface TerrainApplicableEffect{
    void applyOnBlock(ServerWorld world, BlockPos block, int duration, int amplifier);
}
