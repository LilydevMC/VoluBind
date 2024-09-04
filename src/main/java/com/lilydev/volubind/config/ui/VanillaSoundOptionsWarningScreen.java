package com.lilydev.volubind.config.ui;

import com.lilydev.volubind.VolubindClient;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class VanillaSoundOptionsWarningScreen extends BaseUIModelScreen<FlowLayout> {

    Screen parent;

    public VanillaSoundOptionsWarningScreen(Screen parent) {
        super(FlowLayout.class, DataSource.asset(Identifier.of(VolubindClient.MOD_ID, "vanilla_sound_options_warning_screen")));
        this.parent = parent;
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        assert this.client != null;

        Objects.requireNonNull(rootComponent.childById(
                ButtonComponent.class, "sound-selector-vanilla-sound-options-button"
        )).onPress(button -> this.client.setScreen(new SoundOptionsScreen(this, this.client.options)));

        Objects.requireNonNull(rootComponent.childById(
                ButtonComponent.class, "sound-selector-back-button"
        )).onPress(button -> this.client.setScreen(parent));
    }
}
