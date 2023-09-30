package com.lilydev.volubind;

import com.lilydev.volubind.config.VolubindConfig;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.sound.SoundCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
    }
}
