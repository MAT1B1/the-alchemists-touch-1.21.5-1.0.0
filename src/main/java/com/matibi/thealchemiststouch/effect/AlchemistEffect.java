package com.matibi.thealchemiststouch.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.Nullable;

public class AlchemistEffect extends StatusEffect{
    public AlchemistEffect() {
            super(StatusEffectCategory.BENEFICIAL, 0xd1c70d);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void applyInstantEffect(ServerWorld world, @Nullable Entity effectEntity, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        if (!(target instanceof PlayerEntity player)) return;

        ItemStack offhand = player.getOffHandStack();
        int count = offhand.getCount();

        if (offhand.getItem() == Items.COAL || offhand.getItem() == Items.CHARCOAL)
            player.setStackInHand(Hand.OFF_HAND, new ItemStack(Items.GOLD_INGOT, count));
        else if (offhand.getItem() == Items.COAL_BLOCK)
            player.setStackInHand(Hand.OFF_HAND, new ItemStack(Items.GOLD_BLOCK, count));
        else if (offhand.getItem() == Items.COAL_ORE)
            player.setStackInHand(Hand.OFF_HAND, new ItemStack(Items.GOLD_ORE, count));
        else if (offhand.getItem() == Items.DEEPSLATE_COAL_ORE)
            player.setStackInHand(Hand.OFF_HAND, new ItemStack(Items.DEEPSLATE_GOLD_ORE, count));

        super.applyInstantEffect(world, effectEntity, attacker, target, amplifier, proximity);
    }
}

