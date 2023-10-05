package com.lilydev.volubind.util;

import com.lilydev.volubind.VolumeControl;
import com.lilydev.volubind.config.VolubindConfig;
import net.minecraft.client.option.KeyBinding;
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
                case MASTER   -> config::subscribeToMasterVolume;
                case MUSIC    -> config::subscribeToMusicVolume;
                case RECORDS  -> config::subscribeToMusicBlockVolume;
                case WEATHER  -> config::subscribeToWeatherVolume;
                case BLOCKS   -> config::subscribeToBlockVolume;
                case HOSTILE  -> config::subscribeToHostileVolume;
                case NEUTRAL  -> config::subscribeToFriendlyVolume;
                case PLAYERS  -> config::subscribeToPlayerVolume;
                case AMBIENT  -> config::subscribeToAmbientVolume;
                case VOICE    -> config::subscribeToVoiceVolume;
            };
        } else {
            return switch (category) {
                case MASTER   -> config.volumeToggled::subscribeToMasterVolumeToggled;
                case MUSIC    -> config.volumeToggled::subscribeToMusicVolumeToggled;
                case RECORDS  -> config.volumeToggled::subscribeToMusicBlockVolumeToggled;
                case WEATHER  -> config.volumeToggled::subscribeToWeatherVolumeToggled;
                case BLOCKS   -> config.volumeToggled::subscribeToBlockVolumeToggled;
                case HOSTILE  -> config.volumeToggled::subscribeToHostileVolumeToggled;
                case NEUTRAL  -> config.volumeToggled::subscribeToFriendlyVolumeToggled;
                case PLAYERS  -> config.volumeToggled::subscribeToPlayerVolumeToggled;
                case AMBIENT  -> config.volumeToggled::subscribeToAmbientVolumeToggled;
                case VOICE    -> config.volumeToggled::subscribeToVoiceVolumeToggled;
            };
        }
    }

    public static Supplier<Integer> getVolumeSupplierByCategory(
            VolubindConfig config,
            SoundCategory category,
            ConfigVolumeType volType
    ) {
        if (volType == ConfigVolumeType.UNTOGGLED) {
            return switch (category) {
                case MASTER   -> config::masterVolume;
                case MUSIC    -> config::musicVolume;
                case RECORDS  -> config::musicBlockVolume;
                case WEATHER  -> config::weatherVolume;
                case BLOCKS   -> config::blockVolume;
                case HOSTILE  -> config::hostileVolume;
                case NEUTRAL  -> config::friendlyVolume;
                case PLAYERS  -> config::playerVolume;
                case AMBIENT  -> config::ambientVolume;
                case VOICE    -> config::voiceVolume;
            };
        } else {
            return switch (category) {
                case MASTER   -> config.volumeToggled::masterVolumeToggled;
                case MUSIC    -> config.volumeToggled::musicVolumeToggled;
                case RECORDS  -> config.volumeToggled::musicBlockVolumeToggled;
                case WEATHER  -> config.volumeToggled::weatherVolumeToggled;
                case BLOCKS   -> config.volumeToggled::blockVolumeToggled;
                case HOSTILE  -> config.volumeToggled::hostileVolumeToggled;
                case NEUTRAL  -> config.volumeToggled::friendlyVolumeToggled;
                case PLAYERS  -> config.volumeToggled::playerVolumeToggled;
                case AMBIENT  -> config.volumeToggled::ambientVolumeToggled;
                case VOICE    -> config.volumeToggled::voiceVolumeToggled;
            };
        }
    }

    public static Supplier<Boolean> getToggleSupplierByCategory(VolubindConfig config, SoundCategory category) {
        return switch (category) {
            case MASTER   -> config::masterToggled;
            case MUSIC    -> config::musicToggled;
            case RECORDS  -> config::musicBlockToggled;
            case WEATHER  -> config::weatherToggled;
            case BLOCKS   -> config::blockToggled;
            case HOSTILE  -> config::hostileToggled;
            case NEUTRAL  -> config::friendlyToggled;
            case PLAYERS  -> config::playerToggled;
            case AMBIENT  -> config::ambientToggled;
            case VOICE    -> config::voiceToggled;
        };
    }

    public static Consumer<Boolean> getToggleConsumerByCategory(VolubindConfig config, SoundCategory category) {
        return switch (category) {
            case MASTER   -> config::masterToggled;
            case MUSIC    -> config::musicToggled;
            case RECORDS  -> config::musicBlockToggled;
            case WEATHER  -> config::weatherToggled;
            case BLOCKS   -> config::blockToggled;
            case HOSTILE  -> config::hostileToggled;
            case NEUTRAL  -> config::friendlyToggled;
            case PLAYERS  -> config::playerToggled;
            case AMBIENT  -> config::ambientToggled;
            case VOICE    -> config::voiceToggled;
        };
    }


    public static KeyBinding getKeyBindingByCategory(SoundCategory category) {
        return switch (category) {
            case MASTER   -> VolumeControl.toggleMaster;
            case MUSIC    -> VolumeControl.toggleMusic;
            case RECORDS  -> VolumeControl.toggleMusicBlock;
            case WEATHER  -> VolumeControl.toggleWeather;
            case BLOCKS   -> VolumeControl.toggleBlock;
            case HOSTILE  -> VolumeControl.toggleHostile;
            case NEUTRAL  -> VolumeControl.toggleFriendly;
            case PLAYERS  -> VolumeControl.togglePlayer;
            case AMBIENT  -> VolumeControl.toggleAmbient;
            case VOICE    -> VolumeControl.toggleVoice;
        };
    }
}
