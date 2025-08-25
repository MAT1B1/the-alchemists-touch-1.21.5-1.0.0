package com.matibi.thealchemiststouch.mixin.imbuedWeapon;

import com.matibi.thealchemiststouch.client.render.GreenGlintState;
import com.matibi.thealchemiststouch.datacomponent.ModDataComponents;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Inject(
            method = "renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/world/World;III)V",
            at = @At("HEAD")
    )
    private void att$startGreenGlint(LivingEntity entity, ItemStack stack,
                                     ItemDisplayContext displayContext, MatrixStack matrices,
                                     VertexConsumerProvider vertexConsumers, World world,
                                     int light, int overlay, int seed, CallbackInfo ci) {
        if (stack.contains(ModDataComponents.IMBUED_EFFECT))
            GreenGlintState.enable();
    }

    @Inject(
            method = "renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/world/World;III)V",
            at = @At("RETURN")
    )
    private void att$endGreenGlint(LivingEntity entity, ItemStack stack,
                                   ItemDisplayContext displayContext, MatrixStack matrices,
                                   VertexConsumerProvider vertexConsumers, World world,
                                   int light, int overlay, int seed, CallbackInfo ci) {
        if (GreenGlintState.isEnabled())
            GreenGlintState.disable();
    }
}
