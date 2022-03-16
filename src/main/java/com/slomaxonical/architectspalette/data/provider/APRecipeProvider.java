package com.slomaxonical.architectspalette.data.provider;

import com.slomaxonical.architectspalette.blocks.util.StoneBlockSet;
import com.slomaxonical.architectspalette.crafting.WarpingRecipeJsonBuilder;
import com.slomaxonical.architectspalette.registry.ConfigResourceCondition;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

import static com.slomaxonical.architectspalette.registry.APBlocks.*;
import static com.slomaxonical.architectspalette.registry.APItems.*;

public class APRecipeProvider extends FabricRecipeProvider {

    private final Identifier NETHER = new Identifier("the_nether");

    public APRecipeProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        Consumer<RecipeJsonProvider> verticaExport =  withConditions(exporter, ConfigResourceCondition.configEnabeled("enableVerticalSlabs"));
        //Warping
        offerWarpingRecipe(exporter, Items.CLAY, WARPSTONE.asItem(), NETHER);
        offerWarpingRecipe(exporter, ItemTags.SAPLINGS, TWISTED_SAPLING.asItem(), NETHER);
        offerWarpingRecipe(exporter, ItemTags.LOGS, TWISTED_LOG.asItem(), NETHER);
        offerWarpingRecipe(exporter, ItemTags.PLANKS, TWISTED_PLANKS.asItem(), NETHER);
        offerWarpingRecipe(exporter, ItemTags.LEAVES, TWISTED_LEAVES.asItem(), NETHER);
        offerWarpingRecipe(exporter, SUNSTONE.asItem(), MOONSTONE.asItem(), NETHER);
        offerWarpingRecipe(exporter, ROTTEN_FLESH_BLOCK.asItem(), ENTRAILS.asItem(), NETHER);
    //Setts
        offerSetRecipes(exporter,verticaExport, MYONITE_SET);
        offerSetRecipes(exporter,verticaExport, MYONITE_BRICK_SET);
        offerSetRecipes(exporter,verticaExport, MUSHY_MYONITE_BRICK_SET);
        offerSetRecipes(exporter,verticaExport, OLIVESTONE_BRICK_SET);
        offerSetRecipes(exporter,verticaExport, OLIVESTONE_TILES_SET);

        ShapedRecipeJsonBuilder.create(CHISELED_OLIVESTONE).input('#', APItemTagProvider.OLIVESTONE).pattern("#").pattern("#").criterion("has_olivstone",conditionsFromTag(APItemTagProvider.OLIVESTONE)).offerTo(exporter);

        offerSetRecipes(exporter,verticaExport, ALGAL_BRICK_SET, null, CHISELED_ALGAL_BRICKS);
        offerSetRecipes(exporter,verticaExport, OVERGROWN_ALGAL_BRICK_SET);
        offerSetRecipes(exporter,verticaExport, SUNMETAL_SET, false, SUNMETAL_PILLAR, CHISELED_SUNMETAL_BLOCK);
        offerSetRecipes(exporter,verticaExport, PLATING_SET);
        offerSetRecipes(exporter,verticaExport, POLISHED_PACKED_ICE_SET, PACKED_ICE_PILLAR, CHISELED_PACKED_ICE);
        offerSetRecipes(exporter,verticaExport, GILDED_SANDSTONE_SET, false, GILDED_SANDSTONE_PILLAR, CHISELED_GILDED_SANDSTONE);
        offerSlabsRecipes(exporter,verticaExport, POLISHED_GLOWSTONE_SET.SLAB, POLISHED_GLOWSTONE_SET.VERTICAL_SLAB, POLISHED_GLOWSTONE_SET.BLOCK);

        offerPillarRecipe(exporter, RUNIC_GLOWSTONE, POLISHED_GLOWSTONE_SET.SLAB);

        offerSetRecipes(exporter,verticaExport, OSSEOUS_BRICK_SET, OSSEOUS_PILLAR, OSSEOUS_SKULL);
        offerSetRecipes(exporter,verticaExport, WITHERED_OSSEOUS_BRICK_SET, WITHERED_OSSEOUS_PILLAR, WITHERED_OSSEOUS_SKULL);
        offerSetRecipes(exporter,verticaExport, FLINT_TILES_SET);

        offerPillarRecipe(exporter, FLINT_PILLAR, FLINT_BLOCK);

        offerSetRecipes(exporter,verticaExport, BASALT_TILES_SET,null, CHISELED_BASALT_TILES);
        offerSetRecipes(exporter,verticaExport, DRIPSTONE_BRICK_SET, DRIPSTONE_PILLAR, CHISELED_DRIPSTONE);
        offerPolishedStoneRecipe(exporter, DRIPSTONE_BRICKS, Items.DRIPSTONE_BLOCK);

        offerSetRecipes(exporter,verticaExport, CALCITE_BRICK_SET, CALCITE_PILLAR, CHISELED_CALCITE);
        offerSetRecipes(exporter,verticaExport, TUFF_BRICK_SET, TUFF_PILLAR, CHISELED_TUFF);
        offerSetRecipes(exporter,verticaExport, ENTWINE_SET, false, ENTWINE_PILLAR, CHISELED_ENTWINE);
        offerSetRecipes(exporter,verticaExport, WARPSTONE_SET);
        offerSetRecipes(exporter,verticaExport, TWISTED_PLANKS_SET, false,null,null);


        //Abyssaline
        ShapedRecipeJsonBuilder.create(ABYSSALINE).input('#', Items.PRISMARINE_SHARD).input('$', Items.OBSIDIAN).pattern("$#$").pattern("# #").pattern("$#$").criterion("has_obsidian",conditionsFromItem(Items.OBSIDIAN)).offerTo(exporter);
        offerPolishedStoneRecipe(exporter, ABYSSALINE_BRICKS, ABYSSALINE);
        offerPolishedStoneRecipe(exporter, ABYSSALINE_TILES, ABYSSALINE_BRICKS);
        createCondensingRecipe(ABYSSALINE_BRICKS, Ingredient.ofItems(ABYSSALINE_TILES)).criterion("has_abyssaline_tiles", conditionsFromItem(ABYSSALINE_TILES)).offerTo(exporter,"abyssaline_bricks_from_tiles");
        offerSlabsRecipes(exporter, verticaExport, ABYSSALINE_BRICK_SLAB, ABYSSALINE_BRICK_VERTICAL_SLAB, ABYSSALINE_BRICKS);
        offerSlabsRecipes(exporter, verticaExport, ABYSSALINE_TILE_SLAB, ABYSSALINE_TILE_VERTICAL_SLAB, ABYSSALINE_TILES);
        offerPillarRecipe(exporter,ABYSSALINE_PILLAR, ABYSSALINE_BRICKS);
        ShapedRecipeJsonBuilder.create(ABYSSALINE_LAMP_BLOCK, 8).input('#', Items.PRISMARINE_SHARD).input('$', Items.OBSIDIAN).input('C',Items.PRISMARINE_CRYSTALS).pattern("$C$").pattern("#C#").pattern("$C$").criterion("has_obsidian",conditionsFromItem(Items.OBSIDIAN)).offerTo(exporter);
        offerChiseledBlockRecipe(exporter,CHISELED_ABYSSALINE_BRICKS, ABYSSALINE_BRICK_SLAB);
        //boards
        offerBoardsRecipe(exporter, ACACIA_BOARDS, Items.ACACIA_PLANKS);
        offerBoardsRecipe(exporter, BIRCH_BOARDS, Items.BIRCH_PLANKS);
        offerBoardsRecipe(exporter, OAK_BOARDS, Items.OAK_PLANKS);
        offerBoardsRecipe(exporter, SPRUCE_BOARDS, Items.SPRUCE_PLANKS);
        offerBoardsRecipe(exporter, DARK_OAK_BOARDS, Items.DARK_OAK_PLANKS);
        offerBoardsRecipe(exporter, JUNGLE_BOARDS, Items.JUNGLE_PLANKS);
        offerBoardsRecipe(exporter, CRIMSON_BOARDS, Items.CRIMSON_PLANKS);
        offerBoardsRecipe(exporter, WARPED_BOARDS, Items.WARPED_PLANKS);
        offerBoardsRecipe(exporter, TWISTED_BOARDS, TWISTED_PLANKS);
        //Railings
        offerRailingsRecipe(exporter, ACACIA_RAILING, Items.ACACIA_PLANKS);
        offerRailingsRecipe(exporter, BIRCH_RAILING, Items.BIRCH_PLANKS);
        offerRailingsRecipe(exporter, OAK_RAILING, Items.OAK_PLANKS);
        offerRailingsRecipe(exporter, SPRUCE_RAILING, Items.SPRUCE_PLANKS);
        offerRailingsRecipe(exporter, DARK_OAK_RAILING, Items.DARK_OAK_PLANKS);
        offerRailingsRecipe(exporter, JUNGLE_RAILING, Items.JUNGLE_PLANKS);
        offerRailingsRecipe(exporter, CRIMSON_RAILING, Items.CRIMSON_PLANKS);
        offerRailingsRecipe(exporter, WARPED_RAILING, Items.WARPED_PLANKS);
        offerRailingsRecipe(exporter, TWISTED_RAILING, TWISTED_PLANKS);
        //Algal
        ShapelessRecipeJsonBuilder.create(ALGAL_BLEND,2).input(Items.CLAY_BALL).input(Items.KELP).criterion(hasItem(Items.CLAY_BALL),conditionsFromItem(Items.CLAY_BALL)).offerTo(exporter);
        offerSmelting(exporter, List.of(ALGAL_BLEND),ALGAL_BRICK,0.3f, 200, null);
        offerCondensingRecipe(exporter,ALGAL_BRICKS, ALGAL_BRICK,1);
        ShapedRecipeJsonBuilder.create(ALGAL_CAGE_LANTERN).input('#', ALGAL_BRICK).input('p', Items.GLASS_PANE).input('d', Items.GLOWSTONE_DUST).pattern(" p ").pattern("#d#").criterion(hasItem(ALGAL_BRICK), conditionsFromItem(ALGAL_BRICK)).offerTo(exporter);
        //basalt
        offerPolishedStoneRecipe(exporter, BASALT_TILES, Items.POLISHED_BASALT);
        offerShapelessRecipe(exporter, Items.BONE_MEAL, WITHERED_BONE,null,3);
        //calcite
        offerPolishedStoneRecipe(exporter, CALCITE_BRICKS, Items.CALCITE);
        //lamps
        ShapedRecipeJsonBuilder.create(ALGAL_LAMP).input('#', Items.GLOWSTONE_DUST).input('A',ALGAL_BRICK).pattern("A#A").pattern("###").pattern("A#A").criterion("has_algal_brick", RecipeProvider.conditionsFromItem(ALGAL_BRICK)).offerTo(exporter);
        offerLampRecipe(exporter,DRIPSTONE_LAMP, Items.DRIPSTONE_BLOCK, Items.AMETHYST_SHARD);
        offerLampRecipe(exporter,CALCITE_LAMP, Items.CALCITE, Items.GLOW_INK_SAC);
        offerLampRecipe(exporter,TUFF_LAMP, Items.TUFF, Items.GLOW_LICHEN);
        //endStone
        offerChiseledBlockRecipe(exporter,CHISELED_END_STONE_BRICKS, Items.END_STONE_BRICK_SLAB);
        ShapelessRecipeJsonBuilder.create(CHORAL_END_STONE_BRICKS).input(Ingredient.ofItems(Items.END_STONE_BRICKS,Items.CHORUS_FRUIT)).criterion(hasItem(Items.END_STONE_BRICKS),conditionsFromItem(Items.END_STONE_BRICKS));
        //Myonite
        ShapedRecipeJsonBuilder.create(MYONITE, 8).input('#',Items.STONE).input('$', APItemTagProvider.MUSHROOMS).pattern("###").pattern("#$#").pattern("###").criterion("has_mushroom",conditionsFromTag(APItemTagProvider.MUSHROOMS)).offerTo(exporter);
        offerPolishedStoneRecipe(exporter, MYONITE_BRICKS, MYONITE);
        ShapedRecipeJsonBuilder.create(MUSHY_MYONITE_BRICK, 2).input('#',MYONITE_BRICKS).input('$', APItemTagProvider.MUSHROOMS).pattern("#$").pattern("$#").criterion("has_mushroom",conditionsFromTag(APItemTagProvider.MUSHROOMS)).offerTo(exporter);
        //heavy
        offerHeavyRecipe(exporter,HEAVY_CALCITE_BRICKS,Items.CALCITE);
        offerHeavyRecipe(exporter,HEAVY_DRIPSTONE_BRICKS,Items.DRIPSTONE_BLOCK);
        offerHeavyRecipe(exporter,HEAVY_TUFF_BRICKS,Items.TUFF);
        offerHeavyRecipe(exporter,HEAVY_END_STONE_BRICKS,Items.END_STONE);
        offerHeavyRecipe(exporter,HEAVY_STONE_BRICKS,Items.STONE);
        offerShapelessRecipe(exporter,1,HEAVY_MOSSY_STONE_BRICKS,HEAVY_STONE_BRICKS,Items.VINE);
        //misc
        offerPolishedStoneRecipe(exporter, CHARCOAL_BLOCK, Items.CHARCOAL);
        offerSingleOutputShapelessRecipe(exporter, Items.CHARCOAL, CHARCOAL_BLOCK,null);
        offerMiniXRecipe(exporter,COARSE_SNOW, Items.SNOW_BLOCK,Items.GRAVEL,4);
        offerCondensingRecipe(exporter, COD_LOG, Items.COD,6);
        offerCondensingRecipe(exporter, COD_SCALES, COD_LOG,3);
        offerCondensingRecipe(exporter, ENDER_PEARL_BLOCK, Items.ENDER_PEARL,1);
        offerShapelessRecipe(exporter,Items.ENDER_PEARL,ENDER_PEARL_BLOCK,null,4);
        offerSlabsRecipes(exporter,verticaExport,ENTRAILS_SLAB,ENTRAILS_VERTICAL_SLAB,ENTRAILS);
        offerStairsRecipe(exporter,ENTRAILS_STAIRS,ENTRAILS);
        offerPolishedStoneRecipe(exporter,ENTWINE,ENTWINE_ROD);
        offerBarsRecipe(exporter,ENTWINE_BARS,ENTWINE_ROD);
        ShapedRecipeJsonBuilder.create(ENTWINE_ROD, 2).input('#',Items.ENDER_PEARL).input('$', Items.IRON_NUGGET).pattern("#$#").criterion(hasItem(Items.ENDER_PEARL),conditionsFromItem(Items.ENDER_PEARL)).offerTo(exporter);
        offerPolishedStoneRecipe(exporter,FLINT_BLOCK,Items.FLINT);
        ShapedRecipeJsonBuilder.create(GRINNING_ACACIA_TOTEM, 2).input('#',Items.ACACIA_LOG).input('$', Items.STICK).pattern("$#$").pattern(" # ").criterion(hasItem(Items.ACACIA_LOG),conditionsFromItem(Items.ACACIA_LOG)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ILLUMINATED_OLIVESTONE, 2).input('#', APItemTagProvider.OLIVESTONE).input('$',Items.GLOWSTONE_DUST).pattern("#$").pattern("$#").criterion("has_olivestone", conditionsFromTag(APItemTagProvider.OLIVESTONE)).offerTo(exporter);
        offerMiniXRecipe(exporter,MOLTEN_NETHER_BRICKS,Items.MAGMA_BLOCK,Items.NETHER_BRICKS,4);
        //skulls
        offerShapelessRecipe(exporter,1,LIT_OSSEOUS_SKULL,OSSEOUS_SKULL,Items.TORCH);
        offerShapelessRecipe(exporter,1,LIT_WITHERED_OSSEOUS_SKULL,WITHERED_OSSEOUS_SKULL,Items.SOUL_TORCH);
    }
    public static void offerWarpingRecipe(Consumer<RecipeJsonProvider> exporter, Item input, Item output, Identifier dimension) {
        WarpingRecipeJsonBuilder.create(Ingredient.ofItems(input), output, dimension).offerTo(exporter, "warping/"+RecipeProvider.convertBetween(output,input)+"_warping");
    }
    public static void offerWarpingRecipe(Consumer<RecipeJsonProvider> exporter, TagKey<Item> input, Item output, Identifier dimension) {
        WarpingRecipeJsonBuilder.create(Ingredient.fromTag(input), output, dimension).offerTo(exporter, "warping/"+ RecipeProvider.getItemPath(output) + "_from_" + input.id().getPath() +"_warping");
    }
    public static void offerStonecuttingRecipe(Consumer<RecipeJsonProvider> exporter, int count, ItemConvertible output, ItemConvertible... inputs){
        SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(inputs), output, count).criterion(RecipeProvider.hasItem(inputs[0]), RecipeProvider.conditionsFromItem(inputs[0])).offerTo(exporter, "stonecutting/"+output.asItem().toString() + "_stonecutting");
    }
    public static void offerShapelessRecipe(Consumer<RecipeJsonProvider> exporter, int count, ItemConvertible output, ItemConvertible... inputs){
        ShapelessRecipeJsonBuilder.create(output,count).input(Ingredient.ofItems(inputs)).criterion(RecipeProvider.hasItem(inputs[0]), RecipeProvider.conditionsFromItem(inputs[0])).offerTo(exporter, output.asItem().toString() + "_shapeless");
    }
    public static void offerBoardsRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(output, 9).input('#', input).pattern("###").pattern("###").pattern("###").group("boards").criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }
    public static void offerRailingsRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(output, 9).input('#', input).input('|', Items.STICK).pattern("#|#").group("boards").criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }
    public static void offerLampRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible base, ItemConvertible core) {
        ShapedRecipeJsonBuilder.create(output,2).input('#',base).input('$', core).pattern(" # ").pattern("#$#").pattern(" # ").criterion(hasItem(base), RecipeProvider.conditionsFromItem(base)).offerTo(exporter);
    }
    public static void offerHeavyRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(output,9).input('#', input).pattern("###").pattern("###").pattern("###").criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input)).offerTo(exporter);
    }
    public static void offerEmptyFrameRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible surround , int count){
        ShapedRecipeJsonBuilder.create(output, count).input('#', surround).pattern("###").pattern("# #").pattern("###").criterion(hasItem(surround), conditionsFromItem(surround)).offerTo(exporter);
    }
    public static void  offerFramedRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible center,ItemConvertible around , int count){
        ShapedRecipeJsonBuilder.create(output, count).input('#', around).input('$',center).pattern("###").pattern("#$#").pattern("###").criterion(hasItem(around), conditionsFromItem(around)).offerTo(exporter);
    }
    public static void offerMiniXRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input,ItemConvertible input2 , int count){
        ShapedRecipeJsonBuilder.create(output, count).input('#', input).input('$',input2).pattern("#$").pattern("$#").criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }
    public static void offerCondensingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input, int count){
        ShapedRecipeJsonBuilder.create(output, count).input('#', input).pattern("##").pattern("##").criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }
    public static void offerPillarRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input){
        ShapedRecipeJsonBuilder.create(output, 2).input('#', input).pattern("#").pattern("#").criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }
    public static void offerStairsRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input){
        createStairsRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input),conditionsFromItem(input)).offerTo(exporter);
    }
    public static void offerBarsRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input){
        ShapedRecipeJsonBuilder.create(output, 16).input('#', input).pattern("###").pattern("###").criterion(hasItem(input), RecipeProvider.conditionsFromItem(input)).offerTo(exporter);
    }

    public static void offerSetRecipes(Consumer<RecipeJsonProvider> exporter, Consumer<RecipeJsonProvider> vertExporter, StoneBlockSet blockSet){
        offerSetRecipes(exporter, vertExporter, blockSet, null, null);
    }
    public static void offerSetRecipes(Consumer<RecipeJsonProvider> exporter, Consumer<RecipeJsonProvider> vertExporter, StoneBlockSet blockSet, @Nullable Block pillar, @Nullable Block chiseled){
        offerSetRecipes(exporter, vertExporter, blockSet, true, pillar, chiseled);
    }
    public static void offerSetRecipes(Consumer<RecipeJsonProvider> exporter, Consumer<RecipeJsonProvider> vertExporter, StoneBlockSet blockSet, boolean wall, @Nullable Block pillar, @Nullable Block chiseled){
        offerStairsRecipe(exporter,blockSet.STAIRS, blockSet.BLOCK);
        offerSlabsRecipes(exporter,vertExporter,blockSet.SLAB, blockSet.VERTICAL_SLAB, blockSet.BLOCK);
        if (wall) offerWallRecipe(exporter, blockSet.WALL, blockSet.BLOCK);
        if (pillar != null) offerPillarRecipe(exporter, pillar, blockSet.BLOCK);
        if (chiseled != null) offerChiseledBlockRecipe(exporter, chiseled, blockSet.SLAB);

    }
    public static void offerSlabsRecipes(Consumer<RecipeJsonProvider> exporter, Consumer<RecipeJsonProvider> vertExporter, ItemConvertible slab, ItemConvertible vertical, ItemConvertible input){
        offerSlabRecipe( exporter, slab, input);
        offerVerticalSlabRecipes(vertExporter, vertical, slab);
    }
    public static void offerVerticalSlabRecipes(Consumer<RecipeJsonProvider> vertExporter, ItemConvertible output, ItemConvertible input){
        ShapedRecipeJsonBuilder.create(output,3).input('#', input).pattern("#").pattern("#").pattern("#").criterion("has_slab",conditionsFromItem(input)).offerTo(vertExporter,"vertical/"+output.asItem().toString());
        createTransmutationRecipe(input, Ingredient.ofItems(output)).criterion(hasItem(input),conditionsFromItem(input)). offerTo(vertExporter,"vertical/vertical_to_"+input.asItem().toString());
    }
}
