package com.clocken.emibook.config;

import com.mojang.blaze3d.platform.InputConstants;
import dev.emi.emi.config.*;
import dev.emi.emi.input.EmiBind;

import java.util.List;

public class ModPresets {

    @EmiConfig.ConfigGroup("presets.emibook")

    // Recipe Book
    @EmiConfig.Comment("Recipe book-like preset with index and craftables.")
    @EmiConfig.ConfigValue("presets.recipe-book")
    public static Runnable recipeBook = () -> {
        ModConfig.getConfig().screenShift = true;
        ModConfig.getConfig().disableEmiOnScreenOpening = true;
        ModConfig.getConfig().switchEffectLocation = true;
        ModConfig.save();

        EmiConfig.searchSidebar = SidebarSide.LEFT;
        EmiConfig.recipeBookAction = RecipeBookAction.TOGGLE_VISIBILITY;
        EmiConfig.effectLocation = EffectLocation.RIGHT;
        EmiConfig.searchSidebarFocus = SidebarType.NONE;
        EmiConfig.recipeTreeButtonVisibility = ButtonVisibility.HIDDEN;

        // left sidebar
        setPages(EmiConfig.leftSidebarPages, List.of(
                new SidebarPages.SidebarPage(SidebarType.CRAFTABLES),
                new SidebarPages.SidebarPage(SidebarType.INDEX)
        ));
        EmiConfig.leftSidebarTheme = SidebarTheme.VANILLA;
        EmiConfig.leftSidebarSize.values.set(0, 7);
        EmiConfig.leftSidebarSize.values.set(1, 7);
        EmiConfig.leftSidebarAlign = new ScreenAlign(
                ScreenAlign.Horizontal.RIGHT,
                ScreenAlign.Vertical.CENTER
        );
        EmiConfig.leftSidebarHeader = HeaderType.VISIBLE;

        // right sidebar
        setPages(EmiConfig.rightSidebarPages, List.of(
        ));
        EmiConfig.rightSidebarTheme = SidebarTheme.VANILLA;

        // binds
        EmiConfig.toggleVisibility = new EmiBind("key.emi.toggle_visibility",InputConstants.UNKNOWN.getValue());
    };

    // Two Pages
    @EmiConfig.Comment("Two pages on the sides in the recipe book style. With favorites and lookup history.")
    @EmiConfig.ConfigValue("presets.two-pages")
    public static Runnable twoPages = () -> {
        ModConfig.getConfig().screenShift = false;
        ModConfig.getConfig().disableEmiOnScreenOpening = true;
        ModConfig.save();

        EmiConfig.searchSidebar = SidebarSide.RIGHT;
        EmiConfig.recipeBookAction = RecipeBookAction.TOGGLE_VISIBILITY;
        EmiConfig.searchSidebarFocus = SidebarType.NONE;
        EmiConfig.recipeTreeButtonVisibility = ButtonVisibility.HIDDEN;

        // left sidebar
        setPages(EmiConfig.leftSidebarPages, List.of(
                new SidebarPages.SidebarPage(SidebarType.CRAFTABLES),
                new SidebarPages.SidebarPage(SidebarType.FAVORITES)
        ));
        EmiConfig.leftSidebarTheme = SidebarTheme.VANILLA;
        EmiConfig.leftSidebarSize.values.set(0, 8);
        EmiConfig.leftSidebarSize.values.set(1, 7);
        EmiConfig.leftSidebarAlign = new ScreenAlign(
                ScreenAlign.Horizontal.RIGHT,
                ScreenAlign.Vertical.CENTER
        );

        // right sidebar
        setPages(EmiConfig.rightSidebarPages, List.of(
                new SidebarPages.SidebarPage(SidebarType.INDEX),
                new SidebarPages.SidebarPage(SidebarType.LOOKUP_HISTORY)
        ));
        EmiConfig.rightSidebarTheme = SidebarTheme.VANILLA;
        EmiConfig.rightSidebarSize.values.set(0, 8);
        EmiConfig.rightSidebarSize.values.set(1, 7);
        EmiConfig.rightSidebarAlign = new ScreenAlign(
                ScreenAlign.Horizontal.LEFT,
                ScreenAlign.Vertical.CENTER
        );

        // binds
        EmiConfig.toggleVisibility = new EmiBind("key.emi.toggle_visibility",InputConstants.UNKNOWN.getValue());
    };

    private static void setPages(SidebarPages pages, List<SidebarPages.SidebarPage> list) {
        pages.pages.clear();
        pages.pages.addAll(list);
    }
}
