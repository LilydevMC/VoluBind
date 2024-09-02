package com.lilydev.volubind.ui.config;

import com.lilydev.volubind.VolubindClient;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.ui.ConfigScreen;
import io.wispforest.owo.config.ui.OptionComponentFactory;
import io.wispforest.owo.config.ui.component.OptionValueProvider;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;


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

    private static class SoundDevicesContainer extends FlowLayout implements OptionValueProvider {
        protected final String backingString;

        protected SoundDevicesContainer(Option<String> option) {
            super(Sizing.fill(100), Sizing.content(), Algorithm.VERTICAL);
            this.backingString = option.value();

            this.allowOverflow();

            FlowLayout layout = Containers.horizontalFlow(Sizing.expand(), Sizing.content());
            layout.padding(Insets.of(5));
            layout.alignment(HorizontalAlignment.LEFT, VerticalAlignment.CENTER);
            layout.allowOverflow();

            FlowLayout buttonFlow = Containers.horizontalFlow(Sizing.content(), Sizing.content());
            buttonFlow.horizontalAlignment(HorizontalAlignment.RIGHT);
            buttonFlow.allowOverflow();

            var buttonLabel = Components.label(Text.translatable("text.config.volubind.category.soundDevice"));

            var button = Components.button(
                    Text.translatable("options.audioDevice", "test"),
                    buttonComponent -> {
                        VolubindClient.LOGGER.info("opened sound device selector");
                    }
            );

            button.horizontalSizing(Sizing.fixed(120));

            var dropdown = Components.dropdown(Sizing.content());

            dropdown.sizing(Sizing.fixed(120), Sizing.content());


            MinecraftClient client = MinecraftClient.getInstance();
            if (client != null) {
                var soundDevices = client.getSoundManager().getSoundDevices();
                for (String device : soundDevices) {
                    dropdown.button(
                            Text.of(device),
                            dropdownComponent -> {
                                VolubindClient.LOGGER.info("selected sound device: {}", device);
                            }
                    );
                }
            }

            var dropdownContainer = Containers
                    .overlay(dropdown)
                    .surface(Surface.DARK_PANEL)
                    .positioning(Positioning.absolute(0, 0))
                    .sizing(Sizing.fixed(120), Sizing.fixed(100))
                    .zIndex(100);
            layout.child(buttonLabel);
            buttonFlow.child(button);

            ((FlowLayout) layout.root()).child(dropdownContainer);

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
