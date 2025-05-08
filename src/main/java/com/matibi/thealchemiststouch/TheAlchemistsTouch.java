package com.matibi.thealchemiststouch;

import com.matibi.thealchemiststouch.potion.ModPotion;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TheAlchemistsTouch implements ModInitializer {
	public static final String MOD_ID = "the-alchemists-touch";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModPotion.register();

		// max stack des potions
		DefaultItemComponentEvents.MODIFY.register(context -> {
			context.modify(item ->
							item == Items.POTION || item == Items.SPLASH_POTION || item == Items.LINGERING_POTION,
					(builder, item) -> {
						builder.add(DataComponentTypes.MAX_STACK_SIZE, 16);
					}
			);
		});
	}
}