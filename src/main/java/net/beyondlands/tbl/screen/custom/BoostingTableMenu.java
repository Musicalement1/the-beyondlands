package net.beyondlands.tbl.screen.custom;

import net.beyondlands.tbl.block.ModBlocks;
import net.beyondlands.tbl.block.entity.BoostingTableBlockEntity;
import net.beyondlands.tbl.item.ModItems;
import net.beyondlands.tbl.screen.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;

public class BoostingTableMenu extends AbstractContainerMenu {
    private final BoostingTableBlockEntity blockEntity;
    private final ContainerLevelAccess access;

    public BoostingTableMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, getBlockEntity(inv, extraData));
    }



    private static BoostingTableBlockEntity getBlockEntity(Inventory inv, FriendlyByteBuf extraData) {
        if (extraData == null) {
            throw new IllegalStateException("BoostingTableMenu opened without extra data");
        }

        BlockPos pos = extraData.readBlockPos();
        BlockEntity be = inv.player.level().getBlockEntity(pos);
        if (be instanceof BoostingTableBlockEntity boostingTableBlockEntity) {
            return boostingTableBlockEntity;
        }

        throw new IllegalStateException("BoostingTableBlockEntity cannot be found in pos " + pos);
    }

    public BoostingTableMenu(int containerId, Inventory inv, BlockEntity blockEntity) {
        super(ModMenuTypes.BOOSTING_TABLE_MENU.get(), containerId);
        this.blockEntity = (BoostingTableBlockEntity) blockEntity;
        this.access = ContainerLevelAccess.create(this.blockEntity.getLevel(), this.blockEntity.getBlockPos());

        this.addSlot(new SlotItemHandler(this.blockEntity.getInventory(), 0, 81, 16) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItems.ENCHANTITE_SHARD.get());
            }
        });

        this.addSlot(new SlotItemHandler(this.blockEntity.getInventory(), 1, 43, 53) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return !stack.getEnchantments().isEmpty();
            }
        });

        this.addSlot(new SlotItemHandler(this.blockEntity.getInventory(), 2, 118, 53) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                super.onTake(player, stack);
                BoostingTableMenu.this.blockEntity.craft(player);
            }

            @Override
            public boolean mayPickup(Player player) {
                return !this.getItem().isEmpty();
            }
        });



        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }


    @Override
    public boolean stillValid(Player player) {
        return AbstractContainerMenu.stillValid(this.access, player, ModBlocks.BOOSTING_TABLE.get());
    }
    private static boolean hasEnchantments(ItemStack stack) {
        return !stack.getEnchantments().isEmpty();
    }
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack current = slot.getItem();
            result = current.copy();

            if (index == 2) {
                if (!moveItemStackTo(current, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(current, result);
            } else if (index == 0) {
                if (!moveItemStackTo(current, 3, 39, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index == 1) {
                if (!moveItemStackTo(current, 3, 39, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (current.is(ModItems.ENCHANTITE_SHARD.get())) {
                    if (!moveItemStackTo(current, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (hasEnchantments(current)) {
                    if (!moveItemStackTo(current, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 30) {
                    if (!moveItemStackTo(current, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!moveItemStackTo(current, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (current.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (current.getCount() == result.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, current);
        }

        return result;
    }

    private void addPlayerInventory(Inventory inventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory inventory) {
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(inventory, col, 8 + col * 18, 142));
        }
    }
}
