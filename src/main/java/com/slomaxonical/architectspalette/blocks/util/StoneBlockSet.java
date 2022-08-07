package com.slomaxonical.architectspalette.blocks.util;

import com.slomaxonical.architectspalette.blocks.VerticalSlabBlock;
import com.slomaxonical.architectspalette.registry.APBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;


public class StoneBlockSet {
    public static ArrayList<StoneBlockSet> BlockSets = new ArrayList<>();
    //SETS:
    public static List<StoneBlockSet> oreBrickSets = new ArrayList<>();
    public Block SLAB;
    public Block VERTICAL_SLAB;
    public Block STAIRS;
    public Block WALL;
    public Block BLOCK;
    private final String material_name;

    public StoneBlockSet(Block  base_block) {
        this(base_block, true);
    }

    public StoneBlockSet(Block base_block, Boolean auto_fill){
        this.BLOCK = base_block;
        this.material_name = getMaterialFromBlock(Registry.BLOCK.getId(base_block).getPath());
        if (auto_fill) {
            this.addAll();
        }
        if (this.material_name.contains("ore_brick")) {
            oreBrickSets.add(this);

        } else {
            BlockSets.add(this);
        }
    }

    // Stone Bricks Slab -> Stone Brick Slab. Oak Planks Stairs -> Oak Stairs
    private String getMaterialFromBlock(String blockName) {
        return blockName
                .replace("bricks", "brick")
                .replace("_planks", "")
                .replace("_block", "")
                .replace("tiles", "tile");
    }

    private FabricBlockSettings properties() {
        return FabricBlockSettings.copyOf(this.BLOCK);
    }

    public Block get() {
        return this.BLOCK;
    }

    public StoneBlockSet addSlabs() {
        SLAB = APBlocks.createBlock(material_name + "_slab", new SlabBlock(properties()));
        VERTICAL_SLAB = APBlocks.createBlock(material_name + "_vertical_slab", new VerticalSlabBlock(properties()));
        return this;
    }

    public StoneBlockSet addStairs() {
        STAIRS = APBlocks.createBlock(material_name + "_stairs", new StairsBlock(BLOCK.getDefaultState(), properties()));
        return this;
    }

    public StoneBlockSet addWalls() {
        WALL = APBlocks.createBlock(material_name + "_wall", new WallBlock(properties()), ItemGroup.DECORATIONS);
        return this;
    }

    public StoneBlockSet addAll() {
        this.addSlabs();
        this.addStairs();
        this.addWalls();
        return this;
    }


}
