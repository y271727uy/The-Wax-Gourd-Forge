package com.ytgld.the_wax.items;

import com.ytgld.the_wax.block.init.BlockInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class SmallWaxItem extends BlockItem {
    public SmallWaxItem(Properties properties) {
        super(BlockInit.SMALL_WAX.get(), properties);
    }
}