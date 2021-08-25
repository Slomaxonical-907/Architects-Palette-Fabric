package com.slomaxonical.architectspalette.compat.rei;

import com.slomaxonical.architectspalette.core.crafting.WarpingRecipe;
import com.slomaxonical.architectspalette.core.registry.APBlocks;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.displays.DefaultInformationDisplay;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.TranslatableText;

public class APPlugin implements REIClientPlugin {
    public static final CategoryIdentifier<WarpingDisplay> WARPING = CategoryIdentifier.of("architects_palette", "portal_warping");
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new WarpingCategory());
        registry.removePlusButton(APPlugin.WARPING);
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(WarpingRecipe.class, WarpingDisplay::new);

        registry.add(addInfo(Items.HEART_OF_THE_SEA,"heart_info"));
        registry.add(addInfo(APBlocks.FLINT_BLOCK.asItem(),"flint_blocks_info"));
        registry.add(addInfo(APBlocks.FLINT_PILLAR.asItem(),"flint_blocks_info"));
        registry.add(addInfo(APBlocks.FLINT_TILES.asItem(), "flint_blocks_info"));

        registry.add(addInfo(APBlocks.SUNSTONE.asItem(),"sunstone_info"));
        registry.add(addInfo(APBlocks.MOONSTONE.asItem(), "moonstone_info"));
        registry.add(addInfo(APBlocks.GRINNING_ACACIA_TOTEM.asItem(),"acacia_totems_info"));

        registry.add(addInfo(APBlocks.HEAVY_STONE_BRICKS.asItem(),"heavy_bricks_info"));
        registry.add(addInfo(APBlocks.HEAVY_END_STONE_BRICKS.asItem(),"heavy_bricks_info"));
        registry.add(addInfo(APBlocks.HEAVY_MOSSY_STONE_BRICKS.asItem(),"heavy_bricks_info"));

        registry.add(addInfo(APBlocks.ALGAL_CAGE_LANTERN.asItem(),"cage_lantern_info"));
        registry.add(addInfo(APBlocks.GLOWSTONE_CAGE_LANTERN.asItem(),"cage_lantern_info"));
        registry.add(addInfo(APBlocks.REDSTONE_CAGE_LANTERN.asItem(),"cage_lantern_info"));
    }

    private DefaultInformationDisplay addInfo(Item item, String key){
        EntryStack<ItemStack> entryStack = EntryStacks.of(item);
        DefaultInformationDisplay stackInfo = DefaultInformationDisplay.createFromEntry(entryStack,new TranslatableText(item.getTranslationKey()));
        stackInfo.line(new TranslatableText("architects_palette.rei."+key));
        return stackInfo;
    }
}
