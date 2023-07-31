package com.slomaxonical.architectspalette.registry;

import com.slomaxonical.architectspalette.ArchitectsPalette;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class APItems implements ItemRegistryContainer {
    public static final Item ALGAL_BLEND = LinkItem(APBlocks.ALGAL_BRICKS);
    public static final Item ALGAL_BRICK = LinkItem(APBlocks.ALGAL_BRICKS);

    public static final Item NETHER_BRASS_BLEND = LinkItem(APBlocks.NETHER_BRASS_BLOCK);
    public static final Item NETHER_BRASS_NUGGET = LinkItem(APBlocks.NETHER_BRASS_BLOCK);
    public static final Item NETHER_BRASS_INGOT = LinkItem(APBlocks.NETHER_BRASS_BLOCK);
    public static final Item NETHER_BRASS_TORCH = LinkItem(new WallStandingBlockItem(APBlocks.NETHER_BRASS_TORCH, APBlocks.NETHER_BRASS_WALL_TORCH,new FabricItemSettings().group(ItemGroup.DECORATIONS)),APBlocks.NETHER_BRASS_LANTERN);

    public static final Item SUNMETAL_BLEND = LinkItem(APBlocks.SUNMETAL_BLOCK);
    public static final Item SUNMETAL_BRICK = LinkItem(APBlocks.SUNMETAL_BLOCK);

    public static final Item WITHERED_BONE = LinkItem(APBlocks.WITHERED_BONE_BLOCK);
    public static final Item WARDSTONE_BLEND = LinkItem(APBlocks.WARDSTONE);
    public static final Item WARDSTONE_BRICK = LinkItem(APBlocks.WARDSTONE);
    public static final Item ENTWINE_ROD = LinkItem(APBlocks.ENTWINE_BLOCK);

    public static final Item UNOBTANIUM = LinkItem(APBlocks.UNOBTANIUM_BLOCK);

    //TODO:can possibly just use tags to link em when i have multiple tabs
    public static Item LinkItem(Item item,Block block){
        int index = ArchitectsPalette.ITEMGROUP_LIST.indexOf(block.asItem());
        ArchitectsPalette.ITEMGROUP_LIST.add(index, item);
        return item;
    }
    public static Item LinkItem(Block block){
        Item item = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
        int index = ArchitectsPalette.ITEMGROUP_LIST.indexOf(block.asItem());
        ArchitectsPalette.ITEMGROUP_LIST.add(index, item);
        return item;
    }


}