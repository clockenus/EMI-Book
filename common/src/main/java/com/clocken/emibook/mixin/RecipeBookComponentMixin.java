package com.clocken.emibook.mixin;

import com.clocken.emibook.config.ModConfig;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.emi.emi.config.EmiConfig;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RecipeBookComponent.class)
public class RecipeBookComponentMixin {

    @ModifyReturnValue(method = "updateScreenPosition", remap = false, at = @At("RETURN"))
    private int mixinUpdateScreenPosition(int original, int width, int imageWidth) {
        int i;

        if (EmiConfig.enabled && ModConfig.getConfig().screenShift) {
            i = 177 + (width - imageWidth - 200) / 2;
        } else {
            i = (width - imageWidth) / 2;
        }
        return i;
    }
}