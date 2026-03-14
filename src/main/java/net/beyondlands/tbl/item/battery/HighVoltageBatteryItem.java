package net.beyondlands.tbl.item.battery;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class HighVoltageBatteryItem extends Item {

    public HighVoltageBatteryItem(Properties pProperties) {
        super(pProperties);
    }


    private int getEnergyColor(float percentage) {

        int red = (int) (255 * (1 - percentage));
        int green = (int) (255 * percentage);

        return (red << 16) | (green << 8);
    }

    @Override
    public void appendHoverText(ItemStack stack,
                                TooltipContext context,
                                List<Component> tooltipComponents,
                                TooltipFlag flag) {

        int maxEnergy = stack.getMaxDamage();
        int currentEnergy = maxEnergy - stack.getDamageValue();

        float percentage = (float) currentEnergy / maxEnergy;
        int color = getEnergyColor(percentage);

        tooltipComponents.add(
                Component.literal(currentEnergy + " / " + maxEnergy + " J")
                        .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(color)))
        );
    }


    @Override
    public void inventoryTick(ItemStack stack, Level level, net.minecraft.world.entity.Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (!level.isClientSide && entity instanceof LivingEntity living && stack.isDamageableItem()) {
            boolean inMainHand = living.getMainHandItem() == stack;
            boolean inOffHand = living.getOffhandItem() == stack;

            if ((inMainHand || inOffHand) && level.getGameTime() % 2 == 0) {
                stack.hurtAndBreak(5, living, EquipmentSlot.MAINHAND);
            }
        }
    }
}