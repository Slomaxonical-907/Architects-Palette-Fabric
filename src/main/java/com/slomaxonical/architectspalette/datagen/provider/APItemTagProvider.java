package com.slomaxonical.architectspalette.datagen.provider;

import com.slomaxonical.architectspalette.registry.APBlocks;
import com.slomaxonical.architectspalette.registry.APItems;
import com.slomaxonical.architectspalette.registry.APTags;
import com.slomaxonical.architectspalette.registry.util.RegistryUtil;
import com.slomaxonical.architectspalette.registry.util.StoneBlockSet;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

import static com.slomaxonical.architectspalette.registry.util.StoneBlockSet.SetComponent.*;
import static com.slomaxonical.architectspalette.registry.util.StoneBlockSet.SetComponent.FENCE;

public class APItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public APItemTagProvider(FabricDataGenerator dataGenerator, @Nullable BlockTagProvider blockTagProvider) {super(dataGenerator, blockTagProvider);}
    @Override
    protected void generateTags() {
//        for (StoneBlockSet set : RegistryUtil.BlockSets.values()){
//            this.getOrCreateTagBuilder(APTags.SET_TAB).add(set.getBase().asItem());
//            if (set.getPart(SLAB) !=null) this.getOrCreateTagBuilder(APTags.SET_TAB).add(set.getPart(SLAB).asItem());
//            if (set.getPart(VERTICAL_SLAB) !=null) this.getOrCreateTagBuilder(APTags.SET_TAB).add(set.getPart(VERTICAL_SLAB).asItem());//config check here?
//            if (set.getPart(STAIRS) !=null) this.getOrCreateTagBuilder(APTags.SET_TAB).add(set.getPart(STAIRS).asItem());
//            if (set.getPart(WALL) !=null) this.getOrCreateTagBuilder(APTags.SET_TAB).add(set.getPart(WALL).asItem());
//            if (set.getPart(FENCE) !=null) this.getOrCreateTagBuilder(APTags.SET_TAB).add(set.getPart(FENCE).asItem());
//        }
//        this.copy(APTags.NUBS,APTags.NUB_TAB);
        this.copy(APTags.TWISTED_LOGS, APTags.ITEM_TWISTED_LOGS);
        this.getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN).addTag(APTags.ITEM_TWISTED_LOGS);
        this.copy(BlockTags.LEAVES, ItemTags.LEAVES);
        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
        this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        this.copy(BlockTags.SLABS,ItemTags.SLABS);
        this.copy(BlockTags.STAIRS,ItemTags.STAIRS);
        this.copy(BlockTags.WALLS,ItemTags.WALLS);
        this.copy(BlockTags.WOODEN_BUTTONS,ItemTags.WOODEN_BUTTONS);
        this.copy(BlockTags.WOODEN_DOORS,ItemTags.WOODEN_DOORS);
        this.copy(BlockTags.WOODEN_FENCES,ItemTags.WOODEN_FENCES);
        this.copy(BlockTags.WOODEN_PRESSURE_PLATES,ItemTags.WOODEN_PRESSURE_PLATES);
        this.copy(BlockTags.WOODEN_SLABS,ItemTags.WOODEN_SLABS);
        this.copy(BlockTags.WOODEN_STAIRS,ItemTags.WOODEN_STAIRS);
        this.copy(BlockTags.WOODEN_TRAPDOORS,ItemTags.WOODEN_TRAPDOORS);

        this.getOrCreateTagBuilder(APTags.MUSHROOMS)
                .add(Items.RED_MUSHROOM)
                .add(Items.BROWN_MUSHROOM);

        this.getOrCreateTagBuilder(APTags.OLIVESTONE)
                .add(APBlocks.OLIVESTONE_BRICKS.asItem())
                .add(APBlocks.OLIVESTONE_TILES.asItem());

        this.getOrCreateTagBuilder(APTags.OLIVESTONE_SLABS)
                .add(Registry.BLOCK.get(new Identifier("architects_palette:olivestone_brick_slab")).asItem())
                .add(Registry.BLOCK.get(new Identifier("architects_palette:olivestone_tile_slab")).asItem());

        this.getOrCreateTagBuilder(APTags.WITHERED_BONES).add(APItems.WITHERED_BONE);
    }
}
