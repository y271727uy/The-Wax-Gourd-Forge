package com.ytgld.the_wax.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record WaterFeatureConfig(int number, ResourceLocation blockId) implements FeatureConfiguration {
    public static final Codec<WaterFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Codec.INT.fieldOf("number").forGetter(WaterFeatureConfig::number),
                            ResourceLocation.CODEC.fieldOf("blockID").forGetter(WaterFeatureConfig::blockId))
                    .apply(instance, WaterFeatureConfig::new));
}