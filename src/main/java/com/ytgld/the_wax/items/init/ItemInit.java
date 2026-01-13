package com.ytgld.the_wax.items.init;

import com.ytgld.the_wax.WaxMod;
import com.ytgld.the_wax.block.init.BlockInit;
import com.ytgld.the_wax.items.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.Rarity;

import java.util.function.Supplier;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WaxMod.MOD_ID);

    // 方块物品
    public static final RegistryObject<Item> WAX_BLOCK = register("wax", () -> new BlockItem(BlockInit.Wax.get(), 
            new Item.Properties()));
    public static final RegistryObject<Item> SMALL_WAX_BLOCK = register("small_wax", () -> new BlockItem(BlockInit.SMALL_WAX.get(), 
            new Item.Properties()));
    public static final RegistryObject<Item> SMALL_WAX_GOLDEN_BLOCK = register("small_wax_golden", () -> new BlockItem(BlockInit.SMALL_WAX_GOLDEN.get(), 
            new Item.Properties()));
    public static final RegistryObject<Item> WAX_VINE_PIPE_BLOCK = register("wax_vine_pipe", () -> new BlockItem(BlockInit.WAX_PINE.get(), 
            new Item.Properties()));
    public static final RegistryObject<Item> WAX_BASE_BLOCK = register("wax_base", () -> new BlockItem(BlockInit.WAX_BASE.get(), 
            new Item.Properties()));
    public static final RegistryObject<Item> WAX_SUGAR_BLOCK = register("wax_sugar", () -> new BlockItem(BlockInit.WAX_SUGAR.get(), 
            new Item.Properties()));
    public static final RegistryObject<Item> WAX_PIPE_CANDLE_BLOCK = register("wax_pipe_candle", () -> new BlockItem(BlockInit.WAX_PIPE_CANDLE.get(), 
            new Item.Properties()));
    public static final RegistryObject<Item> PIPE_HONEY_CANDLE_BLOCK = register("pipe_honey_candle", () -> new BlockItem(BlockInit.PIPE_HONEY_CANDLE.get(), 
            new Item.Properties()));
    public static final RegistryObject<Item> WAX_JUMP_SLIME_BLOCK = register("wax_jump_slime", () -> new BlockItem(BlockInit.WAX_JUMP_SLIME.get(), 
            new Item.Properties()));
    public static final RegistryObject<Item> WATER_WAX_BLOCK = register("water_wax", () -> new BlockItem(BlockInit.WATER_WAX.get(), 
            new Item.Properties()));
    public static final RegistryObject<Item> HUGE_PIPE_BLOCK = register("huge_pipe", () -> new BlockItem(BlockInit.HUGE_PIPE.get(), 
            new Item.Properties()));
    public static final RegistryObject<Item> WATER_WAX_BASE_BLOCK = register("water_wax_base", () -> new BlockItem(BlockInit.WATER_WAX_BASE.get(), 
            new Item.Properties()));
    public static final RegistryObject<Item> WAX_SPONGE_BLOCK = register("wax_sponge", () -> new BlockItem(BlockInit.WAX_SPONGE.get(), 
            new Item.Properties()));
    public static final RegistryObject<Item> WET_WAX_SPONGE_BLOCK = register("wet_wax_sponge", () -> new BlockItem(BlockInit.WET_WAX_SPONGE.get(), 
            new Item.Properties()));
    public static final RegistryObject<Item> PIPE_WALL_BLOCK = register("pipe_wall", () -> new BlockItem(BlockInit.PIPE_WALL.get(), 
            new Item.Properties()));

    // 功能性物品
    public static final RegistryObject<Item> ITEM_WAX = register("item_wax", () -> new SmallWaxItem(
            new Item.Properties().rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder())
                            .nutrition(5).saturationMod(0.8f).build())));
    public static final RegistryObject<Item> GOLDEN_WAX = register("golden_wax", () -> new GoldWax(
            new Item.Properties().rarity(Rarity.EPIC).food((new FoodProperties.Builder())
                            .nutrition(10).saturationMod(0.5f).build())));

    public static final RegistryObject<Item> WAX_STRIPS = register("wax_strips", () -> new WaxStrips(
            new Item.Properties().rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder())
                            .nutrition(6).saturationMod(1.1f).build())));
    public static final RegistryObject<Item> WAX_CANDLE = register("wax_pipe_candle_item", () -> new WaxPipeCandleItem(
            new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PIPE_HONEY_CANDLE_ITEM = register("pipe_honey_candle_item", () -> new PipeHoneyCandleItem(
            new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> WAX_JUMP_SLIME_ITEM = register("wax_jump_slime_item", () -> new WaxJumpSlimeItem(
            new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> SMALL_JUMPING_SLIME = register("small_jump_slime", () -> new Item(
            new Item.Properties().rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder())
                            .nutrition(7).saturationMod( 1.3f).build())));
    public static final RegistryObject<Item> SMALL_WATER_WAX = register("small_water_wax", () -> new Item(
            new Item.Properties().rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).fast()
                            .nutrition(4).saturationMod( 0.5f).build())));

    private static <T extends Item> RegistryObject<T> register(String path, Supplier<T> item) {
        return ITEMS.register(path, item);
    }

    public static void init() {}
}