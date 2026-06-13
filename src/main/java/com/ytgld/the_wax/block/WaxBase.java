package com.ytgld.the_wax.block;

import com.ytgld.the_wax.block.init.BlockInit;
import com.ytgld.the_wax.feature.gen.TheConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class WaxBase extends MushroomBlock {
    public WaxBase(Properties properties) {
        super(properties, TheConfiguredFeatures.BIG_WAX);
    }

    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        return List.of(new ItemStack(BlockInit.WAX_BASE.get()));
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return levelReader.getBlockState(blockPos.above()).is(BlockTags.BASE_STONE_OVERWORLD)
                || levelReader.getBlockState(blockPos.above()).is(BlockTags.DIRT);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
    }
}
