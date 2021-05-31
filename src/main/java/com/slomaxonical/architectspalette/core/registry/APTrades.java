package com.slomaxonical.architectspalette.core.registry;


import com.slomaxonical.architectspalette.common.factories.BasicTradeFactory;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static net.minecraft.village.VillagerProfession.*;
import static net.minecraft.village.TradeOffers.PROFESSION_TO_LEVELED_TRADE;


public class APTrades {
    public static void registerVillagerTrades(){

        // Fish Blocks
//        addTrade(FISHERMAN, 2, new TradeOffers.SellItemFactory(new ItemStack(APBlocks.COD_LOG), 2, 8, 6, 4, 0.05F));
//        addTrade(FISHERMAN, 2, new TradeOffers.SellItemFactory(new ItemStack(APBlocks.SALMON_LOG), 2, 8, 6, 4, 0.05F));

        // Entrails
//        addTrade(BUTCHER, 2, new TradeOffers.SellItemFactory(new ItemStack(APBlocks.ENTRAILS), 1, 5, 5, 4, 0.0F));

        // Plating
//        addTrade(ARMORER, 2, new TradeOffers.SellItemFactory(new ItemStack(APBlocks.PLATING_BLOCK.BLOCK), 3, 12, 6, 4, 0.1F));

        // Pipes
//        addTrade(TOOLSMITH, 2, new TradeOffers.SellItemFactory(new ItemStack(APBlocks.PIPE), 4, 12, 6, 4, 0.1F));

        // Spools
//        addTrade(SHEPHERD, 2, new TradeOffers.SellItemFactory(new ItemStack(APBlocks.SPOOL), 1, 2, 5, 4, 0.0F));


        // Temporary survival recipes until properly implemented
//        addTrade(MASON, 1, new TradeOffers.SellItemFactory(new ItemStack(APBlocks.LIMESTONE.BLOCK), 1, 16, 5, 3, 0.05F));
//        addTrade(MASON, 1, new TradeOffers.SellItemFactory(new ItemStack(APBlocks.OLIVESTONE_BRICK.BLOCK), 1, 16, 5, 3, 0.05F));
        TradeOfferHelper.registerVillagerOffers(MASON,1,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 1),
                new ItemStack(APBlocks.LIMESTONE.BLOCK,16),5,3,0.05F))));
        TradeOfferHelper.registerVillagerOffers(MASON,1,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 1),
                new ItemStack(APBlocks.OLIVESTONE_BRICK.BLOCK,16),5,3,0.05F))));
    }

    public static void registerWanderingTrades(){
      TradeOfferHelper.registerWanderingTraderOffers(2, factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 2),new ItemStack(APBlocks.SUNSTONE, 6),20,2,0.0F))));
      TradeOfferHelper.registerWanderingTraderOffers(2,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 2),new ItemStack(APBlocks.MOONSTONE, 6),20,2,0.0F))));

//        addWanderingRareTrade(new TradeOffers.SellItemFactory(new ItemStack(APBlocks.MOONSTONE),2,6,20, 2, 0.0f));
//        addWanderingRareTrade(new TradeOffers.SellItemFactory(new ItemStack(APBlocks.SUNSTONE),2,6,20, 2, 0.0f));
    }
//    public static void addWanderingRareTrade(TradeOffers.Factory trade) {
//        List<TradeOffers.Factory> fixedRareTrades = DefaultedList.of();
//        fixedRareTrades.addAll(Arrays.asList(TradeOffers.WANDERING_TRADER_TRADES.get(1)));
//        fixedRareTrades.add(trade);
//        TradeOffers.WANDERING_TRADER_TRADES.put(1, fixedRareTrades.toArray(new TradeOffers.Factory[0]));
//    }
    public static void addTrade (VillagerProfession profession, int level, TradeOffers.Factory trade){
        TradeOffers.Factory[] fixedTrades = PROFESSION_TO_LEVELED_TRADE.get(profession).get(level);
        int newSize = fixedTrades.length + 1;

        TradeOffers.Factory[] newTrades = new TradeOffers.Factory[newSize];
        System.arraycopy(fixedTrades, 0, newTrades, 0, fixedTrades.length);
        newTrades[newSize - 1] = trade;

        PROFESSION_TO_LEVELED_TRADE.get(profession).put(level, newTrades);
    }
}
