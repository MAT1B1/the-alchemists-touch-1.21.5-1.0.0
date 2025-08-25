package com.matibi.thealchemiststouch.util;

import com.matibi.thealchemiststouch.effect.ModEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FireballUtil {
    public static void spawnFireball(PlayerEntity player) {
        World world = player.getWorld();
        if (world.isClient() || !player.hasStatusEffect(ModEffects.IGNITION)) return;
        Vec3d eyePos = player.getEyePos();
        Vec3d look = player.getRotationVec(1.0F).normalize();
        Vec3d velocity = look.multiply(20);
        FireballEntity fireball = new FireballEntity(world, player, velocity, 1);
        fireball.setPosition(eyePos.add(look.multiply(1.5)));
        world.spawnEntity(fireball);
    }
}
