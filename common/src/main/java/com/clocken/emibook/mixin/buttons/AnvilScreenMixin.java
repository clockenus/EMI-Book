package com.clocken.emibook.mixin.buttons;

import com.clocken.emibook.config.ModConfig;
import dev.emi.emi.config.EmiConfig;
import dev.emi.emi.config.RecipeBookAction;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(remap = false, value = AnvilScreen.class)
public abstract class AnvilScreenMixin extends ItemCombinerScreen<AnvilMenu> {
    public AnvilScreenMixin(AnvilMenu menu, Inventory playerInventory, Component title, ResourceLocation menuResource) {
        super(menu, playerInventory, title, menuResource);
    }

    @Unique
    private final RecipeBookComponent _$recipeBookComponent = new RecipeBookComponent();

    @Inject(remap = false, method = "subInit()V", at = @At("RETURN"))
    protected void emibook$subInit(CallbackInfo ci) {
        if (EmiConfig.recipeBookAction == RecipeBookAction.DEFAULT || !ModConfig.get().addButtons) return;

        this.leftPos = this._$recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
        this.addRenderableWidget(new ImageButton(this.leftPos + 24, this.height / 2 - 64, 20, 18, RecipeBookComponent.RECIPE_BUTTON_SPRITES, (p_313434_) -> {
            if (ModConfig.get().screenShift) {
                this.leftPos = this._$recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
                p_313434_.setPosition(this.leftPos + 135, this.height / 2 - 50);
            }
            this._$recipeBookComponent.toggleVisibility();
        }));
    }

    @ModifyVariable(remap = false, method = "subInit()V", index = 1, at = @At("STORE"))
    public int emibook$subInitVariable(int i) {
        if (EmiConfig.recipeBookAction != RecipeBookAction.DEFAULT && EmiConfig.enabled && ModConfig.get().screenShift && ModConfig.get().addButtons) {
            return i + 77;
        } else {
            return i;
        }
    }
}
