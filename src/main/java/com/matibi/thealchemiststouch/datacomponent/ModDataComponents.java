package com.matibi.thealchemiststouch.datacomponent;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModDataComponents {
    private ModDataComponents() {}

    public static final ComponentType<ImbuedEffectComponent> IMBUED_EFFECT =
            Registry.register(
                    Registries.DATA_COMPONENT_TYPE,
                    Identifier.of(TheAlchemistsTouch.MOD_ID, "imbued_effect"),
                    ComponentType.<ImbuedEffectComponent>builder()
                            .codec(ImbuedEffectComponent.CODEC)
                            .build()
            );

    public static void register() {

    }
}
