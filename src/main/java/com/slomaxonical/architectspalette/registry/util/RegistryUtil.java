package com.slomaxonical.architectspalette.registry.util;

import com.slomaxonical.architectspalette.ArchitectsPalette;
import com.slomaxonical.architectspalette.blocks.CopperNubBlock;
import com.slomaxonical.architectspalette.blocks.NubBlock;
import com.slomaxonical.architectspalette.blocks.abyssaline.AbyssalineNubBlock;
import com.slomaxonical.architectspalette.blocks.util.APBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.Oxidizable;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.slomaxonical.architectspalette.ArchitectsPalette.CONFIGS;

public class RegistryUtil {
    //SETS:
    public static Map<Block,StoneBlockSet> BlockSets = new HashMap<>();
    public static Map<Block,List<ItemConvertible>> nubs = new HashMap<>();
    public static Map<Block,List<Block>> chiseledNcrackedOres = new HashMap<>();
    public static List<StoneBlockSet> oreBrickSets = new ArrayList<>();


    public static Block makeNubOf(Block copyOf) {
        return makeNubOf(copyOf,List.of(copyOf));
    }
    public static Block makeNubOf(Block copyOf,Block additional) {
        return makeNubOf(copyOf,List.of(copyOf,additional));
    }
    public static Block makeNubOf(Block copyOf,List<ItemConvertible> from) {
        Block nub =  new NubBlock(FabricBlockSettings.copy(copyOf));
        nubs.put(nub,from);
        return nub;
    }
    public static Block makeCopperNub(Oxidizable.OxidationLevel lvl, Block base) {
        Block nub =  new CopperNubBlock(lvl,FabricBlockSettings.copy(base));
        nubs.put(nub,List.of(base));
        return nub;
    }
    public static AbyssalineNubBlock makeAbyssalineNub( List<ItemConvertible> recipeBases){
        AbyssalineNubBlock nub = new AbyssalineNubBlock(FabricBlockSettings.copyOf(APBlockSettings.ABYSSALINE).luminance(AbyssalineNubBlock.getLuminance()));
        nubs.put(nub,recipeBases);
        return nub;
    }
    //Create Blocks
    public static <B extends Block> B createBlock(String name, B anyBlock) {
        return createBlock(name, anyBlock, ItemGroup.BUILDING_BLOCKS);
    }

    public static <B extends Block> B createBlock(String name, B anyBlock, @Nullable ItemGroup group) {
        B block = Registry.register(Registry.BLOCK, new Identifier(ArchitectsPalette.MOD_ID, name), anyBlock);

        BlockItem blockItem = new BlockItem(block, new Item.Settings().group(group));
        Registry.register(Registry.ITEM, new Identifier(ArchitectsPalette.MOD_ID,name), blockItem);
        if (!(name.contains("vertical") && !CONFIGS.enableVerticalSlabs())) {
            ArchitectsPalette.ITEMGROUP_LIST.add(blockItem);
        }
        return block;
    }
    public static Block createPottedPlant(Block plant) {
        String name = Registry.BLOCK.getId(plant).getPath();
        return new FlowerPotBlock(plant, FabricBlockSettings.copy(Blocks.POTTED_ACACIA_SAPLING).breakInstantly().nonOpaque());
    }

}
