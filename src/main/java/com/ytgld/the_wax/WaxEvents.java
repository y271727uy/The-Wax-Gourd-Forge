package com.ytgld.the_wax;

import com.ytgld.the_wax.block.init.BlockInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = WaxMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WaxEvents {

    private static Map<ItemLike, Integer> fuelMap;

    private static Map<ItemLike, Integer> getFuelMap() {
        if (fuelMap == null) {
            Map<ItemLike, Integer> map = new HashMap<>();
            map.put(BlockInit.Wax.get(), 500);
            map.put(BlockInit.WAX_PINE.get(), 600);
            map.put(BlockInit.NETHER_WAX_BASE.get(), 300);
            map.put(BlockInit.HUGE_PIPE.get(), 600);
            map.put(BlockInit.PIPE_WALL.get(), 100);
            map.put(BlockInit.NETHER_WAX.get(), 6000);
            map.put(BlockInit.NETHER_PIPE.get(), 2400);
            map.put(BlockInit.NETHER_PIPE_OTHER.get(), 2400);
            fuelMap = map;
        }
        return fuelMap;
    }

    @SubscribeEvent
    public static void onFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        ItemStack stack = event.getItemStack();
        Integer burnTime = getFuelMap().get(stack.getItem());
        if (burnTime != null) {
            event.setBurnTime(burnTime);
        }
    }

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();
        if (name.equals(BuiltInLootTables.SNIFFER_DIGGING)) {
            event.getTable().addPool(
                    LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(BlockInit.NETHER_WAX_BASE.get())
                                    .when(LootItemRandomChanceCondition.randomChance(0.25f)))
                            .build());
        }
    }
}
