package com.lilydev.volubind

import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer
import org.slf4j.LoggerFactory

object VolubindClient : ClientModInitializer {
    const val MOD_NAME = "VoluBind"
    const val MOD_ID = "volubind"
    @JvmField
    val LOGGER = LoggerFactory.getLogger("VoluBind")


    override fun onInitializeClient(mod: ModContainer?) {
        LOGGER.info("client initialized")
    }


}
