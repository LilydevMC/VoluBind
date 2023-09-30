package com.lilydev.volubind.config;

import com.lilydev.volubind.VolubindClient;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.Surface;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class SoundOptionsSelectorScreen extends BaseUIModelScreen<FlowLayout> {

    Screen parent;

    public SoundOptionsSelectorScreen(Screen parent) {
        super(FlowLayout.class, DataSource.asset(new Identifier(VolubindClient.MOD_ID, "sound_selector_model")));
        this.parent = parent;
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        Objects.requireNonNull(rootComponent.childById(ButtonComponent.class, "volubind-button")).onPress(button -> {
            assert this.client != null;
            this.client.setScreen(new ConfigScreenWrapper(this));
        });

        Objects.requireNonNull(rootComponent.childById(ButtonComponent.class, "sound-options-button")).onPress(button -> {
            assert this.client != null;
            this.client.setScreen(new SoundOptionsScreen(this, this.client.options));
        });

        Objects.requireNonNull(rootComponent.childById(ButtonComponent.class, "done-button")).onPress(button -> {
            assert this.client != null;
            this.client.setScreen(parent);
        });

        rootComponent.surface(Surface.OPTIONS_BACKGROUND);
    }
}
