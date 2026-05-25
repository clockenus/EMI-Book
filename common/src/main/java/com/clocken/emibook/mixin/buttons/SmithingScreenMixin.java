package com.clocken.emibook.mixin.buttons;

import com.clocken.emibook.config.ModConfig;
import dev.emi.emi.config.EmiConfig;
import dev.emi.emi.config.RecipeBookAction;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.client.gui.screens.inventory.SmithingScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SmithingMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SmithingScreen.class)
public abstract class SmithingScreenMixin extends ItemCombinerScreen<SmithingMenu> {
    public SmithingScreenMixin(SmithingMenu menu, Inventory playerInventory, Component title, ResourceLocation menuResource) {
        super(menu, playerInventory, title, menuResource);
    }

    @Unique
    private final RecipeBookComponent _$recipeBookComponent = new RecipeBookComponent();

    @Inject(method = "subInit()V", at = @At("RETURN"))
    protected void emibook$subInit(CallbackInfo ci) {
        if (EmiConfig.recipeBookAction == RecipeBookAction.DEFAULT || !ModConfig.get().addButtons) return;

        this.leftPos = this._$recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
        this.addRenderableWidget(new ImageButton(this.leftPos + 150, this.height / 2 - 77, 20, 18, RecipeBookComponent.RECIPE_BUTTON_SPRITES, (p_313434_) -> {
            if (ModConfig.get().screenShift) {
                this.leftPos = this._$recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
                p_313434_.setPosition(this.leftPos + 135, this.height / 2 - 50);
            }
            this._$recipeBookComponent.toggleVisibility();
        }));
    }
}
