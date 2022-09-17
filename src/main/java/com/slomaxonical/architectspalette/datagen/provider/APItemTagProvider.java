package com.slomaxonical.architectspalette.datagen.provider;

import com.slomaxonical.architectspalette.registry.APBlocks;
import com.slomaxonical.architectspalette.registry.APItems;
import com.slomaxonical.architectspalette.registry.APTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

public class APItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public APItemTagProvider(FabricDataGenerator dataGenerator, @Nullable BlockTagProvider blockTagProvider) {super(dataGenerator, blockTagProvider);
    }
    /*
    //not used
    private FabricTagBuilder<Item> getOrCreateTagBuilder(Identifier id) {
        TagKey<Item> tag = TagKey.of(Registry.ITEM_KEY, id);
        return this.getOrCreateTagBuilder(tag);
    }
    //may use
    private void copy(Identifier id) {
        TagKey<Block> blockTag = TagKey.of(Registry.BLOCK_KEY, id);
        TagKey<Item> itemTag = TagKey.of(Registry.ITEM_KEY, id);
        this.copy(blockTag, itemTag);
    }
    */
    @Override
    protected void generateTags() {
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
