package com.slomaxonical.architectspalette.loot;

import com.google.gson.internal.bind.TreeTypeAdapter;
import com.slomaxonical.architectspalette.registry.APItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.Arrays;

public class LootTableModifications {
    //todo:waiting on modifyLootPool modification soon
    private static final Identifier WITHER_SKELETON_LOOT_TABLE_ID = new Identifier("minecraft", "entities/wither_skeleton");
    public static void registerWitheredBones(){
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (WITHER_SKELETON_LOOT_TABLE_ID.equals(id) && source != LootTableSource.REPLACED) {
                if (source != LootTableSource.VANILLA) {
                    throw new AssertionError("wither skelleton loot table should have LootTableSource.VANILLA");
                }
                LootPool customPool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(APItems.WITHERED_BONE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.1f, 3.0f)).build())
                        .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.5f)).build())
                        .build();

                tableBuilder.pool(customPool);
//                System.out.println(lootManager.getTable(WITHER_SKELETON_LOOT_TABLE_ID).toString());
//                for (LootPool pool : lootManager.getTable(WITHER_SKELETON_LOOT_TABLE_ID).pools){
//                    System.out.println(pool.rolls);
//                    for (LootPoolEntry entry: pool.entries){
//                        System.out.println(entry.getType().getJsonSerializer().toString());
//                    };
//                    System.out.println(Arrays.toString(pool.functions));
//                    System.out.println(Arrays.toString(pool.conditions));
//                }
            }
        });
    }
}
