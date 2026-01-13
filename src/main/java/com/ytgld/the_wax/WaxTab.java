package com.ytgld.the_wax;

import com.ytgld.the_wax.block.init.BlockInit;
import com.ytgld.the_wax.items.init.ItemInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;

public class WaxTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), WaxMod.MOD_ID);
    
    public static final RegistryObject<CreativeModeTab> THE_WAX_TAB = CREATIVE_MODE_TABS.register("the_wax", () -> 
        new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable("itemGroup.the_wax.group"))
            .icon(() -> new ItemStack(ItemInit.WAX_BLOCK.get())) // 使用方块物品作为图标
            .displayItems((parameters, output) -> {
                // 方块
                output.accept(new ItemStack(ItemInit.WAX_BASE_BLOCK.get()));
                output.accept(new ItemStack(ItemInit.WATER_WAX_BASE_BLOCK.get()));

                output.accept(new ItemStack(ItemInit.WAX_BLOCK.get()));
                output.accept(new ItemStack(ItemInit.SMALL_WAX_BLOCK.get()));
                output.accept(new ItemStack(ItemInit.SMALL_WAX_GOLDEN_BLOCK.get()));
                output.accept(new ItemStack(ItemInit.WAX_VINE_PIPE_BLOCK.get()));
                output.accept(new ItemStack(ItemInit.WAX_SUGAR_BLOCK.get()));
                output.accept(new ItemStack(ItemInit.WATER_WAX_BLOCK.get()));
                output.accept(new ItemStack(ItemInit.HUGE_PIPE_BLOCK.get()));
                output.accept(new ItemStack(ItemInit.WAX_SPONGE_BLOCK.get()));
                output.accept(new ItemStack(ItemInit.WET_WAX_SPONGE_BLOCK.get()));
                output.accept(new ItemStack(ItemInit.PIPE_WALL_BLOCK.get()));
                output.accept(new ItemStack(ItemInit.WAX_JUMP_SLIME_BLOCK.get()));
                output.accept(new ItemStack(ItemInit.WAX_PIPE_CANDLE_BLOCK.get()));
                output.accept(new ItemStack(ItemInit.PIPE_HONEY_CANDLE_BLOCK.get()));

                // 功能性物品
                output.accept(new ItemStack(ItemInit.ITEM_WAX.get()));
                output.accept(new ItemStack(ItemInit.GOLDEN_WAX.get()));
                output.accept(new ItemStack(ItemInit.WAX_STRIPS.get()));
                output.accept(new ItemStack(ItemInit.WAX_CANDLE.get()));
                output.accept(new ItemStack(ItemInit.PIPE_HONEY_CANDLE_ITEM.get()));
                output.accept(new ItemStack(ItemInit.WAX_JUMP_SLIME_ITEM.get()));
                output.accept(new ItemStack(ItemInit.SMALL_JUMPING_SLIME.get()));
                output.accept(new ItemStack(ItemInit.SMALL_WATER_WAX.get()));
            })
            .build()
    );
}