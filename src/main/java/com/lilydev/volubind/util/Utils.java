package com.lilydev.volubind.util;

import com.lilydev.volubind.config.VolubindConfig;
import net.minecraft.sound.SoundCategory;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Utils {
    public enum ConfigVolumeType {
        UNTOGGLED,
        TOGGLED
    }

    public static Consumer<Consumer<Integer>> getSubscriberByCategory(
            VolubindConfig config,
            SoundCategory category,
            ConfigVolumeType volType
    ) {
        if (volType == ConfigVolumeType.UNTOGGLED) {
            return switch (category) {
                case MASTER -> config::subscribeToMasterVolume;
                case MUSIC -> config::subscribeToMusicVolume;
                case RECORDS -> config::subscribeToMusicBlockVolume;
                case WEATHER -> config::subscribeToWeatherVolume;
                case BLOCKS -> config::subscribeToBlockVolume;
                case HOSTILE -> config::subscribeToHostileVolume;
                case NEUTRAL -> config::subscribeToFriendlyVolume;
                case PLAYERS -> config::subscribeToPlayerVolume;
                case AMBIENT -> config::subscribeToAmbientVolume;
                case VOICE -> config::subscribeToVoiceVolume;
            };
        } else {
            return switch (category) {
                case MASTER -> config.volumeToggled::subscribeToMasterVolumeToggled;
                case MUSIC -> config.volumeToggled::subscribeToMusicVolumeToggled;
                case RECORDS -> config.volumeToggled::subscribeToMusicBlockVolumeToggled;
                case WEATHER -> config.volumeToggled::subscribeToWeatherVolumeToggled;
                case BLOCKS -> config.volumeToggled::subscribeToBlockVolumeToggled;
                case HOSTILE -> config.volumeToggled::subscribeToHostileVolumeToggled;
                case NEUTRAL -> config.volumeToggled::subscribeToFriendlyVolumeToggled;
                case PLAYERS -> config.volumeToggled::subscribeToPlayerVolumeToggled;
                case AMBIENT -> config.volumeToggled::subscribeToAmbientVolumeToggled;
                case VOICE -> config.volumeToggled::subscribeToVoiceVolumeToggled;
            };
        }
    }

    public static Supplier<Boolean> getToggleGetterByCategory(VolubindConfig config, SoundCategory category) {
        return switch (category) {
            case MASTER -> config::masterToggled;
            case MUSIC -> config::musicToggled;
            case RECORDS -> config::musicBlockToggled;
            case WEATHER -> config::weatherToggled;
            case BLOCKS -> config::blockToggled;
            case HOSTILE -> config::hostileToggled;
            case NEUTRAL -> config::friendlyToggled;
            case PLAYERS -> config::playerToggled;
            case AMBIENT -> config::ambientToggled;
            case VOICE -> config::voiceToggled;
        };
    }
}
