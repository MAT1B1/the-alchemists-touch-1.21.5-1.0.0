package com.matibi.thealchemiststouch.util;

import net.minecraft.util.PlayerInput;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KeyPermutationManager {
    private static final int REFRESH_TICKS = 20 * 10; // 10 secondes à 20 TPS
    private static int tickCounter = 0;
    private static int[] permutation = {0, 1, 2, 3, 4, 5}; // index de: avant, arrière, gauche, droite, jump, sneak

    public static void tick() {
        tickCounter++;
        if (tickCounter >= REFRESH_TICKS) {
            shufflePermutation();
            tickCounter = 0;
        }
    }

    private static void shufflePermutation() {
        List<Integer> indexes = Arrays.asList(0, 1, 2, 3, 4, 5);
        Collections.shuffle(indexes);
        for (int i = 0; i < permutation.length; i++) {
            permutation[i] = indexes.get(i);
        }
    }

    public static PlayerInput permute(PlayerInput input) {
        boolean[] values = {
                input.forward(),
                input.backward(),
                input.left(),
                input.right(),
                input.jump(),
                input.sneak()
        };

        return new PlayerInput(
                values[permutation[0]], // forward
                values[permutation[1]], // back
                values[permutation[2]], // left
                values[permutation[3]], // right
                values[permutation[4]], // jump
                values[permutation[5]], // sneak
                input.sprint()          // sprint pas touché
        );
    }
}
