package com.phoenixoverlord.pravegaapp.chat

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.phoenixoverlord.pravega.framework.BaseActivity
import com.phoenixoverlord.pravega.views.extensions.onTextChange
import com.phoenixoverlord.pravegaapp.R
import com.phoenixoverlord.pravegaapp.mechanisms.Agent
import com.phoenixoverlord.pravegaapp.mechanisms.DialogFlowRequest
import com.phoenixoverlord.pravegaapp.mechanisms.EvaVoice
import com.phoenixoverlord.pravegaapp.mechanisms.WebSocket
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.fragment_cc.*
import kotlinx.android.synthetic.main.fragment_cc.view.*
import kotlinx.android.synthetic.main.fragment_cc.view.recyclerCustomerCare
import okhttp3.OkHttpClient
import java.io.File

class CustomerCare: BaseActivity() {

    private val http = OkHttpClient()
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val ws = WebSocket(http, moshi, "/text")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_cc)
        EvaVoice.onStart(this)

        recyclerCustomerCare.adapter = ChatAdapter(arrayListOf(
            ChatItem("Hi, how may I help you?", ChatItem.TYPE_OPERATOR_MESSAGE)
        ))

        Handler().postDelayed({
            EvaVoice.speak("Hi, how may I help you?")
        }, 1000)

        recyclerCustomerCare.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, true)

        ws.onMessage(this) {
            runOnUiThread {
                if (it.fulfillment != "") {
                    (recyclerCustomerCare.adapter as ChatAdapter).addChatItem(
                        ChatItem(
                            it.fulfillment,
                            ChatItem.TYPE_OPERATOR_MESSAGE
                        )
                    )
                }
                recyclerCustomerCare.scrollToPosition(0)
                EvaVoice.speak(it.fulfillment)
            }
        }

        buttonSend.setOnClickListener {
            queryInput.text?.apply {
                val content = toString()
                (recyclerCustomerCare.adapter as ChatAdapter).addChatItem(ChatItem(content, ChatItem.TYPE_USER_MESSAGE))
                recyclerCustomerCare.scrollToPosition(0)
                queryInput.text?.clear()
                ws.sendMessage(DialogFlowRequest(Agent.CustomerService, content))
            }
        }
    }

    override fun onPause() {
        super.onPause()
        EvaVoice.onStop()
    }

    override fun onResume() {
        super.onResume()
        EvaVoice.onStart(this)
    }
}