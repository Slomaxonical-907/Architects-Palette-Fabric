package com.slomaxonical.architectspalette.compat.rei;

import com.slomaxonical.architectspalette.crafting.WarpingRecipe;
import com.slomaxonical.architectspalette.registry.APBlocks;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.displays.DefaultInformationDisplay;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.TranslatableText;

import java.util.stream.Stream;

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

        addInfo(registry,APBlocks.CHISELED_ABYSSALINE_BRICKS,"chiseled_chargeable");
        Stream.of(APBlocks.ABYSSALINE, APBlocks.ABYSSALINE_BRICKS, APBlocks.ABYSSALINE_PILLAR, APBlocks.ABYSSALINE_BRICK_SLAB, APBlocks.ABYSSALINE_TILES, APBlocks.ABYSSALINE_TILE_SLAB, APBlocks.ABYSSALINE_LAMP_BLOCK, APBlocks.ABYSSALINE_BRICK_VERTICAL_SLAB, APBlocks.ABYSSALINE_TILE_VERTICAL_SLAB).
                forEach((i) -> addInfo(registry, i, "chargeable"));
        Stream.of(APBlocks.PLACID_ACACIA_TOTEM, APBlocks.GRINNING_ACACIA_TOTEM, APBlocks.SHOCKED_ACACIA_TOTEM, APBlocks.BLANK_ACACIA_TOTEM)
                .forEach((i) -> addInfo(registry, i, "totem_carving"));
        Stream.of(APBlocks.FLINT_BLOCK, APBlocks.FLINT_PILLAR, APBlocks.FLINT_TILES).
                forEach((i) -> addInfo(registry,i,"flint_damage"));
        Stream.of(APBlocks.SUNSTONE, APBlocks.MOONSTONE).
                forEach((i) -> addInfo(registry,i,"celestial_stones"));
        Stream.of(APBlocks.ALGAL_CAGE_LANTERN, APBlocks.GLOWSTONE_CAGE_LANTERN, APBlocks.REDSTONE_CAGE_LANTERN).
                forEach((i) -> addInfo(registry,i,"cage_lanterns"));
        Stream.of(APBlocks.HEAVY_END_STONE_BRICKS,APBlocks.HEAVY_CRACKED_END_STONE_BRICKS, APBlocks.HEAVY_STONE_BRICKS, APBlocks.HEAVY_MOSSY_STONE_BRICKS, APBlocks.HEAVY_CRACKED_STONE_BRICKS, APBlocks.HEAVY_CALCITE_BRICKS, APBlocks.HEAVY_DRIPSTONE_BRICKS, APBlocks.HEAVY_TUFF_BRICKS).
                forEach((i) -> addInfo(registry,i,"heavy_bricks"));
    }

    private void addInfo(DisplayRegistry registry, ItemConvertible item, String infoKey){
        registry.add(DefaultInformationDisplay.createFromEntry(EntryStacks.of(item),new TranslatableText(item.asItem().getTranslationKey())).line(new TranslatableText("architects_palette.info."+infoKey)));
    }
}
