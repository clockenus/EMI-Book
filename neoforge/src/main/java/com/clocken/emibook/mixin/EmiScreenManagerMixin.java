package com.clocken.emibook.mixin;

import com.clocken.emibook.config.ModConfig;
import dev.emi.emi.config.EffectLocation;
import dev.emi.emi.config.EmiConfig;
import dev.emi.emi.runtime.EmiDrawContext;
import dev.emi.emi.runtime.EmiReloadManager;
import dev.emi.emi.screen.EmiScreenBase;
import dev.emi.emi.screen.EmiScreenManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EmiScreenManager.class)
public class EmiScreenManagerMixin {

    @Inject(method = "renderWidgets(Ldev/emi/emi/runtime/EmiDrawContext;IIFLdev/emi/emi/screen/EmiScreenBase;)V", at = @At("HEAD"))
    private static void emibook$renderWidgets(EmiDrawContext context, int mouseX, int mouseY, float delta, EmiScreenBase base, CallbackInfo ci) {
        if (!ModConfig.getConfig().switchEffectLocation) return;

        if (EmiConfig.enabled && EmiConfig.effectLocation == EffectLocation.RIGHT) {
            EmiConfig.effectLocation = EffectLocation.RIGHT_COMPRESSED;
        } else if (!EmiConfig.enabled && EmiConfig.effectLocation == EffectLocation.RIGHT_COMPRESSED) {
            EmiConfig.effectLocation = EffectLocation.RIGHT;
        }
    }

    @Redirect(method = "keyPressed(III)Z", at = @At(value = "INVOKE", target = "Ldev/emi/emi/screen/EmiScreenManager;isDisabled()Z"))
    private static boolean emibook$keyPressed() {
        return !EmiReloadManager.isLoaded();
    }
}
