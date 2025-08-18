package com.matibi.thealchemiststouch.util;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class HealthUtils {
    private static final Identifier HEALTH_BONUS_ID = Identifier.of(TheAlchemistsTouch.MOD_ID, "max_health_bonus");

    public static void changeHealthBy(PlayerEntity player, double delta) {
        var attr = player.getAttributeInstance(EntityAttributes.MAX_HEALTH);
        if (attr == null) return;

        var existing = attr.getModifier(HEALTH_BONUS_ID);
        double newAmount = delta;

        if (existing != null) {
            newAmount += existing.value();
            attr.removeModifier(HEALTH_BONUS_ID);
        }

        attr.addPersistentModifier(new EntityAttributeModifier(
                HEALTH_BONUS_ID,
                newAmount,
                EntityAttributeModifier.Operation.ADD_VALUE
        ));

        if (player.getHealth() > player.getMaxHealth())
            player.setHealth(player.getMaxHealth());
    }

    public static void resetHealth(PlayerEntity player) {
        var attr = player.getAttributeInstance(EntityAttributes.MAX_HEALTH);
        if (attr != null) {
            attr.removeModifier(HEALTH_BONUS_ID);
        }
    }
}

