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
    public static final NewAbyssalineBlock      ABYSSALINE                 = new NewAbyssalineBlock(APBlockSettings.ABYSSALINE);
    public static final NewAbyssalineBlock      ABYSSALINE_BRICKS          = new NewAbyssalineBlock(APBlockSettings.ABYSSALINE);
    public static final NewAbyssalineBlock      ABYSSALINE_TILES           = new NewAbyssalineBlock(APBlockSettings.ABYSSALINE);
    public static final AbyssalineSlabBlock     ABYSSALINE_BRICK_SLAB      = new AbyssalineSlabBlock(APBlockSettings.ABYSSALINE);
    public static final AbyssalineSlabBlock     ABYSSALINE_TILE_SLAB       = new AbyssalineSlabBlock(APBlockSettings.ABYSSALINE);
    public static final AbyssalinePillarBlock   ABYSSALINE_PILLAR          = new AbyssalinePillarBlock(APBlockSettings.ABYSSALINE);
    public static final AbyssalineLampBlock     ABYSSALINE_LAMP_BLOCK      = new AbyssalineLampBlock(APBlockSettings.ABYSSALINE.sounds(BlockSoundGroup.GLASS));
    public static final ChiseledAbyssalineBlock CHISELED_ABYSSALINE_BRICKS = new ChiseledAbyssalineBlock(APBlockSettings.CHISELED_ABYSSALINE);

    // Limestone
    public static final Block LIMESTONE             = new Block(APBlockSettings.LIMESTONE);
    public static final Block LIMESTONE_BRICK       = new Block(APBlockSettings.LIMESTONE);
    public static final Block MUSHY_LIMESTONE_BRICK = new Block(APBlockSettings.LIMESTONE);

    // Olivestone
    public static final Block OLIVESTONE_BRICK = new Block(APBlockSettings.OLIVESTONE);
    public static final Block OLIVESTONE_TILE  = new Block(APBlockSettings.OLIVESTONE);

    public static final Block OLIVESTONE_PILLAR         = new PillarBlock(APBlockSettings.OLIVESTONE);
    public static final Block CRACKED_OLIVESTONE_BRICKS = new Block(APBlockSettings.OLIVESTONE);
    public static final Block CRACKED_OLIVESTONE_TILES  = new Block(APBlockSettings.OLIVESTONE);
    public static final Block CHISELED_OLIVESTONE       = new Block(APBlockSettings.OLIVESTONE);
    public static final Block ILLUMINATED_OLIVESTONE    = new Block(FabricBlockSettings.copy(OLIVESTONE_BRICK).luminance((state) -> 15));

    // Algal Brick
    public static final Block ALGAL_BRICKS                  = new Block(APBlockSettings.ALGAL_BRICK);
    public static final Block CRACKED_ALGAL_BRICKS          = new Block(APBlockSettings.ALGAL_BRICK);
    public static final Block CHISELED_ALGAL_BRICKS         = new Block(APBlockSettings.ALGAL_BRICK);
    public static final Block OVERGROWN_ALGAL_BRICK         =new Block(APBlockSettings.ALGAL_BRICK);
    public static final Block ALGAL_LAMP                    =new Block(FabricBlockSettings.copy(Blocks.SEA_LANTERN));

    // Sunmetal
    public static final Block SUNMETAL                = new Block(APBlockSettings.SUNMETAL);
    public static final Block CHISELED_SUNMETAL_BLOCK = new Block(APBlockSettings.SUNMETAL);
    public static final Block SUNMETAL_PILLAR         = new PillarBlock(APBlockSettings.SUNMETAL);
    public static final Block SUNMETAL_BARS           = new APPaneBlock(APBlockSettings.SUNMETAL.nonOpaque());

    // Rotten Flesh Block
    public static final Block ROTTEN_FLESH_BLOCK = new Block(APBlockSettings.Meat(MapColor.ORANGE));

    // Villager Trade blocks
    // Entrails
    public static final Block ENTRAILS = new DrippyBlock(APBlockSettings.Meat(MapColor.TERRACOTTA_PINK));
    // Funny fish blocks
    public static final Block  SALMON_LOG   = new PillarBlock(APBlockSettings.Meat(MapColor.TERRACOTTA_RED));
    public static final Block       COD_LOG = new PillarBlock(APBlockSettings.Meat(MapColor.TERRACOTTA_YELLOW));
    public static final Block SALMON_SCALES = new PillarBlock(APBlockSettings.Meat(MapColor.TERRACOTTA_RED));
    public static final Block    COD_SCALES = new PillarBlock(APBlockSettings.Meat(MapColor.TERRACOTTA_YELLOW));
        // Plating & Piping
    public static final Block PLATING_BLOCK = new Block(APBlockSettings.PLATING);
    public static final Block PIPE = new PipeBlock(APBlockSettings.PLATING.nonOpaque());
        //Spools
    public static final Block SPOOL = new PillarBlock(FabricBlockSettings.copy(Blocks.WHITE_WOOL));

    // Scute Block
    public static final Block SCUTE_BLOCK = new Block(FabricBlockSettings.of(Material.STONE, MapColor.LIME).strength(5.0F, 6.0F).sounds(BlockSoundGroup.BASALT));

    // Ore Bricks
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
    public static final Block POLISHED_PACKED_ICE = new Block(APBlockSettings.BUILDING_ICE);
    public static final Block CHISELED_PACKED_ICE = new Block(APBlockSettings.BUILDING_ICE);
    public static final Block PACKED_ICE_PILLAR   = new PillarBlock(APBlockSettings.BUILDING_ICE);

    // Gilded Sandstone
    public static final Block GILDED_SANDSTONE = new Block(FabricBlockSettings.copy(Blocks.SANDSTONE));
    public static final Block GILDED_SANDSTONE_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.SANDSTONE));
    public static final Block CHISELED_GILDED_SANDSTONE = new Block(FabricBlockSettings.copy(Blocks.SANDSTONE));

    // Polished Glowstone
    public static final Block POLISHED_GLOWSTONE = new Block(FabricBlockSettings.copy(Blocks.GLOWSTONE));
    public static final Block RUNIC_GLOWSTONE = new Block(FabricBlockSettings.copy(Blocks.GLOWSTONE));

    // Osseous Bricks
    public static final Block OSSEOUS_BRICK = new Block(FabricBlockSettings.copy(Blocks.BONE_BLOCK));
    public static final Block OSSEOUS_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK));
    // Withered
     // Todo: Replace bone block recipe to one that uses withered bone meal if that gets in
    public static final Block WITHERED_BONE_BLOCK = new PillarBlock(FabricBlockSettings.copyOf(Blocks.BONE_BLOCK));
    public static final Block      WITHERED_OSSEOUS_BRICK = new Block(FabricBlockSettings.copy(Blocks.BONE_BLOCK));
    public static final Block WITHERED_OSSEOUS_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK));
    // Wither Lamp
    public static final Block WITHER_LAMP = new Block(FabricBlockSettings.copy(Blocks.SEA_LANTERN));

    // Flint Blocks
    public static final Block FLINT_BLOCK  	= new FlintBlock(APBlockSettings.FLINT);
    public static final Block FLINT_TILES  	= new FlintBlock(APBlockSettings.FLINT);
    public static final Block FLINT_PILLAR 	= new FlintPillarBlock(APBlockSettings.FLINT);

    // Mossy Blackstone Variants
    public static final Block WEEPING_BLACKSTONE         = new Block(FabricBlockSettings.copy(Blocks.BLACKSTONE));
    public static final Block TWISTING_BLACKSTONE        = new Block(FabricBlockSettings.copy(Blocks.BLACKSTONE));
    public static final Block WEEPING_BLACKSTONE_BRICKS  = new Block(FabricBlockSettings.copy(Blocks.POLISHED_BLACKSTONE_BRICKS));
    public static final Block TWISTING_BLACKSTONE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.POLISHED_BLACKSTONE_BRICKS));

    // Basalt Tiles
    public static final Block BASALT_TILES = new Block(FabricBlockSettings.copy(Blocks.BASALT));
    public static final Block  CRACKED_BASALT_TILES = new Block(FabricBlockSettings.copy(Blocks.BASALT));
    public static final Block CHISELED_BASALT_TILES = new Block(FabricBlockSettings.copy(Blocks.BASALT));

    // Heavy Stone Bricks
    public static final Block HEAVY_STONE_BRICKS = new BigBrickBlock(FabricBlockSettings.copy(Blocks.STONE_BRICKS));
    public static final Block HEAVY_MOSSY_STONE_BRICKS = new BigBrickBlock(FabricBlockSettings.copy(Blocks.MOSSY_STONE_BRICKS));
    public static final Block HEAVY_CRACKED_STONE_BRICKS = new BigBrickBlock(FabricBlockSettings.copy(Blocks.CRACKED_STONE_BRICKS));

    // Entwine
    public static final Block ENTWINE = new Block(APBlockSettings.ENTWINE);
    public static final Block ENTWINE_PILLAR = new PillarBlock(APBlockSettings.ENTWINE);
    public static final Block CHISELED_ENTWINE = new Block(APBlockSettings.ENTWINE);
    public static final Block ENTWINE_BARS = new APPaneBlock(FabricBlockSettings.copy(ENTWINE).nonOpaque());
    // Ender Pearl Block
    public static final Block ENDER_PEARL_BLOCK = new Block(APBlockSettings.ENDER_PEARL);

    // End Stone Variants
    public static final Block   CHORAL_END_STONE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.END_STONE_BRICKS));
    public static final Block  CRACKED_END_STONE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.END_STONE_BRICKS));
    public static final Block CHISELED_END_STONE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.END_STONE_BRICKS));

    // Heavy End Stone Bricks
    public static final Block HEAVY_END_STONE_BRICKS = new BigBrickBlock(FabricBlockSettings.copy(Blocks.END_STONE_BRICKS), BigBrickBlock.BrickType.END_STONE);
    public static final Block HEAVY_CRACKED_END_STONE_BRICKS = new BigBrickBlock(FabricBlockSettings.copy(Blocks.END_STONE_BRICKS), BigBrickBlock.BrickType.END_STONE);

    // Warpstone
    public static final Block WARPSTONE = new Block(FabricBlockSettings.copy(Blocks.STONE));

    // Twisted Wood
     // Todo: Bookshelf, sign(?), boat(?)
    public static final Block TWISTED_PLANKS = new Block(APBlockSettings.TwistedWood());

    public static final Block           TWISTED_LOG = new PillarBlock(APBlockSettings.TwistedWood());
    public static final Block          TWISTED_WOOD = new PillarBlock(APBlockSettings.TwistedWood());
    public static final Block  STRIPPED_TWISTED_LOG = new PillarBlock(APBlockSettings.TwistedWood());
    public static final Block STRIPPED_TWISTED_WOOD = new PillarBlock(APBlockSettings.TwistedWood());
    public static final Block        TWISTED_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    public static final Block         TWISTED_FENCE = new FenceBlock(APBlockSettings.TwistedWood());
    public static final Block    TWISTED_FENCE_GATE = new FenceGateBlock(APBlockSettings.TwistedWood());
    public static final Block          TWISTED_DOOR = new APDoorBlock(APBlockSettings.TwistedWood().nonOpaque());
    public static final Block      TWISTED_TRAPDOOR = new APTrapdoorBlock(APBlockSettings.TwistedWood().nonOpaque());
    public static final Block        TWISTED_BUTTON = new APWoodenButtonBlock(APBlockSettings.TwistedWood(true));
    public static final Block TWISTED_PRESSURE_PLATE = new APPressurePlateBlock((PressurePlateBlock.ActivationRule.EVERYTHING), APBlockSettings.TwistedWood(true));


    public static final Block        TWISTED_SAPLING = new APSaplingBlock(new TwistedTree(), FabricBlockSettings.copy(Blocks.OAK_SAPLING));
    public static final Block POTTED_TWISTED_SAPLING = createPottedPlant(TWISTED_SAPLING);

    // Celestial Stones
    public static final Block SUNSTONE  = new SunstoneBlock(FabricBlockSettings.copy(Blocks.BASALT).nonOpaque(), SunstoneBlock::sunstoneLight);
    public static final Block MOONSTONE = new SunstoneBlock(FabricBlockSettings.copy(Blocks.BASALT).nonOpaque(), SunstoneBlock::moonstoneLight);

    // Odd block variants
    public static final Block MOLTEN_NETHER_BRICKS = new Block(APBlockSettings.MOLTEN_BRICK);
    public static final Block COARSE_SNOW = new Block(FabricBlockSettings.copy(Blocks.SNOW_BLOCK));
         // Charcoal Block
    public static final Block CHARCOAL_BLOCK = new PillarBlock(FabricBlockSettings.copy(Blocks.COAL_BLOCK));

    // Cage Lanterns
    public static final Block REDSTONE_CAGE_LANTERN  = new CageLanternBlock(APBlockSettings.CAGE_LANTERN, 3);
    public static final Block GLOWSTONE_CAGE_LANTERN = new CageLanternBlock(APBlockSettings.CAGE_LANTERN, 3);
    public static final Block ALGAL_CAGE_LANTERN     = new CageLanternBlock(APBlockSettings.CAGE_LANTERN, 3);

    // Acacia Totems
    public static final TotemWingBlock ACACIA_TOTEM_WING = new TotemWingBlock(FabricBlockSettings.copy(Blocks.ACACIA_PLANKS).nonOpaque().dropsNothing().sounds(BlockSoundGroup.SCAFFOLDING).noCollision());
    public static final Block GRINNING_ACACIA_TOTEM = new TotemBlock(APBlockSettings.ACACIA_TOTEM, ACACIA_TOTEM_WING, TotemBlock.TotemFace.GRINNING);
    public static final Block PLACID_ACACIA_TOTEM = new TotemBlock(APBlockSettings.ACACIA_TOTEM, ACACIA_TOTEM_WING, TotemBlock.TotemFace.PLACID);
    public static final Block SHOCKED_ACACIA_TOTEM = new TotemBlock(APBlockSettings.ACACIA_TOTEM, ACACIA_TOTEM_WING, TotemBlock.TotemFace.SHOCKED);
    public static final Block BLANK_ACACIA_TOTEM = new TotemBlock(APBlockSettings.ACACIA_TOTEM, ACACIA_TOTEM_WING, TotemBlock.TotemFace.BLANK);


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

        ItemStack stack = new ItemStack(blockItem);
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
        return new FlowerPotBlock(plant, FabricBlockSettings.copy(Blocks.POTTED_ACACIA_SAPLING).breakInstantly().nonOpaque());
    }
    public static void registerBlocks(){
        createBlock("abyssaline",ABYSSALINE);
        createBlock("abyssaline_bricks", ABYSSALINE_BRICKS);
        createBlock("abyssaline_tiles", ABYSSALINE_TILES);
        createBlock("abyssaline_brick_slab",ABYSSALINE_BRICK_SLAB);
        createBlock("abyssaline_tile_slab",ABYSSALINE_TILE_SLAB);
        createBlock("abyssaline_pillar",ABYSSALINE_PILLAR);
        createBlock("abyssaline_lamp",ABYSSALINE_LAMP_BLOCK);
        createBlock("chiseled_abyssaline_bricks",CHISELED_ABYSSALINE_BRICKS);

        // Limestone

        new StoneBlockSet(createBlock("limestone",LIMESTONE));
        new StoneBlockSet(createBlock("limestone_bricks",LIMESTONE_BRICK));
        new StoneBlockSet(createBlock("mushy_limestone_bricks",MUSHY_LIMESTONE_BRICK));

        // Olivestone
        new StoneBlockSet(createBlock("olivestone_bricks",OLIVESTONE_BRICK));
        createBlock("cracked_olivestone_bricks",CRACKED_OLIVESTONE_BRICKS);
        new StoneBlockSet(createBlock("olivestone_tiles",OLIVESTONE_TILE));
        createBlock("cracked_olivestone_tiles",CRACKED_OLIVESTONE_TILES);

        createBlock("olivestone_pillar",OLIVESTONE_PILLAR);
        createBlock("chiseled_olivestone",CHISELED_OLIVESTONE);
         createBlock("illuminated_olivestone",ILLUMINATED_OLIVESTONE);

        // Algal Brick
         new StoneBlockSet(createBlock("algal_bricks",ALGAL_BRICKS));
         createBlock("cracked_algal_bricks",CRACKED_ALGAL_BRICKS);
         createBlock("chiseled_algal_bricks",CHISELED_ALGAL_BRICKS);
         new StoneBlockSet(createBlock("overgrown_algal_bricks",OVERGROWN_ALGAL_BRICK));
         createBlock("algal_lamp",ALGAL_LAMP);

        // Sunmetal
         new StoneBlockSet(createBlock("sunmetal_block",SUNMETAL, ItemGroup.BUILDING_BLOCKS), false).addSlabs().addStairs();
         createBlock("chiseled_sunmetal_block",CHISELED_SUNMETAL_BLOCK);
         createBlock("sunmetal_pillar",SUNMETAL_PILLAR);
         createBlock("sunmetal_bars",SUNMETAL_BARS);

        // Rotten Flesh Block
         createBlock("rotten_flesh_block",ROTTEN_FLESH_BLOCK);

        // Villager Trade blocks
        // Entrails
         createBlock("entrails",ENTRAILS);
        // Funny fish blocks
         createBlock("salmon_log",SALMON_LOG);
         createBlock("cod_log",COD_LOG);
         createBlock("salmon_scales",SALMON_SCALES);
         createBlock("cod_scales",COD_SCALES);
        // Plating & Piping
         new StoneBlockSet(createBlock("plating_block",PLATING_BLOCK));
         createBlock("pipe",PIPE);
        //Spools
         createBlock("spool",SPOOL);

        // Scute Block
         createBlock("scute_block",  SCUTE_BLOCK);

        // Ore Bricks
         List<StoneBlockSet> ORE_BRICKS = addOreBricks();

        // Polished Packed Ice
         new StoneBlockSet(createBlock("polished_packed_ice",POLISHED_PACKED_ICE));
         createBlock("chiseled_packed_ice",CHISELED_PACKED_ICE  );
         createBlock("packed_ice_pillar",PACKED_ICE_PILLAR);

        // Gilded Sandstone
         new StoneBlockSet(createBlock("gilded_sandstone",  GILDED_SANDSTONE ), false).addSlabs().addStairs();
         createBlock("gilded_sandstone_pillar",GILDED_SANDSTONE_PILLAR);
         createBlock("chiseled_gilded_sandstone",CHISELED_GILDED_SANDSTONE);

        // Polished Glowstone
         new StoneBlockSet(createBlock("polished_glowstone",POLISHED_GLOWSTONE), false).addSlabs();
         createBlock("runic_glowstone",RUNIC_GLOWSTONE);

        // Osseous Bricks
         new StoneBlockSet(createBlock("osseous_bricks",OSSEOUS_BRICK));
         createBlock("osseous_pillar",OSSEOUS_PILLAR);
        // Withered
        // Todo: Replace bone block recipe to one that uses withered bone meal if that gets in
         createBlock("withered_bone_block",WITHERED_BONE_BLOCK, ItemGroup.BUILDING_BLOCKS);
         new StoneBlockSet(createBlock("withered_osseous_bricks",WITHERED_OSSEOUS_BRICK));
         createBlock("withered_osseous_pillar",WITHERED_OSSEOUS_PILLAR);
         createBlock("wither_lamp",  WITHER_LAMP);

        // Flint Blocks
         createBlock("flint_block",FLINT_BLOCK);
         new StoneBlockSet(createBlock("flint_tiles",FLINT_TILES));
         createBlock("flint_pillar",FLINT_PILLAR);

        // Mossy Blackstone Variants
         createBlock("weeping_blackstone",  WEEPING_BLACKSTONE);
         createBlock("twisting_blackstone", TWISTING_BLACKSTONE);
         createBlock("weeping_blackstone_bricks",  WEEPING_BLACKSTONE_BRICKS);
         createBlock("twisting_blackstone_bricks",  TWISTING_BLACKSTONE_BRICKS);

        // Basalt Tiles
         new StoneBlockSet(createBlock("basalt_tiles",BASALT_TILES));
         createBlock("cracked_basalt_tiles",CRACKED_BASALT_TILES);
         createBlock("chiseled_basalt_tiles",CHISELED_BASALT_TILES);

        // Heavy Stone Bricks
         createBlock("heavy_stone_bricks",HEAVY_STONE_BRICKS);
         createBlock("heavy_mossy_stone_bricks",HEAVY_MOSSY_STONE_BRICKS);
         createBlock("heavy_cracked_stone_bricks",HEAVY_CRACKED_STONE_BRICKS);

        // Entwine
         new StoneBlockSet(createBlock("entwine_block",  ENTWINE ), false).addSlabs().addStairs();
         createBlock("entwine_pillar",ENTWINE_PILLAR);
         createBlock("chiseled_entwine",CHISELED_ENTWINE);
         createBlock("entwine_bars",ENTWINE_BARS);
        // Ender Pearl Block
         createBlock("ender_pearl_block",ENDER_PEARL_BLOCK);

        // End Stone Variants
         createBlock("choral_end_stone_bricks",CHORAL_END_STONE_BRICKS);
         createBlock("cracked_end_stone_bricks",CRACKED_END_STONE_BRICKS);
         createBlock("chiseled_end_stone_bricks",CHISELED_END_STONE_BRICKS);

        // Heavy End Stone Bricks
         createBlock("heavy_end_stone_bricks",  HEAVY_END_STONE_BRICKS);
         createBlock("heavy_cracked_end_stone_bricks",HEAVY_CRACKED_END_STONE_BRICKS);

        // Warpstone
         new StoneBlockSet(createBlock("warpstone",  WARPSTONE));

        // Twisted Wood
         new StoneBlockSet(createBlock("twisted_planks",TWISTED_PLANKS), false).addSlabs().addStairs();

         createBlock("twisted_log", TWISTED_LOG);
         createBlock("twisted_wood", TWISTED_WOOD);
         createBlock("stripped_twisted_log", STRIPPED_TWISTED_LOG);
         createBlock("stripped_twisted_wood", STRIPPED_TWISTED_WOOD);
         createBlock("twisted_leaves", TWISTED_LEAVES);
         createBlock("twisted_fence", TWISTED_FENCE, ItemGroup.DECORATIONS);
         createBlock("twisted_fence_gate", TWISTED_FENCE_GATE, ItemGroup.REDSTONE);
         createBlock("twisted_door", TWISTED_DOOR, ItemGroup.REDSTONE);
         createBlock("twisted_trapdoor", TWISTED_TRAPDOOR, ItemGroup.REDSTONE);
         createBlock("twisted_button", TWISTED_BUTTON, ItemGroup.REDSTONE);
         createBlock("twisted_pressure_plate", TWISTED_PRESSURE_PLATE, ItemGroup.REDSTONE);

         createBlock("twisted_sapling",TWISTED_SAPLING, ItemGroup.DECORATIONS);
         createBlockNoItem("potted_twisted_sapling" ,POTTED_TWISTED_SAPLING);


        // Celestial Stones
         createBlock("sunstone",SUNSTONE);
         createBlock("moonstone",MOONSTONE);

        // Odd block variants
        createBlock("molten_nether_bricks",MOLTEN_NETHER_BRICKS);
        createBlock("coarse_snow",COARSE_SNOW);
        // Charcoal Block
        createBlock("charcoal_block",CHARCOAL_BLOCK);

        // Cage Lanterns
         createBlock("redstone_cage_lantern", REDSTONE_CAGE_LANTERN, ItemGroup.REDSTONE);
         createBlock("glowstone_cage_lantern", GLOWSTONE_CAGE_LANTERN, ItemGroup.REDSTONE);
         createBlock("algal_cage_lantern", ALGAL_CAGE_LANTERN, ItemGroup.REDSTONE);

        // Acacia Totems
         createBlock("acacia_totem_wing",ACACIA_TOTEM_WING, ItemGroup.DECORATIONS);
         createBlock("grinning_acacia_totem",GRINNING_ACACIA_TOTEM);
         createBlock("placid_acacia_totem",  PLACID_ACACIA_TOTEM);
         createBlock("shocked_acacia_totem",SHOCKED_ACACIA_TOTEM);
         createBlock("blank_acacia_totem",BLANK_ACACIA_TOTEM);

    }

}