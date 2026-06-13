package com.ytgld.the_wax;

import com.ytgld.the_wax.block.init.BlockInit;
import com.ytgld.the_wax.feature.gen.TheFeatures;
import com.ytgld.the_wax.items.init.ItemInit;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod("the_wax")
public class WaxMod {
	public static final String MOD_ID = "the_wax";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	public WaxMod() {
		var eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		BlockInit.BLOCKS.register(eventBus);
		ItemInit.ITEMS.register(eventBus);
		TheFeatures.FEATURES.register(eventBus);

		WaxTab.CREATIVE_MODE_TABS.register(eventBus);
	}
}