package com.slomaxonical.architectspalette.common.event;

import com.slomaxonical.architectspalette.core.crafting.WarpingRecipe;
import com.slomaxonical.architectspalette.core.registry.APSounds;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import java.util.Optional;


public class ChangeDimensionHandler {
    public static void onDimensionsChanged (ItemEntity itemIn, ServerWorld server) {
        RegistryKey<World> dimension = server.getRegistryKey();
        Identifier dimensionId = dimension.getValue();
        ItemStack item = itemIn.getStack();
        for (Recipe<?> recipe : itemIn.world.getRecipeManager().listAllOfType(WarpingRecipe.TYPE)) {
            if (recipe instanceof WarpingRecipe) {
                WarpingRecipe warpingRecipe = (WarpingRecipe) recipe;
                 //compareTo returns 0 if they're the same
                boolean dimensionPassed = dimensionId.compareTo(warpingRecipe.getDimension()) == 0;
                dimensionPassed = dimensionPassed || (itemIn.world.getRegistryKey().getValue().compareTo(warpingRecipe.getDimension()) == 0);
                if (dimensionPassed && (warpingRecipe.getInput().test(item))) {
                    itemIn.world.playSound(null, itemIn.getX(), itemIn.getY(), itemIn.getZ(), APSounds.ITEM_WARPS, SoundCategory.BLOCKS, 1F, 1F);
                    int n = item.getCount();
                    ItemStack out = warpingRecipe.getOutput().copy();
                    out.setCount(n);
                    itemIn.setStack(out);
                    break;
                }
            }
        }
    }
    //New
    public static class TransformationInv extends SimpleInventory {
        public TransformationInv() {
            super(1);
        }
    }

    public static TransformationInv transformationInv = new TransformationInv();

    private static Optional<WarpingRecipe> getRecipe(ItemStack item, World world) {
        transformationInv.setStack(0, item);
        return WarpingRecipe.Type.INSTANCE.find(transformationInv, world);
    }

    public static ItemStack getTransformedItem(ItemStack itemIn, World world) {
        Optional<WarpingRecipe> recipe = getRecipe(itemIn, world);
//        if (recipe.isPresent()) {
//            return recipe.get().craft(transformationInv);
//        }
//        return null;
        return recipe.map(warpingRecipe -> warpingRecipe.craft(transformationInv)).orElse(null);
    }


    public static void warpItem(ItemEntity itemIn, ServerWorld worldIn) {
        System.out.println("warped alright");
        ItemStack baseStack = itemIn.getStack();
        ItemStack recipeStack = new ItemStack(baseStack.getItem(), 1);
        ItemStack resultStack = getTransformedItem(recipeStack, worldIn);
        if (resultStack != null) {
            resultStack.setCount(baseStack.getCount());
            itemIn.setStack(resultStack);
            itemIn.world.playSound(null, itemIn.getX(), itemIn.getY(), itemIn.getZ(), APSounds.ITEM_WARPS, SoundCategory.BLOCKS, 1F, 1F);
        }
    }



}

