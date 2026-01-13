package com.ytgld.the_wax.block;

import com.mojang.serialization.MapCodec;
import com.ytgld.the_wax.block.init.BlockInit;
import com.ytgld.the_wax.items.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class SmallWax extends Block {
    public static final MapCodec<SmallWax> CODEC = MapCodec.unit(() -> new SmallWax(Block.Properties.of()));
    public static final BooleanProperty IN_AIR = BooleanProperty.create("in_air");
    public static final BooleanProperty IS_GOLDEN = BooleanProperty.create("is_golden");
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(7, 0, 7, 9, 8, 9),
            Block.box(5, 0, 5, 11, 10, 11)
    );
    public SmallWax(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(IN_AIR, false)
                        .setValue(IS_GOLDEN, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(
                IN_AIR,
                IS_GOLDEN
        );
    }
    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
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
                .setValue(IN_AIR, air);
    }
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        if (levelReader.getBlockState(blockPos.above()).is(BlockInit.WAX_PINE.get())){
            return true;
        }
        Direction direction = getConnectedDirection(blockState).getOpposite();
        return Block.canSupportCenter(levelReader, blockPos.relative(direction), direction.getOpposite());
    }

    protected static Direction getConnectedDirection(BlockState blockState) {
        return blockState.getValue(IN_AIR) ? Direction.DOWN : Direction.UP;
    }

        @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos neighborPos, boolean movedByPiston) {
        super.neighborChanged(state, world, pos, block, neighborPos, movedByPiston);
        if (!world.getBlockState(pos.below()).isSolid()) {
            if (!world.getBlockState(pos.above()).isSolid()) {
                world.destroyBlock(pos, true);
            }
        } else {
            world.setBlock(pos, state.setValue(IN_AIR, false), 3);
        }
    }

    protected MapCodec<? extends SmallWax> codec() {
        return CODEC;
    }
    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        if (blockState.getValue(IS_GOLDEN)){
            return List.of(new ItemStack(ItemInit.GOLDEN_WAX.get()));
        }
        return List.of(new ItemStack(ItemInit.ITEM_WAX.get()));
    }
}