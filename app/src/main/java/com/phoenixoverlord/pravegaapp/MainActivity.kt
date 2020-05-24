package com.phoenixoverlord.pravegaapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.phoenixoverlord.pravega.config.PravegaConfig
import com.phoenixoverlord.pravega.api.PravegaService
import com.phoenixoverlord.pravega.api.core.friend.Friend
import com.phoenixoverlord.pravega.api.core.friend.onResult
import com.phoenixoverlord.pravega.cloud.firebase.remoteConfig
import com.phoenixoverlord.pravega.extensions.logDebug
import com.phoenixoverlord.pravega.extensions.logError
import com.phoenixoverlord.pravega.toast
import com.phoenixoverlord.pravegaapp.views.PravegaRecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.view.*
import java.io.IOException
import javax.websocket.*
import kotlin.Error

class MainActivity : AppCompatActivity() {

    private val pravega = PravegaService(PravegaConfig.DEV)
    private val friendList = arrayListOf<Friend>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView(friendList)
        testingWebsockets()
        testRemoteConfig()

        mainFab.setOnClickListener {
            toast(PravegaConfig.DEV.toString())
            pravega.friendAPI.getAllFriends()
                .onResult { values, err ->
                    if (err != null) {
                        logError(err)
                    }
                    else if (values == null) {
                        logError(Error("NULL RESPONSE"))
                    }
                    else {
                        values.forEach { (idx, friend) ->
                            logDebug("$idx: $friend")
                            recyclerview.addModel(friend)
                        }
                    }
                }
        }
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

    private fun setupRecyclerView(friends: ArrayList<Friend>) {
        val binder: ((View, Friend) -> Unit) = { view, friend ->
            view.apply {
                textView.text = friend.name
                textView2.text = "Age: ${friend.age}"
                button.setOnClickListener {
                    toast(friend.toString())
                }
            }
        }
        recyclerview.attach(
            friends,
            R.layout.list_item,
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false),
            binder
        )
    }

    private fun testingWebsockets() {
        val ws = object : Endpoint() {
            override fun onOpen(session: Session?, config: EndpointConfig?) {
                val remote = session!!.basicRemote
                session.addMessageHandler(object : MessageHandler.Whole<String?> {
                    override fun onMessage(text: String?) {
                        try {
                            remote.sendText(text)
                        } catch (ioe: IOException) {
                            // handle send failure here
                        }
                    }
                })
            }

            override fun onClose(session: Session?, closeReason: CloseReason?) {
                super.onClose(session, closeReason)
            }

            override fun onError(session: Session?, thr: Throwable?) {
                super.onError(session, thr)
            }
        }
    }

}
