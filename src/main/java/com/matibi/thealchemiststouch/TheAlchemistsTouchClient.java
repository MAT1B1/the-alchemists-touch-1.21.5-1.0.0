package com.matibi.thealchemiststouch;

import com.matibi.thealchemiststouch.client.OreESP;
import com.matibi.thealchemiststouch.fluid.ModFluids;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;

public class TheAlchemistsTouchClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        OreESP.init();
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_POTION, ModFluids.FLOWING_POTION,
                SimpleFluidRenderHandler.coloredWater(0xffff0025));
        BlockRenderLayerMap.putFluids(BlockRenderLayer.TRANSLUCENT,
                 ModFluids.STILL_POTION, ModFluids.FLOWING_POTION);
    }

}