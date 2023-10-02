package com.lilydev.volubind;

import com.lilydev.volubind.config.VolubindConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;


public class VolubindClient implements ClientModInitializer {
    public static final String MOD_ID = "volubind";
    public static final String MOD_NAME = "VoluBind";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final VolubindConfig CONFIG = VolubindConfig.createAndLoad();

    @Override
    public void onInitializeClient() {
        LOGGER.info("test! " + MOD_NAME);

        //SoundOptionsScreen

        //LOGGER.info(SoundCategory.);

        //CONFIG.subscribeToMasterVolTest();

        MinecraftClient client = MinecraftClient.getInstance();

        CONFIG.subscribeToMasterVolume(vol -> {
            LOGGER.info("volume: " + client.options.getSoundVolumeOption(SoundCategory.MASTER));

            if (!CONFIG.masterToggled()) {
                client.options.getSoundVolumeOption(SoundCategory.MASTER).setValue(vol.doubleValue() / 100);
            }
        });

    }
}
