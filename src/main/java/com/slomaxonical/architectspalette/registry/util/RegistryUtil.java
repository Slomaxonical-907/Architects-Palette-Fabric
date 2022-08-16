package com.slomaxonical.architectspalette.registry.util;

import com.slomaxonical.architectspalette.ArchitectsPalette;
import com.slomaxonical.architectspalette.compat.cloth_config.ApConfigs;
import com.slomaxonical.architectspalette.registry.APBlocks;
import com.slomaxonical.architectspalette.registry.APItemgroup;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

public class RegistryUtil {
    //Create Blocks
    public static <B extends Block> B createBlock(String name, B anyBlock) {
        return createBlock(name, anyBlock, ItemGroup.BUILDING_BLOCKS);
    }

    public static <B extends Block> B createBlock(String name, B anyBlock, @Nullable ItemGroup group) {
        B block = Registry.register(Registry.BLOCK, new Identifier(ArchitectsPalette.MOD_ID, name), anyBlock);

        BlockItem blockItem = new BlockItem(block, new Item.Settings().group(group));
        Registry.register(Registry.ITEM, new Identifier(ArchitectsPalette.MOD_ID,name), blockItem);
        if (!(name.contains("vertical") && !AutoConfig.getConfigHolder(ApConfigs.class).getConfig().enableVerticalSlabs)) {
            APItemgroup.ITEMGROUP_LIST.add(blockItem);
        }
        return block;
    }

    public static <B extends Block> B createBlockNoItem(String name, B anyBlock) {
        return Registry.register(Registry.BLOCK, new Identifier(ArchitectsPalette.MOD_ID, name), anyBlock);
    }

    public static Block createPottedPlant(Block plant) {
        String name = Registry.BLOCK.getId(plant).getPath();
        return new FlowerPotBlock(plant, FabricBlockSettings.copy(Blocks.POTTED_ACACIA_SAPLING).breakInstantly().nonOpaque());
    }

    //coudnt bring myself to make a class for 1 line
    public static void registerFuel(){
        FuelRegistry.INSTANCE.add(APBlocks.CHARCOAL_BLOCK, 1600);
    }

    public static void registerStrippables() {
        StrippableBlockRegistry.register(APBlocks.TWISTED_LOG, APBlocks.STRIPPED_TWISTED_LOG);
        StrippableBlockRegistry.register(APBlocks.TWISTED_WOOD, APBlocks.STRIPPED_TWISTED_WOOD);
    }
}
