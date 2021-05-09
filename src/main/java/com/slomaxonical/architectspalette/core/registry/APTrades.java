package com.slomaxonical.architectspalette.core.registry;


import com.slomaxonical.architectspalette.common.factories.BasicTradeFactory;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import static net.minecraft.village.VillagerProfession.*;
import static net.minecraft.village.TradeOffers.PROFESSION_TO_LEVELED_TRADE;


public class APTrades {
    public static void createBasicTrade(VillagerProfession profession, int rank, int priceInEmeralds, ItemStack sellItem,int uses, int exp, Float multiplier){
        TradeOfferHelper.registerVillagerOffers(profession,rank,  factories -> factories.add(new BasicTradeFactory(
        new TradeOffer(new ItemStack(Items.EMERALD, priceInEmeralds),sellItem,uses,exp,multiplier))));
    }
    public static void registerVillagerTrades(){

        // Fish Blocks
        addTrade(FISHERMAN, 2, new TradeOffers.SellItemFactory(new ItemStack(APBlocks.COD_LOG), 2, 8, 6, 4, 0.05F));
        addTrade(FISHERMAN, 2, new TradeOffers.SellItemFactory(new ItemStack(APBlocks.SALMON_LOG), 2, 8, 6, 4, 0.05F));

        // Entrails
        addTrade(BUTCHER, 2, new TradeOffers.SellItemFactory(new ItemStack(APBlocks.ENTRAILS), 1, 5, 5, 4, 0.0F));

        // Plating
        addTrade(ARMORER, 2, new TradeOffers.SellItemFactory(new ItemStack(APBlocks.PLATING_BLOCK.BLOCK), 3, 12, 6, 4, 0.1F));

        // Pipes
        addTrade(TOOLSMITH, 2, new TradeOffers.SellItemFactory(new ItemStack(APBlocks.PIPE), 4, 12, 6, 4, 0.1F));

        // Spools
        addTrade(SHEPHERD, 2, new TradeOffers.SellItemFactory(new ItemStack(APBlocks.SPOOL), 1, 2, 5, 4, 0.0F));


        // Temporary survival recipes until properly implemented
        addTrade(MASON, 1, new TradeOffers.SellItemFactory(new ItemStack(APBlocks.LIMESTONE.BLOCK), 1, 16, 5, 3, 0.05F));
        addTrade(MASON, 1, new TradeOffers.SellItemFactory(new ItemStack(APBlocks.OLIVESTONE_BRICK.BLOCK), 1, 16, 5, 3, 0.05F));

    }

    //public static void registerWanderingTrades(){
    //        TradeOfferHelper.registerWanderingTraderOffers(6,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 2),new ItemStack(APBlocks.SUNSTONE, 6),20,2,0.0F))));
    //        TradeOfferHelper.registerWanderingTraderOffers(4,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 2),new ItemStack(APBlocks.MOONSTONE, 6),20,2,0.0F))));
    //}

    public static void addTrade (VillagerProfession profession, int level, TradeOffers.Factory trade){
        TradeOffers.Factory[] fixedTrades = PROFESSION_TO_LEVELED_TRADE.get(profession).get(level);
        int newSize = fixedTrades.length + 1;

        TradeOffers.Factory[] newTrades = new TradeOffers.Factory[newSize];
        System.arraycopy(fixedTrades, 0, newTrades, 0, fixedTrades.length);
        newTrades[newSize - 1] = trade;

        PROFESSION_TO_LEVELED_TRADE.get(profession).put(level, newTrades);
    }



}
