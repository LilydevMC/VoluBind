package com.lilydev.volubind;

import com.lilydev.volubind.config.ConfigScreenWrapper;
import com.lilydev.volubind.config.VolubindConfig;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.InputUtil;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class VolumeControl {
    private static KeyBinding openGui;
    private static KeyBinding toggleMaster;
    private static KeyBinding toggleMusic;
    private static KeyBinding toggleMusicBlock;
    private static KeyBinding toggleWeather;
    private static KeyBinding toggleBlock;
    private static KeyBinding toggleHostile;
    private static KeyBinding toggleFriendly;
    private static KeyBinding togglePlayer;
    private static KeyBinding toggleAmbient;
    private static KeyBinding toggleVoice;


    // Translation keys have extra numbers because the Controls GUI orders them lexicographically
    public static void init() {
        openGui           = buildKeybinding("key.volubind.00.openGui");
        toggleMaster      = buildKeybinding("key.volubind.01.toggleMaster");
        toggleMusic       = buildKeybinding("key.volubind.02.toggleMusic");
        toggleMusicBlock  = buildKeybinding("key.volubind.03.toggleMusicBlock");
        toggleWeather     = buildKeybinding("key.volubind.04.toggleWeather");
        toggleBlock       = buildKeybinding("key.volubind.05.toggleBlock");
        toggleHostile     = buildKeybinding("key.volubind.06.toggleHostile");
        toggleFriendly    = buildKeybinding("key.volubind.07.toggleFriendly");
        togglePlayer      = buildKeybinding("key.volubind.08.togglePlayer");
        toggleAmbient     = buildKeybinding("key.volubind.09.toggleAmbient");
        toggleVoice       = buildKeybinding("key.volubind.10.toggleVoice");
    }

    private static KeyBinding buildKeybinding(String translationKey) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
                translationKey, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.volubind.category"
        ));
    }

    public static void checkKeyPresses(MinecraftClient client) {
        VolubindConfig config = VolubindClient.CONFIG;
        assert client.player != null;

        if (openGui.wasPressed()) {
            client.setScreen(new ConfigScreenWrapper(client.currentScreen));
        }

        while (toggleMaster.wasPressed()) {
            if (config.masterToggled()) {
                double newVolume = volumeIntToDouble(config.masterVolume());
                getSoundVolumeOption(client, SoundCategory.MASTER).setValue(newVolume);
                client.player.sendMessage(Text.literal("Master Volume set to: " + newVolume + "%"));
            } else {
                double newVolume = volumeIntToDouble(config.volumeToggled.masterVolumeToggled());
                getSoundVolumeOption(client, SoundCategory.MASTER).setValue(newVolume);
                client.player.sendMessage(Text.literal("Master Volume set to: " + newVolume + "%"));
            }
            config.masterToggled(!config.masterToggled());
        }

    }

    private static SimpleOption<Double> getSoundVolumeOption(MinecraftClient client, SoundCategory category) {
        assert client.options != null;
        return client.options.getSoundVolumeOption(category);
    }

    private static double volumeIntToDouble(int volume) {
        return ((double) volume) / 100;
    }

}
