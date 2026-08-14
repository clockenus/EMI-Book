package com.clocken.emibook.config;

import com.clocken.emibook.Constants;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.minecraft.client.gui.screens.Screen;

import java.util.ArrayList;
import java.util.List;

@Config(name = Constants.MOD_ID)
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    @Comment("Shifts screen in the right direction when EMI is active (simulates a recipe book behaviour): true/false.")
    public boolean screenShift = false;

    @ConfigEntry.Gui.Tooltip
    @Comment("Switches effect location from RIGHT to RIGHT_COMPRESSED when EMI is active: true/false.")
    public boolean switchEffectLocation = false;

    @ConfigEntry.Gui.Tooltip
    @Comment("Makes EMI keybinds work even if EMI is not active: true/false.")
    public boolean independentBinds = false;

    @ConfigEntry.Gui.Tooltip
    @Comment("Adds Recipe Book buttons to some screens, that miss it, only when RecipeBookAction is not DEFAULT: true/false.")
    public boolean addButtons = false;

    @ConfigEntry.Gui.Tooltip
    @Comment("Shows EMI only in places of Recipe Book: true/false.")
    public boolean imitateRecipeBook = false;

//    @ConfigEntry.Gui.Tooltip
//    @Comment("Screen classes, that disables EMI when \"Imitate Recipe Book\" is active.")
//    public List<String> DISALLOWED_SCREENS;

    public static ModConfig get() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }

    public static void save() {
        AutoConfig.getConfigHolder(ModConfig.class).save();
    }
}
