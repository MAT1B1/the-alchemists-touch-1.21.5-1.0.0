package com.matibi.thealchemiststouch;

import com.matibi.thealchemiststouch.datagen.ModBlockTagProvider;
import com.matibi.thealchemiststouch.datagen.ModItemTagProvider;
import com.matibi.thealchemiststouch.datagen.ModModelProvider;
import com.matibi.thealchemiststouch.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TheAlchemistsTouchDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}
}
