package com.slomaxonical.architectspalette.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.FeatureConfig;

public record CrystalClusterConfig(int minLength, int maxLength ,BlockState crystalState, boolean hanging, BlockState extrusionState) implements FeatureConfig {
    public static final Codec<CrystalClusterConfig> CODEC = RecordCodecBuilder.create(configInstance ->
            configInstance.group(
                            Codec.intRange(0, 100)
                                    .fieldOf("min_length")
                                    .forGetter(CrystalClusterConfig::minLength),
                            Codec.intRange(0, 100)
                                    .fieldOf("height")
                                    .forGetter(CrystalClusterConfig::maxLength),
                            BlockState.CODEC
                                    .fieldOf("crystal_state")
                                    .forGetter(CrystalClusterConfig::crystalState),
                            Codec.BOOL
                                    .fieldOf("hanging")
                                    .forGetter(CrystalClusterConfig::hanging),
                            BlockState.CODEC
                                    .fieldOf("extrusionState")
                                    .forGetter(CrystalClusterConfig::extrusionState))
                    .apply(configInstance, CrystalClusterConfig::new));

}
