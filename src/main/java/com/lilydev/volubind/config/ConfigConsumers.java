package com.lilydev.volubind.config;

import com.lilydev.volubind.VolubindClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;

public class ConfigConsumers {

    public static void register() {

        MinecraftClient client = MinecraftClient.getInstance();

        VolubindClient.CONFIG.subscribeToMasterVolume(vol -> {
            VolubindClient.LOGGER.info("volume: " + client.options.getSoundVolumeOption(SoundCategory.MASTER));

            if (!VolubindClient.CONFIG.masterToggled()) {
                client.options.getSoundVolumeOption(SoundCategory.MASTER).setValue(vol.doubleValue() / 100);
            }
        });


    }

}
