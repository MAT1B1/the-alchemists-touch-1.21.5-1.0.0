package com.matibi.thealchemiststouch.datagen;

import com.matibi.thealchemiststouch.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(ItemTags.BREWING_FUEL)
                .add(Items.LAVA_BUCKET);

        valueLookupBuilder(ModTags.Items.IMBUEABLE_WEAPONS)
                .addOptionalTag(ItemTags.SWORDS)
                .addOptionalTag(ItemTags.AXES)
                .add(Items.TRIDENT)
                .add(Items.MACE);
    }
}
