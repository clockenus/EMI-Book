package com.clocken.emibook.mixin.buttons;

import com.clocken.emibook.config.ModConfig;
import dev.emi.emi.config.EmiConfig;
import dev.emi.emi.config.RecipeBookAction;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.BrewingStandScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.BrewingStandMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingStandScreen.class)
public abstract class BrewingStandScreenMixin extends AbstractContainerScreen<BrewingStandMenu> {
    public BrewingStandScreenMixin(BrewingStandMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Unique
    private final RecipeBookComponent _$recipeBookComponent = new RecipeBookComponent();

    @Inject(remap = false, method = "init()V", at = @At("RETURN"))
    protected void emibook$init(CallbackInfo ci) {
        if (EmiConfig.recipeBookAction == RecipeBookAction.DEFAULT || !ModConfig.get().addButtons) return;

        this.leftPos = this._$recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
        this.addRenderableWidget(new ImageButton(this.leftPos + 135, this.height / 2 - 50, 20, 18, RecipeBookComponent.RECIPE_BUTTON_SPRITES, (p_313434_) -> {
            if (ModConfig.get().screenShift) {
                this.leftPos = this._$recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
                p_313434_.setPosition(this.leftPos + 135, this.height / 2 - 50);
            }
            this._$recipeBookComponent.toggleVisibility();
        }));
    }

    @ModifyVariable(method = "renderBg(Lnet/minecraft/client/gui/GuiGraphics;FII)V", index = 5, at = @At("STORE"))
    public int emibook$renderBgVariable(int i) {
        if (EmiConfig.recipeBookAction != RecipeBookAction.DEFAULT && EmiConfig.enabled && ModConfig.get().screenShift && ModConfig.get().addButtons) {
            return i + 77;
        } else {
            return i;
        }
    }
}
