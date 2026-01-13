package com.ytgld.the_wax.items;

import com.ytgld.the_wax.block.init.BlockInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class WaxJumpSlimeItem extends BlockItem {
    public WaxJumpSlimeItem(Properties properties) {
        super(BlockInit.WAX_JUMP_SLIME.get(), properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> tooltip, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, world, tooltip, tooltipFlag);
        tooltip.add(Component.translatable("item.the_wax.wax_jump_slime_item.tip").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.the_wax.wax_jump_slime_item.tip.2").withStyle(ChatFormatting.GRAY));
    }
}