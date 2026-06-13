package com.ytgld.the_wax.block.nether;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SporeBlossomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class NetherFlower extends SporeBlossomBlock {
    private static final VoxelShape TEST_SHAPE_POST = Block.box(7.0, 0.0, 7.0, 9.0, 16.0, 9.0);

    public NetherFlower(Properties properties) {
        super(properties);
    }

    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        return List.of(new ItemStack(this.asItem(), Mth.nextInt(RandomSource.create(), 5, 16)));
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return TEST_SHAPE_POST;
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        Direction direction = Direction.getRandom(randomSource);
        if (direction != Direction.UP) {
            BlockPos blockPos2 = blockPos.relative(direction);
            BlockState blockState2 = level.getBlockState(blockPos2);
            if (!blockState.canOcclude() || !blockState2.isFaceSturdy(level, blockPos2, direction.getOpposite())) {
                double d = blockPos.getX();
                double e = blockPos.getY();
                double f = blockPos.getZ();
                if (direction == Direction.DOWN) {
                    e -= 0.05;
                    d += randomSource.nextDouble();
                    f += randomSource.nextDouble();
                }

                level.addParticle(ParticleTypes.FALLING_LAVA, d, e, f, 0.0, 0.0, 0.0);
            }
        }
    }
}
