package com.matibi.thealchemiststouch.mixin;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.LingeringPotionItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LingeringPotionItem.class)
public abstract class LingeringPotionItemMixin {

    @Inject(method = "use", at = @At("HEAD"))
    private void applyCooldownOnUse(World world, PlayerEntity user, Hand hand,  CallbackInfoReturnable<ActionResult> cir) {
        if (!world.isClient) {
            ItemStack stack = user.getStackInHand(hand);
            user.getItemCooldownManager().set(stack, 5 * 20);
        }
    }
}