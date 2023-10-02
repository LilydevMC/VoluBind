package com.lilydev.volubind.config;

import io.wispforest.owo.config.annotation.*;
import com.lilydev.volubind.VolubindClient;

@SuppressWarnings("unused")
@Modmenu(modId = VolubindClient.MOD_ID)
@Config(name = "volubind", wrapperName = "VolubindConfig")
public class ConfigModel {
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

    public static class VolumeToggled {
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int masterVolumeToggled = 0;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int musicVolumeToggled = 100;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int musicBlockVolumeToggled = 100;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int weatherVolumeToggled = 100;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int blockVolumeToggled = 100;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int hostileVolumeToggled = 100;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int friendlyVolumeToggled = 100;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int playerVolumeToggled = 100;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int ambientVolumeToggled = 100;
        @Hook
        @RangeConstraint(min = 0, max = 100)
        public int voiceVolumeToggled = 100;
    }
}
