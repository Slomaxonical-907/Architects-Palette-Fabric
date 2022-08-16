package com.slomaxonical.architectspalette.registry;

import com.slomaxonical.architectspalette.blocks.entrails.DrippyBlock;
import com.slomaxonical.architectspalette.blocks.entrails.DrippySlabBlock;
import com.slomaxonical.architectspalette.blocks.entrails.DrippyVerticalSlabBlock;
import com.slomaxonical.architectspalette.blocks.flint.*;
import com.slomaxonical.architectspalette.blocks.util.APBlockSettings;
import com.slomaxonical.architectspalette.blocks.abyssaline.*;
import com.slomaxonical.architectspalette.blocks.*;
import com.slomaxonical.architectspalette.blocks.util.StoneBlockSet;
import com.slomaxonical.architectspalette.features.TwistedTree;
import com.slomaxonical.architectspalette.ArchitectsPalette;
import com.slomaxonical.architectspalette.registry.util.RegistryUtil;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;

import java.util.*;

import static com.slomaxonical.architectspalette.blocks.util.StoneBlockSet.SetGroup.*;
import static com.slomaxonical.architectspalette.registry.util.RegistryUtil.createBlock;


public class APBlocks {
    public static Map<Block,List<Block>> chiseledNcrackedOres = new HashMap<>();

    // Abyssaline
    public static final AbyssalineBlock ABYSSALINE                 = new AbyssalineBlock(APBlockSettings.ABYSSALINE);
    public static final AbyssalineBlock ABYSSALINE_BRICKS          = new AbyssalineBlock(APBlockSettings.ABYSSALINE);
    public static final AbyssalineBlock ABYSSALINE_TILES           = new AbyssalineBlock(APBlockSettings.ABYSSALINE);
    public static final AbyssalinePillarBlock   ABYSSALINE_PILLAR          = new AbyssalinePillarBlock(APBlockSettings.ABYSSALINE);
    public static final AbyssalineLampBlock     ABYSSALINE_LAMP_BLOCK      = new AbyssalineLampBlock(APBlockSettings.ABYSSALINE);
    public static final ChiseledAbyssalineBlock CHISELED_ABYSSALINE_BRICKS = new ChiseledAbyssalineBlock(APBlockSettings.CHISELED_ABYSSALINE);

    // Limestone
    public static final Block MYONITE = new Block(APBlockSettings.MYONITE);
    public static final Block MYONITE_BRICKS = new Block(APBlockSettings.MYONITE);
    public static final Block MUSHY_MYONITE_BRICK = new Block(APBlockSettings.MYONITE);

    // Olivestone
    public static final Block OLIVESTONE_BRICKS = new Block(APBlockSettings.OLIVESTONE);
    public static final Block OLIVESTONE_TILE  = new Block(APBlockSettings.OLIVESTONE);

    public static final Block OLIVESTONE_PILLAR         = new PillarBlock(APBlockSettings.OLIVESTONE);
    public static final Block CRACKED_OLIVESTONE_BRICKS = new Block(APBlockSettings.OLIVESTONE);
    public static final Block CRACKED_OLIVESTONE_TILES  = new Block(APBlockSettings.OLIVESTONE);
    public static final Block CHISELED_OLIVESTONE       = new Block(APBlockSettings.OLIVESTONE);
    public static final Block ILLUMINATED_OLIVESTONE    = new Block(FabricBlockSettings.copy(OLIVESTONE_BRICKS).luminance((state) -> 15));

    // Algal Brick
    public static final Block ALGAL_BRICKS                  = new Block(APBlockSettings.ALGAL_BRICK);
    public static final Block CRACKED_ALGAL_BRICKS          = new Block(APBlockSettings.ALGAL_BRICK);
    public static final Block CHISELED_ALGAL_BRICKS         = new Block(APBlockSettings.ALGAL_BRICK);
    public static final Block OVERGROWN_ALGAL_BRICK         =new Block(APBlockSettings.ALGAL_BRICK);
    public static final Block ALGAL_LAMP                    =new Block(FabricBlockSettings.copy(Blocks.SEA_LANTERN));

    // Nether Brass
    public static final Block NETHER_BRASS = new Block(APBlockSettings.NETHER_BRASS);
    public static final Block CUT_NETHER_BRASS =new Block(APBlockSettings.NETHER_BRASS);
    public static final Block SMOOTH_NETHER_BRASS = new Block(APBlockSettings.NETHER_BRASS);
    public static final Block NETHER_BRASS_PILLAR = new PillarBlock(APBlockSettings.NETHER_BRASS);
    public static final Block NETHER_BRASS_FIRE = new GreenFireBlock(APBlockSettings.GREEN_FIRE);

    public static final Block NETHER_BRASS_CHAIN = new ChainBlock(APBlockSettings.NETHER_BRASS.sounds(BlockSoundGroup.CHAIN));
    public static final Block NETHER_BRASS_LANTERN = new LanternBlock(FabricBlockSettings.of(Material.METAL, MapColor.YELLOW).strength(4.0F, 10.0F).sounds(BlockSoundGroup.COPPER).requiresTool().luminance((a)->13));
    public static final Block NETHER_BRASS_TORCH = new TorchBlock(APBlockSettings.BRASS_TORCH, ArchitectsPalette.GREEN_FLAME);
    public static final Block NETHER_BRASS_WALL_TORCH = new WallTorchBlock(APBlockSettings.BRASS_TORCH.dropsLike(NETHER_BRASS_TORCH), ArchitectsPalette.GREEN_FLAME);

    // Sunmetal
    public static final Block SUNMETAL                = new Block(APBlockSettings.SUNMETAL);
    public static final Block CHISELED_SUNMETAL_BLOCK = new Block(APBlockSettings.SUNMETAL);
    public static final Block SUNMETAL_PILLAR         = new PillarBlock(APBlockSettings.SUNMETAL);
    public static final Block SUNMETAL_BARS           = new PaneBlock(APBlockSettings.SUNMETAL.nonOpaque());

    // Rotten Flesh Block
    public static final Block ROTTEN_FLESH_BLOCK = new Block(APBlockSettings.Meat(MapColor.ORANGE));

  // Villager Trade blocks
    // Entrails
    public static final Block ENTRAILS = new DrippyBlock(APBlockSettings.Meat(MapColor.TERRACOTTA_PINK));
    // Funny fish blocks
    public static final Block  SALMON_LOG   = new PillarBlock(APBlockSettings.Meat(MapColor.TERRACOTTA_RED));
    public static final Block  COD_LOG = new PillarBlock(APBlockSettings.Meat(MapColor.TERRACOTTA_YELLOW));
    public static final Block  SALMON_SCALES = new PillarBlock(APBlockSettings.Meat(MapColor.TERRACOTTA_RED));
    public static final Block  COD_SCALES = new PillarBlock(APBlockSettings.Meat(MapColor.TERRACOTTA_YELLOW));
    // Plating & Piping
    public static final Block PLATING_BLOCK = new Block(APBlockSettings.PLATING);
    public static final Block PIPE = new PipeBlock(APBlockSettings.PLATING.nonOpaque());
    //Spools
    public static final Block SPOOL = new PillarBlock(FabricBlockSettings.copy(Blocks.WHITE_WOOL));

    // Scute Block
    public static final Block SCUTE_BLOCK = new Block(FabricBlockSettings.of(Material.STONE, MapColor.LIME).strength(5.0F, 6.0F).sounds(BlockSoundGroup.BASALT));

    // Ore Bricks
    private static void addOreBricks() {
        List<String> ores = List.of("coal", "lapis", "redstone", "iron", "gold", "emerald", "diamond");

        for (String ore : ores) {
            StoneBlockSet set = new StoneBlockSet(createBlock(ore + "_ore_bricks", new Block(FabricBlockSettings.copy(Blocks.STONE_BRICKS))));
            Block chiseled = createBlock("chiseled_" + ore + "_ore_bricks", new Block(FabricBlockSettings.copy(Blocks.CHISELED_STONE_BRICKS)));
            Block cracked = createBlock("cracked_" + ore + "_ore_bricks", new Block(FabricBlockSettings.copy(Blocks.CRACKED_STONE_BRICKS)));
            chiseledNcrackedOres.put(set.getBase(),List.of(chiseled,cracked));
        }
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
    public static final Block OSSEOUS_SKULL = new Block(FabricBlockSettings.copy(Blocks.BONE_BLOCK));
    public static final Block LIT_OSSEOUS_SKULL = new Block(FabricBlockSettings.copy(Blocks.BONE_BLOCK).luminance((state) -> 12));
    // Withered
     // Todo: Replace bone block recipe to one that uses withered bone meal if that gets in
    public static final Block WITHERED_BONE_BLOCK = new PillarBlock(FabricBlockSettings.copyOf(Blocks.BONE_BLOCK));
    public static final Block WITHERED_OSSEOUS_BRICK = new Block(FabricBlockSettings.copy(Blocks.BONE_BLOCK));
    public static final Block WITHERED_OSSEOUS_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK));
    public static final Block WITHERED_OSSEOUS_SKULL = new Block(FabricBlockSettings.copy(Blocks.BONE_BLOCK));
    public static final Block LIT_WITHERED_OSSEOUS_SKULL = new Block(FabricBlockSettings.copy(Blocks.BONE_BLOCK).luminance((state) -> 12));

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

    //Dripstone
    public static final Block DRIPSTONE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.DRIPSTONE_BLOCK));
    public static final Block DRIPSTONE_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.DRIPSTONE_BLOCK));
    public static final Block CHISELED_DRIPSTONE = new Block(FabricBlockSettings.copy(Blocks.DRIPSTONE_BLOCK));
    public static final Block HEAVY_DRIPSTONE_BRICKS = new BigBrickBlock(FabricBlockSettings.copy(Blocks.DRIPSTONE_BLOCK), BigBrickBlock.BrickType.DRIPSTONE);
    public static final Block DRIPSTONE_LAMP = new Block(FabricBlockSettings.copy(Blocks.DRIPSTONE_BLOCK).luminance((state)->8));

    //Calcite
    public static final Block CALCITE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.CALCITE));
    public static final Block CALCITE_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.CALCITE));
    public static final Block CHISELED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE));
    public static final Block HEAVY_CALCITE_BRICKS = new BigBrickBlock(FabricBlockSettings.copy(Blocks.CALCITE), BigBrickBlock.BrickType.CALCITE);
    public static final Block CALCITE_LAMP = new Block(FabricBlockSettings.copy(Blocks.CALCITE).luminance((state)->8));

    //Tuff
    public static final Block TUFF_BRICKS = new Block(FabricBlockSettings.copy(Blocks.TUFF));
    public static final Block TUFF_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.TUFF));
    public static final Block CHISELED_TUFF = new Block(FabricBlockSettings.copy(Blocks.TUFF));
    public static final Block HEAVY_TUFF_BRICKS = new BigBrickBlock(FabricBlockSettings.copy(Blocks.TUFF), BigBrickBlock.BrickType.TUFF);
    public static final Block TUFF_LAMP = new Block(FabricBlockSettings.copy(Blocks.TUFF).luminance((state)->8));

    // Heavy Stone Bricks
    public static final Block HEAVY_STONE_BRICKS = new BigBrickBlock(FabricBlockSettings.copy(Blocks.STONE_BRICKS));
    public static final Block HEAVY_MOSSY_STONE_BRICKS = new BigBrickBlock(FabricBlockSettings.copy(Blocks.MOSSY_STONE_BRICKS));
    public static final Block HEAVY_CRACKED_STONE_BRICKS = new BigBrickBlock(FabricBlockSettings.copy(Blocks.CRACKED_STONE_BRICKS));

    // Entwine
    public static final Block ENTWINE = new Block(APBlockSettings.ENTWINE);
    public static final Block ENTWINE_PILLAR = new PillarBlock(APBlockSettings.ENTWINE);
    public static final Block CHISELED_ENTWINE = new Block(APBlockSettings.ENTWINE);
    public static final Block ENTWINE_BARS = new PaneBlock(FabricBlockSettings.copy(ENTWINE).nonOpaque());
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
    public static final Block        TWISTED_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).mapColor(MapColor.PURPLE));
    public static final Block         TWISTED_FENCE = new FenceBlock(APBlockSettings.TwistedWood());
    public static final Block    TWISTED_FENCE_GATE = new FenceGateBlock(APBlockSettings.TwistedWood());
    public static final Block          TWISTED_DOOR = new DoorBlock(APBlockSettings.TwistedWood().nonOpaque());
    public static final Block      TWISTED_TRAPDOOR = new TrapdoorBlock(APBlockSettings.TwistedWood().nonOpaque());
    public static final Block        TWISTED_BUTTON = new WoodenButtonBlock(APBlockSettings.TwistedWood(true));
    public static final Block TWISTED_PRESSURE_PLATE = new PressurePlateBlock((PressurePlateBlock.ActivationRule.EVERYTHING), APBlockSettings.TwistedWood(true));


    public static final Block        TWISTED_SAPLING = new SaplingBlock(new TwistedTree(), FabricBlockSettings.copy(Blocks.OAK_SAPLING));
    public static final Block POTTED_TWISTED_SAPLING = RegistryUtil.createPottedPlant(TWISTED_SAPLING);

    //Boards
    public static final Block OAK_BOARDS = new BoardBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS));
    public static final Block BIRCH_BOARDS = new BoardBlock(FabricBlockSettings.copy(Blocks.BIRCH_PLANKS));
    public static final Block SPRUCE_BOARDS = new BoardBlock(FabricBlockSettings.copy(Blocks.SPRUCE_PLANKS));
    public static final Block JUNGLE_BOARDS = new BoardBlock(FabricBlockSettings.copy(Blocks.JUNGLE_PLANKS));
    public static final Block DARK_OAK_BOARDS = new BoardBlock(FabricBlockSettings.copy(Blocks.DARK_OAK_PLANKS));
    public static final Block ACACIA_BOARDS = new BoardBlock(FabricBlockSettings.copy(Blocks.ACACIA_PLANKS));
    public static final Block CRIMSON_BOARDS = new BoardBlock(FabricBlockSettings.copy(Blocks.CRIMSON_PLANKS));
    public static final Block WARPED_BOARDS = new BoardBlock(FabricBlockSettings.copy(Blocks.WARPED_PLANKS));
    public static final Block TWISTED_BOARDS = new BoardBlock(APBlockSettings.TwistedWood());
    //Railings
    public static final Block OAK_RAILING = new RailingBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS));
    public static final Block BIRCH_RAILING = new RailingBlock(FabricBlockSettings.copy(Blocks.BIRCH_PLANKS));
    public static final Block SPRUCE_RAILING = new RailingBlock(FabricBlockSettings.copy(Blocks.SPRUCE_PLANKS));
    public static final Block JUNGLE_RAILING = new RailingBlock(FabricBlockSettings.copy(Blocks.JUNGLE_PLANKS));
    public static final Block DARK_OAK_RAILING = new RailingBlock(FabricBlockSettings.copy(Blocks.DARK_OAK_PLANKS));
    public static final Block ACACIA_RAILING = new RailingBlock(FabricBlockSettings.copy(Blocks.ACACIA_PLANKS));
    public static final Block CRIMSON_RAILING = new RailingBlock(FabricBlockSettings.copy(Blocks.CRIMSON_PLANKS));
    public static final Block WARPED_RAILING = new RailingBlock(FabricBlockSettings.copy(Blocks.WARPED_PLANKS));
    public static final Block TWISTED_RAILING = new RailingBlock(APBlockSettings.TwistedWood());

    // Celestial Stones
    public static final Block SUNSTONE  = new SunstoneBlock(APBlockSettings.SUNSTONE, SunstoneBlock::sunstoneLight);
    public static final Block MOONSTONE = new SunstoneBlock(APBlockSettings.SUNSTONE, SunstoneBlock::moonstoneLight);

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

    //Radioactive Crystals
    public static final Block HELIODOR_ROD = new GlassLikePillarBlock(APBlockSettings.NETHER_CRYSTAL);
    public static final Block EKANITE_ROD = new GlassLikePillarBlock(APBlockSettings.NETHER_CRYSTAL);
    public static final Block MONAZITE_ROD = new GlassLikePillarBlock(APBlockSettings.NETHER_CRYSTAL);
    //unobtanium
    public static final Block UNOBTANIUM_BLOCK = new Block(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK));


    //todo:move to seperate ting with flammability and stripping registries

    public static void registerBlocks(){
        createBlock("abyssaline",ABYSSALINE);
        new StoneBlockSet(createBlock("abyssaline_bricks", ABYSSALINE_BRICKS),SLABS);
        new StoneBlockSet(createBlock("abyssaline_tiles", ABYSSALINE_TILES),SLABS);
        createBlock("abyssaline_pillar",ABYSSALINE_PILLAR);
        createBlock("abyssaline_lamp",ABYSSALINE_LAMP_BLOCK);
        createBlock("chiseled_abyssaline_bricks",CHISELED_ABYSSALINE_BRICKS);
        
        // Myonite (Previously Limestone)
         new StoneBlockSet(createBlock("myonite", MYONITE));
         new StoneBlockSet(createBlock("myonite_bricks", MYONITE_BRICKS));
        new StoneBlockSet(createBlock("mushy_myonite_bricks", MUSHY_MYONITE_BRICK));

        // Olivestone
        new StoneBlockSet(createBlock("olivestone_bricks", OLIVESTONE_BRICKS));
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

        //NetherBrass
        new StoneBlockSet(createBlock("nether_brass_block", NETHER_BRASS));
        new StoneBlockSet(createBlock("cut_nether_brass", CUT_NETHER_BRASS));
        createBlock("nether_brass_pillar", NETHER_BRASS_PILLAR);
        new StoneBlockSet(createBlock("smooth_nether_brass", SMOOTH_NETHER_BRASS), NO_WALLS);
        RegistryUtil.createBlockNoItem("nether_brass_fire", NETHER_BRASS_FIRE);
        createBlock("nether_brass_chain", NETHER_BRASS_CHAIN,ItemGroup.DECORATIONS);
        createBlock("nether_brass_lantern", NETHER_BRASS_LANTERN,ItemGroup.DECORATIONS);
        RegistryUtil.createBlockNoItem("nether_brass_torch", NETHER_BRASS_TORCH);
        RegistryUtil.createBlockNoItem("nether_brass_wall_torch",NETHER_BRASS_WALL_TORCH);

        // Sunmetal
         new StoneBlockSet(createBlock("sunmetal_block",SUNMETAL, ItemGroup.BUILDING_BLOCKS), NO_WALLS);
         createBlock("chiseled_sunmetal_block",CHISELED_SUNMETAL_BLOCK);
         createBlock("sunmetal_pillar",SUNMETAL_PILLAR);
         createBlock("sunmetal_bars",SUNMETAL_BARS);

        // Rotten Flesh Block
         createBlock("rotten_flesh_block",ROTTEN_FLESH_BLOCK);

        // Villager Trade blocks
        // Entrails
        new StoneBlockSet(createBlock("entrails", ENTRAILS),NO_WALLS);
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
         addOreBricks();

        // Polished Packed Ice
         new StoneBlockSet(createBlock("polished_packed_ice",POLISHED_PACKED_ICE));
         createBlock("chiseled_packed_ice",CHISELED_PACKED_ICE  );
         createBlock("packed_ice_pillar",PACKED_ICE_PILLAR);

        // Gilded Sandstone
         new StoneBlockSet(createBlock("gilded_sandstone",  GILDED_SANDSTONE ), NO_WALLS);
         createBlock("gilded_sandstone_pillar",GILDED_SANDSTONE_PILLAR);
         createBlock("chiseled_gilded_sandstone",CHISELED_GILDED_SANDSTONE);

        // Polished Glowstone
         new StoneBlockSet(createBlock("polished_glowstone",POLISHED_GLOWSTONE), NO_STAIRS);
         createBlock("runic_glowstone",RUNIC_GLOWSTONE);

        // Osseous Bricks
        new StoneBlockSet(createBlock("osseous_bricks",OSSEOUS_BRICK));
        createBlock("osseous_pillar",OSSEOUS_PILLAR);
        createBlock("osseous_skull",OSSEOUS_SKULL);
        createBlock("lit_osseous_skull",LIT_OSSEOUS_SKULL);
        // Withered
        // Todo: Replace bone block recipe to one that uses withered bone meal if that gets in
         createBlock("withered_bone_block",WITHERED_BONE_BLOCK, ItemGroup.BUILDING_BLOCKS);
         new StoneBlockSet(createBlock("withered_osseous_bricks",WITHERED_OSSEOUS_BRICK));
         createBlock("withered_osseous_pillar",WITHERED_OSSEOUS_PILLAR);
         createBlock("withered_osseous_skull",WITHERED_OSSEOUS_SKULL);
         createBlock("lit_withered_osseous_skull",LIT_WITHERED_OSSEOUS_SKULL);
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

        //Dripstone
         new StoneBlockSet(createBlock("dripstone_bricks",DRIPSTONE_BRICKS));
         createBlock("dripstone_pillar",DRIPSTONE_PILLAR);
         createBlock("chiseled_dripstone",CHISELED_DRIPSTONE);
         createBlock("heavy_dripstone_bricks",HEAVY_DRIPSTONE_BRICKS);
         createBlock("dripstone_lamp",DRIPSTONE_LAMP);

        //Calcite
         new StoneBlockSet(createBlock("calcite_bricks",CALCITE_BRICKS));
         createBlock("calcite_pillar",CALCITE_PILLAR);
         createBlock("chiseled_calcite",CHISELED_CALCITE);
         createBlock("heavy_calcite_bricks",HEAVY_CALCITE_BRICKS);
         createBlock("calcite_lamp",CALCITE_LAMP);

        //Tuff
         new StoneBlockSet(createBlock("tuff_bricks",TUFF_BRICKS));
         createBlock("tuff_pillar",TUFF_PILLAR);
         createBlock("chiseled_tuff",CHISELED_TUFF);
         createBlock("heavy_tuff_bricks",HEAVY_TUFF_BRICKS);
         createBlock("tuff_lamp",TUFF_LAMP);

        // Heavy Stone Bricks
         createBlock("heavy_stone_bricks",HEAVY_STONE_BRICKS);
         createBlock("heavy_mossy_stone_bricks",HEAVY_MOSSY_STONE_BRICKS);
         createBlock("heavy_cracked_stone_bricks",HEAVY_CRACKED_STONE_BRICKS);

        // Entwine
         new StoneBlockSet(createBlock("entwine_block",ENTWINE),NO_WALLS);
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
         new StoneBlockSet(createBlock("twisted_planks",TWISTED_PLANKS), NO_WALLS);

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
         RegistryUtil.createBlockNoItem("potted_twisted_sapling" ,POTTED_TWISTED_SAPLING);

         //Boards
         createBlock("oak_boards", OAK_BOARDS);
         createBlock("birch_boards", BIRCH_BOARDS);
         createBlock("spruce_boards", SPRUCE_BOARDS);
         createBlock("jungle_boards", JUNGLE_BOARDS);
         createBlock("dark_oak_boards", DARK_OAK_BOARDS);
         createBlock("acacia_boards", ACACIA_BOARDS);
         createBlock("crimson_boards", CRIMSON_BOARDS);
         createBlock("warped_boards", WARPED_BOARDS);
         createBlock("twisted_boards", TWISTED_BOARDS);

        //Railings
         createBlock("oak_railing", OAK_RAILING, ItemGroup.DECORATIONS);
         createBlock("birch_railing", BIRCH_RAILING, ItemGroup.DECORATIONS);
         createBlock("spruce_railing", SPRUCE_RAILING, ItemGroup.DECORATIONS);
         createBlock("jungle_railing", JUNGLE_RAILING, ItemGroup.DECORATIONS);
         createBlock("dark_oak_railing", DARK_OAK_RAILING, ItemGroup.DECORATIONS);
         createBlock("acacia_railing", ACACIA_RAILING, ItemGroup.DECORATIONS);
         createBlock("crimson_railing", CRIMSON_RAILING, ItemGroup.DECORATIONS);
         createBlock("warped_railing", WARPED_RAILING, ItemGroup.DECORATIONS);
         createBlock("twisted_railing", TWISTED_RAILING, ItemGroup.DECORATIONS);
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
         //radioactive
        createBlock("heliodor_rod", HELIODOR_ROD);
        createBlock("ekanite_rod", EKANITE_ROD);
        createBlock("monazite_rod", MONAZITE_ROD);
        //unobtanim
        createBlock("unobtanium_block", UNOBTANIUM_BLOCK);


    }

}