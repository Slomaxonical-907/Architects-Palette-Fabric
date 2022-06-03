package com.slomaxonical.architectspalette.compat.rei;

import com.slomaxonical.architectspalette.registry.APBlocks;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;


public class WarpingCategory implements DisplayCategory<WarpingDisplay> {
    private static final Text NAME = Text.translatable("architects_palette.info.warping_recipe_title");

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(APBlocks.WARPSTONE);
    }

    @Override
    public Text getTitle() {
        return NAME;
    }

    @Override
    public CategoryIdentifier<? extends WarpingDisplay> getCategoryIdentifier() {
        return APPlugin.WARPING;
    }
    @Override
    public List<Widget> setupDisplay(WarpingDisplay recipeDisplay, Rectangle bounds) {
        Point origin = new Point(bounds.getCenterX() - 58, bounds.getCenterY() - 27);
        int textureSize = 35;
        List<Widget> widgets = new ArrayList<>();

        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createTexturedWidget(new Identifier("architects_palette", "textures/gui/nether_portal.png"), bounds.getCenterX()+1-textureSize/2, bounds.getCenterY()-14,0,0,textureSize,textureSize,textureSize,textureSize));
        widgets.add(Widgets.createSlot(new Point(bounds.getCenterX() - 45, bounds.y + 27)).entries(getInput(recipeDisplay,0)).markInput());
        widgets.add(Widgets.createSlot(new Point(bounds.getCenterX() + 30, bounds.y + 27)).entries(getOutput(recipeDisplay, 0)).markOutput());
        //will do this later
        Text dimensionName = Text.translatable("architects_palette.info.warping_toss_description", "the Nether");
        widgets.add(Widgets.createLabel(new Point(bounds.getCenterX()-2,bounds.getY()+5),Text.literal("toss")).tooltip(Text.of(dimensionName.getString())).centered());

        return widgets;
    }
    @Override
    public int getDisplayHeight() {
        return 60;
    }

    public EntryIngredient getInput(WarpingDisplay recipeDisplay, int index) {
        List<EntryIngredient> inputs = recipeDisplay.getInputEntries();
        return inputs.size() > index ? inputs.get(index) : EntryIngredient.empty();
    }
    public EntryIngredient getOutput(WarpingDisplay recipeDisplay, int index) {
        List<EntryIngredient> outputs = recipeDisplay.getOutputEntries();
        return outputs.size() > index ? outputs.get(index) : EntryIngredient.empty();
    }

}
