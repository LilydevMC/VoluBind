package com.lilydev.volubind.config;

import io.wispforest.owo.config.annotation.*;
import com.lilydev.volubind.VolubindClient;
import net.minecraft.client.MinecraftClient;

@Modmenu(modId = VolubindClient.MOD_ID)
@Config(name = "volubind", wrapperName = "VolubindConfig")
public class ConfigModel {
    @SectionHeader("volumeSection")

    @Hook
    @RangeConstraint(min = 0, max = 100)
    public int masterVolume = 100;


    @SectionHeader("volumeToggledSection")

    @Hook
    @RangeConstraint(min = 0, max = 100)
    public int masterVolumeToggled = 100;

    @ExcludeFromScreen
    public boolean masterToggled;
}
