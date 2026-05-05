package com.clocken.emibook.config;

import com.clocken.emibook.Constants;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = Constants.MOD_ID)
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public static boolean screenShift = false;

    @ConfigEntry.Gui.Tooltip
    public static boolean disableEmiOnScreenOpening = false;
}
