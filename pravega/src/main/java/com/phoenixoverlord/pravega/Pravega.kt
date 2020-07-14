package com.phoenixoverlord.pravega

import javax.inject.Inject

data class Server @Inject constructor (val API: String, val AI: String)

// Integrate with RemoteConfig later
object Pravega {
    var DEV = Server("http://${BuildConfig.DEV_SERVER}:8080", "http://${BuildConfig.DEV_SERVER}:5000")
    var PROD = Server("https://pravegacore.herokuapp.com/","https://pravegapredictor.herokuapp.com/")
}