package com.ytgld.the_wax.block.nether;

import com.ytgld.the_wax.feature.gen.TheConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class NetherWaxBase extends MushroomBlock {
    public NetherWaxBase(Properties properties) {
        super(properties, TheConfiguredFeatures.HUGE_NETHER_WAX);
    }

    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        return List.of(new ItemStack(this.asItem()));
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return levelReader.getBlockState(blockPos.above()).is(BlockTags.BASE_STONE_OVERWORLD)
                || levelReader.getBlockState(blockPos.above()).is(BlockTags.BASE_STONE_NETHER);
    }

    @Override
    public boolean growMushroom(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState, RandomSource randomSource) {
        if (serverLevel.dimension() != Level.NETHER) {
            return false;
        }
        return super.growMushroom(serverLevel, blockPos, blockState, randomSource);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
    }
}
