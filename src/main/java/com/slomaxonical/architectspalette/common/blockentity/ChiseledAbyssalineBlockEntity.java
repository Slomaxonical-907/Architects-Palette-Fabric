package com.slomaxonical.architectspalette.common.blockentity;

import com.slomaxonical.architectspalette.common.blocks.abyssaline.ChiseledAbyssalineBlock;
import com.slomaxonical.architectspalette.common.blocks.abyssaline.NewAbyssalineBlock;
import com.slomaxonical.architectspalette.core.ArchitectsPalette;
import com.slomaxonical.architectspalette.core.registry.APBlockEntities;
import com.slomaxonical.architectspalette.core.registry.APBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Tickable;
import net.minecraft.world.World;

public class ChiseledAbyssalineBlockEntity extends BlockEntity implements Tickable {
	private boolean decrementing;
	private int frame;
	private int pausedTicks;
	
	public ChiseledAbyssalineBlockEntity() {
		super(APBlockEntities.CHISELED_ABYSSALINE_BLOCK_ENTITY);
	}

	@Override
	public void tick() {
		BlockState state = this.getCachedState();
		World world = this.getWorld();
		assert world != null;
		if(!world.isClient && state != null && state.getBlock() == APBlocks.CHISELED_ABYSSALINE_BRICKS) {
			if(state.get(NewAbyssalineBlock.CHARGED)) {
				if(this.pausedTicks > 0) {
					this.pausedTicks--;
				} else {
					if(this.decrementing && this.frame > 0) {
						this.frame--;
						
						if(this.frame == 0) {
							this.pausedTicks = 10;
							this.decrementing = false;
						}
					} else if(!this.decrementing && this.frame < 20) {
						this.frame++;
						
						if(this.frame == 20) {
							this.pausedTicks = 10;
							this.decrementing = true;
						}
					}
				}
				world.setBlockState(this.pos, state.with(ChiseledAbyssalineBlock.LIGHT, this.frame), 2);
			} else {
				world.setBlockState(this.pos, state.with(ChiseledAbyssalineBlock.LIGHT, 0), 2);
				this.decrementing = false;
				this.frame = 0;
				this.pausedTicks = 0;
			}
		}
	}
}