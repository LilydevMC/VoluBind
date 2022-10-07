package com.lilydev.volubind.config

import dev.isxander.settxi.impl.*
import dev.isxander.settxi.serialization.SettxiFileConfig
import dev.isxander.settxi.serialization.kotlinxSerializer
import dev.isxander.yacl.api.Binding
import dev.isxander.yacl.api.ConfigCategory
import dev.isxander.yacl.api.Option
import dev.isxander.yacl.api.OptionGroup
import dev.isxander.yacl.api.YetAnotherConfigLib
import dev.isxander.yacl.gui.controllers.LabelController
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController
import kotlinx.serialization.json.Json
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.sound.SoundCategory
import net.minecraft.text.Text
import org.quiltmc.loader.api.QuiltLoader

object VolubindConfig : SettxiFileConfig(
    QuiltLoader.getConfigDir().resolve("volubind.json"),
    kotlinxSerializer(Json { prettyPrint = true })
) {

    /*
        Base game volumes
     */

    var masterVolume by int(getVolumeAsInt(SoundCategory.MASTER)) {
        name = "Master Volume"
        category = "Base Volume"
        description = "Base Master volume"
    }

    var musicVolume by int(getVolumeAsInt(SoundCategory.MUSIC)) {
        name = "Music Volume"
        category = "Base Volume"
        description = "Base Music volume"
    }

    var recordsVolume by int(getVolumeAsInt(SoundCategory.RECORDS)) {
        name = "Records Volume"
        category = "Base Volume"
        description = "Base Jukebox/Note Blocks volume"
    }

    var weatherVolume by int(getVolumeAsInt(SoundCategory.WEATHER)) {
        name = "Weather Volume"
        category = "Base Volume"
        description = "Base Weather volume"
    }

    var blocksVolume by int(getVolumeAsInt(SoundCategory.BLOCKS)) {
        name = "Blocks Volume"
        category = "Base Volume"
        description = "Base Blocks volume"
    }

    var hostileVolume by int(getVolumeAsInt(SoundCategory.HOSTILE)) {
        name = "Hostile Volume"
        category = "Base Volume"
        description = "Base Hostile Creatures volume"
    }

    var neutralVolume by int(getVolumeAsInt(SoundCategory.NEUTRAL)) {
        name = "Neutral Volume"
        category = "Base Volume"
        description = "Base Friendly Creatures volume"
    }

    var playersVolume by int(getVolumeAsInt(SoundCategory.PLAYERS)) {
        name = "Players Volume"
        category = "Base Volume"
        description = "Base Players volume"
    }

    var ambientVolume by int(getVolumeAsInt(SoundCategory.AMBIENT)) {
        name = "Ambient Volume"
        category = "Base Volume"
        description = "Base Ambient/Environment volume"
    }

    var voiceVolume by int(getVolumeAsInt(SoundCategory.VOICE)) {
        name = "Voice Volume"
        category = "Base Volume"
        description = "Base Voice/Speech volume"
    }

    /*
        Toggled game volumes
     */

    var toggledMaster by int(0) {
        name = "Master Volume"
        category = "Toggled Volume"
        description = "Toggled Master volume"
    }

    var toggledMusic by int(0) {
        name = "Music Volume"
        category = "Toggled Volume"
        description = "Toggled Music volume"
    }

    var toggledRecords by int(0) {
        name = "Records Volume"
        category = "Toggled Volume"
        description = "Toggled Jukebox/Note Block volume"
    }

    var toggledWeather by int(0) {
        name = "Weather Volume"
        category = "Toggled Volume"
        description = "Toggled Weather volume"
    }

    var toggledBlocks by int(0) {
        name = "Blocks Volume"
        category = "Toggled Volume"
        description = "Toggled Blocks volume"
    }

    var toggledHostile by int(0) {
        name = "Hostile Volume"
        category = "Toggled Volume"
        description = "Toggled Hostile Creatures volume"
    }

    var toggledNeutral by int(0) {
        name = "Neutral Volume"
        category = "Toggled Volume"
        description = "Toggled Friendly Creatures volume"
    }

    var toggledPlayers by int(0) {
        name = "Players Volume"
        category = "Toggled Volume"
        description = "Toggled Player volume"
    }

    var toggledAmbient by int(0) {
        name = "Ambient Volume"
        category = "Toggled Volume"
        description = "Toggled Ambient/Environment volume"
    }

    var toggledVoice by int(0) {
        name = "Voice Volume"
        category = "Toggled Volume"
        description = "Toggled Voice/Speech volume"
    }

    /*
        Volume Toggles
     */

    var masterToggled by boolean(false) {
        name = "Master Volume"
        category = "Is Toggled"
        description = "Whether Master volume is toggled"
    }

    var musicToggled by boolean(false) {
        name = "Music Volume"
        category = "Is Toggled"
        description = "Whether Music volume is toggled"
    }

    var recordsToggled by boolean(false) {
        name = "Records Volume"
        category = "Is Toggled"
        description = "Whether Jukebox/Note Blocks volume is toggled"
    }

    var weatherToggled by boolean(false) {
        name = "Weather Volume"
        category = "Is Toggled"
        description = "Whether Weather volume is toggled"
    }

    var blocksToggled by boolean(false) {
        name = "Blocks Volume"
        category = "Is Toggled"
        description = "Whether Blocks volume is toggled"
    }

    var hostileToggled by boolean(false) {
        name = "Hostile Volume"
        category = "Is Toggled"
        description = "Whether Hostile Creatures volume is toggled"
    }

    var neutralToggled by boolean(false) {
        name = "Neutral Volume"
        category = "Is Toggled"
        description = "Whether Friendly Creatures volume is toggled"
    }

    var playersToggled by boolean(false) {
        name = "Players Volume"
        category = "Is Toggled"
        description = "Whether Players volume is toggled"
    }

    var ambientToggled by boolean(false) {
        name = "Ambient Volume"
        category = "Is Toggled"
        description = "Whether Ambient/Environment volume is toggled"
    }

    var voiceToggled by boolean(false) {
        name = "Voice Volume"
        category = "Is Toggled"
        description = "Whether Voice/Speech volume is toggled"
    }


    init {
        import()
    }

    private fun getVolumeAsInt(soundCategory: SoundCategory): Int {
        return (MinecraftClient.getInstance().options.getSoundVolume(soundCategory) * 100).toInt()
    }

    fun createGui(parent: Screen?): Screen {
        return YetAnotherConfigLib.createBuilder()
            .title(Text.translatable("volubind.gui.label.general.title"))
            .category(ConfigCategory.createBuilder()
                .name(Text.translatable("volubind.gui.menu.settings"))
                .tooltip(Text.translatable("volubind.gui.label.general.tooltip"))

                // this option is essentially just for padding!
                .option(Option.createBuilder(Text::class.java)
                    .name(Text.of(""))
                    .binding(Binding.immutable(Text.of("")))
                    .controller { option: Option<Text> -> LabelController(option) }
                    .build()
                )
                .group(OptionGroup.createBuilder()
                    .name(Text.translatable("volubind.gui.volume.category.label"))
                    .tooltip(Text.translatable("volubind.gui.volume.category.tooltip"))
                    .collapsed(false)
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.volume.master.name"))
                        .tooltip(Text.translatable("volubind.gui.volume.master.tooltip"))
                        .binding(Binding.generic(
                            masterVolume,
                            { run {


                                return@generic masterVolume
                            } },
                            {
                                    newValue: Int ->
                                run {
                                    val newFloat = newValue.toFloat() / 100
                                    masterVolume = newValue
                                    MinecraftClient.getInstance().options.setSoundVolume(
                                        SoundCategory.MASTER,
                                        newFloat
                                    )
                                    masterToggled = false
                                }
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.volume.music.name"))
                        .tooltip(Text.translatable("volubind.gui.volume.music.tooltip"))
                        .binding(Binding.generic(
                            musicVolume,
                            { musicVolume },
                            {
                                    newValue: Int ->
                                run {
                                    val newFloat = newValue.toFloat() / 100
                                    musicVolume = newValue
                                    MinecraftClient.getInstance().options.setSoundVolume(
                                        SoundCategory.MUSIC,
                                        newFloat
                                    )
                                    musicToggled = false
                                }
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.volume.records.name"))
                        .tooltip(Text.translatable("volubind.gui.volume.records.tooltip"))
                        .binding(Binding.generic(
                            recordsVolume,
                            { recordsVolume },
                            {
                                    newValue: Int ->
                                run {
                                    val newFloat = newValue.toFloat() / 100
                                    recordsVolume = newValue
                                    MinecraftClient.getInstance().options.setSoundVolume(
                                        SoundCategory.RECORDS,
                                        newFloat
                                    )
                                    recordsToggled = false
                                }
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.volume.weather.name"))
                        .tooltip(Text.translatable("volubind.gui.volume.weather.tooltip"))
                        .binding(Binding.generic(
                            weatherVolume,
                            { weatherVolume },
                            {
                                    newValue: Int ->
                                run {
                                    val newFloat = newValue.toFloat() / 100
                                    weatherVolume = newValue
                                    MinecraftClient.getInstance().options.setSoundVolume(
                                        SoundCategory.WEATHER,
                                        newFloat
                                    )
                                    weatherToggled = false
                                }
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.volume.blocks.name"))
                        .tooltip(Text.translatable("volubind.gui.volume.blocks.tooltip"))
                        .binding(Binding.generic(
                            blocksVolume,
                            { blocksVolume },
                            {
                                    newValue: Int ->
                                run {
                                    val newFloat = newValue.toFloat() / 100
                                    blocksVolume = newValue
                                    MinecraftClient.getInstance().options.setSoundVolume(
                                        SoundCategory.BLOCKS,
                                        newFloat
                                    )
                                    blocksToggled = false
                                }
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.volume.hostile.name"))
                        .tooltip(Text.translatable("volubind.gui.volume.hostile.tooltip"))
                        .binding(Binding.generic(
                            hostileVolume,
                            { hostileVolume },
                            {
                                    newValue: Int ->
                                run {
                                    val newFloat = newValue.toFloat() / 100
                                    hostileVolume = newValue
                                    MinecraftClient.getInstance().options.setSoundVolume(
                                        SoundCategory.HOSTILE,
                                        newFloat
                                    )
                                    hostileToggled = false
                                }
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.volume.neutral.name"))
                        .tooltip(Text.translatable("volubind.gui.volume.neutral.tooltip"))
                        .binding(Binding.generic(
                            neutralVolume,
                            { neutralVolume },
                            {
                                    newValue: Int ->
                                run {
                                    val newFloat = newValue.toFloat() / 100
                                    neutralVolume = newValue
                                    MinecraftClient.getInstance().options.setSoundVolume(
                                        SoundCategory.NEUTRAL,
                                        newFloat
                                    )
                                    neutralToggled = false
                                }
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.volume.players.name"))
                        .tooltip(Text.translatable("volubind.gui.volume.players.tooltip"))
                        .binding(Binding.generic(
                            playersVolume,
                            { playersVolume },
                            {
                                    newValue: Int ->
                                run {
                                    val newFloat = newValue.toFloat() / 100
                                    playersVolume = newValue
                                    MinecraftClient.getInstance().options.setSoundVolume(
                                        SoundCategory.PLAYERS,
                                        newFloat
                                    )
                                    playersToggled = false
                                }
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.volume.ambient.name"))
                        .tooltip(Text.translatable("volubind.gui.volume.ambient.tooltip"))
                        .binding(Binding.generic(
                            ambientVolume,
                            { ambientVolume },
                            {
                                    newValue: Int ->
                                run {
                                    val newFloat = newValue.toFloat() / 100
                                    ambientVolume = newValue
                                    MinecraftClient.getInstance().options.setSoundVolume(
                                        SoundCategory.AMBIENT,
                                        newFloat
                                    )
                                    ambientToggled = false
                                }
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.volume.voice.name"))
                        .tooltip(Text.translatable("volubind.gui.volume.voice.tooltip"))
                        .binding(Binding.generic(
                            voiceVolume,
                            { voiceVolume },
                            {
                                    newValue: Int ->
                                run {
                                    val newFloat = newValue.toFloat() / 100
                                    voiceVolume = newValue
                                    MinecraftClient.getInstance().options.setSoundVolume(
                                        SoundCategory.VOICE,
                                        newFloat
                                    )
                                    voiceToggled = false
                                }
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .build()
                )
                .group(OptionGroup.createBuilder()
                    .name(Text.translatable("volubind.gui.toggled.category.label"))
                    .tooltip(Text.translatable("volubind.gui.toggled.category.tooltip"))
                    .collapsed(true)
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.toggled.master.name"))
                        .tooltip(Text.translatable("volubind.gui.toggled.master.tooltip"))
                        .binding(Binding.generic(
                            toggledMaster,
                            { toggledMaster },
                            {
                                    newValue: Int -> toggledMaster = newValue
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.toggled.music.name"))
                        .tooltip(Text.translatable("volubind.gui.toggled.music.tooltip"))
                        .binding(Binding.generic(
                            toggledMusic,
                            { toggledMusic },
                            {
                                    newValue: Int -> toggledMusic = newValue
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.toggled.records.name"))
                        .tooltip(Text.translatable("volubind.gui.toggled.records.tooltip"))
                        .binding(Binding.generic(
                            toggledRecords,
                            { toggledRecords },
                            {
                                    newValue: Int -> toggledRecords = newValue
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.toggled.weather.name"))
                        .tooltip(Text.translatable("volubind.gui.toggled.weather.tooltip"))
                        .binding(Binding.generic(
                            toggledWeather,
                            { toggledWeather },
                            {
                                    newValue: Int -> toggledWeather = newValue
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.toggled.blocks.name"))
                        .tooltip(Text.translatable("volubind.gui.toggled.blocks.tooltip"))
                        .binding(Binding.generic(
                            toggledBlocks,
                            { toggledBlocks },
                            {
                                    newValue: Int -> toggledBlocks = newValue
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.toggled.hostile.name"))
                        .tooltip(Text.translatable("volubind.gui.toggled.hostile.tooltip"))
                        .binding(Binding.generic(
                            toggledHostile,
                            { toggledHostile },
                            {
                                    newValue: Int -> toggledHostile = newValue
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.toggled.neutral.name"))
                        .tooltip(Text.translatable("volubind.gui.toggled.neutral.tooltip"))
                        .binding(Binding.generic(
                            toggledNeutral,
                            { toggledNeutral },
                            {
                                    newValue: Int -> toggledNeutral = newValue
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.toggled.players.name"))
                        .tooltip(Text.translatable("volubind.gui.toggled.players.tooltip"))
                        .binding(Binding.generic(
                            toggledPlayers,
                            { toggledPlayers },
                            {
                                    newValue: Int -> toggledPlayers = newValue
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.toggled.ambient.name"))
                        .tooltip(Text.translatable("volubind.gui.toggled.ambient.tooltip"))
                        .binding(Binding.generic(
                            toggledAmbient,
                            { toggledAmbient },
                            {
                                    newValue: Int -> toggledAmbient = newValue
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .option(Option.createBuilder(Int::class.java)
                        .name(Text.translatable("volubind.gui.toggled.voice.name"))
                        .tooltip(Text.translatable("volubind.gui.toggled.voice.tooltip"))
                        .binding(Binding.generic(
                            toggledVoice,
                            { toggledVoice },
                            {
                                    newValue: Int -> toggledVoice = newValue
                            }
                        ))
                        .controller {
                                option: Option<Int> -> IntegerSliderController(option, 0, 100, 1)
                        }
                        .build()
                    )
                    .build()
                )
                .build()
            )
            .save { export() }
            .build()
            .generateScreen(parent)
    }

}
