package com.slomaxonical.architectspalette.compat.cloth_config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "architects_palette")
 public class ApConfigs implements ConfigData {
    @Comment("  Controls ALL worldgen features added by Architect's Palette. "+
            "   (If false, NOTHING from AP will generate. If true, individual features may still be disabled.)")
    public boolean globalWorldGenToggle = true;

    @Comment("  Controls the spawning of Monazite, Ekanite, and Heliodor rods in various Nether biomes.")
    public boolean netherCrystalGeneration = true;

    @Comment("  Chance of updating adjacent moon/sunstone blocks, cascading updates and helping larger areas stay in sync (RANGE:0.0 ~ 1)")
    public double sunstoneSpreadChance = 1.0;

    @Comment("  Villagers that already sell AP items will continue to do so regardless of this setting.")
    public boolean enableVillagerTrades = true;
    public boolean enableWandererTrades = true;

    @Comment("  Adds vertical slabs for the OG builders out there")
    public boolean enableVerticalSlabs = false;
}