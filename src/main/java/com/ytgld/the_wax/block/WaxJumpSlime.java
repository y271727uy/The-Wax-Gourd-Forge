package com.ytgld.the_wax.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WaxJumpSlime extends Block {
    public static final MapCodec<WaxJumpSlime> CODEC = MapCodec.unit(() -> new WaxJumpSlime(Block.Properties.of()));
    private static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);

    public WaxJumpSlime(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

        public Vec3 getJumpFactor(BlockState state, BlockGetter world, BlockPos pos) {
        return new Vec3(1.0, 1.2, 1.0);
    }

        public Vec3 getFallDistanceModifier(BlockState state, BlockGetter world, BlockPos pos) {
        return new Vec3(1.0, 0.8, 1.0);
    }

    protected MapCodec<? extends WaxJumpSlime> codec() {
        return CODEC;
    }
}