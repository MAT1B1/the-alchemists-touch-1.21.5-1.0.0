package com.matibi.thealchemiststouch;

import com.matibi.thealchemiststouch.client.OreESP;
import com.matibi.thealchemiststouch.datacomponent.ModDataComponents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.item.v1.ComponentTooltipAppenderRegistry;
import net.minecraft.component.DataComponentTypes;

public class TheAlchemistsTouchClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        OreESP.init();
        ComponentTooltipAppenderRegistry.addAfter(
                DataComponentTypes.ENCHANTMENTS,
                ModDataComponents.IMBUED_EFFECT
        );
    }

}