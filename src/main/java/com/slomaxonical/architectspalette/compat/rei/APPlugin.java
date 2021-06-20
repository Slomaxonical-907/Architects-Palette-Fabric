package com.slomaxonical.architectspalette.compat.rei;

import com.slomaxonical.architectspalette.core.crafting.WarpingRecipe;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;

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
    }
}
