//package com.lilydev.volubind
//
//import dev.isxander.yacl.api.Binding
//import dev.isxander.yacl.api.ConfigCategory
//import dev.isxander.yacl.api.Option
//import dev.isxander.yacl.api.YetAnotherConfigLib
//import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController
//import dev.isxander.yacl.gui.controllers.LabelController
//import net.minecraft.client.MinecraftClient
//import net.minecraft.client.gui.screen.Screen
//import net.minecraft.sound.SoundCategory
//import net.minecraft.text.Text
//import java.util.function.Function
//
//object VolubindConfigScreen {
//
//    var testVolume = 0
//
//    fun save() {
//        VolubindClient.LOGGER.info("config saved!")
//    }
//
//    fun createGui(parent: Screen?): Screen {
//        return YetAnotherConfigLib.createBuilder()
//            .title(Text.of(VolubindClient.MOD_NAME)) // General Category
//            .category(ConfigCategory.createBuilder()
//                .name(Text.of("General"))
//                .tooltip(Text.of("General settings for " + VolubindClient.MOD_NAME + ".")) // Game Volume Label
//                .option(
//                    Option.createBuilder(Text::class.java)
//                        .name(Text.translatable("volubind.gui.label.volume"))
//                        .binding(Binding.immutable(Text.translatable("volubind.gui.label.volume")))
//                        .controller(Function { option: Option<Text>? ->
//                            LabelController(
//                                option
//                            )
//                        })
//                        .build()
//                ) // Master Volume Int Slider
//                .option(
//                    Option.createBuilder(Int::class.javaPrimitiveType)
//                        .name(Text.translatable("volubind.gui.volume.name.master"))
//                        .tooltip(Text.translatable("volubind.gui.volume.label.music"))
//                        .binding(
//                            testVolume,
//                            {
//                                testVolume =
//                                    (MinecraftClient.getInstance().options.getSoundVolume(SoundCategory.MASTER) * 100).toInt()
//                                testVolume
//                            }
//                        ) { newValue: Int ->
//                            val newFloat = newValue.toFloat() / 100
//                            testVolume = newValue
//                            MinecraftClient.getInstance().options.setSoundVolume(
//                                SoundCategory.MASTER,
//                                newFloat
//                            )
//                            VolubindClient.LOGGER.info(newFloat.toString())
//                        }
//                        .controller { option: Option<Int>? ->
//                            IntegerSliderController(
//                                option,
//                                0,
//                                100,
//                                1
//                            )
//                        }
//                        .build()
//                )
//                .option(
//                    Option.createBuilder(Int::class.javaPrimitiveType)
//                        .name(Text.translatable("volubind.gui.volume.name.music"))
//                        .tooltip(Text.translatable("volubind.gui.volume.label.music"))
//                        .binding(
//                            testVolume,
//                            {
//                                testVolume =
//                                    (MinecraftClient.getInstance().options.getSoundVolume(SoundCategory.MUSIC) * 100).toInt()
//                                testVolume
//                            }
//                        ) { newValue: Int ->
//                            val newFloat = newValue.toFloat() / 100
//                            testVolume = newValue
//                            MinecraftClient.getInstance().options.setSoundVolume(SoundCategory.MUSIC, newFloat)
//                            VolubindClient.LOGGER.info(newFloat.toString())
//                        }
//                        .controller { option: Option<Int>? ->
//                            IntegerSliderController(
//                                option,
//                                0,
//                                100,
//                                1
//                            )
//                        }
//                        .build()
//                )
//                .build())
//            .save { save() }
//            .build()
//            .generateScreen(parent)
//    }
//}
