package com.ytgld.the_wax.block;

import com.ytgld.the_wax.block.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SpongeBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class WaxSponge extends SpongeBlock {
    private static final Direction[] ALL_DIRECTIONS = Direction.values();

    public WaxSponge(Properties properties) {
        super(properties);
    }

    @Override
    protected void tryAbsorbWater(Level level, BlockPos blockPos) {
        if (this.removeWaterBreadthFirstSearch(level, blockPos)) {
            level.setBlock(blockPos, BlockInit.WET_WAX_SPONGE.get().defaultBlockState(), 2);
            level.playSound(null, blockPos, SoundEvents.POINTED_DRIPSTONE_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }
    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        return List.of(new ItemStack(this.asItem()));
    }
    private boolean removeWaterBreadthFirstSearch(Level level, BlockPos blockPos) {
        return BlockPos.betweenClosedStream(blockPos.offset(-6, -6, -6), blockPos.offset(6, 6, 6))
                .limit(65)
                .map(pos -> new BlockPos(pos))
                .anyMatch(blockPos2 -> {
                    if (blockPos2.equals(blockPos)) {
                        return true;
                    } else {
                        BlockState blockState = level.getBlockState(blockPos2);
                        FluidState fluidState = level.getFluidState(blockPos2);
                        if (!fluidState.is(FluidTags.WATER)) {
                            return false;
                        } else if (blockState.getBlock() instanceof BucketPickup bucketPickup && !bucketPickup.pickupBlock(level, blockPos2, blockState).isEmpty()) {
                            return true;
                        } else {
                            if (blockState.getBlock() instanceof LiquidBlock) {
                                level.setBlock(blockPos2, Blocks.AIR.defaultBlockState(), 3);
                            } else {
                                if (!blockState.is(Blocks.KELP) && !blockState.is(Blocks.KELP_PLANT) && !blockState.is(Blocks.SEAGRASS) && !blockState.is(Blocks.TALL_SEAGRASS)) {
                                    return false;
                                }

                                BlockEntity blockEntity = blockState.hasBlockEntity() ? level.getBlockEntity(blockPos2) : null;
                                dropResources(blockState, level, blockPos2, blockEntity);
                                level.setBlock(blockPos2, Blocks.AIR.defaultBlockState(), 3);
                            }

                            return true;
                        }
                    }
                });
    }
}