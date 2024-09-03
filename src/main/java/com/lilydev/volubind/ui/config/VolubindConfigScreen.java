package com.lilydev.volubind.ui.config;

import com.lilydev.volubind.VolubindClient;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.ui.ConfigScreen;
import io.wispforest.owo.config.ui.OptionComponentFactory;
import io.wispforest.owo.config.ui.component.OptionValueProvider;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.LabelComponent;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.OverlayContainer;
import io.wispforest.owo.ui.container.ScrollContainer;
import io.wispforest.owo.ui.core.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;


// Adapted from https://github.com/wisp-forest/owo-whats-this/blob/773ca40e414b84bd357618aa6039f8cf9649eef3/src/main/java/io/wispforest/owowhatsthis/client/OwoWhatsThisConfigScreen.java
@Environment(EnvType.CLIENT)
public class VolubindConfigScreen extends ConfigScreen {
    public VolubindConfigScreen(@Nullable Screen parent) {
        super(DEFAULT_MODEL_ID, VolubindClient.CONFIG, parent);

        this.extraFactories.put(option -> option.backingField().field().getName().equals("soundDevice"), SOUND_DEVICES_CONFIG_FACTORY);
    }

    private static final OptionComponentFactory<String> SOUND_DEVICES_CONFIG_FACTORY = (model, option) -> {
        var container = new SoundDevicesContainer(option);
        return new OptionComponentFactory.Result<>(container, container);
    };

    private void onSoundDeviceSelect(ButtonComponent button) {
        button.active(false);

        FlowLayout rootComponent = (FlowLayout) button.root();
        String device = button.getMessage().getString();

        assert this.client != null;
        this.client.options.getSoundDevice().setValue(device);

        rootComponent.childById(OverlayContainer.class, "sound-devices-modal-overlay").remove();
        rootComponent.childById(ButtonComponent.class, "sound-devices-reset-button").active(true);
    }

    private void onSoundDeviceReset(ButtonComponent button) {
        assert this.client != null;
        this.client.options.getSoundDevice().setValue("");

        FlowLayout rootComponent = (FlowLayout) button.root();

        var overlay = rootComponent.childById(OverlayContainer.class, "sound-devices-modal-overlay");
        if (overlay != null) {
            overlay.remove();
        }

        button.active(false);
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        super.build(rootComponent);

        FlowLayout dropdownFlow = Containers.verticalFlow(Sizing.fill(), Sizing.content());
        dropdownFlow.id("sound-devices-dropdown");
        dropdownFlow.surface(Surface.BLANK);
        dropdownFlow.gap(1);
        dropdownFlow.padding(Insets.of(2, 2, 1, 1));

        assert this.client != null;
        List<String> soundDevices = this.client.getSoundManager().getSoundDevices();
        for (String device : soundDevices) {
            int deviceIndex = soundDevices.indexOf(device);
            boolean isSelected = Objects.equals(this.client.options.getSoundDevice().getValue(), device);

            ButtonComponent deviceOptionButton = Components.button(
                    Text.of(device), this::onSoundDeviceSelect
            );
            deviceOptionButton.id("sound-device-option-" + deviceIndex);
            deviceOptionButton.horizontalSizing(Sizing.fill());

            deviceOptionButton.active(!isSelected);

            dropdownFlow.child(deviceOptionButton);
        }

        ScrollContainer<FlowLayout> dropdownScrollContainer = Containers.verticalScroll(
                Sizing.content(), Sizing.expand(), dropdownFlow
        );
        dropdownScrollContainer.surface(
                Surface.VANILLA_TRANSLUCENT
        );

        LabelComponent modalLabel = Components.label(
                Text.translatable("text.config.volubind.modal.soundDevice.title")
        );
        modalLabel.horizontalSizing(Sizing.expand());
        modalLabel.verticalTextAlignment(VerticalAlignment.CENTER);

        ButtonComponent modalExitButton = Components.button(
                Text.of("❌"),
                exitButton -> rootComponent.childById(OverlayContainer.class, "sound-devices-modal-overlay").remove()
        );
        modalExitButton.sizing(Sizing.fixed(20));

        FlowLayout titleBarFlow = Containers.horizontalFlow(Sizing.fill(), Sizing.content());
        titleBarFlow.verticalAlignment(VerticalAlignment.CENTER);
        titleBarFlow.child(modalLabel);
        titleBarFlow.child(modalExitButton);
        titleBarFlow.padding(Insets.horizontal(5));

        FlowLayout modalFlow = Containers.verticalFlow(Sizing.fill(), Sizing.fill());
        modalFlow.id("sound-devices-modal");
        modalFlow.child(titleBarFlow);
        modalFlow.child(dropdownScrollContainer);
        modalFlow.padding(Insets.of(6, 6, 5, 5));
        modalFlow.gap(2);

        Sizing overlayWidth = Sizing.fixed(320);
        Sizing overlayHeight = Sizing.fixed(145);

        OverlayContainer<FlowLayout> overlay = Containers.overlay(modalFlow);
        overlay.id("sound-devices-modal-overlay");
        overlay.closeOnClick(false);
        overlay.surface(Surface.DARK_PANEL);
        overlay.positioning(Positioning.relative(50, 50));
        overlay.sizing(overlayWidth, overlayHeight);
        overlay.zIndex(100);

        ButtonComponent soundDevicesSelectButton =
                rootComponent.childById(ButtonComponent.class, "sound-devices-select-button");
        ButtonComponent soundDevicesResetButton =
                rootComponent.childById(ButtonComponent.class, "sound-devices-reset-button");

        soundDevicesSelectButton.onPress(button -> {
            // Only show modal if it doesn't already exist. Otherwise, remove it.
            if (rootComponent.childById(OverlayContainer.class, "sound-devices-modal-overlay") == null) {
                rootComponent.child(overlay);
                overlay.childById(FlowLayout.class, "sound-devices-dropdown").children().forEach(child -> {
                    ButtonComponent optionButton = (ButtonComponent) child;
                    optionButton.active(!Objects.equals(
                            optionButton.getMessage().getString(),
                            this.client.options.getSoundDevice().getValue()
                    ));
                });
            } else {
                overlay.remove();
            }
        });
        soundDevicesResetButton.onPress(this::onSoundDeviceReset);
    }

    private static class SoundDevicesContainer extends FlowLayout implements OptionValueProvider {
        protected String backingString;

        protected SoundDevicesContainer(Option<String> option) {
            super(Sizing.fill(100), Sizing.content(), Algorithm.VERTICAL);
            MinecraftClient client = MinecraftClient.getInstance();

            this.backingString = option.value();

            FlowLayout layout = Containers.horizontalFlow(Sizing.fill(100), Sizing.fixed(32));
            layout.padding(Insets.of(5));

            var optionLabel = Components.label(Text.translatable("text.config.volubind.option.soundDevice"));
            optionLabel.positioning(Positioning.relative(0, 50));

            FlowLayout buttonFlow = Containers.horizontalFlow(Sizing.content(), Sizing.content());
            buttonFlow.positioning(Positioning.relative(100, 50));

            var soundDeviceModalButton = Components.button(
                    Text.of("Select Sound Device"),
                    buttonComponent -> {}
            );

            soundDeviceModalButton.horizontalSizing(Sizing.fixed(120));
            soundDeviceModalButton.id("sound-devices-select-button");

            ButtonComponent resetSoundDeviceButton = Components.button(
                    Text.of("⇄"),
                    buttonComponent -> {}
            );

            resetSoundDeviceButton.id("sound-devices-reset-button");
            resetSoundDeviceButton.margins(Insets.horizontal(5));

            if (client.options.getSoundDevice().getValue().isEmpty()) {
                resetSoundDeviceButton.active(false);
            }

            buttonFlow.child(soundDeviceModalButton);
            buttonFlow.child(resetSoundDeviceButton);

            layout.child(optionLabel);
            layout.child(buttonFlow);

            this.child(layout);
        }

        @Override
        public boolean isValid() {
            return true;
        }

        @Override
        public Object parsedValue() {
            return this.backingString;
        }
    }

}
