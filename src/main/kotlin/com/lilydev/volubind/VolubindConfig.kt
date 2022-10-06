package com.lilydev.volubind

import dev.isxander.settxi.impl.*
import dev.isxander.settxi.serialization.SettxiFileConfig
import dev.isxander.settxi.serialization.kotlinxSerializer
import dev.isxander.yacl.api.Binding
import dev.isxander.yacl.api.ConfigCategory
import dev.isxander.yacl.api.Option
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


    init {
        import()
    }

    fun getVolumeAsInt(soundCategory: SoundCategory): Int {
        return (MinecraftClient.getInstance().options.getSoundVolume(soundCategory) * 100).toInt()
    }

    fun saveConfig() {
        export()
    }

    fun createGui(parent: Screen): Screen {
        return YetAnotherConfigLib.createBuilder()
            .title(Text.translatable("volubind.gui.menu.settings"))
            .category(ConfigCategory.createBuilder()
                .name(Text.translatable("volubind.gui.label.general.title"))
                .tooltip(Text.translatable("volubind.gui.label.general.tooltip"))
                .option(Option.createBuilder(Text::class.java)
                    .name(Text.translatable("volubind.gui.volume.category.label"))
                    .tooltip(Text.translatable("volubind.gui.volume.category.tooltip"))
                    .binding(Binding.immutable(Text.translatable("volubind.gui.volume.category.label")))
                    .controller { option: Option<Text> -> LabelController(option) }
                    .build()
                )
                .option(Option.createBuilder(Int::class.java)
                    .name(Text.translatable("volubind.gui.volume.master.name"))
                    .tooltip(Text.translatable("volubind.gui.volume.master.tooltip"))
                    .binding(Binding.generic(
                        this.masterVolume,
                        { run {


                            return@generic this.masterVolume
                        } },
                        {
                            newValue: Int ->
                            run {
                                val newFloat = newValue.toFloat() / 100
                                this.masterVolume = newValue
                                MinecraftClient.getInstance().options.setSoundVolume(
                                    SoundCategory.MASTER,
                                    newFloat
                                )
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
                        this.musicVolume,
                        { this.musicVolume },
                        {
                            newValue: Int ->
                            run {
                                val newFloat = newValue.toFloat() / 100
                                this.musicVolume = newValue
                                MinecraftClient.getInstance().options.setSoundVolume(
                                    SoundCategory.MUSIC,
                                    newFloat
                                )
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
                        this.recordsVolume,
                        { this.recordsVolume },
                        {
                                newValue: Int ->
                            run {
                                val newFloat = newValue.toFloat() / 100
                                this.recordsVolume = newValue
                                MinecraftClient.getInstance().options.setSoundVolume(
                                    SoundCategory.RECORDS,
                                    newFloat
                                )
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
                        this.weatherVolume,
                        { this.weatherVolume },
                        {
                                newValue: Int ->
                            run {
                                val newFloat = newValue.toFloat() / 100
                                this.weatherVolume = newValue
                                MinecraftClient.getInstance().options.setSoundVolume(
                                    SoundCategory.WEATHER,
                                    newFloat
                                )
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
                        this.blocksVolume,
                        { this.blocksVolume },
                        {
                                newValue: Int ->
                            run {
                                val newFloat = newValue.toFloat() / 100
                                this.blocksVolume = newValue
                                MinecraftClient.getInstance().options.setSoundVolume(
                                    SoundCategory.BLOCKS,
                                    newFloat
                                )
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
                        this.hostileVolume,
                        { this.hostileVolume },
                        {
                                newValue: Int ->
                            run {
                                val newFloat = newValue.toFloat() / 100
                                this.hostileVolume = newValue
                                MinecraftClient.getInstance().options.setSoundVolume(
                                    SoundCategory.HOSTILE,
                                    newFloat
                                )
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
                        this.neutralVolume,
                        { this.neutralVolume },
                        {
                                newValue: Int ->
                            run {
                                val newFloat = newValue.toFloat() / 100
                                this.neutralVolume = newValue
                                MinecraftClient.getInstance().options.setSoundVolume(
                                    SoundCategory.NEUTRAL,
                                    newFloat
                                )
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
                        this.playersVolume,
                        { this.playersVolume },
                        {
                                newValue: Int ->
                            run {
                                val newFloat = newValue.toFloat() / 100
                                this.playersVolume = newValue
                                MinecraftClient.getInstance().options.setSoundVolume(
                                    SoundCategory.PLAYERS,
                                    newFloat
                                )
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
                        this.ambientVolume,
                        { this.ambientVolume },
                        {
                                newValue: Int ->
                            run {
                                val newFloat = newValue.toFloat() / 100
                                this.ambientVolume = newValue
                                MinecraftClient.getInstance().options.setSoundVolume(
                                    SoundCategory.AMBIENT,
                                    newFloat
                                )
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
                        this.voiceVolume,
                        { this.voiceVolume },
                        {
                                newValue: Int ->
                            run {
                                val newFloat = newValue.toFloat() / 100
                                this.voiceVolume = newValue
                                MinecraftClient.getInstance().options.setSoundVolume(
                                    SoundCategory.VOICE,
                                    newFloat
                                )
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
            .save { saveConfig() }
            .build()
            .generateScreen(parent)
    }

}
