package com.lilydev.volubind

import dev.isxander.settxi.impl.*
import dev.isxander.settxi.serialization.PrimitiveType
import dev.isxander.settxi.SettxiConfig
import dev.isxander.yacl.api.Binding
import dev.isxander.yacl.api.ConfigCategory
import dev.isxander.yacl.api.Option
import dev.isxander.yacl.api.YetAnotherConfigLib
import dev.isxander.yacl.gui.controllers.LabelController
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.sound.SoundCategory
import net.minecraft.text.Text

object VolubindConfig : SettxiConfig() {

    var masterVolume by int(getVolumeAsInt(SoundCategory.MASTER)) {
        name = "Master Volume"
        category = "Game Volume"
        description = "Game's music volume"
    }

    var musicVolume by int(getVolumeAsInt(SoundCategory.MUSIC)) {
        name = "Music Volume"
        category = "Game Volume"
        description = "Game's music volume"
    }

    init {
        import()
    }

    override fun export() {
        for (setting in this.settings) {
            setting.setSerialized(PrimitiveType.of(true))
        }
    }

    override fun import() {
        for (settings in this.settings) {
            val volume = settings.get()
        }
    }

    fun getVolumeAsInt(soundCategory: SoundCategory): Int {
        return (MinecraftClient.getInstance().options.getSoundVolume(soundCategory) * 100).toInt()
    }

    fun saveConfig() {
        //
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
                        { this.masterVolume },
                        {
                            newValue: Int ->
                            run {
                                val newFloat = newValue.toFloat() / 100
                                this.masterVolume = newValue
                                MinecraftClient.getInstance().options.setSoundVolume(
                                    SoundCategory.MASTER,
                                    newFloat
                                )
                                VolubindClient.LOGGER.info(newFloat.toString())
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
                                VolubindClient.LOGGER.info(newFloat.toString())
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
