package com.ytgld.the_wax.block;

import com.ytgld.the_wax.block.init.BlockInit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class HugePipe extends Block {
    public HugePipe(Properties properties) {
        super(properties);
    }
    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        return List.of(new ItemStack(BlockInit.HUGE_PIPE.get().asItem()));
    }

}