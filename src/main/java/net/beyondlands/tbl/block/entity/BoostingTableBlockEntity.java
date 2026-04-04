package net.beyondlands.tbl.block.entity;

import net.beyondlands.tbl.item.ModItems;
import net.beyondlands.tbl.screen.custom.BoostingTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.minecraft.world.item.enchantment.ItemEnchantments;


public class BoostingTableBlockEntity extends BlockEntity implements MenuProvider {
    public static final int SHARD_SLOT = 0;
    public static final int INPUT_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;

    private final ItemStackHandler inventory = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide) {
                updateResult();
            }
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return switch (slot) {
                case SHARD_SLOT -> stack.is(ModItems.ENCHANTITE_SHARD.get());
                case INPUT_SLOT -> !stack.getEnchantments().isEmpty();
                case OUTPUT_SLOT -> false;
                default -> false;
            };
        }

        @Override
        public int getSlotLimit(int slot) {
            return slot == OUTPUT_SLOT ? 64 : 64;
        }
    };

    private int requiredShards = 0;

    public BoostingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BOOSTING_TABLE_BE.get(), pos, state);
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.tbl.boosting_table");
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new BoostingTableMenu(containerId, playerInventory, this);
    }

    public int getRequiredShards() {
        return requiredShards;
    }

    private boolean updatingResult = false;

    public void updateResult() {
        if (level == null || level.isClientSide || updatingResult) return;

        updatingResult = true;
        try {
            ItemStack input = inventory.getStackInSlot(INPUT_SLOT);
            ItemStack shards = inventory.getStackInSlot(SHARD_SLOT);

            ItemStack newOutput = ItemStack.EMPTY;
            requiredShards = 0;

            if (input.isEmpty() || input.getEnchantments().isEmpty()) {
                inventory.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
                setChanged();
                return;
            }

            ItemEnchantments enchants = EnchantmentHelper.getEnchantmentsForCrafting(input);

            int cost = 0;
            for (var entry : enchants.entrySet()) {
                cost += entry.getIntValue();
            }

            if (cost <= 0 || shards.getCount() < cost) {
                inventory.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
                requiredShards = cost;
                setChanged();
                return;
            }

            requiredShards = cost;

            ItemStack output = input.copy();
            ItemEnchantments current = EnchantmentHelper.getEnchantmentsForCrafting(output);
            ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(current);

            for (var entry : current.entrySet()) {
                mutable.set(entry.getKey(), entry.getIntValue() + 1);
            }

            EnchantmentHelper.setEnchantments(output, mutable.toImmutable());
            newOutput = output;

            inventory.setStackInSlot(OUTPUT_SLOT, newOutput);
            setChanged();
        } finally {
            updatingResult = false;
        }
    }


    public void craft(Player player) {
        if (level == null || level.isClientSide) return;

        ItemStack output = inventory.getStackInSlot(OUTPUT_SLOT);
        if (output.isEmpty()) return;

        ItemStack shards = inventory.getStackInSlot(SHARD_SLOT);
        ItemStack input = inventory.getStackInSlot(INPUT_SLOT);

        if (input.isEmpty() || shards.isEmpty()) return;
        if (shards.getCount() < requiredShards) return;

        inventory.extractItem(SHARD_SLOT, requiredShards, false);
        inventory.extractItem(INPUT_SLOT, 1, false);
        inventory.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);

        requiredShards = 0;
        updateResult();
        setChanged();
    }


    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        requiredShards = tag.getInt("requiredShards");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", inventory.serializeNBT(registries));
        tag.putInt("requiredShards", requiredShards);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.BOOSTING_TABLE_BE.get();
    }
}
