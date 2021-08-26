package com.slomaxonical.architectspalette.compat.rei;

import com.slomaxonical.architectspalette.core.crafting.WarpingRecipe;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.RecipeDisplay;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class WarpingDisplay implements RecipeDisplay {

    protected WarpingRecipe display;
    protected List<EntryStack> input;
    protected List<EntryStack> output;

    public WarpingDisplay(WarpingRecipe recipe) {
        this.display = recipe;

        this.input = EntryStack.ofIngredient(recipe.getInput());

        this.output = Collections.singletonList(EntryStack.create(recipe.getOutput()));
    }
    @Override
    public @NotNull List<List<EntryStack>> getInputEntries() {
        return Collections.singletonList(input);
    }

    @Override
    public @NotNull Identifier getRecipeCategory() {
        return APPlugin.WARPING;
    }
    @Override
    public @NotNull List<List<EntryStack>> getResultingEntries() {
        return Collections.singletonList(output);
    }
}
