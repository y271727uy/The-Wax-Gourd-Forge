package com.ytgld.the_wax;

import com.ytgld.the_wax.items.init.ItemInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class WaxTagGenerator extends ItemTagsProvider {
    public static final TagKey<Item> WAX = bind("wax");
    public static final TagKey<Item> PIPE = bind("pipe");
    public static final TagKey<Item> NETHER_PIPE = bind("nether_pipe");

    public WaxTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), WaxMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.PLANKS)
                .add(ItemInit.NETHER_PIPE_PLANKS_BLOCK.get());

        this.tag(WAX)
                .add(ItemInit.WAX_BLOCK.get())
                .add(ItemInit.WATER_WAX_BLOCK.get())
                .add(ItemInit.NETHER_WAX_BLOCK.get());

        this.tag(PIPE)
                .add(ItemInit.WAX_VINE_PIPE_BLOCK.get())
                .add(ItemInit.HUGE_PIPE_BLOCK.get())
                .add(ItemInit.PIPE_WALL_BLOCK.get())
                .add(ItemInit.NETHER_PIPE_BLOCK.get())
                .add(ItemInit.NETHER_PIPE_OTHER_BLOCK.get());

        this.tag(NETHER_PIPE)
                .add(ItemInit.NETHER_PIPE_BLOCK.get())
                .add(ItemInit.NETHER_PIPE_OTHER_BLOCK.get());
    }

    private static TagKey<Item> bind(String string) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(WaxMod.MOD_ID, string));
    }
}
