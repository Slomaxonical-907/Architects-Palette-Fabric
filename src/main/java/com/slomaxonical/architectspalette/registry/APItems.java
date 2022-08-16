package com.slomaxonical.architectspalette.registry;

import com.slomaxonical.architectspalette.ArchitectsPalette;
import com.slomaxonical.architectspalette.registry.util.RegistryUtil;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Set;

public class APItems {
    public static final Item ALGAL_BLEND = createItem("algal_blend", new Item(new Item.Settings().group(ItemGroup.MATERIALS)), APBlocks.ALGAL_BRICKS);
    public static final Item ALGAL_BRICK = createItem("algal_brick", new Item(new Item.Settings().group(ItemGroup.MATERIALS)), APBlocks.ALGAL_BRICKS);

    public static final Item BRASS_BLEND = createItem("nether_brass_blend",  new Item(new Item.Settings().group(ItemGroup.MATERIALS)), APBlocks.NETHER_BRASS);
    public static final Item NETHER_BRASS_NUGGET = createItem("nether_brass_nugget", new Item(new Item.Settings().group(ItemGroup.MATERIALS)), APBlocks.NETHER_BRASS);
    public static final Item BRASS_INGOT = createItem("nether_brass_ingot", new Item(new Item.Settings().group(ItemGroup.MATERIALS)), APBlocks.NETHER_BRASS);
    public static final Item NETHER_BRASS_TORCH = createItem("nether_brass_torch", new WallStandingBlockItem(APBlocks.NETHER_BRASS_TORCH, APBlocks.NETHER_BRASS_WALL_TORCH,new Item.Settings().group(ItemGroup.DECORATIONS)), APBlocks.NETHER_BRASS_LANTERN);

    public static final Item SUNMETAL_BLEND = createItem("sunmetal_blend", new Item(new Item.Settings().group(ItemGroup.MATERIALS)), APBlocks.SUNMETAL);
    public static final Item SUNMETAL_BRICK = createItem("sunmetal_brick", new Item(new Item.Settings().group(ItemGroup.MATERIALS)), APBlocks.SUNMETAL);

    public static final Item WITHERED_BONE = createItem("withered_bone", new Item(new Item.Settings().group(ItemGroup.MATERIALS)), APBlocks.WITHERED_BONE_BLOCK);
    public static final Item ENTWINE_ROD = createItem("entwine_rod", new Item(new Item.Settings().group(ItemGroup.MATERIALS)), APBlocks.ENTWINE);

    public static final Item UNOBTANIUM = createItem("unobtanium", new Item(new Item.Settings().group(ItemGroup.MATERIALS)), APBlocks.UNOBTANIUM_BLOCK);

    public static <I extends Item> I createItem(String name, I item, Block block ) {
        I registeredItem = Registry.register(Registry.ITEM, new Identifier(ArchitectsPalette.MOD_ID, name), item);

        //add to itemgroup
        int index = APItemgroup.ITEMGROUP_LIST.indexOf(block.asItem());
        APItemgroup.ITEMGROUP_LIST.add(index, item);

        return registeredItem;
    }

        public static void registerItems(){}



}