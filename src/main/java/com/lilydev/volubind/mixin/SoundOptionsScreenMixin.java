package com.lilydev.volubind.mixin;

import com.lilydev.volubind.config.VolubindConfig;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.ScreenTexts;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundOptionsScreen.class)
public class SoundOptionsScreenMixin extends GameOptionsScreen {
	public SoundOptionsScreenMixin(Screen screen, GameOptions gameOptions, Text text) {
		super(screen, gameOptions, text);
	}

	@Inject(method = "init", at = @At("HEAD"), cancellable = true)
	protected void init(CallbackInfo ci) {
		int i = this.height / 6 - 12;
		int k = 0;

		this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, i, 310, 20,
				Text.translatable("volubind.gui.menu.game_volume.title"),
				(button) -> {
					assert this.client != null;
					this.client.setScreen(VolubindConfig.INSTANCE.createGui(this));
				}));
		k += 2;
		this.addDrawableChild(this.gameOptions.getSoundDevice().createButton(this.gameOptions, this.width / 2 - 155, i + 22 * (k >> 1), 310));
		k += 2;
		this.addDrawableChild(this.gameOptions.getShowSubtitles().createButton(this.gameOptions, this.width / 2 - 155, i + 22 * (k >> 1), 150));
		this.addDrawableChild(this.gameOptions.getDirectionalAudio().createButton(this.gameOptions, this.width / 2 + 5, i + 22 * (k >> 1), 150));
		k += 2;
		this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, i + 22 * (k >> 1), 200, 20, ScreenTexts.DONE, (button) -> {
			assert this.client != null;
			this.client.setScreen(this.parent);
		}));
		ci.cancel();
	}

}
