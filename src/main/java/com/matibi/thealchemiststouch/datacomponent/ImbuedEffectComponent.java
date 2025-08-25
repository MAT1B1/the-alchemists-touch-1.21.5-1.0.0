package com.matibi.thealchemiststouch.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

public record ImbuedEffectComponent(RegistryEntry<StatusEffect> effect, int hitsRemaining, int amplifier)
        implements TooltipAppender {

    public static final Codec<ImbuedEffectComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Registries.STATUS_EFFECT.getEntryCodec().fieldOf("effect").forGetter(ImbuedEffectComponent::effect),
            Codec.INT.fieldOf("hits").forGetter(ImbuedEffectComponent::hitsRemaining),
            Codec.INT.optionalFieldOf("amp", 0).forGetter(ImbuedEffectComponent::amplifier)
    ).apply(instance, ImbuedEffectComponent::new));

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> textConsumer,
                              TooltipType type, ComponentsAccess components) {

        Formatting color = switch (this.effect().value().getCategory()) {
            case BENEFICIAL, NEUTRAL -> Formatting.BLUE;
            case HARMFUL    -> Formatting.RED;
        };

        MutableText effectName = Text.translatable(this.effect().value().getTranslationKey());
        if (this.amplifier() > 0) {
            effectName = effectName.append(Text.literal(" "))
                    .append(Text.translatable("potion.potency." + this.amplifier()));
        }
        textConsumer.accept(
                Text.translatable("tooltip.the-alchemists-touch.imbued_line",
                                effectName, this.hitsRemaining())
                        .formatted(color)
        );
    }
}
