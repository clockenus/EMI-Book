package com.clocken.emibook.config;

import com.clocken.emibook.Constants;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Constants.MOD_ID)
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    @Comment("Shifts screen in the right direction when EMI is opened (simulates a recipe book behaviour): true/false.")
    public boolean screenShift = false;

    @ConfigEntry.Gui.Tooltip
    @Comment("Closes EMI every time a screen is opened (simulates a recipe book behaviour): true/false.")
    public boolean disableEmiOnScreenOpening = false;

    public static ModConfig getConfig() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }

    public static void save() {
        AutoConfig.getConfigHolder(ModConfig.class).save();
    }
}
