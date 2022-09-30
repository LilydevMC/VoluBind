package com.lilydev.volubind;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VolubindClient implements ClientModInitializer {

	public static final String MOD_NAME = "VoluBind";
	public static final String MOD_ID = "volubind";
	public static final Logger LOGGER = LoggerFactory.getLogger("VoluBind");



	@Override
	public void onInitializeClient(ModContainer mod) {
		LOGGER.info("client initialized");


	}
}
