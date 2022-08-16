package com.slomaxonical.architectspalette.registry;

import com.slomaxonical.architectspalette.ArchitectsPalette;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class APItemgroup {
    public static List<Item> ITEMGROUP_LIST = new ArrayList<>();
    public static List<ItemStack> ITEMGROUP_STACKS = new ArrayList<>();

    public static void registerItemgroup() {
        for (Item item: ITEMGROUP_LIST) {
            ITEMGROUP_STACKS.add(new ItemStack(item));
        }
        FabricItemGroupBuilder.create(
                new Identifier(ArchitectsPalette.MOD_ID, "everything"))
                .icon(() -> new ItemStack(APBlocks.CHISELED_ABYSSALINE_BRICKS))
                .appendItems(stackList -> stackList.addAll(ITEMGROUP_STACKS))
                .build();

    }
}
