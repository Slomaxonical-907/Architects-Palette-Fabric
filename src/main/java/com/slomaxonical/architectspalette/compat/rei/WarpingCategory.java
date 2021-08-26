package com.slomaxonical.architectspalette.compat.rei;

import com.slomaxonical.architectspalette.core.registry.APBlocks;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.RecipeCategory;
import me.shedaniel.rei.api.widgets.Widgets;
import me.shedaniel.rei.gui.widget.Widget;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WarpingCategory implements RecipeCategory<WarpingDisplay> {

    public static final TranslatableText NAME = new TranslatableText("architects_palette.rei.portal_warping");

    @Override
    public @NotNull EntryStack getLogo() {
        return EntryStack.create(APBlocks.WARPSTONE);
    }
    @Override
    public @NotNull String getCategoryName() {
        return NAME.getString();
    }
    @Override
    public @NotNull Identifier getIdentifier() {
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

    public List<EntryStack> getInput(WarpingDisplay recipeDisplay, int index) {
        List<EntryStack> inputs = recipeDisplay.getInputEntries().get(0);
//        return inputs.size() > index ? inputs.get(index) : EntryStack.empty();
        return inputs;
    }
    public List<EntryStack> getOutput(WarpingDisplay recipeDisplay, int index) {
        List<EntryStack> outputs = recipeDisplay.getResultingEntries().get(0);
//        return outputs.size() > index ? outputs.get(index) : EntryStack.empty();
        return outputs;
    }

}
