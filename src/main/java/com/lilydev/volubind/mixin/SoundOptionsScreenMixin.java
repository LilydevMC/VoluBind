package com.lilydev.volubind.mixin;

import com.lilydev.volubind.config.VolubindConfig;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import net.minecraft.text.ScreenTexts;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundOptionsScreen.class)
public abstract class SoundOptionsScreenMixin extends GameOptionsScreen {
	@Shadow private ButtonListWidget f_jdalyxvz;

	@Shadow
	private static Option<?>[] m_fccohjcg(GameOptions gameOptions) {
		return new Option[0];
	}

	public SoundOptionsScreenMixin(Screen screen, GameOptions gameOptions) {
		super(screen, gameOptions, Text.translatable("options.sounds.title"));
	}

	@Inject(method = "init", at = @At("HEAD"), cancellable = true)
	protected void init(CallbackInfo ci) {
		this.f_jdalyxvz = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
		this.f_jdalyxvz.addSingleOptionEntry(this.gameOptions.getSoundDevice());
		this.f_jdalyxvz.addEntries(m_fccohjcg(this.gameOptions));

		this.addSelectableChild(this.f_jdalyxvz);

		// Done Button
		this.addDrawableChild(
			ButtonWidget.builder(
				ScreenTexts.DONE,
				button -> {
					assert this.client != null;
					this.client.setScreen(this.parent);
				})
			.positionAndSize(
					this.width / 2 - 101,
					this.height - 27,
					100,
					20
			).build());

		// Volume settings button
		this.addDrawableChild(
			ButtonWidget.builder(
				Text.translatable("volubind.gui.menu.game_volume.title"),
				(button) -> {
					assert this.client != null;
					this.client.setScreen(VolubindConfig.INSTANCE.createGui(this));
				})
			.positionAndSize(
				this.width / 2 + 1,
				this.height - 27,
				100,
				20
			).build());

		ci.cancel();
	}

}
