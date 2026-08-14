package com.clocken.emibook;

import com.clocken.emibook.config.ModConfig;
import dev.emi.emi.config.EmiConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;

public class EMIBookClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        // imitateRecipeBook
        ScreenEvents.BEFORE_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if(!ModConfig.get().imitateRecipeBook) return;

            if (!(Constants.BOOK_SCREENS.test(screen) || (ModConfig.get().addButtons && Constants.BUTTON_SCREENS.test(screen)))) {
                EmiConfig.enabled = false;
            }
        });
    }
}
