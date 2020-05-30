package com.phoenixoverlord.pravega.networking

import com.phoenixoverlord.pravega.config.Server
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import okhttp3.*
import okhttp3.WebSocket
import java.util.concurrent.ConcurrentLinkedQueue

public class PubSub() {
    val bus = PublishSubject.create<String>()
    val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    inline fun<reified Value> send(value: Value) {
        val adapter = moshi.adapter(Value::class.java)
        bus.onNext(adapter.toJson(value))
    }

    inline fun<reified Value> subscribe(crossinline consumer: ((Value) -> Unit)): Disposable? {
        val adapter = moshi.adapter(Value::class.java)
        return bus.subscribe {
            adapter.fromJson(it)?.let(consumer)
        }
    }
}

public class WebSocket(
    private val server: Server,
    okHttpClient: OkHttpClient
) {
    private val ws: WebSocket
    private val queue =  ConcurrentLinkedQueue<String>()

    init {
        ws = okHttpClient.newWebSocket(
            createRequest(),
            createListener()
        );
    }

    fun send() {

    }
    
    private fun createRequest(): Request {
        return Request.Builder()
            .url(server.WS)
            .build()
    }
    
    private fun createListener(): WebSocketListener {
        return object : WebSocketListener() {
            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosing(webSocket, code, reason)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                queue.offer(text)
            }

            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
            }
        }
    } 
}