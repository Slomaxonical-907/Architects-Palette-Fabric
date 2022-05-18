package com.slomaxonical.architectspalette.features;

import com.slomaxonical.architectspalette.ArchitectsPalette;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class APFeatures {
    public static final ConfiguredFeature<TreeFeatureConfig, ?> TWISTED_TREE = new ConfiguredFeature<>(Feature.TREE,TwistedTree.TWISTED_TREE_CONFIG().build());
    public static final Feature<CrystalClusterConfig> CRYSTAL_CLUSTER = new CrystalClusterFeature(CrystalClusterConfig.CODEC);

    public static void register(){
       Registry.register(Registry.FEATURE, new Identifier(ArchitectsPalette.MOD_ID,"crystal_cluster"), CRYSTAL_CLUSTER);
       Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ArchitectsPalette.MOD_ID, "twisted"), TWISTED_TREE);

    }
}
