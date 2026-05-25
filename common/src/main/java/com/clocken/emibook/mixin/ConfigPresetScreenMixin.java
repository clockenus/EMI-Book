package com.clocken.emibook.mixin;

import com.clocken.emibook.config.ModPresets;
import dev.emi.emi.screen.ConfigPresetScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

@Mixin(ConfigPresetScreen.class)
public class ConfigPresetScreenMixin {

    @Redirect(method = "init()V", at = @At(value = "INVOKE", target = "Ljava/lang/Class;getFields()[Ljava/lang/reflect/Field;"))
    private Field[] emibook$initRedirect(Class instance) {
        return Stream.concat(
                Arrays.stream(ModPresets.class.getFields()),
                Arrays.stream(instance.getFields())
        ).toArray(Field[]::new);
    }
}
