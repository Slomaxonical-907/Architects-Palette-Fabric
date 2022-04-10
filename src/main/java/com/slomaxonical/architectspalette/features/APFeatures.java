package com.slomaxonical.architectspalette.features;

import com.slomaxonical.architectspalette.ArchitectsPalette;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;

public class APFeatures {

    public static final Feature<CrystalClusterConfig> CRYSTAL_CLUSTER = new CrystalClusterFeature(CrystalClusterConfig.CODEC);
    public static void register(){
       Registry.register(Registry.FEATURE, new Identifier(ArchitectsPalette.MOD_ID,"crystal_cluster"), CRYSTAL_CLUSTER);
    }
}
