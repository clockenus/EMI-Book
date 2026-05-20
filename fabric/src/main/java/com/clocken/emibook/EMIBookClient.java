package com.clocken.emibook;

import com.clocken.emibook.config.ModConfig;
import dev.emi.emi.config.EmiConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;

public class EMIBookClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        // todo: screen != newScreen from neoforge
        ScreenEvents.BEFORE_INIT.register((client, screen, scaledWidth, scaledHeight) -> {

            if(ModConfig.get().disableEmiOnScreenOpening) {
                EmiConfig.enabled = false;
            }
        });
    }
}
