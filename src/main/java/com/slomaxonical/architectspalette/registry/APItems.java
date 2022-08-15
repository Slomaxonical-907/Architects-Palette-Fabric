package com.slomaxonical.architectspalette.registry;

import com.slomaxonical.architectspalette.ArchitectsPalette;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class APItems {
    private static int ITEM_REGISTERING_LAG = 0;
    public static final Item ALGAL_BLEND = createItem("algal_blend", new Item(new Item.Settings().group(ItemGroup.MATERIALS)),APBlocks.INDEXS.get(0));
    public static final Item ALGAL_BRICK = createItem("algal_brick", new Item(new Item.Settings().group(ItemGroup.MATERIALS)),APBlocks.INDEXS.get(0));

    public static final Item BRASS_BLEND = createItem("nether_brass_blend",  new Item(new Item.Settings().group(ItemGroup.MATERIALS)),APBlocks.INDEXS.get(1));
    public static final Item NETHER_BRASS_NUGGET = createItem("nether_brass_nugget", new Item(new Item.Settings().group(ItemGroup.MATERIALS)),APBlocks.INDEXS.get(1));
    public static final Item BRASS_INGOT = createItem("nether_brass_ingot", new Item(new Item.Settings().group(ItemGroup.MATERIALS)),APBlocks.INDEXS.get(1));
    public static final Item NETHER_BRASS_TORCH = createItem("nether_brass_torch", new WallStandingBlockItem(APBlocks.NETHER_BRASS_TORCH, APBlocks.NETHER_BRASS_WALL_TORCH,new Item.Settings().group(ItemGroup.DECORATIONS)),APBlocks.INDEXS.get(2));

    public static final Item SUNMETAL_BLEND = createItem("sunmetal_blend", new Item(new Item.Settings().group(ItemGroup.MATERIALS)),APBlocks.INDEXS.get(3));
    public static final Item SUNMETAL_BRICK = createItem("sunmetal_brick", new Item(new Item.Settings().group(ItemGroup.MATERIALS)),APBlocks.INDEXS.get(3));

    public static final Item WITHERED_BONE = createItem("withered_bone", new Item(new Item.Settings().group(ItemGroup.MATERIALS)),APBlocks.INDEXS.get(4));
    public static final Item WARDSTONE_BLEND = createItem("wardstone_blend",new Item(new Item.Settings().group(ItemGroup.MATERIALS)),APBlocks.INDEXS.get(5));
    public static final Item WARDSTONE_BRICK = createItem("wardstone_brick",new Item(new Item.Settings().group(ItemGroup.MATERIALS)),APBlocks.INDEXS.get(5));
    public static final Item ENTWINE_ROD = createItem("entwine_rod", new Item(new Item.Settings().group(ItemGroup.MATERIALS)),APBlocks.INDEXS.get(6));

    public static final Item UNOBTANIUM = createItem("unobtanium", new Item(new Item.Settings().group(ItemGroup.MATERIALS)),APBlocks.INDEXS.get(7));
    public static <I extends Item> I createItem(String name, I item, int index ) {
        I registeredItem = Registry.register(Registry.ITEM, new Identifier(ArchitectsPalette.MOD_ID, name), item);
        //add to itemgroup
        APItemgroup.ITEMGROUP_LIST.add(index + ITEM_REGISTERING_LAG, new ItemStack(item));
        ITEM_REGISTERING_LAG++;//Todo:Improve this shitty ting... shoudnt need index

        return registeredItem;
    }

        public static void registerItems(){}



}