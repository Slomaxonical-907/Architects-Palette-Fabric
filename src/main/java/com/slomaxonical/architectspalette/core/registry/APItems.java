package com.slomaxonical.architectspalette.core.registry;

import com.slomaxonical.architectspalette.core.ArchitectsPalette;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.List;

import static com.slomaxonical.architectspalette.core.registry.APBlocks.*;

public class APItems {

    public static final Item ALGAL_BLEND = createItem("algal_blend", new Item(new Item.Settings().group(ItemGroup.MATERIALS)),33);
    public static final Item ALGAL_BRICK = createItem("algal_brick", new Item(new Item.Settings().group(ItemGroup.MATERIALS)),34);

    public static final Item SUNMETAL_BLEND = createItem("sunmetal_blend", new Item(new Item.Settings().group(ItemGroup.MATERIALS)),46);
    public static final Item SUNMETAL_BRICK = createItem("sunmetal_brick", new Item(new Item.Settings().group(ItemGroup.MATERIALS)),47);

    public static final Item WITHERED_BONE = createItem("withered_bone", new Item(new Item.Settings().group(ItemGroup.MATERIALS)),114);
    public static final Item ENTWINE_ROD = createItem("entwine_rod", new Item(new Item.Settings().group(ItemGroup.MATERIALS)),155);


    public static <I extends Item> I createItem(String name, I item, int index ) {
        I registeredItem = Registry.register(Registry.ITEM, new Identifier(ArchitectsPalette.MOD_ID, name), item);
        //add to itemgroup
        APItemgroup.modItems.add(index,new ItemStack(item));
        return registeredItem;
    }

        public static void registerItems(){}



}