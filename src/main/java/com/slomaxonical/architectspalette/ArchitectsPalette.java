package com.slomaxonical.architectspalette;

import com.slomaxonical.architectspalette.compat.cloth_config.ApConfigs;
import com.slomaxonical.architectspalette.crafting.WarpingRecipe;
import com.slomaxonical.architectspalette.features.APConfiguredFeatures;
import com.slomaxonical.architectspalette.features.APFeatures;
import com.slomaxonical.architectspalette.loot.LootTableModifications;
import com.slomaxonical.architectspalette.registry.*;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ArchitectsPalette implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("Architect's Palette");
    public static final String MOD_ID = "architects_palette";
    public static final OwoItemGroup AP_GROUP = new OwoItemGroup(new Identifier(MOD_ID,"everything")) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(APBlocks.CHISELED_ABYSSALINE_BRICKS);
        }

        @Override
        public void appendStacks(DefaultedList<ItemStack> stacks) {
            super.appendStacks(stacks);
            for (Item item : ITEMGROUP_LIST) stacks.add(new ItemStack(item));
        }

        @Override
        protected void setup() {
            setCustomTexture(new Identifier(MOD_ID,"textures/gui/item_group.png"));
        }
    };
    public static List<Item> ITEMGROUP_LIST = new ArrayList<>();

    @Override
    public void onInitialize() {
        AutoConfig.register(ApConfigs.class, JanksonConfigSerializer::new);
        APParticles.register();

//        APBlocks.registerBlocks();
        FieldRegistrationHandler.register(APBlocks.class,MOD_ID,false);
        ConfigResourceCondition.init();
        FieldRegistrationHandler.register(APItems.class,MOD_ID,false);

//        APItemgroup.registerItemgroup();
        AP_GROUP.initialize();
        APMisc.registerFuel();
        APMisc.registerStrippables();
        APMisc.registerOxidizables();
        APMisc.registerWaxables();

        APSounds.registerSounds();
        WarpingRecipe.registerRecipe();
        APTrades.registerVillagerTrades();
        APTrades.registerWanderingTrades();

        APFeatures.register();
        APConfiguredFeatures.registerBiomeModifications();
        LootTableModifications.registerWitheredBones();
    }
}
