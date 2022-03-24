package com.slomaxonical.architectspalette.data.provider;

import com.slomaxonical.architectspalette.blocks.util.StoneBlockSet;
import com.slomaxonical.architectspalette.registry.APBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;

import java.util.stream.Stream;

public class APBlockLootTableProvider extends FabricBlockLootTableProvider {
    public APBlockLootTableProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    public void dropSlabWithSilkTouch(Block drop) {
         this.addDrop(drop, LootTable.builder().pool(LootPool.builder()
                .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create()
                        .enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1)))))
                .rolls(ConstantLootNumberProvider.create(1.0f))
                .with(applyExplosionDecay(drop, ItemEntry.builder(drop)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))
                                .conditionally(BlockStatePropertyLootCondition.builder((Block) drop)
                                        .properties(StatePredicate.Builder.create()
                                                .exactMatch(SlabBlock.TYPE, SlabType.DOUBLE))))))));
    }
    @Override
    protected void generateBlockLootTables() {
        Stream.of(
                APBlocks.ABYSSALINE,
                APBlocks.ABYSSALINE_BRICKS,
                APBlocks.ABYSSALINE_LAMP_BLOCK,
                APBlocks.ABYSSALINE_PILLAR,
                APBlocks.ABYSSALINE_TILES,
                APBlocks.ACACIA_BOARDS,
                APBlocks.ACACIA_RAILING,
                APBlocks.ACACIA_TOTEM_WING,
                APBlocks.ALGAL_BRICK_SET.BLOCK,
                APBlocks.ALGAL_BRICK_SET.STAIRS,
                APBlocks.ALGAL_BRICK_SET.WALL,
                APBlocks.ALGAL_CAGE_LANTERN,
                APBlocks.ALGAL_LAMP,
                APBlocks.BASALT_TILES_SET.BLOCK,
                APBlocks.BASALT_TILES_SET.STAIRS,
                APBlocks.BASALT_TILES_SET.WALL,
                APBlocks.BIRCH_BOARDS,
                APBlocks.BIRCH_RAILING,
                APBlocks.BLANK_ACACIA_TOTEM,
                APBlocks.CALCITE_BRICK_SET.BLOCK,
                APBlocks.CALCITE_BRICK_SET.STAIRS,
                APBlocks.CALCITE_BRICK_SET.WALL,
                APBlocks.CALCITE_LAMP,
                APBlocks.CALCITE_PILLAR,
                APBlocks.CHARCOAL_BLOCK,
                APBlocks.CHISELED_ABYSSALINE_BRICKS,
                APBlocks.CHISELED_ALGAL_BRICKS,
                APBlocks.CHISELED_BASALT_TILES,
                APBlocks.CHISELED_CALCITE,
                APBlocks.CHISELED_DRIPSTONE,
                APBlocks.CHISELED_END_STONE_BRICKS,
                APBlocks.CHISELED_ENTWINE,
                APBlocks.CHISELED_GILDED_SANDSTONE,
                APBlocks.CHISELED_OLIVESTONE,
                APBlocks.CHISELED_SUNMETAL_BLOCK,
                APBlocks.CHISELED_TUFF,
                APBlocks.CHORAL_END_STONE_BRICKS,
                APBlocks.COARSE_SNOW,
                APBlocks.COD_LOG,
                APBlocks.COD_SCALES,
                APBlocks.CRACKED_ALGAL_BRICKS,
                APBlocks.CRACKED_BASALT_TILES,
                APBlocks.CRACKED_END_STONE_BRICKS,
                APBlocks.CRACKED_OLIVESTONE_BRICKS,
                APBlocks.CRACKED_OLIVESTONE_TILES,
                APBlocks.CRIMSON_BOARDS,
                APBlocks.CRIMSON_RAILING,
                APBlocks.DARK_OAK_BOARDS,
                APBlocks.DARK_OAK_RAILING,
                APBlocks.DRIPSTONE_BRICK_SET.BLOCK,
                APBlocks.DRIPSTONE_BRICK_SET.STAIRS,
                APBlocks.DRIPSTONE_BRICK_SET.WALL,
                APBlocks.DRIPSTONE_LAMP,
                APBlocks.DRIPSTONE_PILLAR,
                APBlocks.ENDER_PEARL_BLOCK,
                APBlocks.ENTRAILS,
                APBlocks.ENTRAILS_STAIRS,
                APBlocks.ENTWINE_BARS,
                APBlocks.ENTWINE_PILLAR,
                APBlocks.ENTWINE_SET.BLOCK,
                APBlocks.ENTWINE_SET.STAIRS,
                APBlocks.FLINT_BLOCK,
                APBlocks.FLINT_PILLAR,
                APBlocks.FLINT_TILES_SET.BLOCK,
                APBlocks.FLINT_TILES_SET.STAIRS,
                APBlocks.FLINT_TILES_SET.WALL,
                APBlocks.GILDED_SANDSTONE_PILLAR,
                APBlocks.GILDED_SANDSTONE_SET.BLOCK,
                APBlocks.GILDED_SANDSTONE_SET.STAIRS,
                APBlocks.GLOWSTONE_CAGE_LANTERN,
                APBlocks.GRINNING_ACACIA_TOTEM,
                APBlocks.HEAVY_CALCITE_BRICKS,
                APBlocks.HEAVY_CRACKED_END_STONE_BRICKS,
                APBlocks.HEAVY_CRACKED_STONE_BRICKS,
                APBlocks.HEAVY_DRIPSTONE_BRICKS,
                APBlocks.HEAVY_END_STONE_BRICKS,
                APBlocks.HEAVY_MOSSY_STONE_BRICKS,
                APBlocks.HEAVY_STONE_BRICKS,
                APBlocks.HEAVY_TUFF_BRICKS,
                APBlocks.ILLUMINATED_OLIVESTONE,
                APBlocks.JUNGLE_BOARDS,
                APBlocks.JUNGLE_RAILING,
                APBlocks.LIT_OSSEOUS_SKULL,
                APBlocks.LIT_WITHERED_OSSEOUS_SKULL,
                APBlocks.MOLTEN_NETHER_BRICKS,
                APBlocks.MOONSTONE,
                APBlocks.MUSHY_MYONITE_BRICK_SET.BLOCK,
                APBlocks.MUSHY_MYONITE_BRICK_SET.STAIRS,
                APBlocks.MUSHY_MYONITE_BRICK_SET.WALL,
                APBlocks.MYONITE_BRICK_SET.BLOCK,
                APBlocks.MYONITE_BRICK_SET.STAIRS,
                APBlocks.MYONITE_BRICK_SET.WALL,
                APBlocks.MYONITE_SET.BLOCK,
                APBlocks.MYONITE_SET.STAIRS,
                APBlocks.MYONITE_SET.WALL,
                APBlocks.OAK_BOARDS,
                APBlocks.OAK_RAILING,
                APBlocks.OLIVESTONE_BRICK_SET.BLOCK,
                APBlocks.OLIVESTONE_BRICK_SET.STAIRS,
                APBlocks.OLIVESTONE_BRICK_SET.WALL,
                APBlocks.OLIVESTONE_PILLAR,
                APBlocks.OLIVESTONE_TILES_SET.BLOCK,
                APBlocks.OLIVESTONE_TILES_SET.STAIRS,
                APBlocks.OLIVESTONE_TILES_SET.WALL,
                APBlocks.OSSEOUS_BRICK_SET.BLOCK,
                APBlocks.OSSEOUS_BRICK_SET.STAIRS,
                APBlocks.OSSEOUS_BRICK_SET.WALL,
                APBlocks.OSSEOUS_PILLAR,
                APBlocks.OSSEOUS_SKULL,
                APBlocks.OVERGROWN_ALGAL_BRICK_SET.BLOCK,
                APBlocks.OVERGROWN_ALGAL_BRICK_SET.STAIRS,
                APBlocks.OVERGROWN_ALGAL_BRICK_SET.WALL,
                APBlocks.PIPE,
                APBlocks.PLACID_ACACIA_TOTEM,
                APBlocks.PLATING_SET.BLOCK,
                APBlocks.PLATING_SET.STAIRS,
                APBlocks.PLATING_SET.WALL,
                APBlocks.POLISHED_GLOWSTONE_SET.BLOCK,
                APBlocks.REDSTONE_CAGE_LANTERN,
                APBlocks.ROTTEN_FLESH_BLOCK,
                APBlocks.RUNIC_GLOWSTONE,
                APBlocks.SALMON_LOG,
                APBlocks.SALMON_SCALES,
                APBlocks.SCUTE_BLOCK,
                APBlocks.SHOCKED_ACACIA_TOTEM,
                APBlocks.SPOOL,
                APBlocks.SPRUCE_BOARDS,
                APBlocks.SPRUCE_RAILING,
                APBlocks.STRIPPED_TWISTED_LOG,
                APBlocks.STRIPPED_TWISTED_WOOD,
                APBlocks.SUNMETAL_BARS,
                APBlocks.SUNMETAL_PILLAR,
                APBlocks.SUNMETAL_SET.BLOCK,
                APBlocks.SUNMETAL_SET.STAIRS,
                APBlocks.SUNSTONE,
                APBlocks.TUFF_BRICK_SET.BLOCK,
                APBlocks.TUFF_BRICK_SET.STAIRS,
                APBlocks.TUFF_BRICK_SET.WALL,
                APBlocks.TUFF_LAMP,
                APBlocks.TUFF_PILLAR,
                APBlocks.TWISTED_BOARDS,
                APBlocks.TWISTED_BUTTON,
                APBlocks.TWISTED_FENCE,
                APBlocks.TWISTED_FENCE_GATE,
                APBlocks.TWISTED_LOG,
                APBlocks.TWISTED_PLANKS_SET.BLOCK,
                APBlocks.TWISTED_PLANKS_SET.STAIRS,
                APBlocks.TWISTED_PRESSURE_PLATE,
                APBlocks.TWISTED_RAILING,
                APBlocks.TWISTED_SAPLING,
                APBlocks.TWISTED_TRAPDOOR,
                APBlocks.TWISTED_WOOD,
                APBlocks.TWISTING_BLACKSTONE,
                APBlocks.TWISTING_BLACKSTONE_BRICKS,
                APBlocks.WARPED_BOARDS,
                APBlocks.WARPED_RAILING,
                APBlocks.WARPSTONE_SET.BLOCK,
                APBlocks.WARPSTONE_SET.STAIRS,
                APBlocks.WARPSTONE_SET.WALL,
                APBlocks.WEEPING_BLACKSTONE,
                APBlocks.WEEPING_BLACKSTONE_BRICKS,
                APBlocks.WITHERED_BONE_BLOCK,
                APBlocks.WITHERED_OSSEOUS_BRICK_SET.BLOCK,
                APBlocks.WITHERED_OSSEOUS_BRICK_SET.STAIRS,
                APBlocks.WITHERED_OSSEOUS_BRICK_SET.WALL,
                APBlocks.WITHERED_OSSEOUS_PILLAR,
                APBlocks.WITHERED_OSSEOUS_SKULL,
                APBlocks.WITHER_LAMP)
            .forEach(this::addDrop);
        for (StoneBlockSet set : APBlocks.ORE_SETS) {
            this.addDrop(set.BLOCK);
            this.addDrop(set.STAIRS);
            this.addDrop(set.WALL);
        }
        for (Block chiseled : APBlocks.CHISELED_ORES.values()) this.addDrop(chiseled);
        for (Block cracked : APBlocks.CRACKED_ORES.values()) this.addDrop(cracked);

        Stream.of(
                APBlocks.ABYSSALINE_BRICK_SLAB,
                APBlocks.ABYSSALINE_BRICK_VERTICAL_SLAB,
                APBlocks.ABYSSALINE_TILE_SLAB,
                APBlocks.ABYSSALINE_TILE_VERTICAL_SLAB,
                APBlocks.ALGAL_BRICK_SET.SLAB,
                APBlocks.ALGAL_BRICK_SET.VERTICAL_SLAB,
                APBlocks.BASALT_TILES_SET.SLAB,
                APBlocks.BASALT_TILES_SET.VERTICAL_SLAB,
                APBlocks.CALCITE_BRICK_SET.SLAB,
                APBlocks.CALCITE_BRICK_SET.VERTICAL_SLAB,
                APBlocks.DRIPSTONE_BRICK_SET.SLAB,
                APBlocks.DRIPSTONE_BRICK_SET.VERTICAL_SLAB,
                APBlocks.ENTRAILS_SLAB,
                APBlocks.ENTRAILS_VERTICAL_SLAB,
                APBlocks.ENTWINE_SET.SLAB,
                APBlocks.ENTWINE_SET.VERTICAL_SLAB,
                APBlocks.FLINT_TILES_SET.SLAB,
                APBlocks.FLINT_TILES_SET.VERTICAL_SLAB,
                APBlocks.GILDED_SANDSTONE_SET.SLAB,
                APBlocks.GILDED_SANDSTONE_SET.VERTICAL_SLAB,
                APBlocks.MUSHY_MYONITE_BRICK_SET.SLAB,
                APBlocks.MUSHY_MYONITE_BRICK_SET.VERTICAL_SLAB,
                APBlocks.MYONITE_BRICK_SET.SLAB,
                APBlocks.MYONITE_BRICK_SET.VERTICAL_SLAB,
                APBlocks.MYONITE_SET.SLAB,
                APBlocks.MYONITE_SET.VERTICAL_SLAB,
                APBlocks.OLIVESTONE_BRICK_SET.SLAB,
                APBlocks.OLIVESTONE_BRICK_SET.VERTICAL_SLAB,
                APBlocks.OLIVESTONE_TILES_SET.SLAB,
                APBlocks.OLIVESTONE_TILES_SET.VERTICAL_SLAB,
                APBlocks.OSSEOUS_BRICK_SET.SLAB,
                APBlocks.OSSEOUS_BRICK_SET.VERTICAL_SLAB,
                APBlocks.OVERGROWN_ALGAL_BRICK_SET.SLAB,
                APBlocks.OVERGROWN_ALGAL_BRICK_SET.VERTICAL_SLAB,
                APBlocks.PLATING_SET.SLAB,
                APBlocks.PLATING_SET.VERTICAL_SLAB,
                APBlocks.POLISHED_GLOWSTONE_SET.SLAB,
                APBlocks.POLISHED_GLOWSTONE_SET.VERTICAL_SLAB,
                APBlocks.SUNMETAL_SET.SLAB,
                APBlocks.SUNMETAL_SET.VERTICAL_SLAB,
                APBlocks.TUFF_BRICK_SET.SLAB,
                APBlocks.TUFF_BRICK_SET.VERTICAL_SLAB,
                APBlocks.TWISTED_PLANKS_SET.SLAB,
                APBlocks.TWISTED_PLANKS_SET.VERTICAL_SLAB,
                APBlocks.WARPSTONE_SET.SLAB,
                APBlocks.WARPSTONE_SET.VERTICAL_SLAB,
                APBlocks.WITHERED_OSSEOUS_BRICK_SET.SLAB,
                APBlocks.WITHERED_OSSEOUS_BRICK_SET.VERTICAL_SLAB)
            .forEach((i) -> this.addDrop(i, BlockLootTableGenerator::slabDrops));
        for (StoneBlockSet set : APBlocks.ORE_SETS){
                this.addDrop(set.SLAB, BlockLootTableGenerator::slabDrops);
                this.addDrop(set.VERTICAL_SLAB, BlockLootTableGenerator::slabDrops);
        }

        Stream.of(
                APBlocks.POLISHED_PACKED_ICE_SET.BLOCK,
                APBlocks.POLISHED_PACKED_ICE_SET.STAIRS,
                APBlocks.POLISHED_PACKED_ICE_SET.WALL,
                APBlocks.CHISELED_PACKED_ICE,
                APBlocks.PACKED_ICE_PILLAR)
            .forEach(this::addDropWithSilkTouch);

        this.addDrop(APBlocks.TWISTED_DOOR, BlockLootTableGenerator::addDoorDrop);
        this.dropSlabWithSilkTouch(APBlocks.POLISHED_PACKED_ICE_SET.SLAB);
        this.dropSlabWithSilkTouch(APBlocks.POLISHED_PACKED_ICE_SET.VERTICAL_SLAB);
        this.addDrop(APBlocks.TWISTED_LEAVES, (blockx) -> BlockLootTableGenerator.leavesDrop(blockx, APBlocks.TWISTED_SAPLING, 0.05f, 0.0625f, 0.083333336f, 0.1f));
        this.addPottedPlantDrop(APBlocks.POTTED_TWISTED_SAPLING);

    }
}
