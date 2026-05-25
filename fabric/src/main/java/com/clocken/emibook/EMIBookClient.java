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

            for (String disallowedScreen : ModConfig.get().DISALLOWED_SCREENS) {
                try {
                    Class<?> clazz = Class.forName(disallowedScreen);
                    if(clazz.isInstance(screen)) {
                        EmiConfig.enabled = false;
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
