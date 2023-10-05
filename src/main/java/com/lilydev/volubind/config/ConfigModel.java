package com.lilydev.volubind.config;

import io.wispforest.owo.config.annotation.*;
import com.lilydev.volubind.VolubindClient;

@SuppressWarnings("unused")
@Modmenu(modId = VolubindClient.MOD_ID)
@Config(name = "volubind", wrapperName = "VolubindConfig")
public class ConfigModel {

    @SectionHeader("general")
    public boolean sendChatMessages = true;
    public boolean logVolumeChange = false;

    @SectionHeader("volumeControl")

    @Hook
    @RangeConstraint(min = 0, max = 100)
    public int masterVolume = 100;
    @Hook
    @RangeConstraint(min = 0, max = 100)
    public int musicVolume = 100;
    @Hook
    @RangeConstraint(min = 0, max = 100)
    public int musicBlockVolume = 100;
    @Hook
    @RangeConstraint(min = 0, max = 100)
    public int weatherVolume = 100;
    @Hook
    @RangeConstraint(min = 0, max = 100)
    public int blockVolume = 100;
    @Hook
    @RangeConstraint(min = 0, max = 100)
    public int hostileVolume = 100;
    @Hook
    @RangeConstraint(min = 0, max = 100)
    public int friendlyVolume = 100;
    @Hook
    @RangeConstraint(min = 0, max = 100)
    public int playerVolume = 100;
    @Hook
    @RangeConstraint(min = 0, max = 100)
    public int ambientVolume = 100;
    @Hook
    @RangeConstraint(min = 0, max = 100)
    public int voiceVolume = 100;

    @Nest
    public VolumeToggled volumeToggled = new VolumeToggled();

    @ExcludeFromScreen
    public boolean masterToggled = false;
    @ExcludeFromScreen
    public boolean musicToggled = false;
    @ExcludeFromScreen
    public boolean musicBlockToggled = false;
    @ExcludeFromScreen
    public boolean weatherToggled = false;
    @ExcludeFromScreen
    public boolean blockToggled = false;
    @ExcludeFromScreen
    public boolean hostileToggled = false;
    @ExcludeFromScreen
    public boolean friendlyToggled = false;
    @ExcludeFromScreen
    public boolean playerToggled = false;
    @ExcludeFromScreen
    public boolean ambientToggled = false;
    @ExcludeFromScreen
    public boolean voiceToggled = false;

    public static class VolumeToggled {
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int masterVolumeToggled = 0;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int musicVolumeToggled = 0;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int musicBlockVolumeToggled = 0;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int weatherVolumeToggled = 0;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int blockVolumeToggled = 0;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int hostileVolumeToggled = 0;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int friendlyVolumeToggled = 0;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int playerVolumeToggled = 0;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int ambientVolumeToggled = 0;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int voiceVolumeToggled = 0;
    }
}
