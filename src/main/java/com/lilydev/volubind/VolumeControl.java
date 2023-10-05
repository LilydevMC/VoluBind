package com.lilydev.volubind;

import com.lilydev.volubind.config.ConfigScreenWrapper;
import com.lilydev.volubind.config.VolubindConfig;
import com.lilydev.volubind.util.Utils;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.InputUtil;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.EnumSet;

public class VolumeControl {
    private static KeyBinding openGui;
    public static KeyBinding toggleMaster;
    public static KeyBinding toggleMusic;
    public static KeyBinding toggleMusicBlock;
    public static KeyBinding toggleWeather;
    public static KeyBinding toggleBlock;
    public static KeyBinding toggleHostile;
    public static KeyBinding toggleFriendly;
    public static KeyBinding togglePlayer;
    public static KeyBinding toggleAmbient;
    public static KeyBinding toggleVoice;


    // Translation keys have extra numbers because the Controls GUI orders them lexicographically
    public static void init() {
        openGui           = buildKeybinding("volubind.key.00.openGui");
        toggleMaster      = buildKeybinding("volubind.key.01.toggleMaster");
        toggleMusic       = buildKeybinding("volubind.key.02.toggleMusic");
        toggleMusicBlock  = buildKeybinding("volubind.key.03.toggleMusicBlock");
        toggleWeather     = buildKeybinding("volubind.key.04.toggleWeather");
        toggleBlock       = buildKeybinding("volubind.key.05.toggleBlock");
        toggleHostile     = buildKeybinding("volubind.key.06.toggleHostile");
        toggleFriendly    = buildKeybinding("volubind.key.07.toggleFriendly");
        togglePlayer      = buildKeybinding("volubind.key.08.togglePlayer");
        toggleAmbient     = buildKeybinding("volubind.key.09.toggleAmbient");
        toggleVoice       = buildKeybinding("volubind.key.10.toggleVoice");
    }

    public static void initVolumeOptions(MinecraftClient client) {
        VolubindConfig config = VolubindClient.CONFIG;
        EnumSet.allOf(SoundCategory.class)
                .forEach(category -> {
                    boolean isToggled = Utils.getToggleSupplierByCategory(config, category).get();

                    int newVolInt = Utils.getVolumeSupplierByCategory(
                            config,
                            category,
                            isToggled ? Utils.ConfigVolumeType.TOGGLED : Utils.ConfigVolumeType.UNTOGGLED
                    ).get();
                    double newVolDouble = volumeIntToDouble(newVolInt);

                    getSoundVolumeOption(client, category).setValue(newVolDouble);
                });
    }

    private static KeyBinding buildKeybinding(String translationKey) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
                translationKey, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "volubind.key.category"
        ));
    }

    public static void processKeyPress(MinecraftClient client) {
        VolubindConfig config = VolubindClient.CONFIG;
        assert client.player != null;

        if (openGui.wasPressed()) {
            client.setScreen(new ConfigScreenWrapper(client.currentScreen));
        }

        // Not entirely sure if this presents performance issues, but it seems
        // fine for now and is a lot cleaner than previous iterations
        EnumSet.allOf(SoundCategory.class)
                .forEach(category -> {
                    while (Utils.getKeyBindingByCategory(category).wasPressed()) {
                        boolean isToggled = Utils.getToggleSupplierByCategory(config, category).get();

                        int newVolInt = Utils.getVolumeSupplierByCategory(
                                config,
                                category,
                                isToggled ? Utils.ConfigVolumeType.UNTOGGLED : Utils.ConfigVolumeType.TOGGLED
                        ).get();
                        double newVolDouble = volumeIntToDouble(newVolInt);
                        getSoundVolumeOption(client, category).setValue(newVolDouble);

                        client.player.sendMessage(Text.translatable(
                                Utils.getTranslationStringByCategory(category), newVolInt
                        ));

                        Utils.getToggleConsumerByCategory(config, category)
                                .accept(!isToggled);
                    }
                });
    }

    private static SimpleOption<Double> getSoundVolumeOption(MinecraftClient client, SoundCategory category) {
        assert client.options != null;
        return client.options.getSoundVolumeOption(category);
    }

    private static double volumeIntToDouble(int volume) {
        return ((double) volume) / 100;
    }

}
