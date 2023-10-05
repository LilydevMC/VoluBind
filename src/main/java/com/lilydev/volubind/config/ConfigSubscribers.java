package com.lilydev.volubind.config;

import com.lilydev.volubind.VolubindClient;
import com.lilydev.volubind.config.VolubindConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;

import java.util.EnumSet;

import static com.lilydev.volubind.util.Utils.ConfigVolumeType;
import static com.lilydev.volubind.util.Utils.getSubscriberByCategory;
import static com.lilydev.volubind.util.Utils.getToggleGetterByCategory;

public class ConfigSubscribers {

    public static void register() {
        MinecraftClient client = MinecraftClient.getInstance();

        EnumSet.allOf(SoundCategory.class)
                .forEach(category -> {
                    registerVolume(client, category, ConfigVolumeType.UNTOGGLED);
                    registerVolume(client, category, ConfigVolumeType.TOGGLED);
                });
    }

    private static void registerVolume(MinecraftClient client, SoundCategory category, ConfigVolumeType volType) {
        VolubindConfig config = VolubindClient.CONFIG;
        getSubscriberByCategory(config, category, volType).accept(vol -> {
            if (config.logVolumeChange()) {
                logVolumeChange(category, volType, vol);
            }
            if (volType == ConfigVolumeType.UNTOGGLED) {
                if (!getToggleGetterByCategory(config, category).get()) {
                    client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                }
            } else {
                if (getToggleGetterByCategory(config, category).get()) {
                    client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                }
            }
        });
    }

    private static void logVolumeChange(SoundCategory category, ConfigVolumeType volType, int newVolume) {
        VolubindClient.LOGGER.info(
                "Volume '" + category + "'" +
                (volType == ConfigVolumeType.TOGGLED ? " (Toggled)" : "") +
                " set to: " + newVolume
        );
    }

}
