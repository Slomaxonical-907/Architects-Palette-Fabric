package com.slomaxonical.architectspalette.compat.rei;

import com.slomaxonical.architectspalette.core.ArchitectsPalette;
import com.slomaxonical.architectspalette.core.crafting.WarpingRecipe;
import me.shedaniel.rei.api.RecipeHelper;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import net.minecraft.util.Identifier;

public class APPlugin implements REIPluginV0 {

    public static final Identifier WARPING = new Identifier(ArchitectsPalette.MOD_ID,"warping");

    @Override
    public Identifier getPluginIdentifier() {
        return new Identifier(ArchitectsPalette.MOD_ID,"warping_plugin");
    }
    @Override
    public void registerPluginCategories(RecipeHelper recipeHelper){
        recipeHelper.registerCategory(new WarpingCategory());
    }
    @Override
    public void registerRecipeDisplays(RecipeHelper recipeHelper){
        recipeHelper.registerRecipes(WARPING, WarpingRecipe.class, WarpingDisplay::new);
    }


}
