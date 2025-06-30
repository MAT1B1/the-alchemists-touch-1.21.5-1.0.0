package com.matibi.thealchemiststouch.item;

import com.matibi.thealchemiststouch.effect.TerrainApplicableEffect;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.PotionItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

import java.util.List;
import java.util.Optional;

public class AlchemicalRuneItem extends PotionItem {
    private final StatusEffectInstance effect;

    public AlchemicalRuneItem(Settings settings, StatusEffectInstance effect) {
        super(settings);
        this.effect = effect;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            user.sendMessage(Text.literal("Cette potion ne peut être utilisée que sur un bloc."), true);
        }
        return ActionResult.FAIL;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        ItemStack stack = context.getStack();

        if (!(world instanceof ServerWorld serverWorld)) return ActionResult.SUCCESS;

        StatusEffect effectType = effect.getEffectType().value();
        if (effectType instanceof TerrainApplicableEffect terrainEffect)
            terrainEffect.applyOnBlock(serverWorld, pos, effect.getDuration(), effect.getAmplifier());

        stack.decrement(1);
        return ActionResult.SUCCESS;
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = new ItemStack(this);

        if (effect != null) {
            PotionContentsComponent contents = new PotionContentsComponent(
                    Optional.empty(),
                    Optional.empty(),
                    List.of(effect),
                    Optional.empty()
            );
            stack.set(DataComponentTypes.POTION_CONTENTS, contents);
        }

        return stack;
    }

}

