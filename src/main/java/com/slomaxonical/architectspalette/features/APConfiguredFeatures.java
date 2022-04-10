package com.slomaxonical.architectspalette.features;

import com.slomaxonical.architectspalette.registry.APBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;


public class APConfiguredFeatures {
    private static final CrystalClusterConfig HELIODOR_CLUSTER_CONFIG = new CrystalClusterConfig(1, 7, APBlocks.HELIODOR_ROD.getDefaultState(),true, Blocks.BASALT.getDefaultState());
    public static final RegistryEntry<ConfiguredFeature<CrystalClusterConfig, ?>> HELIODOR_CLUSTER= ConfiguredFeatures.register("heliodor_cluster", APFeatures.CRYSTAL_CLUSTER, HELIODOR_CLUSTER_CONFIG);
    public static final RegistryEntry<PlacedFeature> HELIODOR_CLUSTER_PLACED = PlacedFeatures.register("heliodor_cluster",
            HELIODOR_CLUSTER,
            CountPlacementModifier.of(7),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_TOP_RANGE,
            BiomePlacementModifier.of()
    );

    private static final CrystalClusterConfig EKANITE_CLUSTER_CONFIG = new CrystalClusterConfig(1, 6, APBlocks.EKANITE_ROD.getDefaultState(),false, Blocks.BASALT.getDefaultState());
    public static final RegistryEntry<ConfiguredFeature<CrystalClusterConfig, ?>> EKANITE_CLUSTER= ConfiguredFeatures.register("ekanite_cluster", APFeatures.CRYSTAL_CLUSTER, EKANITE_CLUSTER_CONFIG);
    public static final RegistryEntry<PlacedFeature> EKANITE_CLUSTER_PLACED = PlacedFeatures.register("ekanite_cluster",
            EKANITE_CLUSTER,
            CountPlacementModifier.of(8),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_TOP_RANGE,
            BiomePlacementModifier.of()
    );

    private static final CrystalClusterConfig HANGING_MOZANITE_CLUSTER_CONFIG = new CrystalClusterConfig(0, 7, APBlocks.MOZANITE_ROD.getDefaultState(),true, Blocks.BASALT.getDefaultState());
    public static final RegistryEntry<ConfiguredFeature<CrystalClusterConfig, ?>> HANGING_MOZANITE_CLUSTER= ConfiguredFeatures.register("hanging_mozanite_cluster", APFeatures.CRYSTAL_CLUSTER, HANGING_MOZANITE_CLUSTER_CONFIG);
    public static final RegistryEntry<PlacedFeature> HANGING_MOZANITE_CLUSTER_PLACED = PlacedFeatures.register("hanging_mozanite_cluster",
            HANGING_MOZANITE_CLUSTER,
            CountPlacementModifier.of(4),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_TOP_RANGE,
            BiomePlacementModifier.of()
    );

    private static final CrystalClusterConfig GROUNDED_MOZANITE_CLUSTER_CONFIG = new CrystalClusterConfig(0, 6, APBlocks.MOZANITE_ROD.getDefaultState(),false, Blocks.BASALT.getDefaultState());
    public static final RegistryEntry<ConfiguredFeature<CrystalClusterConfig, ?>> GROUNDED_MOZANITE_CLUSTER= ConfiguredFeatures.register("grounded_mozanite_cluster", APFeatures.CRYSTAL_CLUSTER, GROUNDED_MOZANITE_CLUSTER_CONFIG);
    public static final RegistryEntry<PlacedFeature> GROUNDED_MOZANITE_CLUSTER_PLACED = PlacedFeatures.register("mozanite_cluster",
            GROUNDED_MOZANITE_CLUSTER,
            CountPlacementModifier.of(4),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_TOP_RANGE,
            BiomePlacementModifier.of()
    );




    public static void registerBiomeModifications() {
//        register("heliodor_cluster", HELIODOR_CLUSTER.value(), HELIODOR_CLUSTER_PLACED.value());
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BASALT_DELTAS), GenerationStep.Feature.UNDERGROUND_DECORATION,HELIODOR_CLUSTER_PLACED.getKey().orElseThrow());
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.WARPED_FOREST), GenerationStep.Feature.UNDERGROUND_DECORATION,EKANITE_CLUSTER_PLACED.getKey().orElseThrow());
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.NETHER_WASTES), GenerationStep.Feature.UNDERGROUND_DECORATION,HANGING_MOZANITE_CLUSTER_PLACED.getKey().orElseThrow());
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.CRIMSON_FOREST), GenerationStep.Feature.UNDERGROUND_DECORATION,GROUNDED_MOZANITE_CLUSTER_PLACED.getKey().orElseThrow());
    }

//    private static void register(String name, ConfiguredFeature<?, ?> configuredFeature, PlacedFeature placedFeature) {
//        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ArchitectsPalette.MOD_ID, name), configuredFeature);
//        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(ArchitectsPalette.MOD_ID, name), placedFeature);
//    }
}

