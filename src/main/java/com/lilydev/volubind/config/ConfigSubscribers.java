package com.lilydev.volubind.config;

import com.lilydev.volubind.VolubindClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;

import java.util.EnumSet;

public class ConfigSubscribers {

    public static void register() {

        MinecraftClient client = MinecraftClient.getInstance();

        EnumSet.allOf(SoundCategory.class)
                .forEach(category -> {
                    registerVolume(client, category, ConfigVolumeType.UNTOGGLED);
                    registerVolume(client, category, ConfigVolumeType.TOGGLED);
                });
    }

    private enum ConfigVolumeType {
        UNTOGGLED,
        TOGGLED
    }

    // I know there's a better way to do this, but I'm too tired :(
    private static void registerVolume(MinecraftClient client, SoundCategory category, ConfigVolumeType volType) {
        if (volType == ConfigVolumeType.UNTOGGLED) {
            switch (category) {
                case MASTER -> VolubindClient.CONFIG.subscribeToMasterVolume(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (!VolubindClient.CONFIG.masterToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case MUSIC -> VolubindClient.CONFIG.subscribeToMusicVolume(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (!VolubindClient.CONFIG.musicToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case RECORDS -> VolubindClient.CONFIG.subscribeToMusicBlockVolume(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (!VolubindClient.CONFIG.musicBlockToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case WEATHER -> VolubindClient.CONFIG.subscribeToWeatherVolume(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (!VolubindClient.CONFIG.weatherToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case BLOCKS -> VolubindClient.CONFIG.subscribeToBlockVolume(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (!VolubindClient.CONFIG.blockToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case HOSTILE -> VolubindClient.CONFIG.subscribeToHostileVolume(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (!VolubindClient.CONFIG.hostileToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case NEUTRAL -> VolubindClient.CONFIG.subscribeToFriendlyVolume(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (!VolubindClient.CONFIG.friendlyToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case PLAYERS -> VolubindClient.CONFIG.subscribeToPlayerVolume(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (!VolubindClient.CONFIG.playerToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case AMBIENT -> VolubindClient.CONFIG.subscribeToAmbientVolume(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (!VolubindClient.CONFIG.ambientToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case VOICE -> VolubindClient.CONFIG.subscribeToVoiceVolume(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (!VolubindClient.CONFIG.voiceToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
            }
        } else {
            switch (category) {
                case MASTER -> VolubindClient.CONFIG.volumeToggled.subscribeToMasterVolumeToggled(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (VolubindClient.CONFIG.masterToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case MUSIC -> VolubindClient.CONFIG.volumeToggled.subscribeToMusicVolumeToggled(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (VolubindClient.CONFIG.musicToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case RECORDS -> VolubindClient.CONFIG.volumeToggled.subscribeToMusicBlockVolumeToggled(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (VolubindClient.CONFIG.musicBlockToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case WEATHER -> VolubindClient.CONFIG.volumeToggled.subscribeToWeatherVolumeToggled(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (VolubindClient.CONFIG.weatherToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case BLOCKS -> VolubindClient.CONFIG.volumeToggled.subscribeToBlockVolumeToggled(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (VolubindClient.CONFIG.blockToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case HOSTILE -> VolubindClient.CONFIG.volumeToggled.subscribeToHostileVolumeToggled(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (VolubindClient.CONFIG.hostileToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case NEUTRAL -> VolubindClient.CONFIG.volumeToggled.subscribeToFriendlyVolumeToggled(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (VolubindClient.CONFIG.friendlyToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case PLAYERS -> VolubindClient.CONFIG.volumeToggled.subscribeToPlayerVolumeToggled(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (VolubindClient.CONFIG.playerToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case AMBIENT -> VolubindClient.CONFIG.volumeToggled.subscribeToAmbientVolumeToggled(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (VolubindClient.CONFIG.ambientToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
                case VOICE -> VolubindClient.CONFIG.volumeToggled.subscribeToVoiceVolumeToggled(vol -> {
                    logVolumeChange(category, volType, vol);

                    if (VolubindClient.CONFIG.voiceToggled()) {
                        client.options.getSoundVolumeOption(category).setValue(vol.doubleValue() / 100);
                    }
                });
            }
        }
    }

    private static void logVolumeChange(SoundCategory category, ConfigVolumeType volType, int newVolume) {
        VolubindClient.LOGGER.info(
                "Volume '" + category + "'" +
                (volType == ConfigVolumeType.TOGGLED ? " (Toggled)" : "") +
                " set to: " + newVolume
        );
    }

}
