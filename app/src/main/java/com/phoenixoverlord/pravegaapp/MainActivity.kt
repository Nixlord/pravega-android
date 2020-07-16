package com.phoenixoverlord.pravegaapp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.QUEUE_ADD
import androidx.lifecycle.Observer
import com.phoenixoverlord.pravega.api.PravegaService
import com.phoenixoverlord.pravega.api.core.friend.Friend
import com.phoenixoverlord.pravega.config.PravegaConfig
import com.phoenixoverlord.pravega.extensions.logError
import com.phoenixoverlord.pravega.framework.BaseActivity
import com.phoenixoverlord.pravega.toast
import com.phoenixoverlord.pravega.views.recyclerview.PravegaAdapter
import com.phoenixoverlord.pravegaapp.mechanisms.*
import com.phoenixoverlord.pravegaapp.mechanisms.WebSocket
import com.phoenixoverlord.pravegaapp.rockPaperScissor.RockPaperScissorActivity
import com.phoenixoverlord.pravegaapp.rockPaperScissor.RockPaperScissorActivity.Companion.WINNER
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.view.*
import okhttp3.*
import okio.ByteString
import java.io.File
import java.io.IOException


class MainActivity : BaseActivity() {
    private val pravega = PravegaService(PravegaConfig.PROD)
    private lateinit var adapter: PravegaAdapter<Friend>
    // See why there are two okhttp, maybe one is from retrofit
    private val http = OkHttpClient()
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private lateinit var notificationHelper: NotificationHelper
    private var file: File? = null
    private val ws = WebSocket(http, moshi, "/text")
    private val predictor = PravegaPredictor(http, moshi)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ws.onMessage(this) {
            EvaVoice.speak(it.fullfillment)
        }

        notificationHelper = NotificationHelper(this)
        notificationHelper.setUpNotificationChannels()

        buttonSensorActivity.setOnClickListener {
//            notificationHelper.showNotification(true)
            file = VoiceRecorder.createFile(this)
            VoiceRecorder.start(file!!)
            Handler().postDelayed({
                VoiceRecorder.stop()
                predictor.getDialogFlowResponse(file!!) {
                    withPermissions(Manifest.permission.INTERNET)
                        .execute {
                            it?.let{ ws.sendMessage(it.content) }
                        }
                }
            }, 7000)

        }

        rockPaperScissorButton.setOnClickListener {
            val intent = Intent(this, RockPaperScissorActivity::class.java)
            startActivityForResult(intent, 1)
        }

        mainFab.setOnClickListener {
            EvaVoice.speak("Hello Shibasis, how may I help you?")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            val winner = data.getStringExtra(WINNER)
            winner?.run { toast(this) }
        }
    }

    override fun onStop() {
        super.onStop()
        EvaVoice.onStop()
    }

    override fun onStart() {
        super.onStart()
        EvaVoice.onStart(this)
    }
}
