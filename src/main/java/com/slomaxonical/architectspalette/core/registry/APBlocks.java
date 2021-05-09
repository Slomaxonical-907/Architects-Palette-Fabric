package com.slomaxonical.architectspalette.core.registry;

import com.slomaxonical.architectspalette.common.APBlockSettings;
import com.slomaxonical.architectspalette.common.blocks.abyssaline.*;
import com.slomaxonical.architectspalette.common.blocks.*;
import com.slomaxonical.architectspalette.common.features.TwistedTree;
import com.slomaxonical.architectspalette.core.ArchitectsPalette;
import com.slomaxonical.architectspalette.core.registry.util.*;
import com.slomaxonical.architectspalette.core.registry.util.extended.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class APBlocks {

    // Abyssaline
    public static final NewAbyssalineBlock      ABYSSALINE = createBlock("abyssaline", new NewAbyssalineBlock(APBlockSettings.ABYSSALINE));
    public static final NewAbyssalineBlock      ABYSSALINE_BRICKS          = createBlock("abyssaline_bricks", new NewAbyssalineBlock(APBlockSettings.ABYSSALINE));
    public static final NewAbyssalineBlock      ABYSSALINE_TILES           = createBlock("abyssaline_tiles", new NewAbyssalineBlock(APBlockSettings.ABYSSALINE));
    public static final AbyssalineSlabBlock     ABYSSALINE_BRICK_SLAB      = createBlock("abyssaline_brick_slab", new AbyssalineSlabBlock(APBlockSettings.ABYSSALINE));
    public static final AbyssalineSlabBlock     ABYSSALINE_TILE_SLAB       = createBlock("abyssaline_tile_slab", new AbyssalineSlabBlock(APBlockSettings.ABYSSALINE));
    public static final AbyssalinePillarBlock   ABYSSALINE_PILLAR          = createBlock("abyssaline_pillar", new AbyssalinePillarBlock(APBlockSettings.ABYSSALINE));
    public static final AbyssalineLampBlock     ABYSSALINE_LAMP_BLOCK      = createBlock("abyssaline_lamp", new AbyssalineLampBlock(APBlockSettings.ABYSSALINE.sounds(BlockSoundGroup.GLASS)));
    public static final ChiseledAbyssalineBlock CHISELED_ABYSSALINE_BRICKS = createBlock("chiseled_abyssaline_bricks", new ChiseledAbyssalineBlock(APBlockSettings.ABYSSALINE));

    // Limestone
    public static final StoneBlockSet LIMESTONE             = new StoneBlockSet(createBlock("limestone",               new Block(APBlockSettings.LIMESTONE)));
    public static final StoneBlockSet LIMESTONE_BRICK       = new StoneBlockSet(createBlock("limestone_bricks",        new Block(APBlockSettings.LIMESTONE)));
    public static final StoneBlockSet MUSHY_LIMESTONE_BRICK = new StoneBlockSet(createBlock("mushy_limestone_bricks",  new Block(APBlockSettings.LIMESTONE)));

    // Olivestone
    public static final StoneBlockSet OLIVESTONE_BRICK = new StoneBlockSet(createBlock("olivestone_bricks",  new Block(APBlockSettings.OLIVESTONE)));
    public static final StoneBlockSet OLIVESTONE_TILE  = new StoneBlockSet(createBlock("olivestone_tiles",   new Block(APBlockSettings.OLIVESTONE)));

    public static final Block OLIVESTONE_PILLAR         = createBlock("olivestone_pillar",  new PillarBlock(APBlockSettings.OLIVESTONE));
    public static final Block CRACKED_OLIVESTONE_BRICKS = createBlock("cracked_olivestone_bricks",  new Block(APBlockSettings.OLIVESTONE));
    public static final Block CRACKED_OLIVESTONE_TILES  = createBlock("cracked_olivestone_tiles",   new Block(APBlockSettings.OLIVESTONE));
    public static final Block CHISELED_OLIVESTONE       = createBlock("chiseled_olivestone",  new Block(APBlockSettings.OLIVESTONE));
    public static final Block ILLUMINATED_OLIVESTONE    = createBlock("illuminated_olivestone",  new Block(AbstractBlock.Settings.copy(OLIVESTONE_BRICK.get()).luminance((state) -> 15)));

    // Algal Brick
    public static final StoneBlockSet ALGAL_BRICKS = new StoneBlockSet(createBlock("algal_bricks",  new Block(APBlockSettings.ALGAL_BRICK), ItemGroup.BUILDING_BLOCKS));
    public static final Block CRACKED_ALGAL_BRICKS  = createBlock("cracked_algal_bricks",   new Block(APBlockSettings.ALGAL_BRICK));
    public static final Block CHISELED_ALGAL_BRICKS = createBlock("chiseled_algal_bricks",  new Block(APBlockSettings.ALGAL_BRICK));
    public static final StoneBlockSet OVERGROWN_ALGAL_BRICK = new StoneBlockSet(createBlock("overgrown_algal_bricks",  new Block(APBlockSettings.ALGAL_BRICK)));
    public static final Block ALGAL_LAMP = createBlock("algal_lamp",  new Block(AbstractBlock.Settings.copy(Blocks.SEA_LANTERN) ));

    // Sunmetal
    public static final StoneBlockSet SUNMETAL = new StoneBlockSet(createBlock("sunmetal_block",  new Block(APBlockSettings.SUNMETAL), ItemGroup.BUILDING_BLOCKS), false).addSlabs().addStairs();
    public static final Block CHISELED_SUNMETAL_BLOCK = createBlock("chiseled_sunmetal_block",  new Block(APBlockSettings.SUNMETAL));
    public static final Block SUNMETAL_PILLAR         = createBlock("sunmetal_pillar",  new PillarBlock(APBlockSettings.SUNMETAL));
    public static final Block SUNMETAL_BARS           = createBlock("sunmetal_bars",  new APPaneBlock(APBlockSettings.SUNMETAL.nonOpaque()));

    // Rotten Flesh Block
    public static final Block ROTTEN_FLESH_BLOCK = createBlock("rotten_flesh_block",  new Block(APBlockSettings.Meat(MaterialColor.ORANGE)));

    // Villager Trade blocks
    // Entrails
    public static final Block ENTRAILS = createBlock("entrails",  new DrippyBlock(APBlockSettings.Meat(MaterialColor.PINK_TERRACOTTA)));
    // Funny fish blocks
    public static final Block  SALMON_LOG = createBlock("salmon_log",    new PillarBlock(APBlockSettings.Meat(MaterialColor.RED_TERRACOTTA)));
    public static final Block       COD_LOG = createBlock("cod_log",        new PillarBlock(APBlockSettings.Meat(MaterialColor.YELLOW_TERRACOTTA)));
    public static final Block SALMON_SCALES = createBlock("salmon_scales",  new PillarBlock(APBlockSettings.Meat(MaterialColor.RED_TERRACOTTA)));
    public static final Block    COD_SCALES = createBlock("cod_scales",     new PillarBlock(APBlockSettings.Meat(MaterialColor.YELLOW_TERRACOTTA)));
        // Plating & Piping
    public static final StoneBlockSet PLATING_BLOCK = new StoneBlockSet(createBlock("plating_block",  new Block(APBlockSettings.PLATING)));
    public static final Block PIPE = createBlock("pipe",  new PipeBlock(APBlockSettings.PLATING.nonOpaque()));
        //Spools
    public static final Block SPOOL = createBlock("spool",  new PillarBlock(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));

    // Scute Block
    public static final Block SCUTE_BLOCK = createBlock("scute_block",  new Block(AbstractBlock.Settings.of(Material.STONE, MaterialColor.LIME).strength(5.0F, 6.0F).sounds(BlockSoundGroup.BASALT)));

    // Ore Bricks
    public static final List<StoneBlockSet> ORE_BRICKS = addOreBricks();

    private static List<StoneBlockSet> addOreBricks() {
        List<String> ores = Arrays.asList("coal", "lapis", "redstone", "iron", "gold", "emerald", "diamond");
        List<StoneBlockSet> l = new LinkedList<>();
        ores.forEach((ore) -> {
                StoneBlockSet set = new StoneBlockSet(createBlock(ore + "_ore_bricks",  new Block(FabricBlockSettings.copy(Blocks.STONE_BRICKS))));
                createBlock("cracked_" + ore + "_ore_bricks",  new Block(FabricBlockSettings.copy(Blocks.CRACKED_STONE_BRICKS)));
                createBlock("chiseled_" + ore + "_ore_bricks",  new Block(FabricBlockSettings.copy(Blocks.CHISELED_STONE_BRICKS)));
                l.add(set);
            }
        );
        return l;
    }

    // Polished Packed Ice
    public static final StoneBlockSet POLISHED_PACKED_ICE = new StoneBlockSet(createBlock("polished_packed_ice",  new Block(APBlockSettings.BUILDING_ICE)));
    public static final Block CHISELED_PACKED_ICE = createBlock("chiseled_packed_ice",  new Block(APBlockSettings.BUILDING_ICE));
    public static final Block PACKED_ICE_PILLAR   = createBlock("packed_ice_pillar",    new PillarBlock(APBlockSettings.BUILDING_ICE));

    // Gilded Sandstone
    public static final StoneBlockSet GILDED_SANDSTONE = new StoneBlockSet(createBlock("gilded_sandstone",  new Block(AbstractBlock.Settings.copy(Blocks.SANDSTONE))), false).addSlabs().addStairs();
    public static final Block GILDED_SANDSTONE_PILLAR = createBlock("gilded_sandstone_pillar",  new PillarBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE)));
    public static final Block CHISELED_GILDED_SANDSTONE = createBlock("chiseled_gilded_sandstone",  new Block(AbstractBlock.Settings.copy(Blocks.SANDSTONE)));

    // Polished Glowstone
    public static final StoneBlockSet POLISHED_GLOWSTONE = new StoneBlockSet(createBlock("polished_glowstone",  new Block(AbstractBlock.Settings.copy(Blocks.GLOWSTONE))), false).addSlabs();
    public static final Block RUNIC_GLOWSTONE = createBlock("runic_glowstone",  new Block(AbstractBlock.Settings.copy(Blocks.GLOWSTONE)));

    // Osseous Bricks
    public static final StoneBlockSet OSSEOUS_BRICK = new StoneBlockSet(createBlock("osseous_bricks",  new Block(FabricBlockSettings.copy(Blocks.BONE_BLOCK))));
    public static final Block OSSEOUS_PILLAR = createBlock("osseous_pillar",  new PillarBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK)));
    // Withered
     // Todo: Replace bone block recipe to one that uses withered bone meal if that gets in
    public static final Block WITHERED_BONE_BLOCK = createBlock("withered_bone_block",  new PillarBlock(FabricBlockSettings.copyOf(Blocks.BONE_BLOCK)), ItemGroup.BUILDING_BLOCKS);
    public static final StoneBlockSet      WITHERED_OSSEOUS_BRICK = new StoneBlockSet(createBlock("withered_osseous_bricks",  new Block(FabricBlockSettings.copy(Blocks.BONE_BLOCK))));
    public static final Block WITHERED_OSSEOUS_PILLAR = createBlock("withered_osseous_pillar",  new PillarBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK)));
    // Wither Lamp
    public static final Block WITHER_LAMP = createBlock("wither_lamp",  new Block(AbstractBlock.Settings.copy(Blocks.SEA_LANTERN)));

    // Flint Blocks
    public static final Block FLINT_BLOCK  	= createBlock("flint_block",   new FlintBlock(APBlockSettings.FLINT));
    public static final StoneBlockSet FLINT_TILES  			= new StoneBlockSet(createBlock("flint_tiles",   new FlintBlock(APBlockSettings.FLINT)));
    public static final Block FLINT_PILLAR 	= createBlock("flint_pillar",  new FlintPillarBlock(APBlockSettings.FLINT));

    // Mossy Blackstone Variants
    public static final Block WEEPING_BLACKSTONE = createBlock("weeping_blackstone",  new Block(AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));
    public static final Block TWISTING_BLACKSTONE = createBlock("twisting_blackstone",  new Block(AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));
    public static final Block WEEPING_BLACKSTONE_BRICKS = createBlock("weeping_blackstone_bricks",  new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_BLACKSTONE_BRICKS)));
    public static final Block TWISTING_BLACKSTONE_BRICKS = createBlock("twisting_blackstone_bricks",  new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_BLACKSTONE_BRICKS)));

    // Basalt Tiles
    public static final StoneBlockSet BASALT_TILES = new StoneBlockSet(createBlock("basalt_tiles",  new Block(AbstractBlock.Settings.copy(Blocks.BASALT))));
    public static final Block  CRACKED_BASALT_TILES = createBlock("cracked_basalt_tiles",   new Block(AbstractBlock.Settings.copy(Blocks.BASALT)));
    public static final Block CHISELED_BASALT_TILES = createBlock("chiseled_basalt_tiles",  new Block(AbstractBlock.Settings.copy(Blocks.BASALT)));

    // Heavy Stone Bricks
    public static final Block HEAVY_STONE_BRICKS = createBlock("heavy_stone_bricks",  new BigBrickBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
    public static final Block HEAVY_MOSSY_STONE_BRICKS = createBlock("heavy_mossy_stone_bricks",  new BigBrickBlock(AbstractBlock.Settings.copy(Blocks.MOSSY_STONE_BRICKS)));
    public static final Block HEAVY_CRACKED_STONE_BRICKS = createBlock("heavy_cracked_stone_bricks",  new BigBrickBlock(AbstractBlock.Settings.copy(Blocks.CRACKED_STONE_BRICKS)));

    // Entwine
    public static final StoneBlockSet ENTWINE = new StoneBlockSet(createBlock("entwine_block",  new Block(APBlockSettings.ENTWINE), ItemGroup.BUILDING_BLOCKS), false).addSlabs().addStairs();
    public static final Block ENTWINE_PILLAR = createBlock("entwine_pillar",  new PillarBlock(APBlockSettings.ENTWINE));
    public static final Block CHISELED_ENTWINE = createBlock("chiseled_entwine",  new Block(APBlockSettings.ENTWINE));
    public static final Block ENTWINE_BARS = createBlock("entwine_bars",  new APPaneBlock(AbstractBlock.Settings.copy(ENTWINE.get()).nonOpaque()));
    // Ender Pearl Block
    public static final Block ENDER_PEARL_BLOCK = createBlock("ender_pearl_block",  new Block(APBlockSettings.ENDER_PEARL));

    // End Stone Variants
    public static final Block   CHORAL_END_STONE_BRICKS = createBlock("choral_end_stone_bricks",    new Block(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICKS)));
    public static final Block  CRACKED_END_STONE_BRICKS = createBlock("cracked_end_stone_bricks",   new Block(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICKS)));
    public static final Block CHISELED_END_STONE_BRICKS = createBlock("chiseled_end_stone_bricks",  new Block(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICKS)));

    // Heavy End Stone Bricks
    public static final Block HEAVY_END_STONE_BRICKS = createBlock("heavy_end_stone_bricks",  new BigBrickBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICKS), BigBrickBlock.BrickType.END_STONE));
    public static final Block HEAVY_CRACKED_END_STONE_BRICKS = createBlock("heavy_cracked_end_stone_bricks",  new BigBrickBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICKS), BigBrickBlock.BrickType.END_STONE));

    // Warpstone
    public static final StoneBlockSet WARPSTONE = new StoneBlockSet(createBlock("warpstone",  new Block(AbstractBlock.Settings.copy(Blocks.STONE))));

    // Twisted Wood
     // Todo: Bookshelf, sign(?), boat(?)
    public static final StoneBlockSet TWISTED_PLANKS = new StoneBlockSet(createBlock("twisted_planks",  new Block(APBlockSettings.TwistedWood())), false).addSlabs().addStairs();

    public static final Block           TWISTED_LOG = createBlock("twisted_log",           new PillarBlock(APBlockSettings.TwistedWood()));
    public static final Block          TWISTED_WOOD = createBlock("twisted_wood",          new PillarBlock(APBlockSettings.TwistedWood()));
    public static final Block  STRIPPED_TWISTED_LOG = createBlock("stripped_twisted_log",  new PillarBlock(APBlockSettings.TwistedWood()));
    public static final Block STRIPPED_TWISTED_WOOD = createBlock("stripped_twisted_wood", new PillarBlock(APBlockSettings.TwistedWood()));
    public static final Block        TWISTED_LEAVES = createBlock("twisted_leaves",        new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block         TWISTED_FENCE = createBlock("twisted_fence",         new FenceBlock(APBlockSettings.TwistedWood()), ItemGroup.DECORATIONS);
    public static final Block    TWISTED_FENCE_GATE = createBlock("twisted_fence_gate",    new FenceGateBlock(APBlockSettings.TwistedWood()), ItemGroup.REDSTONE);
    public static final Block          TWISTED_DOOR = createBlock("twisted_door",          new APDoorBlock(APBlockSettings.TwistedWood().nonOpaque()), ItemGroup.REDSTONE);
    public static final Block      TWISTED_TRAPDOOR = createBlock("twisted_trapdoor",      new APTrapdoorBlock(APBlockSettings.TwistedWood().nonOpaque()), ItemGroup.REDSTONE);
    public static final Block        TWISTED_BUTTON = createBlock("twisted_button",        new APWoodenButtonBlock(APBlockSettings.TwistedWood(true)), ItemGroup.REDSTONE);
    public static final Block TWISTED_PRESSURE_PLATE = createBlock("twisted_pressure_plate", new APPressurePlateBlock((PressurePlateBlock.ActivationRule.EVERYTHING), APBlockSettings.TwistedWood(true)), ItemGroup.REDSTONE);

//   todo:Saplings, tree feature and potted:

    public static final Block        TWISTED_SAPLING = createBlock("twisted_sapling",  new APSaplingBlock(new TwistedTree(), AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)), ItemGroup.DECORATIONS);
    public static final Block POTTED_TWISTED_SAPLING = createPottedPlant(TWISTED_SAPLING);

    // Celestial Stones
    public static final Block SUNSTONE  = createBlock("sunstone",   new SunstoneBlock(AbstractBlock.Settings.copy(Blocks.BASALT).nonOpaque(), SunstoneBlock::sunstoneLight));
    public static final Block MOONSTONE = createBlock("moonstone",  new SunstoneBlock(AbstractBlock.Settings.copy(Blocks.BASALT).nonOpaque(), SunstoneBlock::moonstoneLight));

    // Odd block variants
    public static final Block MOLTEN_NETHER_BRICKS = createBlock("molten_nether_bricks",  new Block(APBlockSettings.MOLTEN_BRICK));
    public static final Block COARSE_SNOW = createBlock("coarse_snow",  new Block(AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK)));
         // Charcoal Block
    public static final Block CHARCOAL_BLOCK = createBlock("charcoal_block",  new PillarBlock(FabricBlockSettings.copy(Blocks.COAL_BLOCK)));

    // Cage Lanterns
    public static final Block REDSTONE_CAGE_LANTERN  = createBlock("redstone_cage_lantern",  new CageLanternBlock(APBlockSettings.CAGE_LANTERN, 3), ItemGroup.REDSTONE);
    public static final Block GLOWSTONE_CAGE_LANTERN = createBlock("glowstone_cage_lantern",  new CageLanternBlock(APBlockSettings.CAGE_LANTERN, 3), ItemGroup.REDSTONE);
    public static final Block ALGAL_CAGE_LANTERN     = createBlock("algal_cage_lantern",  new CageLanternBlock(APBlockSettings.CAGE_LANTERN, 3), ItemGroup.REDSTONE);

    // Acacia Totems
    public static final TotemWingBlock ACACIA_TOTEM_WING = createBlock("acacia_totem_wing",  new TotemWingBlock(FabricBlockSettings.copy(Blocks.ACACIA_PLANKS).nonOpaque().dropsNothing().sounds(BlockSoundGroup.SCAFFOLDING).noCollision()), ItemGroup.DECORATIONS);
    public static final Block GRINNING_ACACIA_TOTEM = createBlock("grinning_acacia_totem",  new TotemBlock(APBlockSettings.ACACIA_TOTEM, ACACIA_TOTEM_WING, TotemBlock.TotemFace.GRINNING));
    public static final Block PLACID_ACACIA_TOTEM = createBlock("placid_acacia_totem",  new TotemBlock(APBlockSettings.ACACIA_TOTEM, ACACIA_TOTEM_WING, TotemBlock.TotemFace.PLACID));
    public static final Block SHOCKED_ACACIA_TOTEM = createBlock("shocked_acacia_totem",  new TotemBlock(APBlockSettings.ACACIA_TOTEM, ACACIA_TOTEM_WING, TotemBlock.TotemFace.SHOCKED));
    public static final Block BLANK_ACACIA_TOTEM = createBlock("blank_acacia_totem",  new TotemBlock(APBlockSettings.ACACIA_TOTEM, ACACIA_TOTEM_WING, TotemBlock.TotemFace.BLANK));


    //Create Blocks
        // Default ItemGroup is BuildingBlocks
    public static int INDEX_ALGAL;
    public static int INDEX_SUNMETAL;
    public static int INDEX_WITHERED;
    public static int INDEX_ENTWINE;

    public static <B extends Block> B createBlock(String name, B anyBlock) {
        return createBlock(name, anyBlock, ItemGroup.BUILDING_BLOCKS);
    }

    public static <B extends Block> B createBlock(String name, B anyBlock, @Nullable ItemGroup group) {
        B block = Registry.register(Registry.BLOCK, new Identifier(ArchitectsPalette.MOD_ID, name), anyBlock);

        BlockItem blockItem = new BlockItem(block, new Item.Settings().group(group));
        Registry.register(Registry.ITEM, new Identifier(ArchitectsPalette.MOD_ID,name), blockItem);

        ItemStack stack = new ItemStack(block);
        APItemgroup.ITEMGROUP_LIST.add(stack);
        switch (name){
            case "algal_bricks":
                INDEX_ALGAL = APItemgroup.ITEMGROUP_LIST.indexOf(stack);
                System.out.println(INDEX_ALGAL+ " is algies");
                break;
            case "sunmetal_block":
                INDEX_SUNMETAL = APItemgroup.ITEMGROUP_LIST.indexOf(stack);
                System.out.println(INDEX_SUNMETAL+ " is sunmetal");
                break;
            case "withered_bone_block":
                INDEX_WITHERED = APItemgroup.ITEMGROUP_LIST.indexOf(stack);
                System.out.println(INDEX_WITHERED+ " is wither");
                break;
            case "entwine_block":
                INDEX_ENTWINE = APItemgroup.ITEMGROUP_LIST.indexOf(stack);
                System.out.println(INDEX_ENTWINE + " is entwine");
                break;
            default:
        }
        return block;
    }


    public static <B extends Block> B createBlockNoItem(String name, B anyBlock) {
        return Registry.register(Registry.BLOCK, new Identifier(ArchitectsPalette.MOD_ID, name), anyBlock);
    }
    private static Block createPottedPlant(Block plant) {
        String name = Registry.BLOCK.getId(plant).getPath();
        return createBlockNoItem("potted_" + name, new FlowerPotBlock(plant, FabricBlockSettings.copy(Blocks.POTTED_ACACIA_SAPLING).breakInstantly().nonOpaque()));
    }
// todo: move to APBlockData?

    public static void registerBlocks(){}

}