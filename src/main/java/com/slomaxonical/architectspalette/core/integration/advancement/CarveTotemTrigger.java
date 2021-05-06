package com.slomaxonical.architectspalette.core.integration.advancement;

import com.google.gson.JsonObject;
import com.slomaxonical.architectspalette.core.ArchitectsPalette;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class CarveTotemTrigger extends AbstractCriterion<CarveTotemTrigger.Instance> {

    private static final Identifier ID = new Identifier(ArchitectsPalette.MOD_ID, "carve_totem");

    public Identifier getId() {
        return ID;
    }

    public void trigger(ServerPlayerEntity player) {
        this.test(player, Instance::test);
    }

    @Override
    protected Instance conditionsFromJson(JsonObject json, EntityPredicate.Extended player, AdvancementEntityPredicateDeserializer conditionsParser) {
        return new Instance(player);
    }

    public static class Instance extends AbstractCriterionConditions {

        public Instance(EntityPredicate.Extended player) {
            super(CarveTotemTrigger.ID, player);
        }

        public static Instance simple() {
            return new Instance(EntityPredicate.Extended.EMPTY);
        }

        public boolean test() {
            return true;
        }
    }
}
