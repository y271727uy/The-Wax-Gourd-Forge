package com.ytgld.the_wax.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PipeHoneyCandle extends WaxPipeCandle {
    public static final MapCodec<PipeHoneyCandle> CODEC = MapCodec.unit(() -> new PipeHoneyCandle(Block.Properties.of()));
    private static final VoxelShape[] SHAPES = new VoxelShape[]{
        net.minecraft.world.level.block.Block.box(7, 0, 7, 9, 4, 9),
        net.minecraft.world.level.block.Block.box(6, 0, 6, 10, 6, 10),
        net.minecraft.world.level.block.Block.box(5, 0, 5, 11, 8, 11),
        net.minecraft.world.level.block.Block.box(4, 0, 4, 12, 12, 12)
    };
    
    public PipeHoneyCandle(Properties properties) {
        super(properties);
        // 不调用 registerDefaultState，让父类处理
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        // 只调用父类方法，不添加任何新属性，因为CandleBlock已经包含了所有必要的属性（包括WATERLOGGED）
        super.createBlockStateDefinition(builder);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
        // 使用WATERLOGGED属性，它应该已经由CandleBlock提供
        return this.defaultBlockState()
            .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        return InteractionResult.PASS;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPES[pState.getValue(CANDLES)-1];
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        Direction direction = getConnectedDirection(state).getOpposite();
        return Block.canSupportCenter(world, pos.relative(direction), direction.getOpposite());
    }

    private Direction getConnectedDirection(BlockState state) {
        return Direction.DOWN; // 默认向下连接
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        if (state.hasProperty(WATERLOGGED) && state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.hasProperty(WATERLOGGED) && state.getValue(WATERLOGGED)) {
            return Fluids.WATER.getSource(false);
        } else {
            return super.getFluidState(state);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (state.hasProperty(WATERLOGGED) && state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        super.randomTick(state, world, pos, random);
    }

    protected final MapCodec<? extends PipeHoneyCandle> codec() {
        return CODEC;
    }
    
    // 提供WATERLOGGED属性的静态访问
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
}