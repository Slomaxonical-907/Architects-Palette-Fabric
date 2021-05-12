package com.slomaxonical.architectspalette.common;

import com.slomaxonical.architectspalette.common.blocks.abyssaline.AbyssalineHelper;
//import com.slomaxonical.architectspalette.common.blocks.abyssaline.ChiseledAbyssalineBlock;
import com.slomaxonical.architectspalette.common.blocks.CageLanternBlock;
import com.slomaxonical.architectspalette.common.blocks.abyssaline.ChiseledAbyssalineBlock;
import com.slomaxonical.architectspalette.core.registry.APSounds;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;


import static com.slomaxonical.architectspalette.common.blocks.abyssaline.AbyssalineBlock.CHARGED;

public class APBlockSettings {
	public static final FabricBlockSettings ABYSSALINE = FabricBlockSettings.copyOf(Blocks.OBSIDIAN)
			.strength(25.0F, 600.0F)
			.emissiveLighting(AbyssalineHelper::needsPostProcessing)
			.postProcess(AbyssalineHelper::needsPostProcessing)
			.allowsSpawning(AbyssalineHelper::allowsMobSpawning);
	public static final FabricBlockSettings CHISELED_ABYSSALINE = FabricBlockSettings.copyOf(Blocks.OBSIDIAN)
			.strength(25.0F, 600.0F)
			.emissiveLighting(AbyssalineHelper::needsPostProcessing)
			.postProcess(AbyssalineHelper::needsPostProcessing)
			.allowsSpawning(AbyssalineHelper::allowsMobSpawning)
			.luminance(ChiseledAbyssalineBlock.getLuminance());

	public static FabricBlockSettings Meat(MaterialColor color) {
		return FabricBlockSettings.of(Material.GOURD, color).strength(1.0F).sounds(BlockSoundGroup.CORAL);
	}

	public static final FabricBlockSettings FLINT = FabricBlockSettings.of(Material.STONE, MaterialColor.GRAY).strength(3.0F, 16.0F).requiresTool();
	public static final FabricBlockSettings LIMESTONE = FabricBlockSettings.copyOf(Blocks.STONE);
	public static final FabricBlockSettings SUNMETAL = FabricBlockSettings.of(Material.METAL, MaterialColor.BROWN).strength(2.0F, 8.0F).sounds(BlockSoundGroup.NETHERITE).requiresTool();
	 // Should be less slippery?
	public static final FabricBlockSettings BUILDING_ICE = FabricBlockSettings.copyOf(Blocks.PACKED_ICE).slipperiness(0.8F);
	 // As Prismarine
	public static final FabricBlockSettings OLIVESTONE = FabricBlockSettings.of(Material.STONE, MaterialColor.GREEN_TERRACOTTA).strength(1.5F, 6.0F).requiresTool();
	 // As Nether Bricks
	public static final FabricBlockSettings ALGAL_BRICK = FabricBlockSettings.of(Material.STONE, MaterialColor.CYAN_TERRACOTTA).strength(2.0F, 6.0F).sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool();

	public static final FabricBlockSettings ENTWINE = FabricBlockSettings.of(Material.STONE, MaterialColor.CYAN).strength(3.0F, 6.0F).sounds(APSounds.APSoundTypes.ENTWINE).requiresTool();
	public static final FabricBlockSettings ENDER_PEARL = FabricBlockSettings.of(Material.STONE, MaterialColor.CYAN).strength(1.5F).sounds(APSounds.APSoundTypes.ENDER_PEARL);
	public static final FabricBlockSettings PLATING = FabricBlockSettings.of(Material.METAL, MaterialColor.STONE).strength(4.0F, 10.0F).sounds(BlockSoundGroup.NETHERITE).requiresTool();

	public static final FabricBlockSettings MOLTEN_BRICK = FabricBlockSettings.of(Material.STONE, MaterialColor.NETHER)
			.requiresTool()
			.strength(2.0F, 6.0F)
			.luminance((state) -> 3)
			.postProcess((a, b, c) -> true)
			.emissiveLighting((a, b, c) -> true);

	public static final FabricBlockSettings CAGE_LANTERN = FabricBlockSettings.of(Material.METAL)
			.requiresTool()
			.emissiveLighting((state, reader, pos) -> state.get(CageLanternBlock.LIT))
			.postProcess((state, reader, pos) -> state.get(CageLanternBlock.LIT))
			.strength(3.5f)
			.sounds(BlockSoundGroup.LANTERN)
			.nonOpaque();

	public static final FabricBlockSettings ACACIA_TOTEM = FabricBlockSettings.of(Material.WOOD, MaterialColor.ORANGE)
			.strength(2.0F)
			.sounds(BlockSoundGroup.WOOD);

	// This makes a new property each time so that setting the door to not solid doesn't interfere.
	// That might not be a thing but I don't care to come up with a way of checking.
	public static AbstractBlock.Settings TwistedWood() {
		return AbstractBlock.Settings.of(Material.WOOD, MaterialColor.PURPLE).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
	}
	public static AbstractBlock.Settings TwistedWood(boolean redstoneComponent) {
		AbstractBlock.Settings p = TwistedWood();
		if (redstoneComponent) {
			return p.noCollision().strength(0.5f);
		}
		return p;
	}
// i think not needed:

//	private static boolean setEmmisiveRendering(BlockState state, BlockView reader, BlockPos pos) {
//		return state.get(ChiseledAbyssalineBlock.LIGHT) / 2 > 4;
//	}
//
//	private static boolean needsPostProcessing(BlockState state, BlockView reader, BlockPos pos) {
//		int light = state.get(ChiseledAbyssalineBlock.LIGHT) / 2;
//		return light < 4;
//	}

}