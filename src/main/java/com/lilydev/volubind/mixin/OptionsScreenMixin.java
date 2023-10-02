package com.lilydev.volubind.mixin;

import com.lilydev.volubind.config.SoundOptionsSelectorScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin extends Screen {
    protected OptionsScreenMixin(Text title) {
        super(title);
    }


    // Modified from https://github.com/CaffeineMC/sodium-fabric/blob/dev/src/main/java/me/jellysquid/mods/sodium/mixin/features/gui/hooks/settings/OptionsScreenMixin.java
    @Inject(method = "method_19829", at = @At("HEAD"), cancellable = true)
    public void replaceSoundOptionsScreen(CallbackInfoReturnable<Screen> cir) {
        cir.setReturnValue(new SoundOptionsSelectorScreen(this));
    }

}
