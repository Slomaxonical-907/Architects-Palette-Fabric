package com.slomaxonical.architectspalette.common.blocks;

//import com.slomaxonical.architectspalette.core.integration.APCriterion;
import com.slomaxonical.architectspalette.core.registry.APBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class TotemBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public final TotemWingBlock WING_BLOCK;
    public final TotemFace totemType;

    public TotemBlock(Settings properties, TotemWingBlock wingBlock, TotemFace face) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
        this.WING_BLOCK = wingBlock;
        this.totemType = face;
    }

    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(FACING, context.getPlayerFacing().getOpposite());
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public BlockState rotate(BlockState state, BlockRotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.get(FACING)));
    }

    public void onPlaced(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onPlaced(worldIn, pos, state, placer, stack);
        if (!worldIn.isClient) {
            BlockPos blockpos = pos.offset(state.get(FACING).rotateYClockwise());
            boolean waterlogged = worldIn.getFluidState(blockpos).getFluid() == Fluids.WATER;
            if (worldIn.isAir(blockpos) || waterlogged) {
                worldIn.setBlockState(blockpos, this.WING_BLOCK.getDefaultState()
                        .with(TotemWingBlock.FACING, state.get(FACING).rotateYClockwise())
                        .with(TotemWingBlock.WATERLOGGED, waterlogged), 3);
            }
            blockpos = pos.offset(state.get(FACING).rotateYCounterclockwise());
            waterlogged = worldIn.getFluidState(blockpos).getFluid() == Fluids.WATER;
            if (worldIn.isAir(blockpos) || waterlogged) {
                worldIn.setBlockState(blockpos, this.WING_BLOCK.getDefaultState()
                        .with(TotemWingBlock.FACING, state.get(FACING).rotateYCounterclockwise())
                        .with(TotemWingBlock.WATERLOGGED, waterlogged), 3);
            }
            worldIn.updateNeighbors(pos, Blocks.AIR);
            state.updateNeighbors(worldIn, pos, 3);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockHitResult hit) {
        ItemStack playerItem = player.getStackInHand(handIn);
        if (playerItem.getItem() instanceof AxeItem) {
            BlockState newState = this.totemType.getStrip().getDefaultState().with(FACING, state.get(FACING));
            worldIn.setBlockState(pos, newState, 3);
            playerItem.damage(1, player, (p) -> p.sendToolBreakStatus(handIn));
            worldIn.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1, 1);
           //todo: remember to uncomment that and the import up once ur doing the advancements/criterions
            /* if (player instanceof ServerPlayerEntity) {
                APCriterion.CARVE_TOTEM.trigger((ServerPlayerEntity) player);
            }*/
            return ActionResult.success(worldIn.isClient);
        }
        return ActionResult.FAIL;
    }

    public enum TotemFace {
        GRINNING,
        PLACID,
        SHOCKED,
        BLANK;

        public Block getStrip() {
            switch(this){
                case GRINNING: return APBlocks.PLACID_ACACIA_TOTEM;
                case PLACID: return APBlocks.SHOCKED_ACACIA_TOTEM;
                case SHOCKED: return APBlocks.BLANK_ACACIA_TOTEM;
                default: return APBlocks.GRINNING_ACACIA_TOTEM;
            }
        }
    }

}
