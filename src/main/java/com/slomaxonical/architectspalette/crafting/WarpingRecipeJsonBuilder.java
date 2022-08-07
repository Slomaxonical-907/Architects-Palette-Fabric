package com.slomaxonical.architectspalette.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class WarpingRecipeJsonBuilder {
    private final Ingredient input;
    private final Item output;
    private final Identifier dimension;
    private final RecipeSerializer<?> serializer;

    public WarpingRecipeJsonBuilder(RecipeSerializer<?> serializer, Ingredient input, Item output, Identifier dimension) {
        this.serializer = serializer;
        this.input = input;
        this.output = output;
        this.dimension = dimension;
    }

    public static WarpingRecipeJsonBuilder create(Ingredient input, Item output, Identifier dimension) {
        return new WarpingRecipeJsonBuilder(WarpingRecipe.SERIALIZER, input, output, dimension);
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter, String recipeId) {
        this.offerTo(exporter, new Identifier(recipeId));
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier recipeId) {
        exporter.accept(new WarpingRecipeJsonProvider(recipeId, this.serializer, this.input, this.output, this.dimension));
    }

    public static class WarpingRecipeJsonProvider implements RecipeJsonProvider {
        private final Identifier recipeId;
        private final Ingredient input;
        private final Item output;
        private final Identifier dimension;
        private final RecipeSerializer<?> serializer;

        public WarpingRecipeJsonProvider(Identifier recipeId, RecipeSerializer<?> serializer, Ingredient input, Item output, Identifier dimension) {
            this.recipeId = recipeId;
            this.serializer = serializer;
            this.input = input;
            this.output = output;
            this.dimension = dimension;
        }

        @Override
        public void serialize(JsonObject json) {
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(this.input.toJson());
            json.add("ingredient", jsonArray);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item", Registry.ITEM.getId(this.output).toString());
            json.add("result",jsonObject);

            json.addProperty("dimension", this.dimension.toString());
        }

        @Override
        public Identifier getRecipeId() {
            return this.recipeId;
        }

        @Override
        public RecipeSerializer<?> getSerializer() {
            return this.serializer;
        }

        @Nullable
        @Override
        public JsonObject toAdvancementJson() {
            return null;
        }

        @Nullable
        @Override
        public Identifier getAdvancementId() {
            return null;
        }

    }
}
