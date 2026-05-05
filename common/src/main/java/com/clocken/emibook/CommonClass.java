package com.clocken.emibook;

import com.clocken.emibook.config.ModConfig;
import com.clocken.emibook.platform.Services;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;

public class CommonClass {

    public static void init() {
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);

        if (Services.PLATFORM.isModLoaded(Constants.MOD_ID)) {
            Constants.LOG.info(Constants.MOD_NAME + " was loaded.");
        }
    }
}