package com.ytgld.the_wax;

import com.ytgld.the_wax.block.init.BlockInit;
import com.ytgld.the_wax.feature.gen.TheConfiguredFeatures;
import com.ytgld.the_wax.feature.gen.TheFeatures;
import com.ytgld.the_wax.items.init.ItemInit;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
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
		TheConfiguredFeatures.CONFIGURED_FEATURES.register(eventBus);
		
		WaxTab.CREATIVE_MODE_TABS.register(eventBus);
	}
}