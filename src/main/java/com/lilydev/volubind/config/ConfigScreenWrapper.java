package com.lilydev.volubind.config;

import com.lilydev.volubind.VolubindClient;
import io.wispforest.owo.config.ui.ConfigScreen;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.Nullable;

// Adapted from https://github.com/wisp-forest/gadget/blob/b9df74aafef0a69a1368d26b76fb12e4d1b82713/src/main/java/io/wispforest/gadget/client/config/GadgetConfigScreen.java
public class ConfigScreenWrapper extends ConfigScreen {
    public ConfigScreenWrapper(@Nullable Screen parent) {
        super(DEFAULT_MODEL_ID, VolubindClient.CONFIG, parent);
    }
}
