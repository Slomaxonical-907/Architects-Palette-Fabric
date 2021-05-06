package com.slomaxonical.architectspalette.core.registry;

import com.slomaxonical.architectspalette.common.blockentity.ChiseledAbyssalineBlockEntity;
import com.slomaxonical.architectspalette.core.ArchitectsPalette;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import static com.slomaxonical.architectspalette.core.ArchitectsPalette.MOD_ID;

public class APBlockEntities {

	public static BlockEntityType<ChiseledAbyssalineBlockEntity> CHISELED_ABYSSALINE_BLOCK_ENTITY;

	public static void registerBlockEntity(){
		Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":chiseled_abyssaline_block_entity", BlockEntityType.Builder.create(ChiseledAbyssalineBlockEntity::new, APBlocks.CHISELED_ABYSSALINE_BRICKS).build(null));
	}

//	public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);

}