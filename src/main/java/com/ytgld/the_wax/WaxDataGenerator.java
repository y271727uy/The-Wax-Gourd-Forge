package com.ytgld.the_wax;

import com.ytgld.the_wax.feature.gen.TheConfiguredFeatures;
import com.ytgld.the_wax.feature.gen.TheWaxConfiguredFeatureProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.item.Item;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagsProvider.TagLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WaxDataGenerator {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		var generator = event.getGenerator();
		var existingFileHelper = event.getExistingFileHelper();
		var blockTagsProvider = generator.addProvider(event.includeServer(), new WaxTagGeneratorBlock(generator.getPackOutput(), event.getLookupProvider(), existingFileHelper));
		generator.addProvider(event.includeServer(), new WaxTagGenerator(
				generator.getPackOutput(),
				event.getLookupProvider(),
				blockTagsProvider,
				existingFileHelper)
		);
		// TODO: Fix ItemTagsProvider constructor issue


		generator.addProvider(event.includeServer(), new TheWaxConfiguredFeatureProvider(generator.getPackOutput(), event.getLookupProvider()));
	}
}