package com.lilydev.volubind

import com.lilydev.volubind.config.VolubindConfig
import com.mojang.blaze3d.platform.InputUtil
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBind
import net.minecraft.sound.SoundCategory
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object VolubindClient : ClientModInitializer {
    private const val MOD_NAME: String = "VoluBind"

    @JvmField
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_NAME)

    /*
        Keybindings!
     */
    private val openConfigBind: KeyBind = registerKeybinding(
        "volubind.keybindings.open_config",
        GLFW.GLFW_KEY_BACKSLASH)

    private val toggleMasterVolume: KeyBind = registerKeybinding(
        "volubind.keybindings.toggle_master",
        GLFW.GLFW_KEY_UNKNOWN)
    private val toggleMusicVolume: KeyBind = registerKeybinding(
        "volubind.keybindings.toggle_music",
        GLFW.GLFW_KEY_UNKNOWN)
    private val toggleRecordsVolume: KeyBind = registerKeybinding(
        "volubind.keybindings.toggle_records",
        GLFW.GLFW_KEY_UNKNOWN)
    private val toggleWeatherVolume: KeyBind = registerKeybinding(
        "volubind.keybindings.toggle_weather",
        GLFW.GLFW_KEY_UNKNOWN)
    private val toggleBlocksVolume: KeyBind = registerKeybinding(
        "volubind.keybindings.toggle_blocks",
        GLFW.GLFW_KEY_UNKNOWN)
    private val toggleHostileVolume: KeyBind = registerKeybinding(
        "volubind.keybindings.toggle_hostile",
        GLFW.GLFW_KEY_UNKNOWN)
    private val toggleNeutralVolume: KeyBind = registerKeybinding(
        "volubind.keybindings.toggle_neutral",
        GLFW.GLFW_KEY_UNKNOWN)
    private val togglePlayersVolume: KeyBind = registerKeybinding(
        "volubind.keybindings.toggle_players",
        GLFW.GLFW_KEY_UNKNOWN)
    private val toggleAmbientVolume: KeyBind = registerKeybinding(
        "volubind.keybindings.toggle_ambient",
        GLFW.GLFW_KEY_UNKNOWN)
    private val toggleVoiceVolume: KeyBind = registerKeybinding(
        "volubind.keybindings.toggle_voice",
        GLFW.GLFW_KEY_UNKNOWN)


    override fun onInitializeClient(mod: ModContainer?) {
        LOGGER.info("client initialized")

        ClientTickEvents.END.register { client ->
            run {

                // Keybindings actions
                if (openConfigBind.wasPressed()) {
                    client.setScreen(VolubindConfig.createGui(client.currentScreen))
                }

                if (toggleMasterVolume.wasPressed()) {
                    if (VolubindConfig.masterToggled) {
                        client.options.setSoundVolume(SoundCategory.MASTER, intToFloat(VolubindConfig.masterVolume))
                        VolubindConfig.masterToggled = false
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'MASTER' set to: ${VolubindConfig.masterVolume}%!"))
                    } else {
                        client.options.setSoundVolume(SoundCategory.MASTER, intToFloat(VolubindConfig.toggledMaster))
                        VolubindConfig.masterToggled = true
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'MASTER' set to: ${VolubindConfig.toggledMaster}%!"))
                    }
                }
                if (toggleMusicVolume.wasPressed()) {
                    if (VolubindConfig.musicToggled) {
                        client.options.setSoundVolume(SoundCategory.MUSIC, intToFloat(VolubindConfig.musicVolume))
                        VolubindConfig.musicToggled = false
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'MUSIC' set to: ${VolubindConfig.musicVolume}%!"))
                    } else {
                        client.options.setSoundVolume(SoundCategory.MUSIC, intToFloat(VolubindConfig.toggledMusic))
                        VolubindConfig.musicToggled = true
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'MUSIC' set to: ${VolubindConfig.toggledMusic}%!"))
                    }
                }
                if (toggleRecordsVolume.wasPressed()) {
                    if (VolubindConfig.recordsToggled) {
                        client.options.setSoundVolume(SoundCategory.RECORDS, intToFloat(VolubindConfig.recordsVolume))
                        VolubindConfig.recordsToggled = false
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'RECORDS' set to: ${VolubindConfig.recordsVolume}%!"))
                    } else {
                        client.options.setSoundVolume(SoundCategory.RECORDS, intToFloat(VolubindConfig.toggledRecords))
                        VolubindConfig.recordsToggled = true
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'RECORDS' set to: ${VolubindConfig.toggledRecords}%!"))
                    }
                }
                if (toggleWeatherVolume.wasPressed()) {
                    if (VolubindConfig.weatherToggled) {
                        client.options.setSoundVolume(SoundCategory.WEATHER, intToFloat(VolubindConfig.weatherVolume))
                        VolubindConfig.weatherToggled = false
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'WEATHER' set to: ${VolubindConfig.weatherVolume}%!"))
                    } else {
                        client.options.setSoundVolume(SoundCategory.WEATHER, intToFloat(VolubindConfig.toggledWeather))
                        VolubindConfig.weatherToggled = true
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'WEATHER' set to: ${VolubindConfig.toggledWeather}%!"))
                    }
                }
                if (toggleBlocksVolume.wasPressed()) {
                    if (VolubindConfig.blocksToggled) {
                        client.options.setSoundVolume(SoundCategory.BLOCKS, intToFloat(VolubindConfig.blocksVolume))
                        VolubindConfig.blocksToggled = false
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'BLOCKS' set to: ${VolubindConfig.blocksVolume}%!"))
                    } else {
                        client.options.setSoundVolume(SoundCategory.BLOCKS, intToFloat(VolubindConfig.toggledBlocks))
                        VolubindConfig.blocksToggled = true
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'BLOCKS' set to: ${VolubindConfig.toggledBlocks}%!"))
                    }
                }
                if (toggleHostileVolume.wasPressed()) {
                    if (VolubindConfig.hostileToggled) {
                        client.options.setSoundVolume(SoundCategory.HOSTILE, intToFloat(VolubindConfig.hostileVolume))
                        VolubindConfig.hostileToggled = false
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'HOSTILE' set to: ${VolubindConfig.hostileVolume}%!"))
                    } else {
                        client.options.setSoundVolume(SoundCategory.HOSTILE, intToFloat(VolubindConfig.toggledHostile))
                        VolubindConfig.hostileToggled = true
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'HOSTILE' set to: ${VolubindConfig.toggledHostile}%!"))
                    }
                }
                if (toggleNeutralVolume.wasPressed()) {
                    if (VolubindConfig.neutralToggled) {
                        client.options.setSoundVolume(SoundCategory.NEUTRAL, intToFloat(VolubindConfig.neutralVolume))
                        VolubindConfig.neutralToggled = false
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'NEUTRAL' set to: ${VolubindConfig.neutralToggled}%!"))
                    } else {
                        client.options.setSoundVolume(SoundCategory.NEUTRAL, intToFloat(VolubindConfig.toggledNeutral))
                        VolubindConfig.neutralToggled = true
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'NEUTRAL' set to: ${VolubindConfig.toggledNeutral}%!"))
                    }
                }
                if (togglePlayersVolume.wasPressed()) {
                    if (VolubindConfig.playersToggled) {
                        client.options.setSoundVolume(SoundCategory.PLAYERS, intToFloat(VolubindConfig.playersVolume))
                        VolubindConfig.playersToggled = false
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'PLAYERS' set to: ${VolubindConfig.playersVolume}%!"))
                    } else {
                        client.options.setSoundVolume(SoundCategory.PLAYERS, intToFloat(VolubindConfig.toggledPlayers))
                        VolubindConfig.playersToggled = true
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'PLAYERS' set to: ${VolubindConfig.toggledPlayers}%!"))
                    }
                }
                if (toggleAmbientVolume.wasPressed()) {
                    if (VolubindConfig.ambientToggled) {
                        client.options.setSoundVolume(SoundCategory.AMBIENT, intToFloat(VolubindConfig.ambientVolume))
                        VolubindConfig.ambientToggled = false
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'AMBIENT' set to: ${VolubindConfig.ambientVolume}%!"))
                    } else {
                        client.options.setSoundVolume(SoundCategory.AMBIENT, intToFloat(VolubindConfig.toggledAmbient))
                        VolubindConfig.ambientToggled = true
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'AMBIENT' set to: ${VolubindConfig.toggledAmbient}%!"))
                    }
                }
                if (toggleVoiceVolume.wasPressed()) {
                    if (VolubindConfig.voiceToggled) {
                        client.options.setSoundVolume(SoundCategory.VOICE, intToFloat(VolubindConfig.voiceVolume))
                        VolubindConfig.voiceToggled = false
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'VOICE' set to: ${VolubindConfig.voiceVolume}%!"))
                    } else {
                        client.options.setSoundVolume(SoundCategory.VOICE, intToFloat(VolubindConfig.toggledVoice))
                        VolubindConfig.voiceToggled = true
                        client.inGameHud.chatHud.addMessage(Text.of("Volume 'VOICE' set to: ${VolubindConfig.toggledVoice}%!"))
                    }
                }
            }
        }
    }

    private fun registerKeybinding(translationKey: String, key: Int): KeyBind {
        return KeyBindingHelper.registerKeyBinding(KeyBind(translationKey, InputUtil.Type.KEYSYM, key, "volubind.keybindings.category"))
    }

    private fun intToFloat(int: Int): Float {
        return int.toFloat() / 100
    }


}
