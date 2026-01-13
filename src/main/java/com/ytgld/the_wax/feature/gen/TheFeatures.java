package com.ytgld.the_wax.feature.gen;

import com.ytgld.the_wax.WaxMod;
import com.ytgld.the_wax.feature.BigWaxFeature;
import com.ytgld.the_wax.feature.WaterFeatureConfig;
import com.ytgld.the_wax.feature.WaterWaxFeature;
import com.ytgld.the_wax.feature.WaxFeatureConfig;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class TheFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, WaxMod.MOD_ID);
    
    public static final RegistryObject<Feature<WaxFeatureConfig>> WAX_FEATURE_CONFIG_FEATURE = FEATURES.register("huge_wax", () -> new BigWaxFeature(WaxFeatureConfig.CODEC));
    public static final RegistryObject<Feature<WaterFeatureConfig>> WATER_FEATURE_CONFIG_FEATURE = FEATURES.register("huge_water_wax", () -> new WaterWaxFeature(WaterFeatureConfig.CODEC));

    public static void init() {
    }
}