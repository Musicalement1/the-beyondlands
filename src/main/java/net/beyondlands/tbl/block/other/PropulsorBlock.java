package net.beyondlands.tbl.block.other;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PropulsorBlock extends Block {

    public PropulsorBlock(Properties properties) {
        super(properties);
    }

    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float distance) {
        entity.causeFallDamage(distance, 0.2F, level.damageSources().fall());
    }

    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {

        if (entity instanceof LivingEntity living && !entity.isSteppingCarefully()) {
            //((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.LEVITATION, 40, 5, false, false, false));
            Vec3 velocity = living.getDeltaMovement();

            float force = living.getItemBySlot(EquipmentSlot.CHEST).is(Items.ELYTRA) ? 3.0f : 1.2f;

            living.setDeltaMovement(velocity.x, force, velocity.z);//impulse
            level.playSound(living, pos, SoundEvents.FIREWORK_ROCKET_LAUNCH, SoundSource.BLOCKS, 1f, 1f);

            living.getPersistentData().putInt("boost_trail", 20);

        }
        super.stepOn(level, pos, state, entity);
    }


    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        VoxelShape voxelshape = this.getShape(state, level, pos, CollisionContext.empty());
        Vec3 vec3 = voxelshape.bounds().getCenter();
        double d0 = (double)pos.getX() + vec3.x;
        double d1 = (double)pos.getZ() + vec3.z;

        for(int i = 0; i < 3; ++i) {
            if (random.nextBoolean()) {
                level.addParticle(ParticleTypes.END_ROD, d0 + random.nextDouble() * (double)3.0F, (double)pos.getY() + ((double)3.0F - (random.nextDouble() * (double)6.0f)), d1 + random.nextDouble() * (double)3.0F, (double)0.0F, (double)0.0F, (double)0.0F);
            }
        }

    }
}
