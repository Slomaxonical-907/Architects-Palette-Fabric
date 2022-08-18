package com.slomaxonical.architectspalette.registry.util;

import com.slomaxonical.architectspalette.blocks.NubBlock;
import com.slomaxonical.architectspalette.blocks.VerticalSlabBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.slomaxonical.architectspalette.registry.util.StoneBlockSet.SetComponent.*;


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
        }//I HATE THIS NEED TO GIVE IT SOME THOUGHT SOMETIME...
        if(this.getPart(NUB) != null) RegistryUtil.nubs.put(this.getPart(NUB),List.of(this.getBase()));
    }
    public StoneBlockSet(Block base_block, SetGroup group, SetComponent... additionalParts) {
        this(base_block, concatArray(group.components, additionalParts));
    }

    // Stone Bricks Slab -> Stone Brick Slab. Oak Planks Stairs -> Oak Stairs
    private static String getMaterialFromBlock(String blockName) {
        return blockName
                .replace("bricks", "brick")
                .replace("_planks", "")
                .replace("_block", "")
                .replace("tiles", "tile");
    }
    // Go all the way. Stone Bricks -> Stone. Meant for pillars and such
    // Written with the assumption that nobody will ever want a pillar and a brick pillar.
    private static String getMaterialAggressive(String block) {
        return getMaterialFromBlock(block)
                .replace("_brick", "")
                .replace("_tile", "")
                .replace("chiseled_", "")
                .replace("cut_", "");
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
        WALL("_wall", ItemGroup.DECORATIONS),
        FENCE("_fence", ItemGroup.DECORATIONS),
        PILLAR(SetComponent::pillarName, ItemGroup.BUILDING_BLOCKS),
        NUB("_nub", ItemGroup.DECORATIONS);

        public final ItemGroup tab;
        public final Function<String, String> nameGenerator;
        SetComponent(String suffix,ItemGroup tab) {
            this((material) -> addSuffix(material, suffix), tab);
        }
        SetComponent(Function<String, String> nameGen, ItemGroup tab) {
            this.nameGenerator = nameGen;
            this.tab = tab;
        }
        public String getName(String material) {
            return nameGenerator.apply(material);
        }

        private static String addSuffix(String material, String suffix) {
            return material + suffix;
        }
        private static String pillarName(String material) {
            return getMaterialAggressive(material) + "_pillar";
        }
    }
    public enum SetGroup {
        SLABS(SLAB, VERTICAL_SLAB),
        NO_WALLS(SLAB, VERTICAL_SLAB, STAIRS),
        NO_STAIRS(SLAB, VERTICAL_SLAB, WALL),
        TYPICAL(SLAB, VERTICAL_SLAB, STAIRS, WALL),
        ADD_PILLAR(SLAB, VERTICAL_SLAB, STAIRS, WALL,PILLAR); //I will wait for jsburg to actiate this


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
            return RegistryUtil.createBlock(part.getName(material_name), base.getBlockForPart(part, properties(), getPart(BLOCK)), part.tab);
        }
        return RegistryUtil.createBlock(part.getName(material_name), getBlockForPart(part, properties(), getPart(BLOCK)), part.tab);
    }
    public static Block getBlockForPart(SetComponent part, FabricBlockSettings settings, Block base) {
        return switch (part) {
            case WALL -> new WallBlock(settings);
            case SLAB -> new SlabBlock(settings);
            case VERTICAL_SLAB -> new VerticalSlabBlock(settings);
            case STAIRS -> new StairsBlock(base.getDefaultState(), settings);
            case FENCE -> new FenceBlock(settings);
            case PILLAR -> new PillarBlock(settings);
            case NUB -> new NubBlock(settings);
            case BLOCK -> throw new IllegalStateException("Should not call createPart on BLOCK. Use setPart instead.");
        };
    }
    private static <T> T[] concatArray(T[] array1, T[] array2) {
        T[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }
}

