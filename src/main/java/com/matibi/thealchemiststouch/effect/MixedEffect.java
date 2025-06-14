package com.matibi.thealchemiststouch.effect;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public class MixedEffect extends StatusEffect {
    public MixedEffect() {
        super(StatusEffectCategory.NEUTRAL, 0xAAAAAA);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        ItemStack stack = entity.getMainHandStack();

        NbtComponent component = stack.getOrDefault(DataComponentTypes.BLOCK_ENTITY_DATA, NbtComponent.DEFAULT);
        NbtCompound nbt = component.copyNbt();

        if (!nbt.contains("MixedEffects")) return false;

        NbtList list = nbt.getListOrEmpty("MixedEffects");

        for (int i = 0; i < list.size(); i++) {
            NbtCompound effectData = list.getCompoundOrEmpty(i);

            Identifier id = Identifier.tryParse(effectData.getString("Id", "erreur"));
            if (id == null) continue;

            RegistryEntry<StatusEffect> effect = Registries.STATUS_EFFECT.getEntry(id).orElse(null);
            if (effect == null) continue;

            int duration = effectData.getInt("Duration", 1);
            int amp = effectData.getInt("Amplifier", 0);

            entity.addStatusEffect(new StatusEffectInstance(effect, duration, amp));
        }

        entity.removeStatusEffect(ModEffects.MIXED);
        return false;
    }
}