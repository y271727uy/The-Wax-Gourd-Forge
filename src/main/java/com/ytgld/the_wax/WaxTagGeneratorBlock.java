package com.ytgld.the_wax;

import com.ytgld.the_wax.block.init.BlockInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class WaxTagGeneratorBlock extends BlockTagsProvider {
    public WaxTagGeneratorBlock(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture, ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, WaxMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.CANDLES)
                .add(BlockInit.WAX_PIPE_CANDLE.get())
                .add(BlockInit.PIPE_HONEY_CANDLE.get());

        this.tag(BlockTags.WALLS)
                .add(BlockInit.PIPE_WALL.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(BlockInit.PIPE_WALL.get())
                .add(BlockInit.Wax.get())
                .add(BlockInit.WAX_PINE.get())
                .add(BlockInit.WATER_WAX.get())
                .add(BlockInit.HUGE_PIPE.get())
                .add(BlockInit.WAX_JUMP_SLIME.get())
                .add(BlockInit.SMALL_WAX.get())
                .add(BlockInit.NETHER_WAX.get())
                .add(BlockInit.NETHER_PIPE.get())
                .add(BlockInit.NETHER_PIPE_OTHER.get())
                .add(BlockInit.NETHER_PIPE_PLANKS.get());

        this.tag(BlockTags.MINEABLE_WITH_HOE)
                .add(BlockInit.WAX_PIPE_CANDLE.get())
                .add(BlockInit.PIPE_HONEY_CANDLE.get())
                .add(BlockInit.WAX_SPONGE.get())
                .add(BlockInit.WET_WAX_SPONGE.get());
    }
}
