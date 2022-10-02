package com.lilydev.volubind.integration

import com.lilydev.volubind.VolubindConfig
import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi

class VolubindModMenuImpl : ModMenuApi {
    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> = ConfigScreenFactory {
        parent -> VolubindConfig.createGui(parent)
    }
}
