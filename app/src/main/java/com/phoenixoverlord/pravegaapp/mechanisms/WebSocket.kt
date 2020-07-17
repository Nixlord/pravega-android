package com.phoenixoverlord.pravegaapp.mechanisms

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.phoenixoverlord.pravega.config.PravegaConfig
import com.phoenixoverlord.pravega.extensions.logDebug
import com.phoenixoverlord.pravega.extensions.logError
import com.squareup.moshi.Moshi
import okhttp3.*
import okhttp3.WebSocket

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

object Agent {
    val PersonalAssistant = "PERSONAL_ASSISTANT"
    val CustomerService = "CUSTOMER_SERVICE"
}


data class Result(
    val colour: String = "",
    val gender: String = "",
    val item: String = ""
) {
    fun isValid(): Boolean {
        return colour != "" && gender != "" && item != ""
    }
}
//  {"text": "\"\"", "intent": "fallback", "intent_confidence": 1.0, "fullfillment": "I didn't get that. Can you word it differently?"}
data class DialogFlowResponse(
    val from: String = "",
    val text: String = "",
    val intent: String = "fallback",
    val intentConfidence: Float = 1.0f,
    val fulfillment: String = "",
    val items: Result? = null
)

data class DialogFlowRequest(
    val to: String,
    val text: String = ""
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
    private val data: MutableLiveData<DialogFlowResponse> = MutableLiveData(DialogFlowResponse())
    private var isActive = false
    private val responseAdapter = moshi.adapter(DialogFlowResponse::class.java)
    private val requestAdapter = moshi.adapter(DialogFlowRequest::class.java)

    private fun setIsActive(active: Boolean) {
        isActive = active
    }

    init {
        val request = Request.Builder().url("${PravegaConfig.PROD.WS}${endpoint}").build()
        webSocket = http.newWebSocket(request, object : DefaultWSListener(::setIsActive) {
            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                logDebug("OkHttpOnMessage", text)
                val dialogFlow = responseAdapter.fromJson(text)
                data.postValue(dialogFlow)
            }
        })
    }

    fun sendMessage(dialogFlowRequest: DialogFlowRequest) {
        webSocket.send(requestAdapter.toJson(dialogFlowRequest))
    }

    fun onMessage(owner: LifecycleOwner, onMessage: (message: DialogFlowResponse) -> Unit) {
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