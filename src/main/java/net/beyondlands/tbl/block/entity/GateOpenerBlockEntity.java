package net.beyondlands.tbl.block.entity;

import net.beyondlands.tbl.block.ModBlocks;
import net.beyondlands.tbl.screen.custom.GateMenu;
import net.beyondlands.tbl.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GateOpenerBlockEntity extends BlockEntity implements MenuProvider {

    public final ItemStackHandler inventory = new ItemStackHandler(1) {

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return stack.is(ModTags.Items.BATTERY);
        }

        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public GateOpenerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GATE_BE.get(), pPos, pBlockState);
    }

    public void removePortal() {
        if(level == null) return;

        BlockPos portalPos = this.worldPosition.above();

        if(level.getBlockState(portalPos).is(ModBlocks.BL_PORTAL.get())) {
            level.removeBlock(portalPos, false);
        }
    }
    private boolean hasPlayedSound = false;
    public static void tick(Level level, BlockPos pos, BlockState state, GateOpenerBlockEntity blockEntity) {

        if(level.isClientSide) return;

        ItemStack battery = blockEntity.inventory.getStackInSlot(0);
        BlockPos portalPos = pos.above();

        if(!battery.isEmpty() && battery.is(ModTags.Items.BATTERY)) {

            if(battery.isDamageableItem() && battery.getDamageValue() < battery.getMaxDamage()) {

                // damage battery
                battery.setDamageValue(battery.getDamageValue() + 3);//3 is high cuz it consumes a lotta

                // place portal
                if(level.getBlockState(portalPos).isAir()) {
                    level.setBlock(portalPos, ModBlocks.BL_PORTAL.get().defaultBlockState(), 3);
                }

                if(level instanceof ServerLevel serverLevel) {
                    if (!blockEntity.hasPlayedSound) {
                        serverLevel.playSound(
                                null,
                                portalPos,
                                SoundEvents.END_PORTAL_SPAWN,
                                SoundSource.BLOCKS,
                                1.0F,
                                1.0F
                        );
                        blockEntity.hasPlayedSound = true;
                    }
                    serverLevel.sendParticles(
                            ParticleTypes.PORTAL,
                            portalPos.getX() + 0.5,
                            portalPos.getY() + 0.5,
                            portalPos.getZ() + 0.5,
                            5,
                            0.3,
                            0.5,
                            0.3,
                            0.01
                    );
                    serverLevel.sendParticles(
                            ParticleTypes.END_ROD,
                            portalPos.getX() + 0.5,
                            portalPos.getY() + 0.8,
                            portalPos.getZ() + 0.5,
                            1,
                            0.2,
                            0.2,
                            0.2,
                            0.01
                    );
                }

            } else {
                blockEntity.inventory.setStackInSlot(0, ItemStack.EMPTY);
                removePortal(level, portalPos, blockEntity);
            }

        } else {
            removePortal(level, portalPos, blockEntity);
        }
    }
    public void resetSoundFlag() {
        this.hasPlayedSound = false;
    }
    private static void removePortal(Level level, BlockPos pos, GateOpenerBlockEntity blockEntity) {
        blockEntity.resetSoundFlag();
        if(level.getBlockState(pos).is(ModBlocks.BL_PORTAL.get())) {
            level.removeBlock(pos, false);
        }
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());

        for(int i = 0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public void clearContents() {
        inventory.setStackInSlot(0, ItemStack.EMPTY);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Gate Opener");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new GateMenu(pContainerId, pPlayerInventory, this);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }
}