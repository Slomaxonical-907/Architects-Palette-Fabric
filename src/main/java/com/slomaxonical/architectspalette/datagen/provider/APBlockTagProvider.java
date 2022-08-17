package com.slomaxonical.architectspalette.datagen.provider;

import com.slomaxonical.architectspalette.blocks.util.StoneBlockSet;
import com.slomaxonical.architectspalette.registry.APBlocks;
import com.slomaxonical.architectspalette.registry.APTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

import static com.slomaxonical.architectspalette.blocks.util.StoneBlockSet.SetComponent.*;

public class APBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public APBlockTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    private FabricTagBuilder<Block> getOrCreateTagBuilder(Identifier id) {
        TagKey<Block> tag = TagKey.of(Registry.BLOCK_KEY, id);
        return this.getOrCreateTagBuilder(tag);
    }
    private void addSets(TagKey<Block> tag, Block... baseBlocks) {
        addSets(tag,true,true,true,true,baseBlocks);
    }
    private void addSets(TagKey<Block> tag,boolean tryBlock,boolean tryStairs,boolean trySlabs,boolean tryWall, Block... baseBlocks){
        ArrayList<StoneBlockSet> sets = new ArrayList<>();
        for (Block base: baseBlocks) {
            StoneBlockSet.BlockSets.forEach(set->{
                if (set.getBase().equals(base)) sets.add(set);
            });
        }
        for (StoneBlockSet set:sets) {
            if (tryBlock) this.getOrCreateTagBuilder(tag).add(set.getBase());
            if(set.getPart(STAIRS)!=null && tryStairs) this.getOrCreateTagBuilder(tag).add(set.getPart(STAIRS));
            if(set.getPart(STAIRS)!=null && trySlabs){
                this.getOrCreateTagBuilder(tag).add(set.getPart(STAIRS));
                this.getOrCreateTagBuilder(tag).add(set.getPart(VERTICAL_SLAB));
            }
            if(set.getPart(WALL)!=null && tryWall) this.getOrCreateTagBuilder(tag).add(set.getPart(WALL));
        }
    }
    private void addOreBricks(TagKey<Block> tag,boolean addBlock,boolean addStairs,boolean addSlabs,boolean addWall){
        for (StoneBlockSet set : StoneBlockSet.oreBrickSets) {
            if (addBlock) this.getOrCreateTagBuilder(tag).add(set.getBase());
            if(addStairs) this.getOrCreateTagBuilder(tag).add(set.getPart(STAIRS));
            if(addSlabs){
                this.getOrCreateTagBuilder(tag).add(set.getPart(STAIRS));
                this.getOrCreateTagBuilder(tag).add(set.getPart(VERTICAL_SLAB));
            }
            if(addWall) this.getOrCreateTagBuilder(tag).add(set.getPart(WALL));
        }
    }
    @Override
    protected void generateTags() {
        addSets(APTags.WIZARD_BLOCKS,APBlocks.WARDSTONE,APBlocks.WARDSTONE_BRICKS);
        this.getOrCreateTagBuilder(APTags.WIZARD_BLOCKS)
                .add(APBlocks.CHISELED_WARDSTONE)
                .add(APBlocks.WARDSTONE_PILLAR)
                .add(APBlocks.WARDSTONE_LAMP);

        addSets(APTags.GREEN_FIRE_SUPPORTING,APBlocks.NETHER_BRASS,APBlocks.CUT_NETHER_BRASS,APBlocks.SMOOTH_NETHER_BRASS);
        this.getOrCreateTagBuilder(APTags.GREEN_FIRE_SUPPORTING)
                .add(APBlocks.NETHER_BRASS_PILLAR);
        this.getOrCreateTagBuilder(APTags.CRYSTAL_REPLACEABLE)
                .add(Blocks.NETHER_WART_BLOCK)
                .add(Blocks.WARPED_WART_BLOCK);

        this.getOrCreateTagBuilder(APTags.TWISTED_LOGS)
                .add(APBlocks.TWISTED_LOG)
                .add(APBlocks.STRIPPED_TWISTED_LOG)
                .add(APBlocks.TWISTED_WOOD)
                .add(APBlocks.STRIPPED_TWISTED_WOOD);

        this.getOrCreateTagBuilder(APTags.CAGE_LANTERNS)
                .add(APBlocks.ALGAL_CAGE_LANTERN)
                .add(APBlocks.GLOWSTONE_CAGE_LANTERN)
                .add(APBlocks.REDSTONE_CAGE_LANTERN);

        //add Pickaxe sets here
        addSets(BlockTags.PICKAXE_MINEABLE,
                APBlocks.ABYSSALINE_BRICKS,
                APBlocks.ABYSSALINE_TILES,
                APBlocks.ALGAL_BRICKS,
                APBlocks.BASALT_TILES,
                APBlocks.CALCITE_BRICKS,
                APBlocks.DRIPSTONE_BRICKS,
                APBlocks.ENTWINE,
                APBlocks.FLINT_TILES,
                APBlocks.GILDED_SANDSTONE,
                APBlocks.MYONITE,
                APBlocks.MYONITE_BRICKS,
                APBlocks.MUSHY_MYONITE_BRICK,
                APBlocks.OLIVESTONE_BRICKS,
                APBlocks.OLIVESTONE_TILE,
                APBlocks.OSSEOUS_BRICK,
                APBlocks.OVERGROWN_ALGAL_BRICK,
                APBlocks.PLATING_BLOCK,
                APBlocks.POLISHED_GLOWSTONE,
                APBlocks.POLISHED_PACKED_ICE,
                APBlocks.SUNMETAL,
                APBlocks.TUFF_BRICKS,
                APBlocks.WARPSTONE,
                APBlocks.WITHERED_OSSEOUS_BRICK,
                APBlocks.NETHER_BRASS,
                APBlocks.CUT_NETHER_BRASS,
                APBlocks.SMOOTH_NETHER_BRASS,
                APBlocks.WARDSTONE,
                APBlocks.WARDSTONE_BRICKS,
                APBlocks.ONYX,
                APBlocks.ESOTERRACK
        );
        this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(APBlocks.ABYSSALINE)
                .add(APBlocks.ABYSSALINE_LAMP_BLOCK)
                .add(APBlocks.ABYSSALINE_PILLAR)
                .add(APBlocks.ALGAL_CAGE_LANTERN)
                .add(APBlocks.ALGAL_LAMP)
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
                .add(APBlocks.DRIPSTONE_LAMP)
                .add(APBlocks.DRIPSTONE_PILLAR)
                .add(APBlocks.ENTWINE_BARS)
                .add(APBlocks.ENTWINE_PILLAR)
                .add(APBlocks.FLINT_BLOCK)
                .add(APBlocks.FLINT_PILLAR)
                .add(APBlocks.GILDED_SANDSTONE_PILLAR)
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
                .add(APBlocks.OLIVESTONE_PILLAR)
                .add(APBlocks.OSSEOUS_PILLAR)
                .add(APBlocks.OSSEOUS_SKULL)
                .add(APBlocks.PACKED_ICE_PILLAR)
                .add(APBlocks.PIPE)
                .add(APBlocks.POTTED_TWISTED_SAPLING)
                .add(APBlocks.REDSTONE_CAGE_LANTERN)
                .add(APBlocks.RUNIC_GLOWSTONE)
                .add(APBlocks.SCUTE_BLOCK)
                .add(APBlocks.SUNMETAL_BARS)
                .add(APBlocks.SUNMETAL_PILLAR)
                .add(APBlocks.SUNSTONE)
                .add(APBlocks.TUFF_LAMP)
                .add(APBlocks.TUFF_PILLAR)
                .add(APBlocks.TWISTING_BLACKSTONE)
                .add(APBlocks.TWISTING_BLACKSTONE_BRICKS)
                .add(APBlocks.WEEPING_BLACKSTONE)
                .add(APBlocks.WEEPING_BLACKSTONE_BRICKS)
                .add(APBlocks.WITHERED_BONE_BLOCK)
                .add(APBlocks.WITHERED_OSSEOUS_PILLAR)
                .add(APBlocks.WITHERED_OSSEOUS_SKULL)
                .add(APBlocks.WITHER_LAMP)
                .add(APBlocks.HELIODOR_ROD)
                .add(APBlocks.EKANITE_ROD)
                .add(APBlocks.MONAZITE_ROD)
                .add(APBlocks.UNOBTANIUM_BLOCK)
                .add(APBlocks.NETHER_BRASS_PILLAR)
                .add(APBlocks.NETHER_BRASS_CHAIN)
                .add(APBlocks.NETHER_BRASS_LANTERN)
                .add(APBlocks.WARDSTONE_PILLAR)
                .add(APBlocks.WARDSTONE_LAMP)
                .add(APBlocks.ONYX_PILLAR)
                .add(APBlocks.ESOTERRACK_PILLAR);
        addOreBricks(BlockTags.PICKAXE_MINEABLE,true,true,true,true);
        for (List<Block> l: APBlocks.chiseledNcrackedOres.values()) l.forEach((b)->this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(b));

        addSets(BlockTags.AXE_MINEABLE,APBlocks.ENTRAILS);
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
                .add(APBlocks.TWISTED_PRESSURE_PLATE)
                .add(APBlocks.TWISTED_RAILING)
                .add(APBlocks.TWISTED_TRAPDOOR)
                .add(APBlocks.TWISTED_WOOD)
                .add(APBlocks.WARPED_BOARDS)
                .add(APBlocks.WARPED_RAILING);
        addSets(BlockTags.AXE_MINEABLE,APBlocks.TWISTED_PLANKS);

        this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(APBlocks.COARSE_SNOW);

        this.getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(APBlocks.ENDER_PEARL_BLOCK)
                .add(APBlocks.TWISTED_LEAVES);

        addSets(BlockTags.DRAGON_IMMUNE,APBlocks.ENTWINE);
        this.getOrCreateTagBuilder(BlockTags.DRAGON_IMMUNE)
                .add(APBlocks.ENTWINE_PILLAR)
                .add(APBlocks.ENTWINE_BARS)
                .add(APBlocks.CHISELED_ENTWINE)
                .add(APBlocks.HEAVY_END_STONE_BRICKS)
                .add(APBlocks.HEAVY_CRACKED_END_STONE_BRICKS)
                .add(APBlocks.CHISELED_END_STONE_BRICKS)
                .add(APBlocks.CHORAL_END_STONE_BRICKS)
                .add(APBlocks.CRACKED_END_STONE_BRICKS)
                .add(Blocks.END_STONE_BRICKS);

        addSets(BlockTags.MUSHROOM_GROW_BLOCK,true,false,true,false,APBlocks.MYONITE,APBlocks.MYONITE_BRICKS,APBlocks.MUSHY_MYONITE_BRICK);

        this.getOrCreateTagBuilder(BlockTags.FIRE).add(APBlocks.NETHER_BRASS_FIRE);//this tag see
        this.getOrCreateTagBuilder(BlockTags.PLANKS).add(APBlocks.TWISTED_PLANKS);
        this.getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(APBlocks.TWISTED_FENCE_GATE);
        this.getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(APBlocks.POTTED_TWISTED_SAPLING);
        this.getOrCreateTagBuilder(BlockTags.LEAVES).add(APBlocks.TWISTED_LEAVES);
        this.getOrCreateTagBuilder(BlockTags.SAPLINGS).add(APBlocks.TWISTED_SAPLING);
        this.getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(APTags.TWISTED_LOGS); //this tag see
        this.getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(APBlocks.TWISTED_BUTTON);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(APBlocks.TWISTED_DOOR);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(APBlocks.TWISTED_TRAPDOOR);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(APBlocks.TWISTED_FENCE);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(APBlocks.TWISTED_PRESSURE_PLATE);
        addSets(BlockTags.WOODEN_STAIRS,false,true,false,false,APBlocks.TWISTED_PLANKS);
        addSets(BlockTags.WOODEN_SLABS,false,false,true,false,APBlocks.TWISTED_PLANKS);
        this.getOrCreateTagBuilder(BlockTags.WALL_POST_OVERRIDE).addTag(APTags.CAGE_LANTERNS);//this tag see
        //SLABS

        for (StoneBlockSet set: StoneBlockSet.BlockSets) {
            if (set.getPart(STAIRS) !=null) this.getOrCreateTagBuilder(BlockTags.SLABS).add(set.getPart(STAIRS));
        }
        addOreBricks(BlockTags.SLABS,false,false,true,false);
        //STAIRS
        for (StoneBlockSet set: StoneBlockSet.BlockSets) {
            if (set.getPart(STAIRS) !=null) this.getOrCreateTagBuilder(BlockTags.STAIRS).add(set.getPart(STAIRS));
        }
        addOreBricks(BlockTags.STAIRS,false,true,false,false);
        //WALLS
        for (StoneBlockSet set: StoneBlockSet.BlockSets) {
            if (set.getPart(WALL) !=null) this.getOrCreateTagBuilder(BlockTags.WALLS).add(set.getPart(WALL));
        }
        addOreBricks(BlockTags.WALLS,false,false,false,true);

    //NEEDS_TIER_TOOL
        //Diamond
        this.getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(APBlocks.ABYSSALINE)
                .add(APBlocks.ABYSSALINE_LAMP_BLOCK)
                .add(APBlocks.ABYSSALINE_PILLAR)
                .add(APBlocks.CHISELED_ABYSSALINE_BRICKS);
        //Iron
        addSets(BlockTags.NEEDS_IRON_TOOL,
                APBlocks.PLATING_BLOCK,
                APBlocks.NETHER_BRASS,
                APBlocks.CUT_NETHER_BRASS,
                APBlocks.SMOOTH_NETHER_BRASS
        );
        this.getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(APBlocks.PIPE)
                .add(APBlocks.UNOBTANIUM_BLOCK)
                .add(APBlocks.NETHER_BRASS_PILLAR)
                .add(APBlocks.NETHER_BRASS_CHAIN)
                .add(APBlocks.NETHER_BRASS_LANTERN);
        //Stone
        addSets(BlockTags.NEEDS_STONE_TOOL,
                APBlocks.ENTWINE,
                APBlocks.FLINT_BLOCK,
                APBlocks.FLINT_TILES,
                APBlocks.SUNMETAL,
                APBlocks.WARDSTONE,
                APBlocks.WARDSTONE_BRICKS
        );
        this.getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(APBlocks.ENTWINE_PILLAR)
                .add(APBlocks.ENTWINE_BARS)
                .add(APBlocks.CHISELED_ENTWINE)
                .add(APBlocks.FLINT_PILLAR)
                .add(APBlocks.SUNMETAL_PILLAR)
                .add(APBlocks.CHISELED_SUNMETAL_BLOCK)
                .add(APBlocks.SUNMETAL_BARS)
                .add(APBlocks.WARDSTONE_PILLAR)
                .add(APBlocks.WARDSTONE_LAMP);

    }
}
