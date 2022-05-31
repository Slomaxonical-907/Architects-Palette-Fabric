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
import net.minecraft.tag.TagKey;

import java.util.ArrayList;
import java.util.List;
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
        for (StoneBlockSet set : StoneBlockSet.BlockSets){
            if (set.BLOCK != APBlocks.POLISHED_PACKED_ICE){
                this.addDrop(set.BLOCK);
                if (set.STAIRS!=null) this.addDrop(set.STAIRS);
                if (set.WALL!=null) this.addDrop(set.WALL);
                if (set.SLAB!=null) {
                    this.addDrop(set.SLAB, BlockLootTableGenerator::slabDrops);
                    this.addDrop(set.VERTICAL_SLAB, BlockLootTableGenerator::slabDrops);
                }
            }else{
                this.addDropWithSilkTouch(set.BLOCK);
                this.addDropWithSilkTouch(set.STAIRS);
                this.addDropWithSilkTouch(set.WALL);
                this.dropSlabWithSilkTouch(set.SLAB);
                this.dropSlabWithSilkTouch(set.VERTICAL_SLAB);
            }
        }
        for (StoneBlockSet set : StoneBlockSet.oreBrickSets) {
            this.addDrop(set.BLOCK);
            this.addDrop(set.STAIRS);
            this.addDrop(set.WALL);
            this.addDrop(set.SLAB, BlockLootTableGenerator::slabDrops);
        }
        for (List<Block> list : APBlocks.chiseledNcrackedOres.values()) list.forEach(this::addDrop); ;

        Stream.of(
                APBlocks.ABYSSALINE,
                APBlocks.ABYSSALINE_BRICKS,
                APBlocks.ABYSSALINE_LAMP_BLOCK,
                APBlocks.ABYSSALINE_PILLAR,
                APBlocks.ABYSSALINE_TILES,
                APBlocks.ACACIA_BOARDS,
                APBlocks.ACACIA_RAILING,
                APBlocks.ACACIA_TOTEM_WING,
                APBlocks.ALGAL_CAGE_LANTERN,
                APBlocks.ALGAL_LAMP,
                APBlocks.BIRCH_BOARDS,
                APBlocks.BIRCH_RAILING,
                APBlocks.BLANK_ACACIA_TOTEM,
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
                APBlocks.DRIPSTONE_LAMP,
                APBlocks.DRIPSTONE_PILLAR,
                APBlocks.ENDER_PEARL_BLOCK,
                APBlocks.ENTRAILS,
                APBlocks.ENTRAILS_STAIRS,
                APBlocks.ENTWINE_BARS,
                APBlocks.ENTWINE_PILLAR,
                APBlocks.FLINT_BLOCK,
                APBlocks.FLINT_PILLAR,
                APBlocks.GILDED_SANDSTONE_PILLAR,
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
                APBlocks.OAK_BOARDS,
                APBlocks.OAK_RAILING,
                APBlocks.OLIVESTONE_PILLAR,
                APBlocks.OSSEOUS_PILLAR,
                APBlocks.OSSEOUS_SKULL,
                APBlocks.PIPE,
                APBlocks.PLACID_ACACIA_TOTEM,
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
                APBlocks.SUNSTONE,
                APBlocks.TUFF_LAMP,
                APBlocks.TUFF_PILLAR,
                APBlocks.TWISTED_BOARDS,
                APBlocks.TWISTED_BUTTON,
                APBlocks.TWISTED_FENCE,
                APBlocks.TWISTED_FENCE_GATE,
                APBlocks.TWISTED_LOG,
                APBlocks.TWISTED_PRESSURE_PLATE,
                APBlocks.TWISTED_RAILING,
                APBlocks.TWISTED_SAPLING,
                APBlocks.TWISTED_TRAPDOOR,
                APBlocks.TWISTED_WOOD,
                APBlocks.TWISTING_BLACKSTONE,
                APBlocks.TWISTING_BLACKSTONE_BRICKS,
                APBlocks.WARPED_BOARDS,
                APBlocks.WARPED_RAILING,
                APBlocks.WEEPING_BLACKSTONE,
                APBlocks.WEEPING_BLACKSTONE_BRICKS,
                APBlocks.WITHERED_BONE_BLOCK,
                APBlocks.WITHERED_OSSEOUS_PILLAR,
                APBlocks.WITHERED_OSSEOUS_SKULL,
                APBlocks.WITHER_LAMP,
                APBlocks.HELIODOR_ROD,
                APBlocks.EKANITE_ROD,
                APBlocks.MONAZITE_ROD,
                APBlocks.UNOBTANIUM_BLOCK,
                APBlocks.NETHER_BRASS_PILLAR,
                APBlocks.NETHER_BRASS_CHAIN,
                APBlocks.NETHER_BRASS_LANTERN,
                APBlocks.NETHER_BRASS_TORCH)
                .forEach(this::addDrop);

        Stream.of(
                APBlocks.ABYSSALINE_BRICK_SLAB,
                APBlocks.ABYSSALINE_BRICK_VERTICAL_SLAB,
                APBlocks.ABYSSALINE_TILE_SLAB,
                APBlocks.ABYSSALINE_TILE_VERTICAL_SLAB,
                APBlocks.ENTRAILS_SLAB,
                APBlocks.ENTRAILS_VERTICAL_SLAB
                )
            .forEach((i) -> this.addDrop(i, BlockLootTableGenerator::slabDrops));

        Stream.of(
            APBlocks.CHISELED_PACKED_ICE,
            APBlocks.PACKED_ICE_PILLAR)
        .forEach(this::addDropWithSilkTouch);

        this.addDrop(APBlocks.TWISTED_DOOR, BlockLootTableGenerator::addDoorDrop);
        this.addDrop(APBlocks.TWISTED_LEAVES, (blockx) -> BlockLootTableGenerator.leavesDrop(blockx, APBlocks.TWISTED_SAPLING, 0.05f, 0.0625f, 0.083333336f, 0.1f));
        this.addPottedPlantDrop(APBlocks.POTTED_TWISTED_SAPLING);

    }
}
