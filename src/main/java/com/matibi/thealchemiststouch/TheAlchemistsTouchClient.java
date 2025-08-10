package com.matibi.thealchemiststouch;

import com.matibi.thealchemiststouch.client.OreESP;
import net.fabricmc.api.ClientModInitializer;

public class TheAlchemistsTouchClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        OreESP.init();
    }
}