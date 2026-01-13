package com.ytgld.the_wax.feature.gen;

import com.ytgld.the_wax.WaxMod;
import com.ytgld.the_wax.feature.WaterFeatureConfig;
import com.ytgld.the_wax.feature.WaxFeatureConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TheConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registries.CONFIGURED_FEATURE, WaxMod.MOD_ID);

    // 暂时注释掉，以解决编译问题
    // public static final RegistryObject<ConfiguredFeature<?, ?>> BIG_WAX = CONFIGURED_FEATURES.register("wax", () -> 
    //     new ConfiguredFeature<>(TheFeatures.WAX_FEATURE_CONFIG_FEATURE.getId(), 
    //         new WaxFeatureConfig(10, new ResourceLocation(WaxMod.MOD_ID, "wax"))));
    // 
    // public static final RegistryObject<ConfiguredFeature<?, ?>> HUGE_WATER_WAX = CONFIGURED_FEATURES.register("huge_water_wax", () -> 
    //     new ConfiguredFeature<>(TheFeatures.WATER_FEATURE_CONFIG_FEATURE.getId(), 
    //         new WaterFeatureConfig(10, new ResourceLocation(WaxMod.MOD_ID, "huge_water_wax"))));

    public static void init(){
    }
}