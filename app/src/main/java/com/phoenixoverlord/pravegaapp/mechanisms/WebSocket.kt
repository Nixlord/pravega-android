package com.phoenixoverlord.pravegaapp.mechanisms

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.phoenixoverlord.pravega.config.PravegaConfig
import com.phoenixoverlord.pravega.config.Server
import com.phoenixoverlord.pravega.extensions.logDebug
import com.phoenixoverlord.pravega.extensions.logError
import com.squareup.moshi.Moshi
import okhttp3.*
import okhttp3.WebSocket
import okio.ByteString

open class DefaultWSListener(private val changeStatus: (active: Boolean) -> Unit): WebSocketListener() {
    fun log(fnName: String, active: Boolean) {
        logDebug("WSListener", "$fnName, CONNECTED = $active")
        changeStatus(active)
    }
    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        log("onClosed", false)
        logError(Error(reason))
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        log("onClosing", false)
        logError(Error(reason))
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        log("onFailure", false)
        logError(t)
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        log("onOpen", true)
    }
}

//  {"text": "\"\"", "intent": "fallback", "intent_confidence": 1.0, "fullfillment": "I didn't get that. Can you word it differently?"}
data class DialogFlow(
    val from: String = "",
    val text: String = "",
    val intent: String = "fallback",
    val intentConfidence: Float = 1.0f,
    val fulfillment: String = "",
    val items: Map<String, String>?
)

// All these components need one reference of context. Mechanism for that required.
// Replace with HILT later, add moshi adapters
// Also add template support
class WebSocket(
    http: OkHttpClient,
    moshi: Moshi,
    endpoint: String
) {

    private var webSocket: WebSocket
    private val data: MutableLiveData<DialogFlow> = MutableLiveData(DialogFlow())
    private var isActive = false
    private val adapter = moshi.adapter(DialogFlow::class.java)

    private fun setIsActive(active: Boolean) {
        isActive = active
    }

    init {
        val request = Request.Builder().url("${PravegaConfig.PROD.WS}${endpoint}").build()
        webSocket = http.newWebSocket(request, object : DefaultWSListener(::setIsActive) {
            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                logDebug("OkHttpOnMessage", text)
                val dialogFlow = adapter.fromJson(text)
                data.postValue(dialogFlow)
            }
        })
    }

    fun sendMessage(message: String) {
        webSocket.send(message)
    }

    fun onMessage(owner: LifecycleOwner, onMessage: (message: DialogFlow) -> Unit) {
        data.observe(owner, Observer(onMessage))
    }

//
//    fun stopUpdates(owner: LifecycleOwner) {
//        data.removeObservers(owner)
//    }
//
////    null checking and resiliency later for both
//    fun connect() {
//        webSocket = newWebSocket()
//    }
//
//    fun disconnect() {
//        webSocket.close(1,  "NORMAL_TERMINATION")
//    }
}