package net.beyondlands.tbl.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NetherWartBlock.class)
public class NetherWartBlockMixin {

    @Inject(method = "mayPlaceOn", at = @At("RETURN"), cancellable = true)
    private void allowSoulSoil(BlockState state, BlockGetter level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) return;

        if (state.is(Blocks.SOUL_SOIL)) {
            cir.setReturnValue(true);
        }
    }
}