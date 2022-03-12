package com.slomaxonical.architectspalette.features;

import com.slomaxonical.architectspalette.ArchitectsPalette;
import com.slomaxonical.architectspalette.registry.APBlocks;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class TwistedTree extends SaplingGenerator {
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> TWISTED_TREE = ConfiguredFeatures.register("twisted", Feature.TREE, twisted().build());

    @Nullable
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random randomIn, boolean largeHive) {
        return TWISTED_TREE;
    }

    public static TreeFeatureConfig.Builder twisted() {
        return new TreeFeatureConfig.Builder(BlockStateProvider.of(APBlocks.TWISTED_LOG),
                new ForkingTrunkPlacer(5, 2, 2),
                BlockStateProvider.of(APBlocks.TWISTED_LEAVES),
                new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                new TwoLayersFeatureSize(1, 0, 2))
                .ignoreVines();
    }

    public static void registerTreeFeature() {
        ConfiguredFeature<?, ?> TWISTED = new ConfiguredFeature<>(Feature.TREE, twisted().build());
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ArchitectsPalette.MOD_ID, "twisted"), TWISTED);
    }
}
