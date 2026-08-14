package com.clocken.emibook;

import dev.emi.emi.screen.RecipeScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.*;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

public class Constants {
	public static final String MOD_ID = "emibook";
	public static final String MOD_NAME = "EMI Book";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

	public static final Predicate<Screen> BOOK_SCREENS = (screen) ->
			screen instanceof RecipeUpdateListener ||
			screen instanceof RecipeScreen;

	public static final Predicate<Screen> BUTTON_SCREENS = (screen) ->
			screen instanceof AnvilScreen ||
			screen instanceof BrewingStandScreen ||
			screen instanceof CartographyTableScreen ||
			screen instanceof GrindstoneScreen ||
			screen instanceof SmithingScreen ||
			screen instanceof StonecutterScreen;
}