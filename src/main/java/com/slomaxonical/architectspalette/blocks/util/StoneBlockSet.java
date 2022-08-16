package com.slomaxonical.architectspalette.blocks.util;

import com.slomaxonical.architectspalette.blocks.VerticalSlabBlock;
import com.slomaxonical.architectspalette.registry.util.RegistryUtil;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static com.slomaxonical.architectspalette.blocks.util.StoneBlockSet.SetComponent.*;


public class StoneBlockSet {
    public static ArrayList<StoneBlockSet> BlockSets = new ArrayList<>();
    //SETS:
    public static List<StoneBlockSet> oreBrickSets = new ArrayList<>();
    private final String material_name;
    public List<Block> parts;
    public StoneBlockSet(Block base_block) {
        this(base_block, SetGroup.TYPICAL);
    }

    public StoneBlockSet(Block base_block, SetGroup group) {
        this(base_block, group.components);
    }


    public StoneBlockSet(Block base_block, SetComponent... parts){
        this.parts = new ArrayList<>();
        //Piece of crap array list doesn't let me preallocate indices ((if it can, you should let me know))
        while(this.parts.size() < values().length) this.parts.add(null);

        this.material_name = getMaterialFromBlock(Registry.BLOCK.getId(base_block).getPath());
        setPart(BLOCK, base_block);
        Arrays.stream(parts).forEachOrdered(this::createPart);


        if (this.material_name.contains("ore_brick")) {
            oreBrickSets.add(this);

        } else {
            BlockSets.add(this);
        }
    }

    // Stone Bricks Slab -> Stone Brick Slab. Oak Planks Stairs -> Oak Stairs
    private static String getMaterialFromBlock(String blockName) {
        return blockName
                .replace("bricks", "brick")
                .replace("_planks", "")
                .replace("_block", "")
                .replace("tiles", "tile");
    }

    private FabricBlockSettings properties() {
        return FabricBlockSettings.copyOf(this.getBase());
    }
    public Block getBase() {
        return this.getPart(BLOCK);
    }


    private Stream<? extends Block> blockStream() {
        //Puts all blocks in a Stream, filters out null entries, then gets the blocks from their registry object
        return parts.stream().filter(Objects::nonNull);//.map(RegistryObject::get);
    }
    public enum SetComponent {
        BLOCK("", ItemGroup.BUILDING_BLOCKS),
        SLAB("_slab", ItemGroup.BUILDING_BLOCKS),
        VERTICAL_SLAB("_vertical_slab", ItemGroup.BUILDING_BLOCKS),
        STAIRS("_stairs", ItemGroup.BUILDING_BLOCKS),
        WALL("_wall", ItemGroup.DECORATIONS);

        public final String suffix;
        public final ItemGroup tab;
        SetComponent(String suffix,ItemGroup tab) {
            this.suffix = suffix;
            this.tab = tab;
        }
    }
    public enum SetGroup {
        SLABS(SLAB, VERTICAL_SLAB),
        NO_WALLS(SLAB, VERTICAL_SLAB, STAIRS),
        NO_STAIRS(SLAB, VERTICAL_SLAB, WALL),
        TYPICAL(SLAB, VERTICAL_SLAB, STAIRS, WALL);


        public final SetComponent[] components;

        SetGroup(SetComponent... components) {
            this.components = components;
        }

        public void forEach(Consumer<SetComponent> action) {
            Arrays.stream(components).forEachOrdered(action);
        }

    }

    public Block getPart(SetComponent part) {
        return parts.get(part.ordinal());
    }
    private <B extends Block> void setPart(SetComponent part, B block) {
        parts.add(part.ordinal(), block);
    }
    private void createPart(SetComponent part) {
        setPart(part, makePart(part));
    }



    private Block makePart(SetComponent part) {
        Block block = getPart(BLOCK);
        if (block instanceof IBlockSetBase base) {
            return RegistryUtil.createBlock(material_name + part.suffix, base.getBlockForPart(part, properties(), getPart(BLOCK)), part.tab);
        }
        return RegistryUtil.createBlock(material_name+part.suffix, getBlockForPart(part, properties(), getPart(BLOCK)), part.tab);
    }
    public static Block getBlockForPart(SetComponent part, FabricBlockSettings settings, Block base) {
        return switch (part) {
            case WALL -> new WallBlock(settings);
            case SLAB -> new SlabBlock(settings);
            case VERTICAL_SLAB -> new VerticalSlabBlock(settings);
            case STAIRS -> new StairsBlock(base.getDefaultState(), settings);
            case BLOCK -> throw new IllegalStateException("Should not call createPart on BLOCK. Use setPart instead.");
        };
    }
}

