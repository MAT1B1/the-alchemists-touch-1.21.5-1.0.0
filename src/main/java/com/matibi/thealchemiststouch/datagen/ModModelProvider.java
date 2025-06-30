package com.matibi.thealchemiststouch.datagen;

import com.matibi.thealchemiststouch.TheAlchemistsTouch;
import com.matibi.thealchemiststouch.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.POISONOUS_BEETROOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.POISONOUS_CARROT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ALCHEMIST_CORE, Models.GENERATED);

        itemModelGenerator.register(ModItems.RUNE, Models.GENERATED);
    }
}
