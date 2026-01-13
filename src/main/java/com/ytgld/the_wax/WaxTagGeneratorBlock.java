package com.ytgld.the_wax;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraft.tags.BlockTags;
import java.util.concurrent.CompletableFuture;

public class WaxTagGeneratorBlock extends BlockTagsProvider {
    public WaxTagGeneratorBlock(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture, ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, "the_wax", existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.CANDLES)
                .add(com.ytgld.the_wax.block.init.BlockInit.WAX_PIPE_CANDLE.get())
                .add(com.ytgld.the_wax.block.init.BlockInit.PIPE_HONEY_CANDLE.get());

        this.tag(BlockTags.WALLS)
                ;
    }
}