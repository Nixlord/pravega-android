package com.phoenixoverlord.pravega.config

import com.phoenixoverlord.pravega.BuildConfig

data class Server(val API: String, val WS: String, val AI: String)

// Integrate with RemoteConfig later
object PravegaConfig {
    var DEV = Server(
        "http://${BuildConfig.DEV_SERVER}:8080",
        "ws://${BuildConfig.DEV_SERVER}:8080/ws",
        "http://${BuildConfig.DEV_SERVER}:8000"
    )
    var PROD = Server(
        "https://pravegacore.herokuapp.com",
        "wss://pravegapredictor.herokuapp.com/ws",
        "https://pravegapredictor.herokuapp.com"
    )
}