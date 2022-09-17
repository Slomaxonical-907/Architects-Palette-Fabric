package com.slomaxonical.architectspalette.registry;

import com.slomaxonical.architectspalette.ArchitectsPalette;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class APItems implements ItemRegistryContainer {
    public static final Item ALGAL_BLEND = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item ALGAL_BRICK = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

    public static final Item NETHER_BRASS_BLEND = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item NETHER_BRASS_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item NETHER_BRASS_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item NETHER_BRASS_TORCH = new WallStandingBlockItem(APBlocks.NETHER_BRASS_TORCH, APBlocks.NETHER_BRASS_WALL_TORCH,new Item.Settings().group(ItemGroup.DECORATIONS));

    public static final Item SUNMETAL_BLEND = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item SUNMETAL_BRICK = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

    public static final Item WITHERED_BONE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item WARDSTONE_BLEND = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item WARDSTONE_BRICK = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item ENTWINE_ROD = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

    public static final Item UNOBTANIUM = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

    public static <I extends Item> I createItem(String name, I item, Block block ) {
        I registeredItem = Registry.register(Registry.ITEM, new Identifier(ArchitectsPalette.MOD_ID, name), item);

        //add to itemgroup
        int index = APItemgroup.ITEMGROUP_LIST.indexOf(block.asItem());
        APItemgroup.ITEMGROUP_LIST.add(index, item);

        return registeredItem;
    }


}