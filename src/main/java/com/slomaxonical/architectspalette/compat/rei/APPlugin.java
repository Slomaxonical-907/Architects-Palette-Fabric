package com.slomaxonical.architectspalette.compat.rei;

import com.slomaxonical.architectspalette.crafting.WarpingRecipe;
import com.slomaxonical.architectspalette.registry.APBlocks;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.displays.DefaultInformationDisplay;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class APPlugin implements REIClientPlugin {
    public static final CategoryIdentifier<WarpingDisplay> WARPING = CategoryIdentifier.of("architects_palette", "portal_warping");
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new WarpingCategory());
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(WarpingRecipe.class, WarpingDisplay::new);

        addInfo(registry,APBlocks.CHISELED_ABYSSALINE_BRICKS,"chiseled_chargeable");
        addBulkInfo(registry,"chargeable",
                APBlocks.ABYSSALINE,
                APBlocks.ABYSSALINE_BRICKS,
                APBlocks.ABYSSALINE_PILLAR,
//                APBlocks.ABYSSALINE_BRICK_SLAB,
                APBlocks.ABYSSALINE_TILES,
//                APBlocks.ABYSSALINE_TILE_SLAB,
                APBlocks.ABYSSALINE_LAMP
//                APBlocks.ABYSSALINE_BRICK_VERTICAL_SLAB,
//                APBlocks.ABYSSALINE_TILE_VERTICAL_SLAB
        );
        addBulkInfo(registry,"totem_carving",
                APBlocks.PLACID_ACACIA_TOTEM,
                APBlocks.GRINNING_ACACIA_TOTEM,
                APBlocks.SHOCKED_ACACIA_TOTEM,
                APBlocks.BLANK_ACACIA_TOTEM
        );
        addBulkInfo(registry,"flint_damage",
                        APBlocks.FLINT_BLOCK,
                        APBlocks.FLINT_PILLAR,
                        APBlocks.FLINT_TILES
                );
        addBulkInfo(registry,"celestial_stones",
                APBlocks.SUNSTONE,
                APBlocks.MOONSTONE
        );
        addBulkInfo(registry,"cage_lanterns",
                APBlocks.ALGAL_CAGE_LANTERN,
                APBlocks.GLOWSTONE_CAGE_LANTERN,
                APBlocks.REDSTONE_CAGE_LANTERN
        );
        addBulkInfo(registry,"heavy_bricks",
                APBlocks.HEAVY_END_STONE_BRICKS,
                APBlocks.HEAVY_CRACKED_END_STONE_BRICKS,
                APBlocks.HEAVY_STONE_BRICKS,
                APBlocks.HEAVY_MOSSY_STONE_BRICKS,
                APBlocks.HEAVY_CRACKED_STONE_BRICKS,
                APBlocks.HEAVY_CALCITE_BRICKS,
                APBlocks.HEAVY_DRIPSTONE_BRICKS,
                APBlocks.HEAVY_TUFF_BRICKS
        );

        addBulkInfo(registry,"nether_brass",
                APBlocks.NETHER_BRASS_BLOCK,
                APBlocks.CUT_NETHER_BRASS,
                APBlocks.SMOOTH_NETHER_BRASS,
                APBlocks.NETHER_BRASS_PILLAR
        );
    }

    private void addInfo(DisplayRegistry registry, ItemConvertible item, String infoKey){
        registry.add(DefaultInformationDisplay.createFromEntry(EntryStacks.of(item),Text.translatable(item.asItem().getTranslationKey())).line(Text.translatable("architects_palette.info."+infoKey)));
    }
    private void addBulkInfo(DisplayRegistry registry, String infoKey, ItemConvertible... items){
        //my little brain coudnt find a better solution to this mess
        List<EntryStack<ItemStack>> stacks = new ArrayList<>();
        for (ItemConvertible item:items) {
            stacks.add(EntryStacks.of(item));
        }
        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredient.builder().addAll(stacks).build(),Text.translatable(items[0].asItem().getTranslationKey())).line(Text.translatable("architects_palette.info."+infoKey)));
    }
}
