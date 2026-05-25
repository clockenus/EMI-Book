package com.clocken.emibook;

import com.clocken.emibook.config.ModConfig;
import dev.emi.emi.config.EmiConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(Constants.MOD_ID)
public class EMIBook {

    public EMIBook() {
        CommonClass.init();
        ModLoadingContext.get().registerExtensionPoint(
                IConfigScreenFactory.class,
                () -> (mc, screen) -> AutoConfig.getConfigScreen(ModConfig.class, screen).get()
        );
    }

    @EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
    public static class ClientEvents {

        // imitateRecipeBook
        @SubscribeEvent
        public static void disableEmi(ScreenEvent.Opening event) {
            if(!ModConfig.get().imitateRecipeBook) return;

            Screen screen = event.getNewScreen();
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
        }
    }
}