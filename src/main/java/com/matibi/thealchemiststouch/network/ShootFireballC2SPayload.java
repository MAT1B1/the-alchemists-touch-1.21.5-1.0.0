package com.matibi.thealchemiststouch.network;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ShootFireballC2SPayload() implements CustomPayload {
    public static final Id<ShootFireballC2SPayload> ID =
            new Id<>(Identifier.of(TheAlchemistsTouch.MOD_ID, "shoot_fireball"));

    public static final PacketCodec<RegistryByteBuf, ShootFireballC2SPayload> CODEC =
            PacketCodec.unit(new ShootFireballC2SPayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
