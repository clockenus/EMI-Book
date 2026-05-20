package com.clocken.emibook.mixin.reliable_advancements;

import com.clocken.emibook.config.ModConfig;
import com.evandev.advancement_enhancement.gui.button.AdvancementsScreenButton;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.emi.emi.config.EmiConfig;
import net.minecraft.client.gui.components.AbstractWidget;
import net.neoforged.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractWidget.class)
public class AbstractWidgetMixin {

    @WrapMethod(method = "getX()I")
    private int emibook$getX(Operation<Integer> original) {
        if (ModList.get().isLoaded("advancement_enhancement")) {
            if ((Object) this instanceof AdvancementsScreenButton) {
                if (EmiConfig.enabled && ModConfig.get().screenShift) {
                    return original.call() + 77;
                }
            }
        }
        return original.call();
    }
}
