package com.matibi.thealchemiststouch.datagen;

import com.matibi.thealchemiststouch.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.state.property.Properties;

import java.util.concurrent.CompletableFuture;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {

    public ModLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        Block carrots = Blocks.CARROTS;
        Block beetroots = Blocks.BEETROOTS;
        addDrop(carrots, cropDrops(
                carrots,
                Items.CARROT,
                Items.CARROT,
                ModItems.POISONOUS_CARROT,
                0.02f,
                BlockStatePropertyLootCondition.builder(carrots)
                    .properties(StatePredicate.Builder.create()
                            .exactMatch(Properties.AGE_7, 7))
        ));
        addDrop(beetroots,cropDrops(
                beetroots,
                Items.BEETROOT,
                Items.BEETROOT_SEEDS,
                ModItems.POISONOUS_BEETROOT,
                0.02f,
                BlockStatePropertyLootCondition.builder(beetroots)
                    .properties(StatePredicate.Builder.create()
                            .exactMatch(Properties.AGE_3, 3))
        ));
    }

    public LootTable.Builder cropDrops(Block crop, Item mainDrop, Item seedDrop, Item specialDrop, float specialChance, LootCondition.Builder maturityCondition) {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.applyExplosionDecay(
                crop,
                LootTable.builder()
                        // Pool pour le drop principal
                        .pool(LootPool.builder()
                                .conditionally(maturityCondition)
                                .with(ItemEntry.builder(mainDrop))
                        )
                        // Pool pour les graines (ou autre item affecté par Fortune)
                        .pool(LootPool.builder()
                                .conditionally(maturityCondition)
                                .with(ItemEntry.builder(seedDrop)
                                        .apply(ApplyBonusLootFunction.binomialWithBonusCount(
                                                impl.getOrThrow(Enchantments.FORTUNE),
                                                0.5714286F, 3
                                        ))
                                )
                        )
                        // Pool pour l'item spécial (comme la Poisonous Carrot)
                        .pool(LootPool.builder()
                                .conditionally(maturityCondition)
                                .with(ItemEntry.builder(specialDrop)
                                        .conditionally(RandomChanceLootCondition.builder(specialChance))
                                )
                        )
        );
    }

}
