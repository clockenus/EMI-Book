package com.clocken.emibook;

import com.clocken.emibook.config.ModConfig;
import dev.emi.emi.config.EmiConfig;
import dev.emi.emi.screen.RecipeScreen;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import java.util.function.Predicate;

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

        @SubscribeEvent
        public static void disableEmi(ScreenEvent.Opening event) {
            Screen screen = event.getCurrentScreen();
            Screen newScreen = event.getNewScreen();

            Predicate<Screen> WHITE_LIST = i -> (
                    i == screen ||
                    i instanceof RecipeScreen ||
                    (i instanceof InventoryScreen && screen instanceof RecipeScreen)
            );

            if(ModConfig.get().disableEmiOnScreenOpening && !WHITE_LIST.test(newScreen)) {
                EmiConfig.enabled = false;
            }
        }
    }
}