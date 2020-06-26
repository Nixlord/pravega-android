package com.phoenixoverlord.pravegaapp

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.phoenixoverlord.pravega.api.PravegaService
import com.phoenixoverlord.pravega.api.core.friend.Friend
import com.phoenixoverlord.pravega.api.core.friend.onResult
import com.phoenixoverlord.pravega.cloud.firebase.remoteConfig
import com.phoenixoverlord.pravega.config.PravegaConfig
import com.phoenixoverlord.pravega.extensions.logDebug
import com.phoenixoverlord.pravega.extensions.logError
import com.phoenixoverlord.pravega.framework.extensions.finishAndStart
import com.phoenixoverlord.pravega.toast
import com.phoenixoverlord.pravega.views.recyclerview.PravegaAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.view.*
import okhttp3.*


class MainActivity : AppCompatActivity() {

    private val pravega = PravegaService(PravegaConfig.DEV)
    private lateinit var adapter: PravegaAdapter<Friend>
    private val model: MainViewModel by viewModels()
    // See why there are two okhttp, maybe one is from retrofit
    private val http = okhttp3.OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSensorActivity.setOnClickListener {
            finishAndStart(SensorActivity::class.java)
        }

        setupRecyclerView()
        testRemoteConfig()
        testingOkHttpWS()

        mainFab.setOnClickListener {
            toast("Calling ${PravegaConfig.DEV}")
//            ws.sendClose()
            pravega.friendAPI.getAllFriends()
                .onResult { friends, err ->
                    when {
                        err != null -> logError(err)
                        else -> loadFriends(friends.values)
                    }
                }
        }
    }

    private fun testingOkHttpWS() {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter = moshi.adapter(Friend::class.java)
        val request = Request.Builder().url(PravegaConfig.DEV.WS).build()
        http.newWebSocket(request, object: WebSocketListener() {
            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosing(webSocket, code, reason)
                logDebug(reason)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                logError(t)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                logDebug("OKHTTPSPRING", text)
                val friend = adapter.fromJson(text)
                logDebug(friend.toString())
            }

            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                webSocket.send(adapter.toJson(Friend("Shibasis", 24)))
            }
        })
    }

    private fun loadFriends(friends: Collection<Friend>) {
        toast("Clearing")
        friends.forEach{ logDebug("FriendAPI", it.toString()) }
        adapter.clear()
        adapter.addModels(friends)
    }

    private fun testRemoteConfig() {
        remoteConfig(
            mapOf(
                "predictor" to "https://pravegapredictor.herokuapp.com/",
                "backend" to "https://pravegacore.herokuapp.com/"
            )
        ).thenApply { config ->
            config.entries.forEach {
                logDebug("${it.key}:${it.value}")
            }
        }
    }

    private fun setupRecyclerView() {
        val binder: ((View, Friend) -> Unit) = { view, friend ->
            view.apply {
                textView.text = friend.name
                textView2.text = "Age: ${friend.age}"
                button.setOnClickListener {
                    toast(friend.toString())
                }
            }
        }
        adapter = recyclerview.attach(
            R.layout.list_item,
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false),
            binder
        )
    }

    // Ex: recyclerView.attach(context, Attachment<Friend>)
    // use extension function, recyclerView.attach(Attachment<Friend>)
//    interface Attachment<Model> {
//        fun getListItemLayoutID()
//        fun getLayoutManager(context: Context)
//        fun onViewBound(activity: AppCompatActivity, view: View, model: Model)
//    }
//
//    class FriendView: Attachment<Friend> {
//        override fun getListItemLayoutID() {
//            TODO("Not yet implemented")
//        }
//
//        override fun getLayoutManager(context: Context) {
//            TODO("Not yet implemented")
//        }
//
//        override fun onViewBound(activity: AppCompatActivity, view: View, model: Friend) {
//            view.apply {
//                textView.text = model.name
//                textView2.text = "Age: ${model.age}"
//                button.setOnClickListener {
//                    toast(model.toString())
//                }
//            }
//        }
//    }

}
