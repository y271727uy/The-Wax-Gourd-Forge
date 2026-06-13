package com.ytgld.the_wax.block.nether;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class NetherPipePlanks extends Block {

    public static final BooleanProperty A = BooleanProperty.create("a");
    public static final BooleanProperty B = BooleanProperty.create("b");
    public static final BooleanProperty C = BooleanProperty.create("c");

    public NetherPipePlanks(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(A, false)
                        .setValue(B, false)
                        .setValue(C, false)
        );
    }

    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        return List.of(new ItemStack(this.asItem()));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return getStateWithConnections(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos(), this.defaultBlockState());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(A, B, C);
    }

    public static BlockState getStateWithConnections(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        int offset = (blockPos.getX() + blockPos.getY() + blockPos.getZ()) / 3 + blockPos.getX() + blockPos.getY() + blockPos.getZ();
        return blockState
                .trySetValue(C, offset * blockPos.hashCode() % 6 == 0)
                .trySetValue(A, offset * blockPos.hashCode() % 5 == 0)
                .trySetValue(B, offset * blockPos.hashCode() % 4 == 0);
    }
}
