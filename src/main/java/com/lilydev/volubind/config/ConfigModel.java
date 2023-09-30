package com.lilydev.volubind.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import com.lilydev.volubind.VolubindClient;
import io.wispforest.owo.config.annotation.RangeConstraint;
import io.wispforest.owo.config.annotation.SectionHeader;
import io.wispforest.owo.config.annotation.ExcludeFromScreen;

@Modmenu(modId = VolubindClient.MOD_ID)
@Config(name = "volubind", wrapperName = "VolubindConfig")
public class ConfigModel {
    @SectionHeader("volumeSection")
    @RangeConstraint(min = 0, max = 100)
    public int masterVolume = 100;
    @SectionHeader("volumeToggledSection")
    @RangeConstraint(min = 0, max = 100)
    public int masterVolumeToggled = 100;

    @ExcludeFromScreen
    public boolean masterToggled;
}
