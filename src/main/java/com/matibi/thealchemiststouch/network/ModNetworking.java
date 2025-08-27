package com.matibi.thealchemiststouch.network;

import com.matibi.thealchemiststouch.util.FireballUtil;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ModNetworking {
   public static void init() {
        PayloadTypeRegistry.playC2S().register(
                ShootFireballC2SPayload.ID, ShootFireballC2SPayload.CODEC
        );

        ServerPlayNetworking.registerGlobalReceiver(
                ShootFireballC2SPayload.ID,
                (payload, context) -> context.server().execute(() ->
                        FireballUtil.spawnFireball(context.player())
                )
        );
    }
}
