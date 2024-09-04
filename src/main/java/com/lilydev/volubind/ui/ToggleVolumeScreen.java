package com.lilydev.volubind.ui;

import com.lilydev.volubind.VolubindClient;
import com.lilydev.volubind.VolumeControl;
import com.lilydev.volubind.util.Utils;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.LabelComponent;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.ScrollContainer;
import io.wispforest.owo.ui.core.Insets;
import io.wispforest.owo.ui.core.Positioning;
import io.wispforest.owo.ui.core.Sizing;
import io.wispforest.owo.ui.core.VerticalAlignment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.EnumSet;

public class ToggleVolumeScreen extends BaseUIModelScreen<FlowLayout> {

    Screen parent;


    public ToggleVolumeScreen(Screen parent) {
        super(FlowLayout.class, DataSource.asset(Identifier.of(VolubindClient.MOD_ID, "toggle_volume_screen")));
        this.parent = parent;
    }

    private static void toggleVolume(ButtonComponent buttonComponent, SoundCategory category) {
        boolean volumeToggled = Utils.getVolumeToggleSupplierByCategory(VolubindClient.CONFIG, category).get();

        Text toggledVolText = Text.translatable("volubind.screen.toggle_volume.entry.button.toggled");
        Text untoggledVolText = Text.translatable("volubind.screen.toggle_volume.entry.button.untoggled");

        VolumeControl.toggleVolumeByCategory(VolubindClient.CONFIG, category);
        if (volumeToggled) {
            buttonComponent.setMessage(untoggledVolText);
        } else {
            buttonComponent.setMessage(toggledVolText);
        }
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        assert this.client != null;

        FlowLayout contentFlow = Containers.verticalFlow(Sizing.content(), Sizing.content());
        contentFlow.id("toggle-volume-content");
        contentFlow.gap(4);
        contentFlow.padding(Insets.horizontal(5));

        ScrollContainer<FlowLayout> pageScroll = Containers.verticalScroll(
            Sizing.fixed(360),
            Sizing.expand(),
            contentFlow
        );

        pageScroll.padding(Insets.bottom(20));

        EnumSet.allOf(SoundCategory.class).forEach(soundCategory -> {
            FlowLayout buttonFlow = Containers.horizontalFlow(Sizing.fill(), Sizing.fixed(20));
            buttonFlow.verticalAlignment(VerticalAlignment.CENTER);

            LabelComponent flowLabel = Components.label(
                Text.translatable("soundCategory." + soundCategory.getName())
            );

            boolean volumeToggled = Utils.getVolumeToggleSupplierByCategory(VolubindClient.CONFIG, soundCategory).get();

            Text toggledVolText = Text.translatable("volubind.screen.toggle_volume.entry.button.toggled");
            Text untoggledVolText = Text.translatable("volubind.screen.toggle_volume.entry.button.untoggled");

            Text buttonText = volumeToggled ? toggledVolText : untoggledVolText;

            ButtonComponent flowButton = Components.button(
                buttonText, buttonComponent -> toggleVolume(buttonComponent, soundCategory)
            );

            flowButton.horizontalSizing(Sizing.fixed(120));
            flowButton.positioning(Positioning.relative(100, 50));

            buttonFlow.child(flowLabel);
            buttonFlow.child(flowButton);

            contentFlow.child(buttonFlow);
        });

        rootComponent.child(pageScroll);
    }
}
