package net.beyondlands.tbl.event;

import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.block.CoriumBlock;
import net.beyondlands.tbl.item.hammer.HammerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.HashSet;
import java.util.Set;

@EventBusSubscriber(modid = TBL.MOD_ID)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    // Don't be a jerk License
    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

    @SubscribeEvent
    public static void coriumDamage(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.level().isClientSide) return;

        HitResult hit = player.pick(5.0D, 0.0F, false);

        if (!(hit instanceof BlockHitResult blockHit)) return;

        BlockPos pos = blockHit.getBlockPos();
        BlockState state = player.level().getBlockState(pos);

        if (!(state.getBlock() instanceof CoriumBlock)) return;

        if (player.swinging) {//if its mining
            if (player.tickCount % 10 == 0) {
                player.hurt(player.level().damageSources().hotFloor(), 1.0F);//hurt them
            }
        }
    }

    @SubscribeEvent
    public static void propulsedEntity(EntityTickEvent.Post event) {
        Entity entity = event.getEntity();

        if (!(entity instanceof LivingEntity living)) return;

        Level level = living.level();
        if (level.isClientSide) return;

        int timer = living.getPersistentData().getInt("boost_trail");

        if (timer > 0) {
            living.getPersistentData().putInt("boost_trail", timer - 1);

            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.END_ROD,
                        living.getX(),
                        living.getY() + 0.5,
                        living.getZ(),
                        1,
                        0.2, 0.2, 0.2,
                        0.01
                );
            }
        }
    }

}