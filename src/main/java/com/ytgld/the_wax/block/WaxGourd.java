package com.ytgld.the_wax.block;

import com.mojang.serialization.MapCodec;
import com.ytgld.the_wax.block.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;
import java.util.Map;


public class WaxGourd  extends Block {
    public static final MapCodec<WaxGourd> CODEC = MapCodec.unit(() -> new WaxGourd(BlockBehaviour.Properties.of()));
    public static final BooleanProperty NORTH = PipeBlock.NORTH;
    public static final BooleanProperty EAST = PipeBlock.EAST;
    public static final BooleanProperty SOUTH = PipeBlock.SOUTH;
    public static final BooleanProperty WEST = PipeBlock.WEST;
    public static final BooleanProperty UP = PipeBlock.UP;
    public static final BooleanProperty DOWN = PipeBlock.DOWN;
    public static final BooleanProperty ON_GROUND = BooleanProperty.create("on_ground");
    public static final BooleanProperty IN_TOP = BooleanProperty.create("in_top");
    public static final BooleanProperty HEAD = BooleanProperty.create("head");
    private static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = PipeBlock.PROPERTY_BY_DIRECTION;

    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }
    public WaxGourd(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(NORTH, true)
                        .setValue(EAST, true)
                        .setValue(SOUTH, true)
                        .setValue(WEST, true)
                        .setValue(UP, true)
                        .setValue(DOWN, true)

                        .setValue(ON_GROUND, false)
                        .setValue(IN_TOP, false)
                        .setValue(HEAD, false)
        );
    }


    @Override
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(PROPERTY_BY_DIRECTION.get(rotation.rotate(Direction.NORTH)), blockState.getValue(NORTH))
                .setValue(PROPERTY_BY_DIRECTION.get(rotation.rotate(Direction.SOUTH)), blockState.getValue(SOUTH))
                .setValue(PROPERTY_BY_DIRECTION.get(rotation.rotate(Direction.EAST)), blockState.getValue(EAST))
                .setValue(PROPERTY_BY_DIRECTION.get(rotation.rotate(Direction.WEST)), blockState.getValue(WEST))
                .setValue(PROPERTY_BY_DIRECTION.get(rotation.rotate(Direction.UP)), blockState.getValue(UP))
                .setValue(PROPERTY_BY_DIRECTION.get(rotation.rotate(Direction.DOWN)), blockState.getValue(DOWN));
    }
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        return neighborState.is(this)
                ? state.setValue(PROPERTY_BY_DIRECTION.get(direction), false)
                : state;
    }
    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        if (Mth.nextInt(RandomSource.create(), 1, 3) == 1) {
            return List.of(new ItemStack(BlockInit.SMALL_WAX.get().asItem(),Mth.nextInt(RandomSource.create(),1,3)));
        }
        return List.of(new ItemStack(BlockInit.Wax.get().asItem()));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockGetter blockView = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        BlockState stateDown = blockView.getBlockState(blockPos.below());
        boolean isTop = false;
        if (stateDown.hasProperty(ON_GROUND)) {
            isTop = stateDown.getValue(ON_GROUND);
        }
        return this.defaultBlockState()
                .setValue(DOWN, !blockView.getBlockState(blockPos.below()).is(this))
                .setValue(UP, !blockView.getBlockState(blockPos.above()).is(this))
                .setValue(NORTH, !blockView.getBlockState(blockPos.north()).is(this))
                .setValue(EAST, !blockView.getBlockState(blockPos.east()).is(this))
                .setValue(SOUTH, !blockView.getBlockState(blockPos.south()).is(this))
                .setValue(WEST, !blockView.getBlockState(blockPos.west()).is(this))


                .setValue(ON_GROUND, !blockView.getBlockState(blockPos.below()).is(this))
                .setValue(IN_TOP, blockView.getBlockState(blockPos.above()).is(Blocks.AIR)&&!isTop)
                .setValue(HEAD,
                        blockView.getBlockState(blockPos.west()).is(this)
                                && blockView.getBlockState(blockPos.north()).is(this)
                                && blockView.getBlockState(blockPos.east()).is(this)
                                && blockView.getBlockState(blockPos.south()).is(this)
                                && blockView.getBlockState(blockPos.below()).hasProperty(ON_GROUND)
                                && !blockView.getBlockState(blockPos.below()).getValue(ON_GROUND))
                ;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(
                UP,
                DOWN,
                NORTH,
                EAST,
                SOUTH,
                WEST
                ,
                IN_TOP,
                ON_GROUND,
                HEAD

        );
    }
}