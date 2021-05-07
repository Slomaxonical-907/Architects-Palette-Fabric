package com.slomaxonical.architectspalette.core.registry;


import com.slomaxonical.architectspalette.common.factories.BasicTradeFactory;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import static net.minecraft.village.VillagerProfession.*;


public class APTrades {
//    public static void createBasicTrade(VillagerProfession profession, int rank, int priceInEmeralds, ItemStack sellItem,int uses, int exp, Float multiplier){
//        TradeOfferHelper.registerVillagerOffers(profession,rank,  factories -> factories.add(new BasicTradeFactory(
//        new TradeOffer(new ItemStack(Items.EMERALD, priceInEmeralds),sellItem,uses,exp,multiplier))));
//    }
    public static void registerVillagerTrades(){
//        // Fish Blocks
//        createBasicTrade(FISHERMAN, 2,2, new ItemStack(APBlocks.COD_LOG, 8), 6, 4, 0.05F);
//        createBasicTrade(FISHERMAN, 2,2, new ItemStack(APBlocks.SALMON_LOG, 8), 6, 4, 0.05F);
//        // Entrails
//        createBasicTrade(BUTCHER, 2, 1, new ItemStack(APBlocks.ENTRAILS, 5), 5, 4, 0.0F);
//        // Plating
//        createBasicTrade(ARMORER, 2, 3, new ItemStack(APBlocks.PLATING_BLOCK.BLOCK, 12), 6, 4, 0.1F);
//        // Pipes
//        createBasicTrade(TOOLSMITH, 2, 4, new ItemStack(APBlocks.PIPE, 12), 6, 4, 0.1F);
//        // Spools
//        createBasicTrade(SHEPHERD, 2, 1, new ItemStack(APBlocks.SPOOL, 2), 5, 4, 0.0F);
//        // Temporary survival recipes until properly implemented
//        createBasicTrade(MASON, 1, 1, new ItemStack(APBlocks.LIMESTONE.BLOCK, 16), 5, 3, 0.05F);
//        createBasicTrade(MASON, 1, 1, new ItemStack(APBlocks.OLIVESTONE_BRICK.BLOCK, 16), 5, 3, 0.05F);
        TradeOfferHelper.registerVillagerOffers(MASON,1,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 1),
                new ItemStack(APBlocks.LIMESTONE.BLOCK,16),5,3,0.05F))));
        TradeOfferHelper.registerVillagerOffers(MASON,1,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 1),
                new ItemStack(APBlocks.OLIVESTONE_BRICK.BLOCK,16),5,3,0.05F))));
    }
    public static void registerWanderingTrades(){
//        TradeOfferHelper.registerWanderingTraderOffers(6,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 2),new ItemStack(APBlocks.SUNSTONE, 6),20,2,0.0F))));
//        TradeOfferHelper.registerWanderingTraderOffers(4,factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 2),new ItemStack(APBlocks.MOONSTONE, 6),20,2,0.0F))));

    }

}
