package com.matibi.thealchemiststouch.mixin.imbuedWeapon;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import com.matibi.thealchemiststouch.client.render.GreenGlintState;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.render.*;
import net.minecraft.client.render.RenderPhase.*;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.*;

@Mixin(RenderLayer.class)
public abstract class GreenGlintMixin {

    @Unique
    private static final Identifier GREEN_GLINT_TEX =
            Identifier.of(TheAlchemistsTouch.MOD_ID, "textures/misc/enchanted_glint_item.png");

    @Unique
    private static final RenderLayer GREEN_GLINT = RenderLayer.of(
            "green_glint",
            1536,
            RenderPipelines.GLINT,
            RenderLayer.MultiPhaseParameters.builder()
                    .texture(new RenderPhase.Texture(GREEN_GLINT_TEX, false))
                    .texturing(RenderPhase.GLINT_TEXTURING)
                    .build(false));

    @Unique
    private static final RenderLayer GREEN_GLINT_TRANSLUCENT = RenderLayer.of(
            "green_glint_translucent",
            1536,
            RenderPipelines.GLINT,
            RenderLayer.MultiPhaseParameters.builder()
                    .texture(new RenderPhase.Texture(GREEN_GLINT_TEX, false))
                    .texturing(RenderPhase.GLINT_TEXTURING)
                    .target(RenderPhase.ITEM_ENTITY_TARGET)
                    .build(false));

    @Unique
    private static final RenderLayer ENTITY_GREEN_GLINT = RenderLayer.of(
            "entity_green_glint",
            1536,
            RenderPipelines.GLINT,
            RenderLayer.MultiPhaseParameters.builder()
                    .texture(new RenderPhase.Texture(GREEN_GLINT_TEX, false))
                    .texturing(RenderPhase.ENTITY_GLINT_TEXTURING)
                    .build(false));

    @Inject(method = "getGlint",
            at = @At("HEAD"), cancellable = true)
    private static void att$swapItemGlintIfEnabled(CallbackInfoReturnable<RenderLayer> cir) {
        if (GreenGlintState.isEnabled()) {
            System.out.println("ok normalement c'est vert");
            cir.setReturnValue(GREEN_GLINT);
            cir.cancel();
        }
    }

    @Inject(method = "getGlintTranslucent",
            at = @At("HEAD"), cancellable = true)
    private static void att$swapItemGlintTranslucent(CallbackInfoReturnable<RenderLayer> cir) {
        if (GreenGlintState.isEnabled()) {
            cir.setReturnValue(GREEN_GLINT_TRANSLUCENT);
            cir.cancel();
        }
    }

    @Inject(method = "getEntityGlint",
            at = @At("HEAD"), cancellable = true)
    private static void att$swapEntityGlint(CallbackInfoReturnable<RenderLayer> cir) {
        if (GreenGlintState.isEnabled()) {
            cir.setReturnValue(ENTITY_GREEN_GLINT);
            cir.cancel();
        }
    }
}
