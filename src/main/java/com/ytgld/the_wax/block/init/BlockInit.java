package com.ytgld.the_wax.block.init;

import com.ytgld.the_wax.WaxMod;
import com.ytgld.the_wax.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;
import java.util.function.Supplier;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WaxMod.MOD_ID);

    public static final RegistryObject<Block> Wax = register("wax", () -> new WaxGourd(
            BlockBehaviour.Properties.of().strength(0.5F).lightLevel((state)->{
                return 12;
            }).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SMALL_WAX = register("small_wax", () -> new SmallWax(
            BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SMALL_WAX_GOLDEN = register("small_wax_golden", () -> new SmallWaxGolden(
            BlockBehaviour.Properties.of().strength(0.5F).lightLevel((state)->{
              return 10;
            }).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WAX_PINE = register("wax_vine_pipe", () -> new WaxVinePipe(
            BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WAX_BASE = register("wax_base", () -> new WaxBase(
            BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WAX_SUGAR = register("wax_sugar", () -> new WaxSugar(
            BlockBehaviour.Properties.of().strength(0.2f)
                    .noOcclusion().friction(0.98f)
                    .isValidSpawn((state, level, pos, entity) -> false)
                    .isRedstoneConductor((state, level, pos) -> false)
                    .isSuffocating((state, level, pos) -> false)
                    .isViewBlocking((state, level, pos) -> false)
                    .sound(SoundType.GLASS)));

    public static final RegistryObject<Block> WAX_PIPE_CANDLE = register("wax_pipe_candle", () -> new WaxPipeCandle(
            BlockBehaviour.Properties.of()
                    .lightLevel(state -> 15) // 固定光等级
                    .strength(0.5f)
                    .randomTicks()
                    .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PIPE_HONEY_CANDLE = register("pipe_honey_candle", () -> new PipeHoneyCandle(
            BlockBehaviour.Properties.of()
                    .lightLevel(state -> 15) // 固定光等级
                    .strength(0.8F)
                    .randomTicks()
                    .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WAX_JUMP_SLIME = register("wax_jump_slime", () -> new WaxJumpSlime(
            BlockBehaviour.Properties.of().strength(1)
                    .noOcclusion().friction(0.98f).lightLevel((state)->{
                        return 8;
                    })
                    .isValidSpawn((state, level, pos, entity) -> false)
                    .isRedstoneConductor((state, level, pos) -> false)
                    .isSuffocating((state, level, pos) -> false)
                    .isViewBlocking((state, level, pos) -> false)
                    .sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<Block> WATER_WAX = register("water_wax", () -> new WaterWax(
            BlockBehaviour.Properties.of().strength(0.75f).lightLevel((state)->{
                return 12;
            }).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> HUGE_PIPE = register("huge_pipe", () -> new HugePipe(
            BlockBehaviour.Properties.of().strength(0.9f).lightLevel((state)->{
                return 8;
            }).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WATER_WAX_BASE = register("water_wax_base", () -> new WaterWaxBase(
            BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WAX_SPONGE = register("wax_sponge", () -> new WaxSponge(
            BlockBehaviour.Properties.of().strength(0.3f).sound(SoundType.CORAL_BLOCK)));
    public static final RegistryObject<Block> WET_WAX_SPONGE = register("wet_wax_sponge", () -> new WetWaxSponge(
            BlockBehaviour.Properties.of().strength(0.45F).sound(SoundType.WET_GRASS)));
    public static final RegistryObject<Block> PIPE_WALL = register("pipe_wall", () -> new PipeWall(
            BlockBehaviour.Properties.of().strength(0.45F).lightLevel((state)->{
                return 8;
            }).sound(SoundType.WOOD)));

    private static <T extends Block> RegistryObject<T> register(String path, Supplier<T> block) {
        return BLOCKS.register(path, block);
    }

    public static void init() {}
}