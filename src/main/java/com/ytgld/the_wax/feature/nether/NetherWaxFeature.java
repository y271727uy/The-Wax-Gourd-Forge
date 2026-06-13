package com.ytgld.the_wax.feature.nether;

import com.mojang.serialization.Codec;
import com.ytgld.the_wax.block.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class NetherWaxFeature extends Feature<NetherWaxFeatureConfig> {

    public NetherWaxFeature(Codec<NetherWaxFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NetherWaxFeatureConfig> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        BlockPos testPos = new BlockPos(origin);
        int layers = random.nextInt(5, 20);
        for (int i = 0; i < layers; i++) {
            if (!world.getBlockState(origin.below(i)).isAir()) {
                layers = i;
                break;
            }
        }
        for (int i = 0; i < layers; i++) {

            BlockPos currentPos = testPos.below(i);
            world.setBlock(testPos.below(i), BlockInit.NETHER_PIPE_OTHER.get().defaultBlockState(), 3);

            if (i == layers - 3) {
                world.setBlock(currentPos.east(), BlockInit.NETHER_PIPE.get().defaultBlockState(), 3);
                world.setBlock(currentPos.north(), BlockInit.NETHER_PIPE.get().defaultBlockState(), 3);
                world.setBlock(currentPos.west(), BlockInit.NETHER_PIPE.get().defaultBlockState(), 3);
                world.setBlock(currentPos.south(), BlockInit.NETHER_PIPE.get().defaultBlockState(), 3);
            }
            if (i == layers - 2) {
                world.setBlock(currentPos.offset(1, 0, 1), BlockInit.NETHER_PIPE.get().defaultBlockState(), 3);
                world.setBlock(currentPos.offset(-1, 0, 1), BlockInit.NETHER_PIPE.get().defaultBlockState(), 3);
                world.setBlock(currentPos.offset(-1, 0, -1), BlockInit.NETHER_PIPE.get().defaultBlockState(), 3);
                world.setBlock(currentPos.offset(1, 0, -1), BlockInit.NETHER_PIPE.get().defaultBlockState(), 3);
            }
            if (i == layers - 1) {
                addMain(currentPos.below(), world);
                addMainCube(currentPos.below(), world);
            }
        }

        return true;
    }

    public void addMainCube(BlockPos testPos, WorldGenLevel world) {
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                BlockPos posXY = testPos.offset(i, j, 0);
                if (world.getBlockState(posXY).isAir()) {
                    world.setBlock(posXY, BlockInit.NETHER_PIPE.get().defaultBlockState(), 3);
                }

                BlockPos posXZ = testPos.offset(i, 0, j);
                if (world.getBlockState(posXZ).isAir()) {
                    world.setBlock(posXZ, BlockInit.NETHER_PIPE.get().defaultBlockState(), 3);
                }
                BlockPos posYZ = testPos.offset(0, i, j);
                if (world.getBlockState(posYZ).isAir()) {
                    world.setBlock(posYZ, BlockInit.NETHER_PIPE.get().defaultBlockState(), 3);
                }
            }
        }
    }

    public void addMain(BlockPos testPos, WorldGenLevel world) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    BlockPos pos = testPos.offset(i - 1, j - 1, k - 1);
                    if (world.getBlockState(pos).isAir()
                            || world.getBlockState(pos).is(BlockInit.NETHER_PIPE.get())
                            || world.getBlockState(pos).is(BlockInit.NETHER_PIPE_OTHER.get())) {
                        world.setBlock(pos, BlockInit.NETHER_WAX.get().defaultBlockState(), 3);
                    }
                }
            }
        }

        world.setBlock(testPos.offset(1, -2, 0), BlockInit.NETHER_WAX.get().defaultBlockState(), 3);
        world.setBlock(testPos.offset(0, -2, -1), BlockInit.NETHER_WAX.get().defaultBlockState(), 3);
        world.setBlock(testPos.offset(-1, -2, 0), BlockInit.NETHER_WAX.get().defaultBlockState(), 3);
        world.setBlock(testPos.offset(0, -2, 1), BlockInit.NETHER_WAX.get().defaultBlockState(), 3);

        world.setBlock(testPos.offset(0, -2, 0), BlockInit.NETHER_WAX.get().defaultBlockState(), 3);
        world.setBlock(testPos.offset(0, -3, 0), BlockInit.NETHER_WAX.get().defaultBlockState(), 3);
        world.setBlock(testPos.offset(0, -4, 0), BlockInit.NETHER_FLOWER.get().defaultBlockState(), 3);
    }
}
