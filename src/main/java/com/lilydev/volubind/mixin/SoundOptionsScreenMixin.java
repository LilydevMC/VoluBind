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

		this.addDrawableChild(
			ButtonWidget.builder(
				Text.translatable("volubind.gui.menu.game_volume.title"),
				(button) -> {
					assert this.client != null;
					this.client.setScreen(VolubindConfig.INSTANCE.createGui(this));
				})
				.position(this.width / 2 - 155, i)
				.width(310)
				.build()
		);
		k += 2;

		this.addDrawableChild(this.gameOptions.getSoundDevice().m_ffpjahoa(this.gameOptions, this.width / 2 - 155, i + 22 * (k >> 1), 310));
		k += 2;

		this.addDrawableChild(this.gameOptions.getShowSubtitles().m_ffpjahoa(this.gameOptions, this.width / 2 - 155, i + 22 * (k >> 1), 150));
		this.addDrawableChild(this.gameOptions.getDirectionalAudio().m_ffpjahoa(this.gameOptions, this.width / 2 + 5, i + 22 * (k >> 1), 150));
		k += 2;

		this.addDrawableChild(
			ButtonWidget.builder(
				ScreenTexts.DONE,
				(button) -> {
					assert this.client != null;
					this.client.setScreen(this.parent);
				})
				.position(this.width / 2 - 100, i + 22 * (k >> 1))
				.width(200)
				.build()
		);

		ci.cancel();
	}

}
