package com.lilydev.volubind;


import dev.isxander.yacl.api.Binding;
import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.gui.controllers.LabelController;
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;

public class VolubindConfigScreen {
	public static int testVolume = 0;

	public static void save() {

	}

	public static Screen createGui(Screen parent) {
		return YetAnotherConfigLib.createBuilder()
				.title(Text.of(VolubindClient.MOD_NAME))
				// General Category
				.category(ConfigCategory.createBuilder()
						.name(Text.of("General"))
						.tooltip(Text.of("General settings for " + VolubindClient.MOD_NAME + "."))
						// Game Volume Label
						.option(Option.createBuilder(Text.class)
								.name(Text.translatable("volubind.gui.label.volume"))
								.binding(Binding.immutable(Text.translatable("volubind.gui.label.volume")))
								.controller(LabelController::new)
								.build()
						)
						// Master Volume Int Slider
						.option(Option.createBuilder(int.class)
								.name(Text.translatable("volubind.gui.volume.name.master"))
								.tooltip(Text.translatable("volubind.gui.volume.label.music"))
								.binding(testVolume,
										() -> {
											testVolume = (int) (MinecraftClient.getInstance().options.getSoundVolume(SoundCategory.MASTER) * 100);
											return testVolume;
										},
										newValue -> {
											float newFloat = ((float) newValue) / 100;
											testVolume = newValue;
											MinecraftClient.getInstance().options.setSoundVolume(SoundCategory.MASTER, newFloat);
											VolubindClient.LOGGER.info(String.valueOf(newFloat));
										}
								)
								.controller(option -> new IntegerSliderController(option, 0, 100, 1))
								.build()
						)
						.option(Option.createBuilder(int.class)
								.name(Text.translatable("volubind.gui.volume.name.music"))
								.tooltip(Text.translatable("volubind.gui.volume.label.music"))
								.binding(testVolume,
										() -> {
											testVolume = (int) (MinecraftClient.getInstance().options.getSoundVolume(SoundCategory.MUSIC) * 100);
											return testVolume;
										},
										newValue -> {
											float newFloat = ((float) newValue) / 100;
											testVolume = newValue;
											MinecraftClient.getInstance().options.setSoundVolume(SoundCategory.MUSIC, newFloat);
											VolubindClient.LOGGER.info(String.valueOf(newFloat));
										}
								)
								.controller(option -> new IntegerSliderController(option, 0, 100, 1))
								.build()
						)
						.build())
				.save(VolubindConfigScreen::save)
				.build().generateScreen(parent);
	}

}
