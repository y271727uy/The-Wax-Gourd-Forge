package com.ytgld.the_wax.block;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.List;

public class WaxPipeCandle extends CandleBlock {
    private static final Int2ObjectMap<List<Vec3>> maPart = new Int2ObjectOpenHashMap<>(4);
    
    static {
        float f = 0.0625f;
        maPart.put(1, List.of(new Vec3(8 ,8, 8).scale(f)));
        maPart.put(2, List.of(new Vec3(8 ,10, 8).scale(f)));
        maPart.put(3, List.of(new Vec3(8 ,12 , 8).scale(f)));
        maPart.put(4, List.of(new Vec3(8 ,16, 8).scale(f)));
    }
    
    public WaxPipeCandle(Properties properties) {
        super(properties);
        // 设置默认状态，确保CANDLES和LIT属性正确初始化
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(CANDLES, 1)
            .setValue(LIT, false));
    }
    
    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (blockState.getValue(LIT)) {
            Vec3 playerPos = blockPos.getCenter();
            int range = 4 + blockState.getValue(CANDLES) * 2;
            int lvl = 0;
            if (blockState.getValue(CANDLES) == 4) {
                lvl=1;
            }
            List<Player> list = serverLevel.getEntitiesOfClass(Player.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
            for (Player player : list) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200 * blockState.getValue(CANDLES), lvl, true, true));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200 * blockState.getValue(CANDLES), lvl, true, true));
            }
        }
    }

    @Override
    protected Iterable<Vec3> getParticleOffsets(BlockState blockState) {
        return maPart.get(blockState.getValue(CANDLES));
    }

    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        return List.of(new ItemStack(this,blockState.getValue(CANDLES)));
    }

    @Override
    public VoxelShape getShape(BlockState blockState,
                                  BlockGetter blockGetter,
                                  BlockPos blockPos,
                                  CollisionContext collisionContext) {
        if (blockState.getValue(CANDLES)==1){
            return Block.box(7, 0, 7, 9, 4, 9);
        } else if (blockState.getValue(CANDLES) == 2) {
            return Block.box(6, 0, 6, 10, 6, 10);
        } else if (blockState.getValue(CANDLES) == 3) {
            return Block.box(5, 0, 5, 11, 8, 11);
        } else if (blockState.getValue(CANDLES) == 4) {
            return Block.box(4, 0, 4, 12, 12, 12);
        }
        return Block.box(7, 0, 7, 9, 4, 9);
    }
}