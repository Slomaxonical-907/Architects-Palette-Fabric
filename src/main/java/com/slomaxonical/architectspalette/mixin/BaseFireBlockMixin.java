package com.slomaxonical.architectspalette.mixin;

import com.slomaxonical.architectspalette.blocks.GreenFireBlock;
import com.slomaxonical.architectspalette.registry.APBlocks;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;


@Mixin(AbstractFireBlock.class)
public class BaseFireBlockMixin {

    @Inject(method = "getState", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    //arguments for target method, callback info, then captured locals
    private static void architectsPaletteAddGreenFireMixin(BlockView world, BlockPos pos, CallbackInfoReturnable<BlockState> cir, BlockPos below, BlockState belowState) {
        if (GreenFireBlock.canHeGreen(world,below)) {
            cir.setReturnValue(APBlocks.NETHER_BRASS_FIRE.getDefaultState());
        }
    }
}
