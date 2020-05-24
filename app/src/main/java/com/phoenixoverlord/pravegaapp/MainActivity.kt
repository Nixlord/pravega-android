package com.phoenixoverlord.pravegaapp

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
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
import com.phoenixoverlord.pravegaapp.views.recyclerview.PravegaAdapter
import com.phoenixoverlord.pravegaapp.views.recyclerview.PravegaRecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.view.*
import java.io.IOException
import javax.websocket.*

class MainActivity : AppCompatActivity() {

    private val pravega = PravegaService(PravegaConfig.DEV)
    private lateinit var adapter: PravegaAdapter<Friend>
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        testingWebsockets()
        testRemoteConfig()

        mainFab.setOnClickListener {
            toast("Calling ${PravegaConfig.DEV}")
            pravega.friendAPI.getAllFriends()
                .onResult { friends, err ->
                    when {
                        err != null -> logError(err)
                        else -> loadFriends(friends.values)
                    }
                }
        }
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
