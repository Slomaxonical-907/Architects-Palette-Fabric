package com.slomaxonical.architectspalette.compat.rei;

import com.slomaxonical.architectspalette.core.crafting.WarpingRecipe;
import com.slomaxonical.architectspalette.core.registry.APItems;
import dev.architectury.event.EventResult;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.entry.EntryRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Objects;

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
