package com.slomaxonical.architectspalette.data.provider;

import com.slomaxonical.architectspalette.ArchitectsPalette;
import com.slomaxonical.architectspalette.blocks.util.StoneBlockSet;
import com.slomaxonical.architectspalette.registry.APBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class APBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public APBlockTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    public static final TagKey<Block> TWISTED_LOGS = TagKey.of(Registry.BLOCK_KEY,new Identifier(ArchitectsPalette.MOD_ID, "twisted_logs"));
    public static final TagKey<Block> CAGE_LANTERNS = TagKey.of(Registry.BLOCK_KEY,new Identifier(ArchitectsPalette.MOD_ID, "cage_lanterns"));
    public static final TagKey<Block> CRYSTAL_REPLACEABLE = TagKey.of(Registry.BLOCK_KEY,new Identifier(ArchitectsPalette.MOD_ID, "crystal_formation_replaceable"));


    private FabricTagBuilder<Block> getOrCreateTagBuilder(Identifier id) {
        TagKey<Block> tag = TagKey.of(Registry.BLOCK_KEY, id);
        return this.getOrCreateTagBuilder(tag);
    }

    @Override
    protected void generateTags() {
        this.getOrCreateTagBuilder(CRYSTAL_REPLACEABLE)
                .add(Blocks.NETHER_WART_BLOCK)
                .add(Blocks.WARPED_WART_BLOCK);

        this.getOrCreateTagBuilder(TWISTED_LOGS)
                .add(APBlocks.TWISTED_LOG)
                .add(APBlocks.STRIPPED_TWISTED_LOG)
                .add(APBlocks.TWISTED_WOOD)
                .add(APBlocks.STRIPPED_TWISTED_WOOD);

        this.getOrCreateTagBuilder(CAGE_LANTERNS)
                .add(APBlocks.ALGAL_CAGE_LANTERN)
                .add(APBlocks.GLOWSTONE_CAGE_LANTERN)
                .add(APBlocks.REDSTONE_CAGE_LANTERN);

        this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(APBlocks.ABYSSALINE)
                .add(APBlocks.ABYSSALINE_BRICKS)
                .add(APBlocks.ABYSSALINE_BRICK_SLAB)
                .add(APBlocks.ABYSSALINE_BRICK_VERTICAL_SLAB)
                .add(APBlocks.ABYSSALINE_LAMP_BLOCK)
                .add(APBlocks.ABYSSALINE_PILLAR)
                .add(APBlocks.ABYSSALINE_TILES)
                .add(APBlocks.ABYSSALINE_TILE_SLAB)
                .add(APBlocks.ABYSSALINE_TILE_VERTICAL_SLAB)
                .add(APBlocks.ALGAL_BRICK_SET.BLOCK)
                .add(APBlocks.ALGAL_BRICK_SET.STAIRS)
                .add(APBlocks.ALGAL_BRICK_SET.SLAB)
                .add(APBlocks.ALGAL_BRICK_SET.VERTICAL_SLAB)
                .add(APBlocks.ALGAL_BRICK_SET.WALL)
                .add(APBlocks.ALGAL_CAGE_LANTERN)
                .add(APBlocks.ALGAL_LAMP)
                .add(APBlocks.BASALT_TILES_SET.BLOCK)
                .add(APBlocks.BASALT_TILES_SET.STAIRS)
                .add(APBlocks.BASALT_TILES_SET.SLAB)
                .add(APBlocks.BASALT_TILES_SET.VERTICAL_SLAB)
                .add(APBlocks.BASALT_TILES_SET.WALL)
                .add(APBlocks.CALCITE_BRICK_SET.BLOCK)
                .add(APBlocks.CALCITE_BRICK_SET.STAIRS)
                .add(APBlocks.CALCITE_BRICK_SET.SLAB)
                .add(APBlocks.CALCITE_BRICK_SET.VERTICAL_SLAB)
                .add(APBlocks.CALCITE_BRICK_SET.WALL)
                .add(APBlocks.CALCITE_LAMP)
                .add(APBlocks.CALCITE_PILLAR)
                .add(APBlocks.CHISELED_ABYSSALINE_BRICKS)
                .add(APBlocks.CHISELED_ALGAL_BRICKS)
                .add(APBlocks.CHISELED_BASALT_TILES)
                .add(APBlocks.CHISELED_CALCITE)
                .add(APBlocks.CHISELED_DRIPSTONE)
                .add(APBlocks.CHISELED_END_STONE_BRICKS)
                .add(APBlocks.CHISELED_ENTWINE)
                .add(APBlocks.CHISELED_GILDED_SANDSTONE)
                .add(APBlocks.CHISELED_OLIVESTONE)
                .add(APBlocks.CHISELED_PACKED_ICE)
                .add(APBlocks.CHISELED_SUNMETAL_BLOCK)
                .add(APBlocks.CHISELED_TUFF)
                .add(APBlocks.CHORAL_END_STONE_BRICKS)
                .add(APBlocks.CRACKED_ALGAL_BRICKS)
                .add(APBlocks.CRACKED_BASALT_TILES)
                .add(APBlocks.CRACKED_END_STONE_BRICKS)
                .add(APBlocks.CRACKED_OLIVESTONE_BRICKS)
                .add(APBlocks.CRACKED_OLIVESTONE_TILES)
                .add(APBlocks.DRIPSTONE_BRICK_SET.BLOCK)
                .add(APBlocks.DRIPSTONE_BRICK_SET.STAIRS)
                .add(APBlocks.DRIPSTONE_BRICK_SET.SLAB)
                .add(APBlocks.DRIPSTONE_BRICK_SET.VERTICAL_SLAB)
                .add(APBlocks.DRIPSTONE_BRICK_SET.WALL)
                .add(APBlocks.DRIPSTONE_LAMP)
                .add(APBlocks.DRIPSTONE_PILLAR)
                .add(APBlocks.ENTWINE_BARS)
                .add(APBlocks.ENTWINE_PILLAR)
                .add(APBlocks.ENTWINE_SET.BLOCK)
                .add(APBlocks.ENTWINE_SET.STAIRS)
                .add(APBlocks.ENTWINE_SET.SLAB)
                .add(APBlocks.ENTWINE_SET.VERTICAL_SLAB)
                .add(APBlocks.FLINT_BLOCK)
                .add(APBlocks.FLINT_PILLAR)
                .add(APBlocks.FLINT_TILES_SET.BLOCK)
                .add(APBlocks.FLINT_TILES_SET.STAIRS)
                .add(APBlocks.FLINT_TILES_SET.SLAB)
                .add(APBlocks.FLINT_TILES_SET.VERTICAL_SLAB)
                .add(APBlocks.FLINT_TILES_SET.WALL)
                .add(APBlocks.GILDED_SANDSTONE_PILLAR)
                .add(APBlocks.GILDED_SANDSTONE_SET.BLOCK)
                .add(APBlocks.GILDED_SANDSTONE_SET.STAIRS)
                .add(APBlocks.GILDED_SANDSTONE_SET.SLAB)
                .add(APBlocks.GILDED_SANDSTONE_SET.VERTICAL_SLAB)
                .add(APBlocks.GLOWSTONE_CAGE_LANTERN)
                .add(APBlocks.HEAVY_CALCITE_BRICKS)
                .add(APBlocks.HEAVY_CRACKED_END_STONE_BRICKS)
                .add(APBlocks.HEAVY_CRACKED_STONE_BRICKS)
                .add(APBlocks.HEAVY_DRIPSTONE_BRICKS)
                .add(APBlocks.HEAVY_END_STONE_BRICKS)
                .add(APBlocks.HEAVY_MOSSY_STONE_BRICKS)
                .add(APBlocks.HEAVY_STONE_BRICKS)
                .add(APBlocks.HEAVY_TUFF_BRICKS)
                .add(APBlocks.ILLUMINATED_OLIVESTONE)
                .add(APBlocks.LIT_OSSEOUS_SKULL)
                .add(APBlocks.LIT_WITHERED_OSSEOUS_SKULL)
                .add(APBlocks.MOLTEN_NETHER_BRICKS)
                .add(APBlocks.MOONSTONE)
                .add(APBlocks.MUSHY_MYONITE_BRICK_SET.BLOCK)
                .add(APBlocks.MUSHY_MYONITE_BRICK_SET.STAIRS)
                .add(APBlocks.MUSHY_MYONITE_BRICK_SET.SLAB)
                .add(APBlocks.MUSHY_MYONITE_BRICK_SET.VERTICAL_SLAB)
                .add(APBlocks.MUSHY_MYONITE_BRICK_SET.WALL)
                .add(APBlocks.MYONITE_BRICK_SET.BLOCK)
                .add(APBlocks.MYONITE_BRICK_SET.STAIRS)
                .add(APBlocks.MYONITE_BRICK_SET.SLAB)
                .add(APBlocks.MYONITE_BRICK_SET.VERTICAL_SLAB)
                .add(APBlocks.MYONITE_BRICK_SET.WALL)
                .add(APBlocks.MYONITE_SET.BLOCK)
                .add(APBlocks.MYONITE_SET.STAIRS)
                .add(APBlocks.MYONITE_SET.SLAB)
                .add(APBlocks.MYONITE_SET.VERTICAL_SLAB)
                .add(APBlocks.MYONITE_SET.WALL)
                .add(APBlocks.OLIVESTONE_BRICK_SET.BLOCK)
                .add(APBlocks.OLIVESTONE_BRICK_SET.STAIRS)
                .add(APBlocks.OLIVESTONE_BRICK_SET.SLAB)
                .add(APBlocks.OLIVESTONE_BRICK_SET.VERTICAL_SLAB)
                .add(APBlocks.OLIVESTONE_BRICK_SET.WALL)
                .add(APBlocks.OLIVESTONE_PILLAR)
                .add(APBlocks.OLIVESTONE_TILES_SET.BLOCK)
                .add(APBlocks.OLIVESTONE_TILES_SET.STAIRS)
                .add(APBlocks.OLIVESTONE_TILES_SET.SLAB)
                .add(APBlocks.OLIVESTONE_TILES_SET.VERTICAL_SLAB)
                .add(APBlocks.OLIVESTONE_TILES_SET.WALL)
                .add(APBlocks.OSSEOUS_BRICK_SET.BLOCK)
                .add(APBlocks.OSSEOUS_BRICK_SET.STAIRS)
                .add(APBlocks.OSSEOUS_BRICK_SET.SLAB)
                .add(APBlocks.OSSEOUS_BRICK_SET.VERTICAL_SLAB)
                .add(APBlocks.OSSEOUS_BRICK_SET.WALL)
                .add(APBlocks.OSSEOUS_PILLAR)
                .add(APBlocks.OSSEOUS_SKULL)
                .add(APBlocks.OVERGROWN_ALGAL_BRICK_SET.BLOCK)
                .add(APBlocks.OVERGROWN_ALGAL_BRICK_SET.STAIRS)
                .add(APBlocks.OVERGROWN_ALGAL_BRICK_SET.SLAB)
                .add(APBlocks.OVERGROWN_ALGAL_BRICK_SET.VERTICAL_SLAB)
                .add(APBlocks.OVERGROWN_ALGAL_BRICK_SET.WALL)
                .add(APBlocks.PACKED_ICE_PILLAR)
                .add(APBlocks.PIPE)
                .add(APBlocks.PLATING_SET.BLOCK)
                .add(APBlocks.PLATING_SET.STAIRS)
                .add(APBlocks.PLATING_SET.SLAB)
                .add(APBlocks.PLATING_SET.VERTICAL_SLAB)
                .add(APBlocks.PLATING_SET.WALL)
                .add(APBlocks.POLISHED_GLOWSTONE_SET.BLOCK)
                .add(APBlocks.POLISHED_GLOWSTONE_SET.SLAB)
                .add(APBlocks.POLISHED_GLOWSTONE_SET.VERTICAL_SLAB)
                .add(APBlocks.POLISHED_PACKED_ICE_SET.BLOCK)
                .add(APBlocks.POLISHED_PACKED_ICE_SET.STAIRS)
                .add(APBlocks.POLISHED_PACKED_ICE_SET.SLAB)
                .add(APBlocks.POLISHED_PACKED_ICE_SET.VERTICAL_SLAB)
                .add(APBlocks.POLISHED_PACKED_ICE_SET.WALL)
                .add(APBlocks.POTTED_TWISTED_SAPLING)
                .add(APBlocks.REDSTONE_CAGE_LANTERN)
                .add(APBlocks.RUNIC_GLOWSTONE)
                .add(APBlocks.SCUTE_BLOCK)
                .add(APBlocks.SUNMETAL_BARS)
                .add(APBlocks.SUNMETAL_PILLAR)
                .add(APBlocks.SUNMETAL_SET.BLOCK)
                .add(APBlocks.SUNMETAL_SET.STAIRS)
                .add(APBlocks.SUNMETAL_SET.SLAB)
                .add(APBlocks.SUNMETAL_SET.VERTICAL_SLAB)
                .add(APBlocks.SUNSTONE)
                .add(APBlocks.TUFF_BRICK_SET.BLOCK)
                .add(APBlocks.TUFF_BRICK_SET.STAIRS)
                .add(APBlocks.TUFF_BRICK_SET.SLAB)
                .add(APBlocks.TUFF_BRICK_SET.VERTICAL_SLAB)
                .add(APBlocks.TUFF_BRICK_SET.WALL)
                .add(APBlocks.TUFF_LAMP)
                .add(APBlocks.TUFF_PILLAR)
                .add(APBlocks.TWISTING_BLACKSTONE)
                .add(APBlocks.TWISTING_BLACKSTONE_BRICKS)
                .add(APBlocks.WARPSTONE_SET.BLOCK)
                .add(APBlocks.WARPSTONE_SET.STAIRS)
                .add(APBlocks.WARPSTONE_SET.SLAB)
                .add(APBlocks.WARPSTONE_SET.VERTICAL_SLAB)
                .add(APBlocks.WARPSTONE_SET.WALL)
                .add(APBlocks.WEEPING_BLACKSTONE)
                .add(APBlocks.WEEPING_BLACKSTONE_BRICKS)
                .add(APBlocks.WITHERED_BONE_BLOCK)
                .add(APBlocks.WITHERED_OSSEOUS_BRICK_SET.BLOCK)
                .add(APBlocks.WITHERED_OSSEOUS_BRICK_SET.STAIRS)
                .add(APBlocks.WITHERED_OSSEOUS_BRICK_SET.SLAB)
                .add(APBlocks.WITHERED_OSSEOUS_BRICK_SET.VERTICAL_SLAB)
                .add(APBlocks.WITHERED_OSSEOUS_BRICK_SET.WALL)
                .add(APBlocks.WITHERED_OSSEOUS_PILLAR)
                .add(APBlocks.WITHERED_OSSEOUS_SKULL)
                .add(APBlocks.WITHER_LAMP)
                .add(APBlocks.HELIODOR_ROD)
                .add(APBlocks.EKANITE_ROD)
                .add(APBlocks.MONAZITE_ROD);

        for (StoneBlockSet set: APBlocks.ORE_SETS) {
            this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(set.BLOCK);
            this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(set.STAIRS);
            this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(set.SLAB);
            this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(set.VERTICAL_SLAB);
            this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(set.WALL);

            this.getOrCreateTagBuilder(BlockTags.STAIRS).add(set.STAIRS);
            this.getOrCreateTagBuilder(BlockTags.SLABS).add(set.SLAB);
            this.getOrCreateTagBuilder(BlockTags.WALLS).add(set.WALL);
        }
        for (Block b: APBlocks.CHISELED_ORES.values()) this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(b);
        for (Block b: APBlocks.CRACKED_ORES.values()) this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(b);


        this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(APBlocks.ACACIA_BOARDS)
                .add(APBlocks.ACACIA_RAILING)
                .add(APBlocks.ACACIA_TOTEM_WING)
                .add(APBlocks.BIRCH_BOARDS)
                .add(APBlocks.BIRCH_RAILING)
                .add(APBlocks.BLANK_ACACIA_TOTEM)
                .add(APBlocks.CHARCOAL_BLOCK)
                .add(APBlocks.COD_LOG)
                .add(APBlocks.COD_SCALES)
                .add(APBlocks.CRIMSON_BOARDS)
                .add(APBlocks.CRIMSON_RAILING)
                .add(APBlocks.DARK_OAK_BOARDS)
                .add(APBlocks.DARK_OAK_RAILING)
                .add(APBlocks.ENTRAILS)
                .add(APBlocks.ENTRAILS_SLAB)
                .add(APBlocks.ENTRAILS_STAIRS)
                .add(APBlocks.ENTRAILS_VERTICAL_SLAB)
                .add(APBlocks.GRINNING_ACACIA_TOTEM)
                .add(APBlocks.JUNGLE_BOARDS)
                .add(APBlocks.JUNGLE_RAILING)
                .add(APBlocks.OAK_BOARDS)
                .add(APBlocks.OAK_RAILING)
                .add(APBlocks.PLACID_ACACIA_TOTEM)
                .add(APBlocks.ROTTEN_FLESH_BLOCK)
                .add(APBlocks.SALMON_LOG)
                .add(APBlocks.SALMON_SCALES)
                .add(APBlocks.SHOCKED_ACACIA_TOTEM)
                .add(APBlocks.SPOOL)
                .add(APBlocks.SPRUCE_BOARDS)
                .add(APBlocks.SPRUCE_RAILING)
                .add(APBlocks.STRIPPED_TWISTED_LOG)
                .add(APBlocks.STRIPPED_TWISTED_WOOD)
                .add(APBlocks.TWISTED_BOARDS)
                .add(APBlocks.TWISTED_BUTTON)
                .add(APBlocks.TWISTED_DOOR)
                .add(APBlocks.TWISTED_FENCE)
                .add(APBlocks.TWISTED_FENCE_GATE)
                .add(APBlocks.TWISTED_LOG)
                .add(APBlocks.TWISTED_PLANKS_SET.BLOCK)
                .add(APBlocks.TWISTED_PLANKS_SET.STAIRS)
                .add(APBlocks.TWISTED_PLANKS_SET.SLAB)
                .add(APBlocks.TWISTED_PLANKS_SET.VERTICAL_SLAB)
                .add(APBlocks.TWISTED_PRESSURE_PLATE)
                .add(APBlocks.TWISTED_RAILING)
                .add(APBlocks.TWISTED_TRAPDOOR)
                .add(APBlocks.TWISTED_WOOD)
                .add(APBlocks.WARPED_BOARDS)
                .add(APBlocks.WARPED_RAILING);

        this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(APBlocks.COARSE_SNOW);

        this.getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(APBlocks.ENDER_PEARL_BLOCK)
                .add(APBlocks.TWISTED_LEAVES);

        this.getOrCreateTagBuilder(BlockTags.DRAGON_IMMUNE)
                .add(APBlocks.ENTWINE)
                .add(APBlocks.ENTWINE_PILLAR)
                .add(APBlocks.ENTWINE_BARS)
                .add(APBlocks.CHISELED_ENTWINE)
                .add(APBlocks.HEAVY_END_STONE_BRICKS)
                .add(APBlocks.HEAVY_CRACKED_END_STONE_BRICKS)
                .add(APBlocks.CHISELED_END_STONE_BRICKS)
                .add(APBlocks.CHORAL_END_STONE_BRICKS)
                .add(APBlocks.CRACKED_END_STONE_BRICKS)
                .add(Blocks.END_STONE_BRICKS);

        this.getOrCreateTagBuilder(BlockTags.MUSHROOM_GROW_BLOCK)
                .add(APBlocks.MYONITE)
                .add(APBlocks.MYONITE_BRICK_SET.BLOCK)
                .add(APBlocks.MYONITE_BRICK_SET.SLAB)
                .add(APBlocks.MYONITE_BRICK_SET.VERTICAL_SLAB)
                .add(APBlocks.MUSHY_MYONITE_BRICK_SET.BLOCK)
                .add(APBlocks.MUSHY_MYONITE_BRICK_SET.SLAB)
                .add(APBlocks.MUSHY_MYONITE_BRICK_SET.VERTICAL_SLAB);

        this.getOrCreateTagBuilder(BlockTags.PLANKS).add(APBlocks.TWISTED_PLANKS);
        this.getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(APBlocks.TWISTED_FENCE_GATE);
        this.getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(APBlocks.POTTED_TWISTED_SAPLING);
        this.getOrCreateTagBuilder(BlockTags.LEAVES).add(APBlocks.TWISTED_LEAVES);
        this.getOrCreateTagBuilder(BlockTags.SAPLINGS).add(APBlocks.TWISTED_SAPLING);
        this.getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(TWISTED_LOGS); //this tag see
        this.getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(APBlocks.TWISTED_BUTTON);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(APBlocks.TWISTED_DOOR);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(APBlocks.TWISTED_TRAPDOOR);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(APBlocks.TWISTED_FENCE);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(APBlocks.TWISTED_PRESSURE_PLATE);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(APBlocks.TWISTED_PLANKS_SET.SLAB);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(APBlocks.TWISTED_PLANKS_SET.STAIRS);
        this.getOrCreateTagBuilder(BlockTags.WALL_POST_OVERRIDE).addTag(CAGE_LANTERNS);//this tag see

        this.getOrCreateTagBuilder(BlockTags.SLABS)
                .add(APBlocks.MYONITE_SET.SLAB)
                .add(APBlocks.MYONITE_BRICK_SET.SLAB)
                .add(APBlocks.MUSHY_MYONITE_BRICK_SET.SLAB)
                .add(APBlocks.OLIVESTONE_BRICK_SET.SLAB)
                .add(APBlocks.OLIVESTONE_TILES_SET.SLAB)
                .add(APBlocks.ALGAL_BRICK_SET.SLAB)
                .add(APBlocks.OVERGROWN_ALGAL_BRICK_SET.SLAB)
                .add(APBlocks.POLISHED_PACKED_ICE_SET.SLAB)
                .add(APBlocks.SUNMETAL_SET.SLAB)
                .add(APBlocks.OSSEOUS_BRICK_SET.SLAB)
                .add(APBlocks.WITHERED_OSSEOUS_BRICK_SET.SLAB)
                .add(APBlocks.FLINT_TILES_SET.SLAB)
                .add(APBlocks.ENTWINE_SET.SLAB)
                .add(APBlocks.POLISHED_GLOWSTONE_SET.SLAB)
                .add(APBlocks.GILDED_SANDSTONE_SET.SLAB)
                .add(APBlocks.PLATING_SET.SLAB)
                .add(APBlocks.WARPSTONE_SET.SLAB)
                .add(APBlocks.BASALT_TILES_SET.SLAB)
                .add(APBlocks.TUFF_BRICK_SET.SLAB)
                .add(APBlocks.CALCITE_BRICK_SET.SLAB)
                .add(APBlocks.DRIPSTONE_BRICK_SET.SLAB)
                .add(APBlocks.ABYSSALINE_BRICK_SLAB)
                .add(APBlocks.ABYSSALINE_TILE_SLAB)
                .add(APBlocks.ENTRAILS_SLAB);

        this.getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(APBlocks.MYONITE_SET.STAIRS)
                .add(APBlocks.MYONITE_BRICK_SET.STAIRS)
                .add(APBlocks.MUSHY_MYONITE_BRICK_SET.STAIRS)
                .add(APBlocks.OLIVESTONE_BRICK_SET.STAIRS)
                .add(APBlocks.OLIVESTONE_TILES_SET.STAIRS)
                .add(APBlocks.ALGAL_BRICK_SET.STAIRS)
                .add(APBlocks.OVERGROWN_ALGAL_BRICK_SET.STAIRS)
                .add(APBlocks.POLISHED_PACKED_ICE_SET.STAIRS)
                .add(APBlocks.SUNMETAL_SET.STAIRS)
                .add(APBlocks.OSSEOUS_BRICK_SET.STAIRS)
                .add(APBlocks.WITHERED_OSSEOUS_BRICK_SET.STAIRS)
                .add(APBlocks.FLINT_TILES_SET.STAIRS)
                .add(APBlocks.ENTWINE_SET.STAIRS)
                .add(APBlocks.GILDED_SANDSTONE_SET.STAIRS)
                .add(APBlocks.PLATING_SET.STAIRS)
                .add(APBlocks.WARPSTONE_SET.STAIRS)
                .add(APBlocks.BASALT_TILES_SET.STAIRS)
                .add(APBlocks.TUFF_BRICK_SET.STAIRS)
                .add(APBlocks.CALCITE_BRICK_SET.STAIRS)
                .add(APBlocks.DRIPSTONE_BRICK_SET.STAIRS)
                .add(APBlocks.ENTRAILS_STAIRS);

        this.getOrCreateTagBuilder(BlockTags.WALLS)
                .add(APBlocks.MYONITE_SET.WALL)
                .add(APBlocks.MYONITE_BRICK_SET.WALL)
                .add(APBlocks.MUSHY_MYONITE_BRICK_SET.WALL)
                .add(APBlocks.OLIVESTONE_BRICK_SET.WALL)
                .add(APBlocks.OLIVESTONE_TILES_SET.WALL)
                .add(APBlocks.ALGAL_BRICK_SET.WALL)
                .add(APBlocks.OVERGROWN_ALGAL_BRICK_SET.WALL)
                .add(APBlocks.POLISHED_PACKED_ICE_SET.WALL)
                .add(APBlocks.OSSEOUS_BRICK_SET.WALL)
                .add(APBlocks.WITHERED_OSSEOUS_BRICK_SET.WALL)
                .add(APBlocks.FLINT_TILES_SET.WALL)
                .add(APBlocks.PLATING_SET.WALL)
                .add(APBlocks.WARPSTONE_SET.WALL)
                .add(APBlocks.BASALT_TILES_SET.WALL)
                .add(APBlocks.TUFF_BRICK_SET.WALL)
                .add(APBlocks.CALCITE_BRICK_SET.WALL)
                .add(APBlocks.DRIPSTONE_BRICK_SET.WALL);

        this.getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(APBlocks.ABYSSALINE)
                .add(APBlocks.ABYSSALINE_BRICKS)
                .add(APBlocks.ABYSSALINE_BRICK_SLAB)
                .add(APBlocks.ABYSSALINE_BRICK_VERTICAL_SLAB)
                .add(APBlocks.ABYSSALINE_LAMP_BLOCK)
                .add(APBlocks.ABYSSALINE_PILLAR)
                .add(APBlocks.ABYSSALINE_TILES)
                .add(APBlocks.ABYSSALINE_TILE_SLAB)
                .add(APBlocks.ABYSSALINE_TILE_VERTICAL_SLAB)
                .add(APBlocks.CHISELED_ABYSSALINE_BRICKS);

        this.getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(APBlocks.PIPE)
                .add(APBlocks.PLATING_SET.BLOCK)
                .add(APBlocks.PLATING_SET.STAIRS)
                .add(APBlocks.PLATING_SET.SLAB)
                .add(APBlocks.PLATING_SET.VERTICAL_SLAB)
                .add(APBlocks.PLATING_SET.WALL);

        this.getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(APBlocks.ENTWINE)
                .add(APBlocks.ENTWINE_SET.STAIRS)
                .add(APBlocks.ENTWINE_SET.SLAB)
                .add(APBlocks.ENTWINE_SET.VERTICAL_SLAB)
                .add(APBlocks.ENTWINE_PILLAR)
                .add(APBlocks.ENTWINE_BARS)
                .add(APBlocks.CHISELED_ENTWINE)
                .add(APBlocks.FLINT_BLOCK)
                .add(APBlocks.FLINT_PILLAR)
                .add(APBlocks.FLINT_TILES_SET.BLOCK)
                .add(APBlocks.FLINT_TILES_SET.SLAB)
                .add(APBlocks.FLINT_TILES_SET.VERTICAL_SLAB)
                .add(APBlocks.FLINT_TILES_SET.STAIRS)
                .add(APBlocks.FLINT_TILES_SET.WALL)
                .add(APBlocks.SUNMETAL_SET.BLOCK)
                .add(APBlocks.SUNMETAL_SET.SLAB)
                .add(APBlocks.SUNMETAL_SET.VERTICAL_SLAB)
                .add(APBlocks.SUNMETAL_PILLAR)
                .add(APBlocks.CHISELED_SUNMETAL_BLOCK)
                .add(APBlocks.SUNMETAL_BARS);

    }
}
