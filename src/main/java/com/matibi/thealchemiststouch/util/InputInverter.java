package com.matibi.thealchemiststouch.util;

import net.minecraft.client.MinecraftClient;
import com.matibi.thealchemiststouch.effect.ModEffects;

import java.util.Random;

public class InputInverter {
    private static final MinecraftClient client = MinecraftClient.getInstance();
    private static long lastShuffleTime = 0;
    private static boolean invertX = false;
    private static boolean invertY = false;

    public static void tick() {
        if (client.player == null || !client.player.hasStatusEffect(ModEffects.BRAIN_WASHING)) {
            invertX = false;
            invertY = false;
            return;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShuffleTime > 10_000) {
            lastShuffleTime = currentTime;
            Random r = new Random();
            invertX = r.nextBoolean();
            invertY = r.nextBoolean();
        }
    }

    public static double modifyDeltaX(double dx) {
        return invertX ? -dx : dx;
    }

    public static double modifyDeltaY(double dy) {
        return invertY ? -dy : dy;
    }
}