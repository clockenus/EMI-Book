package com.clocken.emibook.mixin;

import com.clocken.emibook.config.ModConfig;
import com.clocken.emibook.platform.Services;
import com.llamalad7.mixinextras.sugar.Local;
import dev.emi.emi.config.EffectLocation;
import dev.emi.emi.config.EmiConfig;
import dev.emi.emi.config.RecipeBookAction;
import dev.emi.emi.input.EmiBind;
import dev.emi.emi.runtime.EmiDrawContext;
import dev.emi.emi.runtime.EmiReloadManager;
import dev.emi.emi.screen.EmiScreenBase;
import dev.emi.emi.screen.EmiScreenManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;

@Mixin(value = EmiScreenManager.class, remap = false)
public class EmiScreenManagerMixin {

    @Inject(remap = false, method = "renderWidgets(Ldev/emi/emi/runtime/EmiDrawContext;IIFLdev/emi/emi/screen/EmiScreenBase;)V", at = @At("HEAD"))
    private static void emibook$renderWidgets(EmiDrawContext context, int mouseX, int mouseY, float delta, EmiScreenBase base, CallbackInfo ci) {
        if (!ModConfig.get().switchEffectLocation) return;

        if (EmiConfig.enabled && EmiConfig.effectLocation == EffectLocation.RIGHT) {
            EmiConfig.effectLocation = EffectLocation.RIGHT_COMPRESSED;
        } else if (!EmiConfig.enabled && EmiConfig.effectLocation == EffectLocation.RIGHT_COMPRESSED) {
            EmiConfig.effectLocation = EffectLocation.RIGHT;
        }
    }

    @Redirect(remap = false, method = "keyPressed(III)Z", at = @At(value = "INVOKE", target = "Ldev/emi/emi/screen/EmiScreenManager;isDisabled()Z"))
    private static boolean emibook$keyPressedRedirect() {
        if (ModConfig.get().independentBinds) {
            return !EmiReloadManager.isLoaded();
        }
        return !EmiReloadManager.isLoaded() || !EmiConfig.enabled;
    }

    // Patience fix
    @Inject(remap = false, method = "craftInteraction", at = @At("HEAD"), cancellable = true)
    private static void emibook$craftInteraction(CallbackInfoReturnable<Boolean> cir, @Local(argsOnly = true, name = "arg3") Function<EmiBind, Boolean> function) {
        if (!ModConfig.get().screenShift || EmiConfig.recipeBookAction != RecipeBookAction.DEFAULT) return;
        if (!Services.PLATFORM.isModLoaded("patience")) return;

        if (function.apply(EmiConfig.craftAllToInventory)) {
            cir.setReturnValue(false);
        } else if (function.apply(EmiConfig.craftOneToInventory)) {
            cir.setReturnValue(false);
        } else if (function.apply(EmiConfig.craftOneToCursor)) {
            cir.setReturnValue(false);
        }
    }
}
