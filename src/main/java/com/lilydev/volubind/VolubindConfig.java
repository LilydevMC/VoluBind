package com.lilydev.volubind;


import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class VolubindConfig {
	public static int testVolume = 0;

	public static void save() {

	}

	public static Screen createGui(Screen parent) {
		return YetAnotherConfigLib.createBuilder()
				.title(Text.of(VolubindClient.MOD_NAME))
				.category(ConfigCategory.createBuilder()
						.name(Text.of("General"))
						.tooltip(Text.of("General settings for " + VolubindClient.MOD_NAME))
						.option(Option.createBuilder(int.class)
								.name(Text.of("Master Volume"))
								.tooltip(Text.of("Controls master volume."))
								.binding(testVolume,
										() -> testVolume,
										newValue -> testVolume = newValue
								)
								.controller(option -> new IntegerSliderController(option, 0, 100, 1))
								.build())
						.build())
				.save(VolubindConfig::save)
				.build().generateScreen(parent);
	}

}
