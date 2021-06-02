package com.slomaxonical.architectspalette.core.loot;

import com.slomaxonical.architectspalette.core.registry.APItems;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class LootTableModifications {
    private static final Identifier WITHER_SKELETON_LOOT_TABLE_ID = new Identifier("minecraft", "entities/wither_skeleton");

    public static void registerWitheredBones(){
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if ( WITHER_SKELETON_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(APItems.WITHERED_BONE))
                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.1f,3.0f)).build())
                        .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0f,1.5f)).build());
                supplier.withPool(poolBuilder.build());
            }
        });
    }
}
