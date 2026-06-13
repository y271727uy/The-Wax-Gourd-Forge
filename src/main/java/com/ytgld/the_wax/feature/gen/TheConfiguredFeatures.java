package com.ytgld.the_wax.feature.gen;

import com.ytgld.the_wax.WaxMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

/**
 * 配置特征(ConfiguredFeature)的注册键。
 * 实际的特征定义以数据包 JSON 形式存放于
 * {@code data/the_wax/worldgen/configured_feature/*.json}，
 * 这些键供方块(如 {@link net.minecraft.world.level.block.MushroomBlock})
 * 在骨粉催熟时查找对应的配置特征，也供放置特征(placed_feature)引用。
 */
public class TheConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_WAX = key("wax");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_WATER_WAX = key("huge_water_wax");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_NETHER_WAX = key("huge_nether_wax");

    private static ResourceKey<ConfiguredFeature<?, ?>> key(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(WaxMod.MOD_ID, name));
    }

    public static void init() {
    }
}
