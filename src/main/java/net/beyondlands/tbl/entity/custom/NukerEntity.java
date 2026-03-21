package net.beyondlands.tbl.entity.custom;

import java.util.Collection;
import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.gameevent.GameEvent;

public class NukerEntity extends Creeper implements PowerableMob {
    private static final EntityDataAccessor<Integer> DATA_SWELL_DIR;
    private static final EntityDataAccessor<Boolean> DATA_IS_POWERED;
    private static final EntityDataAccessor<Boolean> DATA_IS_IGNITED;
    private int oldSwell;
    private int swell;
    private int maxSwell = 30;
    private int explosionRadius = 7;
    private int droppedSkulls;

    public NukerEntity(EntityType<? extends net.minecraft.world.entity.monster.Creeper> entityType, Level level) {
        super(entityType, level);
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SwellGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Ocelot.class, 6.0F, (double)1.0F, 1.2));
        this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Cat.class, 6.0F, (double)1.0F, 1.2));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, (double)1.0F, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this, new Class[0]));
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.175F);
    }
    @Override
    public int getMaxFallDistance() {
        return this.getTarget() == null ? this.getComfortableFallDistance(0.0F) : this.getComfortableFallDistance(this.getHealth() - 1.0F);
    }
    @Override
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        boolean flag = super.causeFallDamage(fallDistance, multiplier, source);
        this.swell += (int)(fallDistance * 1.5F);
        if (this.swell > this.maxSwell - 5) {
            this.swell = this.maxSwell - 5;
        }

        return flag;
    }
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_SWELL_DIR, -1);
        builder.define(DATA_IS_POWERED, false);
        builder.define(DATA_IS_IGNITED, false);
    }
    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if ((Boolean)this.entityData.get(DATA_IS_POWERED)) {
            compound.putBoolean("powered", true);
        }

        compound.putShort("Fuse", (short)this.maxSwell);
        compound.putByte("ExplosionRadius", (byte)this.explosionRadius);
        compound.putBoolean("ignited", this.isIgnited());
    }
    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(DATA_IS_POWERED, compound.getBoolean("powered"));
        if (compound.contains("Fuse", 99)) {
            this.maxSwell = compound.getShort("Fuse");
        }

        if (compound.contains("ExplosionRadius", 99)) {
            this.explosionRadius = compound.getByte("ExplosionRadius");
        }

        if (compound.getBoolean("ignited")) {
            this.ignite();
        }

    }
    @Override
    public void tick() {
        if (this.isAlive()) {
            this.oldSwell = this.swell;
            if (this.isIgnited()) {
                this.setSwellDir(1);
            }

            int i = this.getSwellDir();
            if (i > 0 && this.swell == 0) {
                this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
                this.gameEvent(GameEvent.PRIME_FUSE);
            }

            this.swell += i;
            if (this.swell < 0) {
                this.swell = 0;
            }

            if (this.swell >= this.maxSwell) {
                this.swell = this.maxSwell;
                this.explodeCreeper();
            }
        }

        super.tick();
    }
    @Override
    public void setTarget(@Nullable LivingEntity target) {
        if (!(target instanceof Goat)) {
            super.setTarget(target);
        }

    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.CREEPER_HURT;
    }
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.CREEPER_DEATH;
    }
    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource damageSource, boolean recentlyHit) {
        super.dropCustomDeathLoot(level, damageSource, recentlyHit);
        Entity entity = damageSource.getEntity();
        if (entity != this && entity instanceof net.minecraft.world.entity.monster.Creeper creeper) {
            if (creeper.canDropMobsSkull()) {
                creeper.increaseDroppedSkulls();
                this.spawnAtLocation(Items.CREEPER_HEAD);
            }
        }

    }
    @Override
    public boolean doHurtTarget(Entity entity) {
        return true;
    }
    @Override
    public boolean isPowered() {
        return (Boolean)this.entityData.get(DATA_IS_POWERED);
    }
    @Override
    public float getSwelling(float partialTicks) {
        return Mth.lerp(partialTicks, (float)this.oldSwell, (float)this.swell) / (float)(this.maxSwell - 2);
    }
    @Override
    public int getSwellDir() {
        return (Integer)this.entityData.get(DATA_SWELL_DIR);
    }
    @Override
    public void setSwellDir(int state) {
        this.entityData.set(DATA_SWELL_DIR, state);
    }
    @Override
    public void thunderHit(ServerLevel level, LightningBolt lightning) {
        super.thunderHit(level, lightning);
        this.entityData.set(DATA_IS_POWERED, true);
    }
    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.is(ItemTags.CREEPER_IGNITERS)) {
            SoundEvent soundevent = itemstack.is(Items.FIRE_CHARGE) ? SoundEvents.FIRECHARGE_USE : SoundEvents.FLINTANDSTEEL_USE;
            this.level().playSound(player, this.getX(), this.getY(), this.getZ(), soundevent, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
            if (!this.level().isClientSide) {
                this.ignite();
                if (!itemstack.isDamageableItem()) {
                    itemstack.shrink(1);
                } else {
                    itemstack.hurtAndBreak(1, player, getSlotForHand(hand));
                }
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(player, hand);
        }
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
    private void explodeCreeper() {
        if (!this.level().isClientSide) {
            float f = this.isPowered() ? 2.0F : 1.0F;
            this.dead = true;
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * f, true, ExplosionInteraction.MOB);
            this.spawnLingeringCloud();
            this.triggerOnDeathMobEffects(RemovalReason.KILLED);
            spawnSphereParticles((ServerLevel) this.level(), this.getX(), this.getY(), this.getZ());
            this.discard();
        }

    }

    private void spawnLingeringCloud() {
        Collection<MobEffectInstance> collection = this.getActiveEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
            areaeffectcloud.setRadius(2.5F);
            areaeffectcloud.setRadiusOnUse(-0.5F);
            areaeffectcloud.setWaitTime(10);
            areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
            areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());

            for(MobEffectInstance mobeffectinstance : collection) {
                areaeffectcloud.addEffect(new MobEffectInstance(mobeffectinstance));
            }

            this.level().addFreshEntity(areaeffectcloud);
        }

    }
    @Override
    public boolean isIgnited() {
        return (Boolean)this.entityData.get(DATA_IS_IGNITED);
    }
    @Override
    public void ignite() {
        this.entityData.set(DATA_IS_IGNITED, true);
    }
    @Override
    public boolean canDropMobsSkull() {
        return this.isPowered() && this.droppedSkulls < 1;
    }
    @Override
    public void increaseDroppedSkulls() {
        ++this.droppedSkulls;
    }

    static {
        DATA_SWELL_DIR = SynchedEntityData.defineId(NukerEntity.class, EntityDataSerializers.INT);
        DATA_IS_POWERED = SynchedEntityData.defineId(NukerEntity.class, EntityDataSerializers.BOOLEAN);
        DATA_IS_IGNITED = SynchedEntityData.defineId(NukerEntity.class, EntityDataSerializers.BOOLEAN);
    }
}
