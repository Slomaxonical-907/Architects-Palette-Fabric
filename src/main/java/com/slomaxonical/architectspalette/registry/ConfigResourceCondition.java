package com.slomaxonical.architectspalette.registry;

import com.google.gson.*;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import static com.slomaxonical.architectspalette.ArchitectsPalette.CONFIGS;


public class ConfigResourceCondition {
    public static final Identifier CONFIG = new Identifier("architects_palette:config_enabled");
    static {
        ResourceConditions.register(CONFIG, ConfigResourceCondition::configCheck);
    }

    public static ConditionJsonProvider configEnabeled(String config) {
        return new ConditionJsonProvider() {
            @Override
            public void writeParameters(JsonObject object) {
                object.addProperty("config", config);
            }

            @Override
            public Identifier getConditionId() {
                return CONFIG;
            }
        };
    }

    public static boolean configCheck(JsonObject object) {
        String config = JsonHelper.getString(object, "config");
        if (config.equals("enableVerticalSlabs")){
            return CONFIGS.enableVerticalSlabs();
        }else {
        throw new JsonParseException("Invalid config: " + config);
        }
    }

    public static void init(){}
}
