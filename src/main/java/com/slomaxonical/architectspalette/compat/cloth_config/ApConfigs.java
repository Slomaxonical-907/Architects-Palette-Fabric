package com.slomaxonical.architectspalette.compat.cloth_config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "architects_palette")
public class ApConfigs implements ConfigData {
    String _comment = "Chance of updating adjacent moon/sunstone blocks, cascading updates and helping larger areas stay in sync (RANGE:0.0 ~ 1.0) ";
    double sunstoneSpreadChance = 1.0;
    boolean enableAPVillagerTrades = true;
    boolean EnableAPWandererTrades = true;
}
