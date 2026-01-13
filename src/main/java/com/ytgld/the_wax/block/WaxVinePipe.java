package com.ytgld.the_wax.block;

import com.ytgld.the_wax.block.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class WaxVinePipe  extends PipeBlock {

    public WaxVinePipe(BlockBehaviour.Properties properties) {
        super(10.0F, properties);
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(NORTH, false)
                        .setValue(EAST, false)
                        .setValue(SOUTH, false)
                        .setValue(WEST, false)
                        .setValue(UP, false)
                        .setValue(DOWN, false)
                        .setValue(BIG, false)
                        .setValue(LEAVE, false)
                        .setValue(HOLE, false)
        );
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return getStateWithConnections(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos(), this.defaultBlockState());
    }
    public static final BooleanProperty BIG = BooleanProperty.create("big");
    public static final BooleanProperty LEAVE = BooleanProperty.create("leave");
    public static final BooleanProperty HOLE = BooleanProperty.create("hold");

    public static BlockState getStateWithConnections(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        BlockState blockState2 = blockGetter.getBlockState(blockPos.below());
        BlockState blockState3 = blockGetter.getBlockState(blockPos.above());
        BlockState blockState4 = blockGetter.getBlockState(blockPos.north());
        BlockState blockState5 = blockGetter.getBlockState(blockPos.east());
        BlockState blockState6 = blockGetter.getBlockState(blockPos.south());
        BlockState blockState7 = blockGetter.getBlockState(blockPos.west());
        int offset = blockPos.getX() + blockPos.getY() + blockPos.getZ();

        return blockState.trySetValue(DOWN, blockState2.isSolid())
                .trySetValue(UP, blockState3.isSolid() )
                .trySetValue(NORTH, blockState4.isSolid() )
                .trySetValue(EAST, blockState5.isSolid() )
                .trySetValue(SOUTH, blockState6.isSolid() )
                .trySetValue(WEST, blockState7.isSolid() )


                .trySetValue(BIG,offset%2==0)
                .trySetValue(LEAVE,offset%3==0)
                .trySetValue(HOLE,offset%5==0)

                ;
    }
    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        return List.of(new ItemStack(BlockInit.WAX_PINE.get()));
    }


    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor level, BlockPos blockPos, BlockPos blockPos2) {
        if (!blockState.canSurvive(level, blockPos)) {
            return super.updateShape(blockState, direction, blockState2, level, blockPos, blockPos2);
        } else {
            boolean bl = blockState2.is(this)  || direction == Direction.DOWN && blockState2.isSolid();
            return blockState.setValue(PROPERTY_BY_DIRECTION.get(direction), bl);
        }
    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (!blockState.canSurvive(serverLevel, blockPos)) {
            serverLevel.destroyBlock(blockPos, true);
        }
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN,BIG,LEAVE,HOLE);
    }
}
