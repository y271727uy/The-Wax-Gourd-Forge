package com.ytgld.the_wax;

import com.ytgld.the_wax.items.init.ItemInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import java.util.concurrent.CompletableFuture;

public class WaxTagGenerator extends ItemTagsProvider { // Temporarily disabled due to API compatibility issues
    public static final TagKey<Item> WAX = bind("wax");
    public static final TagKey<Item> PIPE = bind("pipe");


    public WaxTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), WaxMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.PLANKS)
                .add(ItemInit.ITEM_WAX.get());

        this.tag(PIPE)
                ;

        this.tag(WAX)
                .add(ItemInit.ITEM_WAX.get());
    }


    private static TagKey<Item> bind(String string) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(WaxMod.MOD_ID, string));
    }
}