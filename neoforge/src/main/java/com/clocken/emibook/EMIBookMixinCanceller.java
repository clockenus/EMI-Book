package com.clocken.emibook;

import com.bawnorton.mixinsquared.api.MixinCanceller;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class EMIBookMixinCanceller implements MixinCanceller {

    @Override
    public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
        var ENTRIES = new HashMap<String, String>();

        // Patience mixin, that cancels EMI craft interaction
        ENTRIES.put(
                "dev.emi.emi.screen.EmiScreenManager",
                "etherested.patience.mixin.compat.EmiScreenManagerMixin"
        );

        // EMI mixin, that rewrites toggleOpen() of RecipeBookWidget
        // (rewrote that mixin for Reliable Advancements compatibility)
        ENTRIES.put(
                "net.minecraft.client.gui.screens.recipebook.RecipeBookComponent",
                "dev.emi.emi.mixin.RecipeBookWidgetMixin"
        );

        for (var target : ENTRIES.keySet()) {
            if (targetClassNames.contains(target) && ENTRIES.get(target).equals(mixinClassName)) {
                return true;
            }
        }
        return false;
    }
}
