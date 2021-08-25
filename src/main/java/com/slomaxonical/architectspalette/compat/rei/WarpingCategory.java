package com.slomaxonical.architectspalette.compat.rei;

import com.slomaxonical.architectspalette.core.registry.APBlocks;
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
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;


public class WarpingCategory implements DisplayCategory<WarpingDisplay> {
    private static final TranslatableText NAME = new TranslatableText("architects_palette.rei.portal_warping");

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
        List<Widget> widgets = new ArrayList<>();

        widgets.add(Widgets.createRecipeBase(bounds));

        widgets.add(Widgets.createSlot(new Point(bounds.x + 15, bounds.y + 75)).entries(getInput(recipeDisplay,0)).markInput());
        widgets.add(Widgets.createTexturedWidget(new Identifier("architects_palette", "textures/gui/nether_portal_lit.png"), bounds.getX() - 64, bounds.getY() -50, 0, 0, 280, 280,280,285));
        widgets.add(Widgets.createSlot(new Point(bounds.getMaxX() -28, bounds.y + 75)).entries(getOutput(recipeDisplay, 0)).markOutput());

        return widgets;
    }
    @Override
    public int getDisplayHeight() {
        return 175;
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
