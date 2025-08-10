package com.matibi.thealchemiststouch.client;

import com.matibi.thealchemiststouch.effect.ModEffects;
import com.matibi.thealchemiststouch.util.ModTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OutlineVertexConsumerProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.model.BlockModelPart;
import net.minecraft.client.render.model.BlockStateModel;
import net.minecraft.client.texture.atlas.Atlases;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public final class OreESP {
    private static int R = 5;
    private static final int PERIOD_T = 20 * 5;
    private static final int DURATION_T = 20 * 2;

    private static List<BlockPos> cached = List.of();
    private static int tick, pulseStartTick;
    private static boolean pulseActive, hadEffect;

    public static void init() {
        // Gestion du pulse + scan au début de chaque pulse
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world == null || client.player == null) return;

            boolean has = client.player.hasStatusEffect(ModEffects.ORE_SENSE);
            if (!has) { cached = List.of(); pulseActive = false; hadEffect = false; return; }

            int t = ++tick;

            if (!hadEffect) { // 1er tick avec l'effet
                hadEffect = true;
                pulseStartTick = t;
                cached = scanOres(client.world, client.player.getBlockPos());
            }

            int since = t - pulseStartTick;
            if (since >= PERIOD_T) { // nouveau pulse toutes 5s
                pulseStartTick = t;
                since = 0;
                cached = scanOres(client.world, client.player.getBlockPos());
            }

            pulseActive = since < DURATION_T; // visible 2s
        });

        // Dessin dans la FBO d’outline pendant le pulse
        WorldRenderEvents.AFTER_ENTITIES.register(ctx -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.world == null || mc.player == null) return;
            if (!mc.player.hasStatusEffect(ModEffects.ORE_SENSE) || !pulseActive || cached.isEmpty()) return;

            R = 5 + 5 * Objects.requireNonNull(mc.player.getStatusEffect(ModEffects.ORE_SENSE)).getAmplifier();

            MatrixStack matrices = ctx.matrixStack();
            Vec3d cam = mc.gameRenderer.getCamera().getPos();

            OutlineVertexConsumerProvider outline = mc.getBufferBuilders().getOutlineVertexConsumers();
            outline.setColor(253, 255, 112, 255);
            VertexConsumer vc = outline.getBuffer(RenderLayer.getOutline(Atlases.BLOCKS));
            BlockRenderManager brm = mc.getBlockRenderManager();
            if (matrices != null) {
                matrices.push();
                matrices.translate(-cam.x, -cam.y, -cam.z);

                for (BlockPos pos : cached) {
                    BlockState sB = mc.world.getBlockState(pos);
                    if (sB.isAir()) continue;

                    BlockStateModel model = brm.getModels().getModel(sB);
                    if (model == null) continue;

                    List<BlockModelPart> parts = model.getParts(Random.create(pos.asLong()));

                    matrices.push();
                    matrices.translate(pos.getX(), pos.getY(), pos.getZ());
                    brm.renderBlock(sB, pos, mc.world, matrices, vc, false, parts);
                    matrices.pop();
                }

                matrices.pop();
            }
            outline.draw();
        });


        // Blit FBO → affichage écran pendant le pulse
        WorldRenderEvents.AFTER_TRANSLUCENT.register(ctx -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.world == null || mc.player == null) return;
            if (!mc.player.hasStatusEffect(ModEffects.ORE_SENSE) || !pulseActive) return;
            mc.worldRenderer.drawEntityOutlinesFramebuffer();
        });
    }

    private static List<BlockPos> scanOres(ClientWorld w, BlockPos c) {
        List<BlockPos> out = new ArrayList<>();
        BlockPos.Mutable m = new BlockPos.Mutable();
        int cx = c.getX(), cy = c.getY(), cz = c.getZ();
        for (int x = -R; x <= R; x++)
            for (int y = -R; y <= R; y++)
                for (int z = -R; z <= R; z++) {
                    m.set(cx + x, cy + y, cz + z);
                    BlockState s = w.getBlockState(m);
                    if (s.isIn(ModTags.Blocks.DETECTABLE_ORES)) out.add(m.toImmutable());
                }
        return out;
    }
}
