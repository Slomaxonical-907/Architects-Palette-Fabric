package com.slomaxonical.architectspalette.core.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.slomaxonical.architectspalette.core.ArchitectsPalette;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import org.jetbrains.annotations.Nullable;
import java.util.Optional;

import static com.slomaxonical.architectspalette.core.ArchitectsPalette.MOD_ID;

public class WarpingRecipe implements Recipe<Inventory> {

    public static final WarpingSerializer SERIALIZER = new WarpingSerializer();
    public static RecipeType<WarpingRecipe> TYPE = new Type();

    private final Ingredient input;
    private final ItemStack output;
    private final Identifier dimension;
    private final Identifier id;

    public WarpingRecipe(Identifier id, Ingredient input, ItemStack output, Identifier dimension) {
        this.input = input;
        this.output = output;
        this.dimension = dimension;
        this.id = id;
    }

    public Ingredient getInput() {
        return this.input;
    }

    public Identifier getDimension() {
        return this.dimension;
    }


    @Override
    public boolean matches(Inventory inv, World worldIn) {
        return this.input.test(inv.getStack(0)) && (this.dimension.compareTo(worldIn.getRegistryKey().getValue()) == 0);
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return false;
    }

    @Override
    public ItemStack craft(Inventory inv) {
        return this.getOutput().copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getOutput() {
        return this.output;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

//chaged warpingrecipe.type / .serial to this
    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return TYPE;
    }


    private static class WarpingSerializer implements RecipeSerializer<WarpingRecipe> {

        private WarpingSerializer() {
//            this.setRegistryName(new Identifier(ArchitectsPalette.MOD_ID, "warping"));
        }
        public static final WarpingSerializer INSTANCE = new WarpingSerializer();
        public static final Identifier ID = new Identifier(ArchitectsPalette.MOD_ID.concat(":warping"));

        @Override
        public WarpingRecipe read(Identifier recipeId, JsonObject json) {

            final JsonElement inputElement = JsonHelper.hasArray(json, "ingredient") ?
                    JsonHelper.getArray(json, "ingredient") : JsonHelper.getObject(json, "ingredient");
            final Ingredient input = Ingredient.fromJson(inputElement);
            final ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result"));
            final Identifier dimensionId = new Identifier(JsonHelper.getString(json, "dimension"));

            return new WarpingRecipe(recipeId, input, output, dimensionId);
        }
        @Override
        public void write(PacketByteBuf buffer, WarpingRecipe recipe) {
            recipe.input.write(buffer);
            buffer.writeItemStack(recipe.output);
            buffer.writeIdentifier(recipe.dimension);
        }

        @Nullable
        @Override
        public WarpingRecipe read(Identifier recipeId, PacketByteBuf buffer) {

            final Ingredient input = Ingredient.fromPacket(buffer);
            final ItemStack output = buffer.readItemStack();
            final Identifier dimensionId = buffer.readIdentifier();

            return new WarpingRecipe(recipeId, input, output, dimensionId);
        }


    }
//    class ExampleRecipeJsonFormat {
//        JsonObject inputA;
//        JsonObject inputB;
//        String outputItem;
//        int outputAmount;
//    }
    public static class Type implements RecipeType<WarpingRecipe> {

        private Type() {}
        public static final Type INSTANCE = new Type();
        @Override
        public String toString() {
            return ArchitectsPalette.MOD_ID.concat(":warping");
        }

        public <C extends Inventory> Optional<WarpingRecipe> find(C inv, World world) {
            return world.getRecipeManager()
                    .getFirstMatch (WarpingRecipe.TYPE, inv, world);
        }
    }

    public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerSerializer(S serializer) {
        return Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MOD_ID, "warping"), serializer);
    }
    public static <T extends Recipe<?>> RecipeType<T> registerType() {
        return Registry.register(Registry.RECIPE_TYPE, new Identifier(MOD_ID, "warping"), new RecipeType<T>() {
            public String toString() {
                return ArchitectsPalette.MOD_ID.concat(":warping");
            }
        });
    }

    public static void registerRecipe(){
        registerSerializer(SERIALIZER);
        registerType();
    }

}
