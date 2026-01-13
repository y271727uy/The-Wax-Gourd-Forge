package com.ytgld.the_wax.feature;

import com.mojang.serialization.Codec;
import com.ytgld.the_wax.block.WaxGourd;
import com.ytgld.the_wax.block.WaxVinePipe;
import com.ytgld.the_wax.block.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class WaterWaxFeature extends Feature<WaterFeatureConfig> {

    public WaterWaxFeature(Codec<WaterFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<WaterFeatureConfig> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        BlockPos testPos = new BlockPos(origin);

        int waterHeight = 0;
        for (int i = 0; i < 10+Mth.nextInt(RandomSource.create(),1,5) && world.getBlockState(testPos.above()).is(Blocks.WATER); i++) {
            waterHeight = i;
        }
        for (int i = 0; i < waterHeight; i++) {
            BlockPos nowPos = testPos.above(i);
            if (i == 0) {
                world.setBlock(nowPos.east(), BlockInit.HUGE_PIPE.get().defaultBlockState(), 3);
                world.setBlock(nowPos.north(), BlockInit.HUGE_PIPE.get().defaultBlockState(), 3);
                world.setBlock(nowPos.west(), BlockInit.HUGE_PIPE.get().defaultBlockState(), 3);
                world.setBlock(nowPos.south(), BlockInit.HUGE_PIPE.get().defaultBlockState(), 3);
            }else if (i == waterHeight - 1) {
                addMain2(nowPos, world);
                addMain(nowPos, world);
                addMain2(nowPos, world);
            }else {
                world.setBlock(nowPos, BlockInit.HUGE_PIPE.get().defaultBlockState(), 3);
            }
        }


        return true;
    }
    public void addMain2(BlockPos testPos,WorldGenLevel world){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    BlockPos pos = testPos.offset(i - 1, j - 1, k - 1);
                    if (world.getBlockState(pos).is(Blocks.WATER)) {
                        world.setBlock(pos, BlockInit.WATER_WAX.get().defaultBlockState(), 3);
                    }
                }
            }
        }
        world.setBlock(testPos.offset(0,0,0), BlockInit.WET_WAX_SPONGE.get().defaultBlockState(), 3);
    }
    public void addMain(BlockPos testPos, WorldGenLevel world) {
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                BlockPos posXY = testPos.offset(i, j, 0);
                if (world.getBlockState(posXY).is(Blocks.WATER)) {
                    world.setBlock(posXY, BlockInit.HUGE_PIPE.get().defaultBlockState(), 3);
                }

                BlockPos posXZ = testPos.offset(i, 0, j);
                if (world.getBlockState(posXZ).is(Blocks.WATER)) {
                    world.setBlock(posXZ, BlockInit.HUGE_PIPE.get().defaultBlockState(), 3);
                }
                BlockPos posYZ = testPos.offset(0, i, j);
                if (world.getBlockState(posYZ).is(Blocks.WATER)) {
                    world.setBlock(posYZ, BlockInit.HUGE_PIPE.get().defaultBlockState(), 3);
                }
            }
        }
    }
}