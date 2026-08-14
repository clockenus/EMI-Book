package com.clocken.emibook.mixin;

import com.clocken.emibook.config.ModConfig;
import dev.emi.emi.config.EmiConfig;
import dev.emi.emi.config.RecipeBookAction;
import dev.emi.emi.config.SidebarType;
import dev.emi.emi.screen.EmiScreenManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeBookComponent.class)
public abstract class RecipeBookComponentMixin {

    @Inject(method = "updateScreenPosition(II)I", at = @At("HEAD"), cancellable = true)
    private void emibook$updateScreenPosition(int width, int imageWidth, CallbackInfoReturnable<Integer> cir) {
        if (EmiConfig.enabled && ModConfig.get().screenShift) {
            int i = 177 + (width - imageWidth - 200) / 2;
            cir.setReturnValue(i);
        }
    }

    @Shadow
    public abstract boolean isVisible();
    @Shadow
    protected abstract void setVisible(boolean visible);

    // Rewritten and expended mixin from EMI
    @Inject(method = "toggleVisibility()V", at = @At("HEAD"), cancellable = true)
    private void emibook$toggleVisibility(CallbackInfo ci) {
        if (EmiConfig.recipeBookAction == RecipeBookAction.DEFAULT) {
            return;
        } else if (EmiConfig.recipeBookAction == RecipeBookAction.TOGGLE_CRAFTABLES) {
            EmiScreenManager.toggleSidebarType(SidebarType.CRAFTABLES);
        } else if (EmiConfig.recipeBookAction == RecipeBookAction.TOGGLE_VISIBILITY) {
            EmiScreenManager.toggleVisibility(false);
        }
        if (isVisible()) {
            setVisible(false);
        }
        // rebuilds screen widgets for better compatibility with other mods
        Minecraft mc = Minecraft.getInstance();
        ((ScreenInvoker) mc.screen).emibook$rebuildWidgets();

        ci.cancel();
    }
}
