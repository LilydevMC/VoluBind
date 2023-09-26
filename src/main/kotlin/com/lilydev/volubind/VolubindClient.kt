package com.lilydev.volubind

import net.fabricmc.api.ClientModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object VolubindClient : ClientModInitializer {

    const val MOD_ID: String = "volubind"
    const val MOD_NAME: String = "VoluBind"

    @JvmField
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_NAME)

    override fun onInitializeClient() {
        LOGGER.info("Hello Fabric world from $MOD_NAME")
    }
}