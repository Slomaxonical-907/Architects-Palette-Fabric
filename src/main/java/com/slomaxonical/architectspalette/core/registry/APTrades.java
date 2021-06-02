package com.slomaxonical.architectspalette.core.registry;


import com.slomaxonical.architectspalette.common.factories.BasicTradeFactory;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;

import static net.minecraft.village.VillagerProfession.*;


public class APTrades {
    public static void registerVillagerTrades(){

        // Fish Blocks
        TradeOfferHelper.registerVillagerOffers(FISHERMAN,2,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 2),
                new ItemStack(APBlocks.COD_LOG,8),8,4,0.05F))));
        TradeOfferHelper.registerVillagerOffers(FISHERMAN,2,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 2),
                new ItemStack(APBlocks.SALMON_LOG,8),8,4,0.05F))));
        // Entrails
        TradeOfferHelper.registerVillagerOffers(BUTCHER,2,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 1),
                new ItemStack(APBlocks.ENTRAILS,4),8,4,0.0F))));
        // Plating
        TradeOfferHelper.registerVillagerOffers(ARMORER,2,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 3),
                new ItemStack(APBlocks.PLATING_BLOCK,12),6,4,0.1F))));
        // Pipes
        TradeOfferHelper.registerVillagerOffers(TOOLSMITH,2,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 4),
                new ItemStack(APBlocks.PIPE,12),6,4,0.1F))));
        // Spools
        TradeOfferHelper.registerVillagerOffers(SHEPHERD,2,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 1),
                new ItemStack(APBlocks.SPOOL,2),5,4,0.0F))));


        // Temporary survival recipes until properly implemented
        TradeOfferHelper.registerVillagerOffers(MASON,1,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 1),
                new ItemStack(APBlocks.LIMESTONE,16),5,3,0.05F))));
        TradeOfferHelper.registerVillagerOffers(MASON,1,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 1),
                new ItemStack(APBlocks.OLIVESTONE_BRICK,16),5,3,0.05F))));
    }

    public static void registerWanderingTrades(){
      TradeOfferHelper.registerWanderingTraderOffers(2, factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 2),new ItemStack(APBlocks.SUNSTONE, 6),16,2,0.0F))));
      TradeOfferHelper.registerWanderingTraderOffers(2,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 2),new ItemStack(APBlocks.MOONSTONE, 6),16,2,0.0F))));
    }
}
