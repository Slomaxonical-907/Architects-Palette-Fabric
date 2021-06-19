package com.slomaxonical.architectspalette.compat.rei;

import com.slomaxonical.architectspalette.core.crafting.WarpingRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Collections;
import java.util.List;

public class WarpingDisplay implements Display {
    protected WarpingRecipe display;
    protected List<EntryIngredient> input;
    protected List<EntryIngredient> output;

    public WarpingDisplay(WarpingRecipe recipe) {
        this.display = recipe;

        this.input = EntryIngredients.ofIngredients(Collections.singletonList(recipe.getInput()));

        this.output = Collections.singletonList(EntryIngredients.of(recipe.getOutput()));
    }

    @Override
    public  List<EntryIngredient> getInputEntries() {
        return input;
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return output;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return APPlugin.WARPING;
    }
}
