package com.slomaxonical.architectspalette.loot;

import com.slomaxonical.architectspalette.registry.APItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class LootTableModifications {
    //todo:waiting on modifyLootPool modification soon
    private static final Identifier WITHER_SKELETON_LOOT_TABLE_ID = new Identifier("minecraft", "entities/wither_skeleton");
    private static final LootPool  WITHER_BONE_POOL = LootPool.builder()
            .rolls(ConstantLootNumberProvider.create(1))
            .with(ItemEntry.builder(Items.BONE))
            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)).build())
            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f)).build())
            .build();
    public static void registerWitheredBones(){
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (WITHER_SKELETON_LOOT_TABLE_ID.equals(id)) {
                LootPool customPool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(APItems.WITHERED_BONE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 3.0f)).build())
                        .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.5f)).build())
                        .build();

                tableBuilder.pool(customPool);
            }
//            if (WITHER_SKELETON_LOOT_TABLE_ID.equals(id)) {
//                tableBuilder.modifyPools(poolbuilder->{
//                    for (LootPoolEntry entry: poolbuilder.build().entries) {
//                        if (entry instanceof ItemEntry IE){
//                            if (IE.getItem() == Items.BONE) poolbuilder.rolls(ConstantLootNumberProvider.create(0.0f));
//                        }//I'm about to inject a getItem method in ItemEntry plz send help
//                    }//still not convinced with this, imma leave it without removing bone for now
//                });
//            }
        });
    }
}
