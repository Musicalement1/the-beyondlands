package net.beyondlands.tbl.item.hydroreactive;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class LithiumReact extends Item {

    public LithiumReact(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack,
                                TooltipContext context,
                                List<Component> tooltipComponents,
                                TooltipFlag flag) {

        tooltipComponents.add(Component.literal("Hydroreactive")
                .withStyle(ChatFormatting.RED));
    }



    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {


        Level level = entity.level();

        if (level.isClientSide()) return false;

        var data = entity.getPersistentData();


        if (!data.contains("hydrotimer")) {
            data.putInt("hydrotimer", 80);
            entity.setPickUpDelay(40);
        }

        int timer = data.getInt("hydrotimer");
        data.putInt("hydrotimer", timer - 1);


        if (timer <= 0 && entity.isInWaterRainOrBubble()) {

            level.explode(
                    null,
                    entity.getX(),
                    entity.getY(),
                    entity.getZ(),
                    4.0F,
                    false,
                    Level.ExplosionInteraction.TNT
            );

            entity.discard();
            return true; // on supprime l'update vanilla
        }

        return false;
    }
}