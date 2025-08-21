package com.matibi.thealchemiststouch;

import com.matibi.thealchemiststouch.datagen.*;
import com.matibi.thealchemiststouch.datagen.language.ModFrenchLanguageProvider;
import com.matibi.thealchemiststouch.datagen.language.ModUsLanguageProvider;
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
		pack.addProvider(ModLootTableGenerator::new);
        pack.addProvider(ModFluidTagProvider::new);

		// langage
		pack.addProvider(ModFrenchLanguageProvider::new);
		pack.addProvider(ModUsLanguageProvider::new);
	}
}
