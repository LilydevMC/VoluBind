package com.lilydev.volubind;

import com.lilydev.volubind.config.ConfigSubscribers;
import com.lilydev.volubind.config.VolubindConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VolubindClient implements ClientModInitializer {
    public static final String MOD_ID = "volubind";
    public static final String MOD_NAME = "VoluBind";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final VolubindConfig CONFIG = VolubindConfig.createAndLoad();

    @Override
    public void onInitializeClient() {
        ConfigSubscribers.register();
        VolumeControl.init();

        ClientTickEvents.END_CLIENT_TICK.register(VolumeControl::processKeyPress);
    }
}
