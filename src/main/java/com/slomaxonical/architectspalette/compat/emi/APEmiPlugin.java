package com.slomaxonical.architectspalette.compat.emi;

import com.mojang.blaze3d.systems.RenderSystem;
import com.slomaxonical.architectspalette.crafting.WarpingRecipe;
import com.slomaxonical.architectspalette.registry.APBlocks;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiRenderable;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.util.Identifier;

public class APEmiPlugin implements EmiPlugin {
    public static EmiRecipeCategory WARPING = new EmiRecipeCategory(new Identifier("architects_palette:warping"),
            EmiStack.of(APBlocks.WARPSTONE),simplifiedRenderer());

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(WARPING);

        for(WarpingRecipe recipe : registry.getRecipeManager().listAllOfType(WarpingRecipe.TYPE)) {
            registry.addRecipe(new WarpingEmiRecipe(recipe));
        }
    }
    //Probably there is a better suiting drawTexture() overload but im too lazy...(this is what emi uses).
    private static EmiRenderable simplifiedRenderer() {
        return (matrices, x, y, delta) -> {
            RenderSystem.setShaderTexture(0, new Identifier("architects_palette", "textures/gui/widget.png"));
            DrawableHelper.drawTexture(matrices, x, y, 0, 0, 16, 16, 16, 16);
        };
    }
}
