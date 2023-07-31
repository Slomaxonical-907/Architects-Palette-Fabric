package com.slomaxonical.architectspalette.config;

import blue.endless.jankson.Comment;
import io.wispforest.owo.config.annotation.*;

import static io.wispforest.owo.config.Option.SyncMode.OVERRIDE_CLIENT;

@Modmenu(modId = "architects_palette")
@Config(name = "architects_palette", wrapperName = "APConfigs")
public class APConfigsModel {
    @Comment("Adds vertical slabs for the OG builders out there")
    @Sync(value = OVERRIDE_CLIENT)
    public boolean enableVerticalSlabs = false;
    @Comment("Chance of updating adjacent moon/sunstone blocks, cascading updates and helping larger areas stay in sync (RANGE:0.0 ~ 1)")
    @RangeConstraint(min = 0.0d,max=1.0d)
    @Sync(value = OVERRIDE_CLIENT)
    public double sunstoneSpreadChance = 1.0d;
    @SectionHeader("trading")
    @Comment("Villagers that already sell AP items will continue to do so regardless of this setting.")
    public boolean enableVillagerTrades = true;
    public boolean enableWandererTrades = true;
    @SectionHeader("worldGen")
    @Comment("Controls ALL worldgen features added by Architect's Palette. "+
            " (If false, NOTHING from AP will generate. If true, individual features may still be disabled.)")
    public boolean globalWorldGenToggle = true;

    @Comment("Controls the spawning of Monazite, Ekanite, and Heliodor rods in various Nether biomes.")
    public boolean netherCrystalGeneration = true;
}
