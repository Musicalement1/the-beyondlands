package net.musicalement.tbl.block.explosives;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.apache.logging.log4j.core.jmx.Server;

import javax.annotation.Nullable;

public class NapalmBlock extends Block {

    public NapalmBlock(Properties properties) {
        super(properties);
    }

    public void onCaughtFire(BlockState state, Level world, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        explode(world, pos, igniter);
    }
    public void onCaughtFireLazy(Level world, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        explode(world, pos, igniter);
    }

    private static void spawnSphereParticles(ServerLevel level, double cx, double cy, double cz) {


        int points = 600;//density
        double radius = 0.5; //ini size

        for (int i = 0; i < points; i++) {

            double theta = 2 * Math.PI * level.random.nextDouble();
            double phi = Math.acos(2 * level.random.nextDouble() - 1);

            double x = Math.sin(phi) * Math.cos(theta);
            double y = Math.sin(phi) * Math.sin(theta);
            double z = Math.cos(phi);

            double px = cx + x * radius;
            double py = cy + y * radius;
            double pz = cz + z * radius;

            double speed = 1.25;

            level.sendParticles(
                    ParticleTypes.FLAME,
                    px, py, pz,
                    0,
                    x * speed,
                    y * speed,
                    z * speed,
                    1
            );
        }
    }


    private static void explode(Level level, BlockPos pos, @Nullable LivingEntity entity) {
        if (!level.isClientSide) {
            spawnSphereParticles((ServerLevel) level, pos.getX(), pos.getY(), pos.getZ());
            level.explode(entity, pos.getX(), pos.getY(), pos.getZ(), 10, true, Level.ExplosionInteraction.TNT);
        }

    }
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (level.hasNeighborSignal(pos)) {
            this.onCaughtFire(state, level, pos, (Direction)null, (LivingEntity)null);
            level.removeBlock(pos, false);
        }

    }
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!oldState.is(state.getBlock()) && level.hasNeighborSignal(pos)) {
            this.onCaughtFire(state, level, pos, (Direction)null, (LivingEntity)null);
            level.removeBlock(pos, false);
        }

    }
    public void wasExploded(Level level, BlockPos pos, Explosion explosion) {
        if (!level.isClientSide) {
            this.onCaughtFireLazy(level, pos, (Direction)null, (LivingEntity)null);
        }
    }

    public boolean dropFromExplosion(Explosion explosion) {
        return false;
    }

    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!stack.is(Items.FLINT_AND_STEEL) && !stack.is(Items.FIRE_CHARGE)) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        } else {
            this.onCaughtFire(state, level, pos, hitResult.getDirection(), player);
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            Item item = stack.getItem();
            if (stack.is(Items.FLINT_AND_STEEL)) {
                stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            } else {
                stack.consume(1, player);
            }

            player.awardStat(Stats.ITEM_USED.get(item));
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
    }


}
