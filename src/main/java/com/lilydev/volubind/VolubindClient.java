package com.lilydev.volubind;

import com.lilydev.volubind.config.ConfigSubscribers;
import com.lilydev.volubind.config.VolubindConfig;
import com.lilydev.volubind.config.ui.VolubindConfigScreen;
import io.wispforest.owo.config.ui.ConfigScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class VolubindClient implements ClientModInitializer {
    public static final String MOD_ID = "volubind";
    public static final String MOD_NAME = "VoluBind";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final VolubindConfig CONFIG = VolubindConfig.createAndLoad();

    @Override
    public void onInitializeClient() {
        ConfigSubscribers.register();
        VolumeControl.init();

        ClientLifecycleEvents.CLIENT_STARTED.register(VolumeControl::initVolumeOptions);
        ClientTickEvents.END_CLIENT_TICK.register(VolumeControl::processKeyPress);

        ConfigScreen.registerProvider(MOD_ID, VolubindConfigScreen::new);
    }
}
