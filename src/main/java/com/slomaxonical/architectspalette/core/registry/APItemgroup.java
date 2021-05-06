package com.slomaxonical.architectspalette.core.registry;

import com.slomaxonical.architectspalette.core.ArchitectsPalette;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static com.slomaxonical.architectspalette.core.registry.APBlocks.*;


public class APItemgroup {
    public static List<ItemStack> modItems = new ArrayList<>();
    public static final ItemGroup AP_ITEMGROUP = FabricItemGroupBuilder.create(
            new Identifier(ArchitectsPalette.MOD_ID, "everything"))
            .icon(() -> new ItemStack(CHISELED_ABYSSALINE_BRICKS))
            .appendItems(stackList ->{stackList.addAll(modItems);
            })
            .build();

    public static void registerItemgroup() {
    }
}
