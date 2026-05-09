package com.clocken.emibook.mixin;

import com.clocken.emibook.config.ModConfig;
import dev.emi.emi.config.EmiConfig;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeBookComponent.class)
public class RecipeBookComponentMixin {

    @Inject(method = "updateScreenPosition(II)I", at = @At("HEAD"), cancellable = true)
    private void emibook$updateScreenPosition(int width, int imageWidth, CallbackInfoReturnable<Integer> cir) {
        if (EmiConfig.enabled && ModConfig.getConfig().screenShift) {
            int i = 177 + (width - imageWidth - 200) / 2;
            cir.setReturnValue(i);
        }
    }

//    @ModifyReturnValue(method = "updateScreenPosition(II)I", at = @At("RETURN"))
//    private int emibook$updateScreenPosition(int original, int width, int imageWidth) {
//        int i;
//
//        if (EmiConfig.enabled && ModConfig.getConfig().screenShift) {
//            i = 177 + (width - imageWidth - 200) / 2;
//        } else {
//            i = (width - imageWidth) / 2;
//        }
//        return i;
//    }
}
