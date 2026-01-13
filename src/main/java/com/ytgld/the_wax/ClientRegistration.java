package com.ytgld.the_wax;

import com.ytgld.the_wax.block.init.BlockInit;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = WaxMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistration {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // 设置需要特殊渲染类型的方块
        ItemBlockRenderTypes.setRenderLayer(BlockInit.WAX_SUGAR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.WAX_PINE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.WAX_PIPE_CANDLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.PIPE_HONEY_CANDLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.SMALL_WAX.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.SMALL_WAX_GOLDEN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.WAX_JUMP_SLIME.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.WAX_BASE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.WATER_WAX_BASE.get(), RenderType.cutout());
    }
}