package com.clocken.emibook.mixin;

import com.clocken.emibook.config.ModConfig;
import dev.emi.emi.config.EmiConfig;
import dev.emi.emi.screen.RecipeScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RecipeScreen.class, remap = false)
public abstract class RecipeScreenMixin extends Screen {
    protected RecipeScreenMixin(Component title) {
        super(title);
    }

    @Shadow
    int backgroundWidth;

    @Shadow
    int x;

    @Inject(remap = false, method = "setRecipePageWidth(I)V", at = @At(value = "INVOKE", target = "Ljava/util/List;get(I)Ljava/lang/Object;"))
    private void emibook$setRecipePageWidth(CallbackInfo ci) {
        if (EmiConfig.enabled && ModConfig.get().screenShift) {
            this.x = 177 + (this.width - backgroundWidth - 200) / 2;
        }
    }
}