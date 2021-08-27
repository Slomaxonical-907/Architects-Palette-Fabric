package com.slomaxonical.architectspalette.compat.rei;

import com.slomaxonical.architectspalette.core.ArchitectsPalette;
import com.slomaxonical.architectspalette.core.crafting.WarpingRecipe;
import com.slomaxonical.architectspalette.core.registry.APBlocks;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.RecipeHelper;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import me.shedaniel.rei.plugin.information.DefaultInformationDisplay;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

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
        recipeHelper.removeAutoCraftButton(WARPING);

        recipeHelper.registerDisplay(addInfo(Items.HEART_OF_THE_SEA,"heart_info"));

        recipeHelper.registerDisplay(addInfo(APBlocks.FLINT_BLOCK.asItem(),"flint_blocks_info"));
        recipeHelper.registerDisplay(addInfo(APBlocks.FLINT_PILLAR.asItem(),"flint_blocks_info"));
        recipeHelper.registerDisplay(addInfo(APBlocks.FLINT_TILES.asItem(), "flint_blocks_info"));

        recipeHelper.registerDisplay(addInfo(APBlocks.SUNSTONE.asItem(),"sunstone_info"));
        recipeHelper.registerDisplay(addInfo(APBlocks.MOONSTONE.asItem(), "moonstone_info"));
        recipeHelper.registerDisplay(addInfo(APBlocks.GRINNING_ACACIA_TOTEM.asItem(),"acacia_totems_info"));

        recipeHelper.registerDisplay(addInfo(APBlocks.HEAVY_STONE_BRICKS.asItem(),"heavy_bricks_info"));
        recipeHelper.registerDisplay(addInfo(APBlocks.HEAVY_END_STONE_BRICKS.asItem(),"heavy_bricks_info"));
        recipeHelper.registerDisplay(addInfo(APBlocks.HEAVY_MOSSY_STONE_BRICKS.asItem(),"heavy_bricks_info"));

        recipeHelper.registerDisplay(addInfo(APBlocks.ALGAL_CAGE_LANTERN.asItem(),"cage_lantern_info"));
        recipeHelper.registerDisplay(addInfo(APBlocks.GLOWSTONE_CAGE_LANTERN.asItem(),"cage_lantern_info"));
        recipeHelper.registerDisplay(addInfo(APBlocks.REDSTONE_CAGE_LANTERN.asItem(),"cage_lantern_info"));
    }
    private DefaultInformationDisplay  addInfo(Item item, String key){
        EntryStack entryStack = EntryStack.create(item);
        DefaultInformationDisplay stackInfo = DefaultInformationDisplay.createFromEntry(entryStack,new TranslatableText(item.getTranslationKey()));
        stackInfo.line(new TranslatableText("architects_palette.rei."+key));
        return stackInfo;
    }

}
