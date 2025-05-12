package com.matibi.thealchemiststouch.datagen;

import com.matibi.thealchemiststouch.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Blocks.DETECTABLE_ORES)
                .addOptionalTag(BlockTags.COPPER_ORES.id())
                .addOptionalTag(BlockTags.DIAMOND_ORES.id())
                .addOptionalTag(BlockTags.GOLD_ORES.id())
                .addOptionalTag(BlockTags.EMERALD_ORES.id())
                .addOptionalTag(BlockTags.IRON_ORES.id())
                .addOptionalTag(BlockTags.LAPIS_ORES.id())
                .addOptionalTag(BlockTags.REDSTONE_ORES.id())
                .addOptionalTag(BlockTags.COAL_ORES.id())
                .add(Blocks.NETHER_GOLD_ORE)
                .add(Blocks.NETHER_QUARTZ_ORE)
                .add(Blocks.ANCIENT_DEBRIS);
    }
}