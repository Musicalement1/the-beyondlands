package net.beyondLands.tbl.item.battery;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class BatteryItem extends Item {

    public BatteryItem(Properties pProperties) {
        super(pProperties);
    }


    private int getEnergyColor(float percentage) {

        int red = (int) (255 * (1 - percentage));
        int green = (int) (255 * percentage);

        return (red << 16) | (green << 8);
    }

    @Override
    public void appendHoverText(ItemStack stack,
                                Item.TooltipContext context,
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
}