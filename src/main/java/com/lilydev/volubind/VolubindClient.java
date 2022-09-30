package com.lilydev.volubind;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class VolubindClient implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {
		Volubind.LOGGER.info("client initialized");
	}
}
