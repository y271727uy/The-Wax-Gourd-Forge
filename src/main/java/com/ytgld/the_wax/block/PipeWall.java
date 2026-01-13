package com.ytgld.the_wax.block;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class PipeWall extends WallBlock {
    public PipeWall(Properties properties) {
        super(properties);
    }


    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        return List.of(new ItemStack(this.asItem()));
    }
}