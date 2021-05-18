package com.slomaxonical.architectspalette.core.loot;

import com.slomaxonical.architectspalette.core.registry.APItems;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.util.Identifier;

public class LootTableModifications {
    private static final Identifier WITHER_SKELETON_LOOT_TABLE_ID = new Identifier("minecraft", "entities/wither_skeleton");

    public static void registerWitheredBones(){
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if ( WITHER_SKELETON_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootTableRange.create(1))
                        .with(ItemEntry.builder(APItems.WITHERED_BONE))
                        .withFunction(SetCountLootFunction.builder(UniformLootTableRange.between(0.0f,2.0f)).build())
                        .withFunction(LootingEnchantLootFunction.builder(UniformLootTableRange.between(0.0f,1.0f)).build());
                supplier.withPool(poolBuilder.build());
            }
        });
    }
}
