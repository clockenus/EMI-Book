package com.clocken.emibook;

import com.clocken.emibook.config.ModConfig;
import com.clocken.emibook.platform.Services;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

import java.util.ArrayList;
import java.util.List;

public class CommonClass {

    public static void init() {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);

        if (Services.PLATFORM.isModLoaded(Constants.MOD_ID)) {
            Constants.LOG.info(Constants.MOD_NAME + " was loaded.");
        }
    }
}