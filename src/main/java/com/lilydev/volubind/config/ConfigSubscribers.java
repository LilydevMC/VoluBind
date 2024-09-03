package com.lilydev.volubind.config;

import com.lilydev.volubind.VolubindClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;

import java.util.EnumSet;

import static com.lilydev.volubind.util.Utils.ConfigVolumeType;
import static com.lilydev.volubind.util.Utils.getVolumeConsumerByCategory;
import static com.lilydev.volubind.util.Utils.getToggleSupplierByCategory;

public class ConfigSubscribers {

    public static void register() {
        MinecraftClient client = MinecraftClient.getInstance();

        com.lilydev.volubind.config.VolubindConfig config = VolubindClient.CONFIG;

        config.subscribeToEnableSubtitles(enabled -> client.options.getShowSubtitles().setValue(enabled));

        config.subscribeToEnableDirectionalAudio(enabled -> client.options.getDirectionalAudio().setValue(enabled));

        EnumSet.allOf(SoundCategory.class)
                .forEach(category -> {
                    registerVolume(client, category, ConfigVolumeType.UNTOGGLED);
                    registerVolume(client, category, ConfigVolumeType.TOGGLED);
                });
    }

    private static void registerVolume(MinecraftClient client, SoundCategory category, ConfigVolumeType volType) {
        com.lilydev.volubind.config.VolubindConfig config = VolubindClient.CONFIG;
        getVolumeConsumerByCategory(config, category, volType).accept(vol -> {
            if (config.logVolumeChange()) {
                logVolumeChange(category, volType, vol);
            }
            if (volType == ConfigVolumeType.UNTOGGLED) {
                if (!getToggleSupplierByCategory(config, category).get()) {
                    client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                }
            } else {
                if (getToggleSupplierByCategory(config, category).get()) {
                    client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                }
            }
        });
    }

    private static void logVolumeChange(SoundCategory category, ConfigVolumeType volType, int newVolume) {
        VolubindClient.LOGGER.info("Volume '{}'{} set to: {}%", category, volType == ConfigVolumeType.TOGGLED ? " (Toggled)" : "", newVolume);
    }

}
