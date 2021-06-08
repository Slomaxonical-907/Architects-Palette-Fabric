package com.slomaxonical.architectspalette.common.blocks.abyssaline;

import com.slomaxonical.architectspalette.common.APBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public interface IAbyssalineChargeable {
    default boolean isCharged(BlockState stateIn){
        return stateIn.get(APBlockSettings.CHARGED);
    }
    default Direction getSourceDirection(BlockState stateIn) {
        return stateIn.get(NewAbyssalineBlock.CHARGE_SOURCE);
    }
    //stateIn is the block accepting or outputting charge, faceIn is the side that the block asking is on
    default boolean outputsChargeFrom(BlockState stateIn, Direction faceIn) {
        return faceIn != getSourceDirection(stateIn) && isCharged(stateIn);
    }
    default boolean acceptsChargeFrom(BlockState stateIn, Direction faceIn) {
        return true;
    }
    default BlockPos getSourceOffset(BlockState stateIn) {
        return new BlockPos(getSourceDirection(stateIn).getVector());
    }
    default BlockState getStateWithCharge(BlockState stateIn, boolean charged) {
        return stateIn.with(APBlockSettings.CHARGED, charged);
    }
    //faceOut is the place that power is coming from, so it should point to that.
    default BlockState getStateWithChargeDirection(BlockState stateIn, Direction faceOut) {
        return stateIn.with(NewAbyssalineBlock.CHARGE_SOURCE, faceOut);
    }
    //if true, block's power will supersede the other's accept charge check, if possible
    default boolean pushesPower(BlockState stateIn) {
        return false;
    }
    default boolean pullsPowerFrom(BlockState stateIn, Direction faceIn) {
        return false;
    }
}
