package com.slomaxonical.architectspalette.registry;

import com.slomaxonical.architectspalette.ArchitectsPalette;
import com.slomaxonical.architectspalette.blocks.entrails.DrippyBlock;
import com.slomaxonical.architectspalette.blocks.flint.*;
import com.slomaxonical.architectspalette.blocks.util.APBlockSettings;
import com.slomaxonical.architectspalette.blocks.abyssaline.*;
import com.slomaxonical.architectspalette.blocks.*;
import com.slomaxonical.architectspalette.blocks.util.DirectionalFacingBlock;
import com.slomaxonical.architectspalette.registry.util.BlockSetBase;
import com.slomaxonical.architectspalette.registry.util.ChangeGroup;
import com.slomaxonical.architectspalette.registry.util.StoneBlockSet;
import com.slomaxonical.architectspalette.features.TwistedTree;
import com.slomaxonical.architectspalette.registry.util.RegistryUtil;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Field;
import java.util.*;

import static com.slomaxonical.architectspalette.ArchitectsPalette.CONFIGS;
import static com.slomaxonical.architectspalette.registry.util.RegistryUtil.*;
import static com.slomaxonical.architectspalette.registry.util.StoneBlockSet.SetComponent.*;
import static net.minecraft.block.Oxidizable.OxidationLevel.*;


public class APBlocks implements BlockRegistryContainer {

    @Override
    public void postProcessField(String namespace, Block block, String identifier, Field field) {
        if (field.isAnnotationPresent(NoBlockItem.class)) return;
        int group = 0;
        if (field.isAnnotationPresent(ChangeGroup.class)) group = field.getAnnotation(ChangeGroup.class).value();
        BlockItem blockItem = new BlockItem(block, new Item.Settings().group(ItemGroup.GROUPS[group]));
        Registry.register(Registry.ITEM, new Identifier(namespace,identifier), blockItem);
        if (!(namespace.contains("vertical") && !CONFIGS.enableVerticalSlabs())) ArchitectsPalette.ITEMGROUP_LIST.add(blockItem);
        if (field.isAnnotationPresent(BlockSetBase.class)) new StoneBlockSet(block, field.getAnnotation(BlockSetBase.class).parts());
    }
    @Override
    public void afterFieldProcessing() {
//        addOreBricks();
        List<String> ores = List.of("coal", "lapis", "redstone", "iron", "gold", "emerald", "diamond");

        for (String ore : ores) {
            StoneBlockSet set = new StoneBlockSet(createBlock(ore + "_ore_bricks", new Block(FabricBlockSettings.copy(Blocks.STONE_BRICKS))));
            Block chiseled = createBlock("chiseled_" + ore + "_ore_bricks", new Block(FabricBlockSettings.copy(Blocks.CHISELED_STONE_BRICKS)));
            Block cracked = createBlock("cracked_" + ore + "_ore_bricks", new Block(FabricBlockSettings.copy(Blocks.CRACKED_STONE_BRICKS)));
            chiseledNcrackedOres.put(set.getBase(),List.of(chiseled,cracked));
        }
    }
    // Abyssaline
    //TODO:mahogany woood
    public static final ChiseledAbyssalineBlock CHISELED_ABYSSALINE_BRICKS = new ChiseledAbyssalineBlock(FabricBlockSettings.copyOf(APBlockSettings.ABYSSALINE).luminance(ChiseledAbyssalineBlock.getLuminance()));
    public static final AbyssalineBlock ABYSSALINE                 = new AbyssalineBlock(APBlockSettings.ABYSSALINE);
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB})
    public static final AbyssalineBlock ABYSSALINE_BRICKS          = new AbyssalineBlock(APBlockSettings.ABYSSALINE);
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB})
    public static final AbyssalineBlock ABYSSALINE_TILES           = new AbyssalineBlock(APBlockSettings.ABYSSALINE);
    public static final AbyssalinePillarBlock   ABYSSALINE_PILLAR          = new AbyssalinePillarBlock(APBlockSettings.ABYSSALINE);
    public static final AbyssalineBlock ABYSSALINE_PLATING = new AbyssalineBlock(APBlockSettings.ABYSSALINE);
    public static final AbyssalineLampBlock ABYSSALINE_LAMP = new AbyssalineLampBlock(FabricBlockSettings.copyOf(ABYSSALINE).sounds(BlockSoundGroup.GLASS).luminance(AbyssalineLampBlock.getLuminance()));

    // Limestone
    @BlockSetBase
    public static final Block MYONITE = new Block(APBlockSettings.MYONITE);
    @BlockSetBase
    public static final Block MYONITE_BRICKS = new Block(APBlockSettings.MYONITE);
    @BlockSetBase
    public static final Block MUSHY_MYONITE_BRICKS = new Block(APBlockSettings.MYONITE);

    // Olivestone
    @BlockSetBase

    public static final Block OLIVESTONE_BRICKS = new Block(APBlockSettings.OLIVESTONE);
    @BlockSetBase
    public static final Block OLIVESTONE_TILES = new Block(APBlockSettings.OLIVESTONE);

    public static final Block OLIVESTONE_PILLAR         = new PillarBlock(APBlockSettings.OLIVESTONE);
    public static final Block CRACKED_OLIVESTONE_BRICKS = new Block(APBlockSettings.OLIVESTONE);
    public static final Block CRACKED_OLIVESTONE_TILES  = new Block(APBlockSettings.OLIVESTONE);
    public static final Block CHISELED_OLIVESTONE       = new Block(APBlockSettings.OLIVESTONE);
    public static final Block ILLUMINATED_OLIVESTONE    = new Block(FabricBlockSettings.copy(OLIVESTONE_BRICKS).luminance((state) -> 15));

    // Algal Brick
    @BlockSetBase
    public static final Block ALGAL_BRICKS                  = new Block(APBlockSettings.ALGAL_BRICK);
    public static final Block CRACKED_ALGAL_BRICKS          = new Block(APBlockSettings.ALGAL_BRICK);
    public static final Block CHISELED_ALGAL_BRICKS         = new Block(APBlockSettings.ALGAL_BRICK);
    @BlockSetBase
    public static final Block OVERGROWN_ALGAL_BRICKS = new Block(APBlockSettings.ALGAL_BRICK);
    public static final Block ALGAL_LAMP = new Block(FabricBlockSettings.copy(Blocks.SEA_LANTERN));
    @BlockSetBase
    public static final Block ONYX = new Block(APBlockSettings.ONYX);
    @BlockSetBase
    public static final Block ONYX_BRICKS = new Block(APBlockSettings.ONYX);
    public static final Block ONYX_PILLAR = new PillarBlock(APBlockSettings.ONYX);
    @BlockSetBase
    public static final Block ESOTERRACK = new Block(APBlockSettings.ESOTERRACK);
    @BlockSetBase
    public static final Block ESOTERRACK_BRICKS = new Block(APBlockSettings.ESOTERRACK);
    public static final Block ESOTERRACK_PILLAR = new PillarBlock(APBlockSettings.ESOTERRACK);
    // Nether Brass
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS,WALL,NUB})
    public static final Block NETHER_BRASS_BLOCK = new Block(APBlockSettings.NETHER_BRASS);
    @BlockSetBase
    public static final Block CUT_NETHER_BRASS = new Block(APBlockSettings.NETHER_BRASS);
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS})
    public static final Block SMOOTH_NETHER_BRASS = new Block(APBlockSettings.NETHER_BRASS);
    public static final Block NETHER_BRASS_PILLAR = new PillarBlock(APBlockSettings.NETHER_BRASS);
    @NoBlockItem
    public static final Block NETHER_BRASS_FIRE = new GreenFireBlock(APBlockSettings.GREEN_FIRE);
    @ChangeGroup
    public static final Block NETHER_BRASS_CHAIN = new ChainBlock(FabricBlockSettings.copyOf(APBlockSettings.NETHER_BRASS).sounds(BlockSoundGroup.CHAIN));
    @ChangeGroup
    public static final Block NETHER_BRASS_LANTERN = new LanternBlock(FabricBlockSettings.of(Material.METAL, MapColor.LIME).strength(4.0F, 10.0F).sounds(BlockSoundGroup.COPPER).requiresTool().luminance((a)->13));
    @NoBlockItem
    public static final Block NETHER_BRASS_TORCH = new TorchBlock(APBlockSettings.BRASS_TORCH, APParticles.GREEN_FLAME);
    @NoBlockItem
    public static final Block NETHER_BRASS_WALL_TORCH = new WallTorchBlock(APBlockSettings.BRASS_TORCH.dropsLike(NETHER_BRASS_TORCH), APParticles.GREEN_FLAME);

    // Sunmetal
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS,NUB})
    public static final Block SUNMETAL_BLOCK = new Block(APBlockSettings.SUNMETAL);
    public static final Block CHISELED_SUNMETAL_BLOCK = new Block(APBlockSettings.SUNMETAL);
    public static final Block SUNMETAL_PILLAR         = new PillarBlock(APBlockSettings.SUNMETAL);
    public static final Block SUNMETAL_BARS           = new PaneBlock(APBlockSettings.SUNMETAL.nonOpaque());

    // Rotten Flesh Block
    public static final Block ROTTEN_FLESH_BLOCK = new Block(APBlockSettings.Meat(MapColor.ORANGE));

    // Villager Trade blocks
    // Entrails
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS})
    public static final Block ENTRAILS = new DrippyBlock(APBlockSettings.Meat(MapColor.TERRACOTTA_PINK));
    // Funny fish blocks
    public static final Block  SALMON_LOG   = new PillarBlock(APBlockSettings.Meat(MapColor.TERRACOTTA_RED));
    public static final Block  COD_LOG = new PillarBlock(APBlockSettings.Meat(MapColor.TERRACOTTA_YELLOW));
    public static final Block  SALMON_SCALES = new PillarBlock(APBlockSettings.Meat(MapColor.TERRACOTTA_RED));
    public static final Block  COD_SCALES = new PillarBlock(APBlockSettings.Meat(MapColor.TERRACOTTA_YELLOW));
    // Plating & Piping
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS,WALL,NUB})
    public static final Block PLATING_BLOCK = new Block(APBlockSettings.PLATING);
    public static final Block PIPE = new PipeBlock(APBlockSettings.PLATING.nonOpaque());
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS,WALL,FENCE})
    public static final Block ANCIENT_PLATING = new Block(APBlockSettings.ANCIENT_PLATING);
    //Spools
    public static final Block SPOOL = new PillarBlock(FabricBlockSettings.copy(Blocks.WHITE_WOOL));

    // Scute Block
    public static final Block SCUTE_BLOCK = new Block(FabricBlockSettings.of(Material.STONE, MapColor.LIME).strength(5.0F, 6.0F).sounds(BlockSoundGroup.BASALT));

    // Polished Packed Ice
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS,WALL})
    public static final Block POLISHED_PACKED_ICE = new Block(APBlockSettings.BUILDING_ICE);
    public static final Block CHISELED_PACKED_ICE = new Block(APBlockSettings.BUILDING_ICE);
    public static final Block PACKED_ICE_PILLAR   = new PillarBlock(APBlockSettings.BUILDING_ICE);

    // Gilded Sandstone
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS})
    public static final Block GILDED_SANDSTONE = new Block(FabricBlockSettings.copy(Blocks.SANDSTONE));
    public static final Block GILDED_SANDSTONE_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.SANDSTONE));
    public static final Block CHISELED_GILDED_SANDSTONE = new Block(FabricBlockSettings.copy(Blocks.SANDSTONE));

    // Polished Glowstone
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,WALL,NUB})
    public static final Block POLISHED_GLOWSTONE = new Block(FabricBlockSettings.copy(Blocks.GLOWSTONE));
    public static final Block RUNIC_GLOWSTONE = new DirectionalFacingBlock(FabricBlockSettings.copy(Blocks.GLOWSTONE));

    // Osseous Bricks
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS,WALL})
    public static final Block OSSEOUS_BRICKS = new Block(FabricBlockSettings.copy(Blocks.BONE_BLOCK));
    public static final Block OSSEOUS_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK));
    public static final Block OSSEOUS_SKULL = new Block(FabricBlockSettings.copy(Blocks.BONE_BLOCK));
    public static final Block LIT_OSSEOUS_SKULL = new Block(FabricBlockSettings.copy(Blocks.BONE_BLOCK).luminance((state) -> 12));
    // Withered
     // Todo: Replace bone block recipe to one that uses withered bone meal if that gets in
    public static final Block WITHERED_BONE_BLOCK = new PillarBlock(FabricBlockSettings.copyOf(Blocks.BONE_BLOCK));
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS,WALL})
    public static final Block WITHERED_OSSEOUS_BRICKS = new Block(FabricBlockSettings.copy(Blocks.BONE_BLOCK));
    public static final Block WITHERED_OSSEOUS_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK));
    public static final Block WITHERED_OSSEOUS_SKULL = new Block(FabricBlockSettings.copy(Blocks.BONE_BLOCK));
    public static final Block LIT_WITHERED_OSSEOUS_SKULL = new Block(FabricBlockSettings.copy(Blocks.BONE_BLOCK).luminance((state) -> 12));

    // Wither Lamp
    public static final Block WITHER_LAMP = new Block(FabricBlockSettings.copy(Blocks.SEA_LANTERN));

    // Flint Blocks
    public static final Block FLINT_BLOCK  	= new FlintBlock(APBlockSettings.FLINT);
    @BlockSetBase
    public static final Block FLINT_TILES  	= new FlintBlock(APBlockSettings.FLINT);
    public static final Block FLINT_PILLAR 	= new FlintPillarBlock(APBlockSettings.FLINT);

    // Mossy Blackstone Variants
    public static final Block WEEPING_BLACKSTONE         = new Block(FabricBlockSettings.copy(Blocks.BLACKSTONE));
    public static final Block TWISTING_BLACKSTONE        = new Block(FabricBlockSettings.copy(Blocks.BLACKSTONE));
    public static final Block WEEPING_BLACKSTONE_BRICKS  = new Block(FabricBlockSettings.copy(Blocks.POLISHED_BLACKSTONE_BRICKS));
    public static final Block TWISTING_BLACKSTONE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.POLISHED_BLACKSTONE_BRICKS));

    // Basalt Tiles
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS,WALL})
    public static final Block BASALT_TILES = new Block(FabricBlockSettings.copy(Blocks.BASALT));
    public static final Block  CRACKED_BASALT_TILES = new Block(FabricBlockSettings.copy(Blocks.BASALT));
    public static final Block CHISELED_BASALT_TILES = new Block(FabricBlockSettings.copy(Blocks.BASALT));

    //Dripstone
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS,WALL})
    public static final Block DRIPSTONE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.DRIPSTONE_BLOCK));
    public static final Block DRIPSTONE_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.DRIPSTONE_BLOCK));
    public static final Block CHISELED_DRIPSTONE = new Block(FabricBlockSettings.copy(Blocks.DRIPSTONE_BLOCK));
    public static final Block HEAVY_DRIPSTONE_BRICKS = new BigBrickBlock(FabricBlockSettings.copy(Blocks.DRIPSTONE_BLOCK), BigBrickBlock.BrickType.DRIPSTONE);
    public static final Block DRIPSTONE_LAMP = new Block(FabricBlockSettings.copy(Blocks.DRIPSTONE_BLOCK).luminance((state)->8));

    //Calcite
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS,WALL})
    public static final Block CALCITE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.CALCITE));
    public static final Block CALCITE_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.CALCITE));
    public static final Block CHISELED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE));
    public static final Block HEAVY_CALCITE_BRICKS = new BigBrickBlock(FabricBlockSettings.copy(Blocks.CALCITE), BigBrickBlock.BrickType.CALCITE);
    public static final Block CALCITE_LAMP = new Block(FabricBlockSettings.copy(Blocks.CALCITE).luminance((state)->8));

    //Tuff
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS,WALL})
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
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS})
    public static final Block ENTWINE_BLOCK = new Block(APBlockSettings.ENTWINE);
    public static final Block ENTWINE_PILLAR = new PillarBlock(APBlockSettings.ENTWINE);
    public static final Block CHISELED_ENTWINE = new Block(APBlockSettings.ENTWINE);
    public static final Block ENTWINE_BARS = new PaneBlock(FabricBlockSettings.copy(ENTWINE_BLOCK).nonOpaque());
    // Ender Pearl Block
    public static final Block ENDER_PEARL_BLOCK = new Block(APBlockSettings.ENDER_PEARL);

    // End Stone Variants
    public static final Block   CHORAL_END_STONE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.END_STONE_BRICKS));
    public static final Block  CRACKED_END_STONE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.END_STONE_BRICKS));
    public static final Block CHISELED_END_STONE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.END_STONE_BRICKS));
    //wardstone
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS,WALL})
    public static final Block WARDSTONE = new Block(APBlockSettings.WARDSTONE);
    public static final Block CHISELED_WARDSTONE = new Block(APBlockSettings.WARDSTONE);
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS,WALL})
    public static final Block WARDSTONE_BRICKS = new Block(APBlockSettings.WARDSTONE);
    public static final Block WARDSTONE_PILLAR = new PillarBlock(APBlockSettings.WARDSTONE);
    public static final Block WARDSTONE_LAMP = new Block(FabricBlockSettings.copy(WARDSTONE).luminance((state)->14));
    // Heavy End Stone Bricks
    public static final Block HEAVY_END_STONE_BRICKS = new BigBrickBlock(FabricBlockSettings.copy(Blocks.END_STONE_BRICKS), BigBrickBlock.BrickType.END_STONE);
    public static final Block HEAVY_CRACKED_END_STONE_BRICKS = new BigBrickBlock(FabricBlockSettings.copy(Blocks.END_STONE_BRICKS), BigBrickBlock.BrickType.END_STONE);

    // Warpstone
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS,WALL})
    public static final Block WARPSTONE = new Block(FabricBlockSettings.copy(Blocks.STONE));

    // Twisted Wood
     // Todo: Bookshelf, sign(?), boat(?)
    @BlockSetBase(parts = {SLAB,VERTICAL_SLAB,STAIRS})
    public static final Block TWISTED_PLANKS = new Block(APBlockSettings.TwistedWood());

    public static final Block           TWISTED_LOG = new PillarBlock(APBlockSettings.TwistedWood());
    public static final Block          TWISTED_WOOD = new PillarBlock(APBlockSettings.TwistedWood());
    public static final Block  STRIPPED_TWISTED_LOG = new PillarBlock(APBlockSettings.TwistedWood());
    public static final Block STRIPPED_TWISTED_WOOD = new PillarBlock(APBlockSettings.TwistedWood());
    public static final Block        TWISTED_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).mapColor(MapColor.PURPLE));
    @ChangeGroup
    public static final Block         TWISTED_FENCE = new FenceBlock(APBlockSettings.TwistedWood());
    @ChangeGroup
    public static final Block    TWISTED_FENCE_GATE = new FenceGateBlock(APBlockSettings.TwistedWood());
    @ChangeGroup(2)
    public static final Block          TWISTED_DOOR = new DoorBlock(APBlockSettings.TwistedWood().nonOpaque());
    @ChangeGroup(2)
    public static final Block      TWISTED_TRAPDOOR = new TrapdoorBlock(APBlockSettings.TwistedWood().nonOpaque());
    @ChangeGroup(2)
    public static final Block        TWISTED_BUTTON = new WoodenButtonBlock(APBlockSettings.TwistedWood(true));
    @ChangeGroup(2)
    public static final Block TWISTED_PRESSURE_PLATE = new PressurePlateBlock((PressurePlateBlock.ActivationRule.EVERYTHING), APBlockSettings.TwistedWood(true));


    @ChangeGroup
    public static final Block TWISTED_SAPLING = new SaplingBlock(new TwistedTree(), FabricBlockSettings.copy(Blocks.OAK_SAPLING));
    @NoBlockItem
    public static final Block POTTED_TWISTED_SAPLING = RegistryUtil.createPottedPlant(TWISTED_SAPLING);
    //unobtanium
    public static final Block UNOBTANIUM_BLOCK = new Block(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK));
    //SmallSigns
    @ChangeGroup
    public static final Block HAZARD_SIGN = new SmallPanelBlock(APBlockSettings.PLATING);
    //NoSetNub
    @ChangeGroup
    public static final Block STONE_NUB = makeNubOf(Blocks.STONE);
    @ChangeGroup
    public static final Block SMOOTH_STONE_NUB = makeNubOf(Blocks.SMOOTH_STONE);
    @ChangeGroup
    public static final Block SANDSTONE_NUB = makeNubOf(Blocks.SANDSTONE,Blocks.CUT_SANDSTONE);
    @ChangeGroup
    public static final Block ANDESITE_NUB = makeNubOf(Blocks.ANDESITE,Blocks.POLISHED_ANDESITE);
    @ChangeGroup
    public static final Block GRANITE_NUB = makeNubOf(Blocks.GRANITE,Blocks.POLISHED_GRANITE);
    @ChangeGroup
    public static final Block DIORITE_NUB = makeNubOf(Blocks.DIORITE,Blocks.POLISHED_DIORITE);
    @ChangeGroup
    public static final Block BLACKSTONE_NUB = makeNubOf(Blocks.BLACKSTONE,Blocks.POLISHED_BLACKSTONE);
    @ChangeGroup
    public static final Block DEEPSLATE_NUB = makeNubOf(Blocks.POLISHED_DEEPSLATE,Blocks.DEEPSLATE);
    @ChangeGroup
    public static final Block BONE_NUB = makeNubOf(Blocks.BONE_BLOCK);
    @ChangeGroup
    public static final Block IRON_NUB = makeNubOf(Blocks.IRON_BLOCK,List.of(Items.IRON_INGOT));
    @ChangeGroup
    public static final Block GOLD_NUB = makeNubOf(Blocks.GOLD_BLOCK,List.of(Items.GOLD_INGOT));
    @ChangeGroup
    public static final Block DIAMOND_NUB = makeNubOf(Blocks.DIAMOND_BLOCK,List.of(Items.DIAMOND));
    @ChangeGroup
    public static final Block EMERALD_NUB = makeNubOf(Blocks.EMERALD_BLOCK,List.of(Items.EMERALD));
    @ChangeGroup
    public static final Block NETHERITE_NUB = makeNubOf(Blocks.NETHERITE_BLOCK,List.of(Items.NETHERITE_INGOT));
    @ChangeGroup
//    public static final Block NUB_OF_ENDER = new NubBlock(FabricBlockSettings.copyOf(ENDER_PEARL_BLOCK));
    public static final Block NUB_OF_ENDER = makeNubOf(ENDER_PEARL_BLOCK,List.of(Items.ENDER_EYE)); //cant remember why im not using the make method
        //copper
    @ChangeGroup
    public static final Block OXIDIZED_COPPER_NUB = makeCopperNub(OXIDIZED,Blocks.OXIDIZED_COPPER);
    @ChangeGroup
    public static final Block WEATHERED_COPPER_NUB = makeCopperNub(WEATHERED,Blocks.WEATHERED_COPPER);
    @ChangeGroup
    public static final Block EXPOSED_COPPER_NUB = makeCopperNub(EXPOSED,Blocks.EXPOSED_COPPER);
    @ChangeGroup
    public static final Block COPPER_NUB = makeCopperNub(UNAFFECTED,Blocks.COPPER_BLOCK);
    @ChangeGroup
    public static final Block WAXED_COPPER_NUB = makeCopperNub(UNAFFECTED,Blocks.COPPER_BLOCK);
    @ChangeGroup
    public static final Block WAXED_EXPOSED_COPPER_NUB = makeCopperNub(EXPOSED,Blocks.EXPOSED_COPPER);
    @ChangeGroup
    public static final Block WAXED_WEATHERED_COPPER_NUB = makeCopperNub(WEATHERED,Blocks.WEATHERED_COPPER);
    @ChangeGroup
    public static final Block WAXED_OXIDIZED_COPPER_NUB = makeCopperNub(OXIDIZED,Blocks.OXIDIZED_COPPER);
    //Boards
    public static final Block OAK_BOARDS = new BoardBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS));
    public static final Block BIRCH_BOARDS = new BoardBlock(FabricBlockSettings.copy(Blocks.BIRCH_PLANKS));
    public static final Block SPRUCE_BOARDS = new BoardBlock(FabricBlockSettings.copy(Blocks.SPRUCE_PLANKS));
    public static final Block JUNGLE_BOARDS = new BoardBlock(FabricBlockSettings.copy(Blocks.JUNGLE_PLANKS));
    public static final Block DARK_OAK_BOARDS = new BoardBlock(FabricBlockSettings.copy(Blocks.DARK_OAK_PLANKS));
    public static final Block ACACIA_BOARDS = new BoardBlock(FabricBlockSettings.copy(Blocks.ACACIA_PLANKS));
    public static final Block MANGROVE_BOARDS = new BoardBlock(FabricBlockSettings.copy(Blocks.MANGROVE_PLANKS));
    public static final Block CRIMSON_BOARDS = new BoardBlock(FabricBlockSettings.copy(Blocks.CRIMSON_PLANKS));
    public static final Block WARPED_BOARDS = new BoardBlock(FabricBlockSettings.copy(Blocks.WARPED_PLANKS));
    public static final Block TWISTED_BOARDS = new BoardBlock(APBlockSettings.TwistedWood());
    //Railings
    @ChangeGroup
    public static final Block OAK_RAILING = new RailingBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS));
    @ChangeGroup
    public static final Block BIRCH_RAILING = new RailingBlock(FabricBlockSettings.copy(Blocks.BIRCH_PLANKS));
    @ChangeGroup
    public static final Block SPRUCE_RAILING = new RailingBlock(FabricBlockSettings.copy(Blocks.SPRUCE_PLANKS));
    @ChangeGroup
    public static final Block JUNGLE_RAILING = new RailingBlock(FabricBlockSettings.copy(Blocks.JUNGLE_PLANKS));
    @ChangeGroup
    public static final Block DARK_OAK_RAILING = new RailingBlock(FabricBlockSettings.copy(Blocks.DARK_OAK_PLANKS));
    @ChangeGroup
    public static final Block ACACIA_RAILING = new RailingBlock(FabricBlockSettings.copy(Blocks.ACACIA_PLANKS));
    @ChangeGroup
    public static final Block MANGROVE_RAILING = new RailingBlock(FabricBlockSettings.copyOf(Blocks.MANGROVE_PLANKS));
    @ChangeGroup
    public static final Block CRIMSON_RAILING = new RailingBlock(FabricBlockSettings.copy(Blocks.CRIMSON_PLANKS));
    @ChangeGroup
    public static final Block WARPED_RAILING = new RailingBlock(FabricBlockSettings.copy(Blocks.WARPED_PLANKS));
    @ChangeGroup
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
    @ChangeGroup(2)
    public static final Block REDSTONE_CAGE_LANTERN  = new CageLanternBlock(APBlockSettings.CAGE_LANTERN, 3);
    @ChangeGroup(2)
    public static final Block GLOWSTONE_CAGE_LANTERN = new CageLanternBlock(APBlockSettings.CAGE_LANTERN, 3);
    @ChangeGroup(2)
    public static final Block ALGAL_CAGE_LANTERN     = new CageLanternBlock(APBlockSettings.CAGE_LANTERN, 3);

    // Acacia Totems
    @ChangeGroup
    public static final TotemWingBlock ACACIA_TOTEM_WING = new TotemWingBlock(FabricBlockSettings.copy(Blocks.ACACIA_PLANKS).nonOpaque().dropsNothing().sounds(BlockSoundGroup.SCAFFOLDING).noCollision());
    public static final Block GRINNING_ACACIA_TOTEM = new TotemBlock(APBlockSettings.ACACIA_TOTEM, ACACIA_TOTEM_WING, TotemBlock.TotemFace.GRINNING);
    public static final Block PLACID_ACACIA_TOTEM = new TotemBlock(APBlockSettings.ACACIA_TOTEM, ACACIA_TOTEM_WING, TotemBlock.TotemFace.PLACID);
    public static final Block SHOCKED_ACACIA_TOTEM = new TotemBlock(APBlockSettings.ACACIA_TOTEM, ACACIA_TOTEM_WING, TotemBlock.TotemFace.SHOCKED);
    public static final Block BLANK_ACACIA_TOTEM = new TotemBlock(APBlockSettings.ACACIA_TOTEM, ACACIA_TOTEM_WING, TotemBlock.TotemFace.BLANK);

    //Radioactive Crystals
    public static final Block HELIODOR_ROD = new GlassLikePillarBlock(APBlockSettings.NETHER_CRYSTAL);
    public static final Block EKANITE_ROD = new GlassLikePillarBlock(APBlockSettings.NETHER_CRYSTAL);
    public static final Block MONAZITE_ROD = new GlassLikePillarBlock(APBlockSettings.NETHER_CRYSTAL);
}