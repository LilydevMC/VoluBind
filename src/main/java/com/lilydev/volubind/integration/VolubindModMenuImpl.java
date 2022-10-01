package com.lilydev.volubind.integration;

import com.lilydev.volubind.VolubindConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class VolubindModMenuImpl implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return VolubindConfigScreen::createGui;
	}
}
