package com.slomaxonical.architectspalette.compat.emi;

import com.slomaxonical.architectspalette.crafting.WarpingRecipe;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class WarpingEmiRecipe implements EmiRecipe {
    private final Identifier id;
    private final EmiIngredient input;
    private final EmiStack output;

    public WarpingEmiRecipe(WarpingRecipe recipe) {
        this.id = recipe.getId();
        input = EmiIngredient.of(recipe.getInput());
        output = EmiStack.of(recipe.getOutput());
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return APEmiPlugin.WARPING;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return List.of(input);
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(output);
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public int getDisplayHeight() {
        return 52;
    }

    @Override
    public int getDisplayWidth() {
        return 142;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(new Identifier("architects_palette", "textures/gui/nether_portal.png"), (widgets.getWidth() / 2) - 32/2, widgets.getHeight()/2-14, 32, 32, 0, 0, 32, 32, 32, 32);
        widgets.addSlot(input, widgets.getWidth()/2-45, 21);
        widgets.addSlot(output, widgets.getWidth()/2+30, 21).recipeContext(this);

        widgets.addText(Text.literal("toss").asOrderedText(), widgets.getWidth()/2-13, 2, 0xffffff, true);
        //TextWidget doesn't have a tooltip method, so we add a no-op Drawable and add the tooltip to that instead.
        //Might as well make the widget cover the Portal texture too while we're here.
        Text dimensionName = Text.translatable("architects_palette.info.warping_toss_description","the Nether");
        widgets.addDrawable(widgets.getWidth()/2-18, 2, 36, 44, (matrices, mouseX, mouseY, delta) -> {})
                .tooltip((mouseX, mouseY) -> List.of(TooltipComponent.of(Text.literal(dimensionName.getString()).asOrderedText())));
    }
}
