package com.slomaxonical.architectspalette.compat.emi;

import com.slomaxonical.architectspalette.crafting.WarpingRecipe;
import com.slomaxonical.architectspalette.registry.APBlocks;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.util.Identifier;

public class APEmiPlugin implements EmiPlugin {
	public static EmiRecipeCategory WARPING = new EmiRecipeCategory(new Identifier("architects_palette:warping"),
			EmiStack.of(APBlocks.WARPSTONE));
	
	@Override
	public void register(EmiRegistry registry) {
		registry.addCategory(WARPING);
		
		registry.addWorkstation(WARPING, EmiStack.of(APBlocks.WARPSTONE));
		
		for(WarpingRecipe recipe : registry.getRecipeManager().listAllOfType(WarpingRecipe.TYPE)) {
			registry.addRecipe(new WarpingEmiRecipe(recipe));
		}
	}

}
