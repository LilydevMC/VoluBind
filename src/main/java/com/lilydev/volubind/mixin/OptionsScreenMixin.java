package com.lilydev.volubind.mixin;

import com.lilydev.volubind.config.SoundOptionsSelectorScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.*;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.client.option.GameOptions;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin extends Screen {

    @Shadow
    @Final
    private static Text SKIN_CUSTOMIZATION_TEXT;

    @Shadow
    @Final
    private static Text SOUNDS_TEXT;

    @Shadow
    @Final
    private static Text VIDEO_TEXT;

    @Shadow
    @Final
    private static Text CONTROL_TEXT;

    @Shadow
    @Final
    private static Text LANGUAGE_TEXT;

    @Shadow
    @Final
    private static Text CHAT_TEXT;

    @Shadow
    @Final
    private static Text RESOURCE_PACK_TEXT;

    @Shadow
    @Final
    private static Text ACCESSIBILITY_TEXT;

    @Shadow
    @Final
    private static Text TELEMETRY_TEXT;

    @Shadow
    @Final
    private static Text CREDITS_AND_ATTRIBUTION_TEXT;

    @Shadow
    @Final
    private Screen parent;

    @Shadow
    @Final
    private GameOptions settings;

    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @Shadow
    private ButtonWidget createButton(Text message, Supplier<Screen> screenSupplier) {
        return null;
    }

    @Shadow
    private Widget createTopRightButton() {
        return null;
    }

    @Shadow
    private void refreshResourcePacks(ResourcePackManager resourcePackManager) {}


    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    public void replaceInit(CallbackInfo ci) {
        GridWidget gridWidget = new GridWidget();
        gridWidget.getMainPositioner().marginX(5).marginBottom(4).alignHorizontalCenter();
        GridWidget.Adder adder = gridWidget.createAdder(2);
        assert this.client != null;
        adder.add(this.settings.getFov().createWidget(this.client.options, 0, 0, 150));
        adder.add(this.createTopRightButton());
        adder.add(EmptyWidget.ofHeight(26), 2);
        adder.add(this.createButton(SKIN_CUSTOMIZATION_TEXT, () -> new SkinOptionsScreen(this, this.settings)));
        // ...

        adder.add(this.createButton(SOUNDS_TEXT, () -> new SoundOptionsSelectorScreen(this)));

        // ...
        adder.add(this.createButton(VIDEO_TEXT, () -> new VideoOptionsScreen(this, this.settings)));
        adder.add(this.createButton(CONTROL_TEXT, () -> new ControlsOptionsScreen(this, this.settings)));
        adder.add(this.createButton(LANGUAGE_TEXT, () -> new LanguageOptionsScreen(this, this.settings, this.client.getLanguageManager())));
        adder.add(this.createButton(CHAT_TEXT, () -> new ChatOptionsScreen(this, this.settings)));
        adder.add(
                this.createButton(
                        RESOURCE_PACK_TEXT,
                        () -> new PackScreen(
                                this.client.getResourcePackManager(), this::refreshResourcePacks, this.client.getResourcePackDir(), Text.translatable("resourcePack.title")
                        )
                )
        );
        adder.add(this.createButton(ACCESSIBILITY_TEXT, () -> new AccessibilityOptionsScreen(this, this.settings)));
        adder.add(this.createButton(TELEMETRY_TEXT, () -> new TelemetryInfoScreen(this, this.settings)));
        adder.add(this.createButton(CREDITS_AND_ATTRIBUTION_TEXT, () -> new CreditsAndAttributionScreen(this)));
        adder.add(ButtonWidget.builder(ScreenTexts.DONE, button -> this.client.setScreen(this.parent)).width(200).build(), 2, adder.copyPositioner().marginTop(6));
        gridWidget.refreshPositions();
        SimplePositioningWidget.setPos(gridWidget, 0, this.height / 6 - 12, this.width, this.height, 0.5F, 0.0F);
        gridWidget.forEachChild(this::addDrawableChild);

        ci.cancel();
    }

}
