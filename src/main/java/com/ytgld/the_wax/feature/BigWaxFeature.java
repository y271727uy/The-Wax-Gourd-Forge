package com.ytgld.the_wax.feature;

import com.mojang.serialization.Codec;
import com.ytgld.the_wax.block.WaxGourd;
import com.ytgld.the_wax.block.WaxVinePipe;
import com.ytgld.the_wax.block.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class BigWaxFeature extends Feature<WaxFeatureConfig> {

    public BigWaxFeature(Codec<WaxFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<WaxFeatureConfig> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        BlockPos testPos = new BlockPos(origin);
        int layers = random.nextInt(3, 12);
        for (int i = 0; i < layers; i++) {
            if (!world.getBlockState(origin.below(i)).isAir()){
                layers = i;
                break;
            }
        }
        for (int i = 0; i < layers; i++) {

            BlockPos currentPos = testPos.below(i);
            world.setBlock(currentPos, BlockInit.WAX_PINE.get().defaultBlockState()
                    .setValue(WaxVinePipe.HOLE, true), 3);

            world.setBlock(testPos.below(i), BlockInit.WAX_PINE.get().defaultBlockState(), 3);

            if (i == layers - 2) {
                world.setBlock(currentPos.east(), BlockInit.WAX_PINE.get().defaultBlockState(), 3);
                world.setBlock(currentPos.north(), BlockInit.WAX_PINE.get().defaultBlockState(), 3);
                world.setBlock(currentPos.west(), BlockInit.WAX_PINE.get().defaultBlockState(), 3);
                world.setBlock(currentPos.south(), BlockInit.WAX_PINE.get().defaultBlockState(), 3);
            }
            if (i == layers - 1) {
                addMain(currentPos.below(), world);
            }
        }

        return true;
    }
    public void addMain(BlockPos testPos,WorldGenLevel world){
        BlockState layer1BlockState = BlockInit.Wax.get().defaultBlockState().setValue(WaxGourd.ON_GROUND,true);
        BlockState layer3BlockState = BlockInit.Wax.get().defaultBlockState().setValue(WaxGourd.IN_TOP,true);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    BlockPos pos = testPos.offset(i - 1, j - 1, k - 1);
                    if (world.getBlockState(pos).isAir()) {
                        if (j == 0) {
                            world.setBlock(pos, layer1BlockState, 3);
                        } else if (j == 1) {
                            world.setBlock(pos, BlockInit.Wax.get().defaultBlockState(), 3);
                        } else {
                            world.setBlock(pos, layer3BlockState, 3);
                        }
                    }
                }
            }
        }

    }
}