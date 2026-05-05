package com.clocken.emibook;


import com.clocken.emibook.config.ModConfig;
import dev.emi.emi.config.EmiConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ScreenEvent;

@Mod(Constants.MOD_ID)
public class EMIBook {

    public EMIBook(IEventBus eventBus) {
        CommonClass.init();
    }

    @EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
    public static class ClientEvents {

        @SubscribeEvent
        public static void disableEmi(ScreenEvent.Opening event) {
            if(ModConfig.disableEmiOnScreenOpening) {
                EmiConfig.enabled = false;
            }
        }
    }
}