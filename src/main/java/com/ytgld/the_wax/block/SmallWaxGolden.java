package com.ytgld.the_wax.block;

import com.mojang.serialization.MapCodec;
import com.ytgld.the_wax.block.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SmallWaxGolden extends SmallWax {
    public static final MapCodec<SmallWaxGolden> CODEC = MapCodec.unit(() -> new SmallWaxGolden(Block.Properties.of()));
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(7, 0, 7, 9, 8, 9),
            Block.box(5, 0, 5, 11, 10, 11)
    );

    public SmallWaxGolden(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(
                IN_AIR,
                IS_GOLDEN
        );
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockGetter blockView = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        BlockState stateUp = blockView.getBlockState(blockPos.above());
        boolean air = !stateUp.is(this)
                && !stateUp.is(Blocks.AIR)
                && blockView.getBlockState(blockPos.below()).isAir();
        return this.defaultBlockState()
                .setValue(IN_AIR, air)
                .setValue(IS_GOLDEN, true);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        if (levelReader.getBlockState(blockPos.above()).is(BlockInit.WAX_PINE.get())){
            return true;
        }
        Direction direction = getConnectedDirection(blockState).getOpposite();
        return Block.canSupportCenter(levelReader, blockPos.relative(direction), direction.getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends SmallWaxGolden> codec() {
        return CODEC;
    }
}