package com.phoenixoverlord.pravegaapp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.QUEUE_ADD
import android.speech.tts.TextToSpeech.QUEUE_FLUSH
import android.speech.tts.Voice
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.stephenvinouze.core.interfaces.RecognitionCallback
import com.github.stephenvinouze.core.managers.KontinuousRecognitionManager
import com.github.stephenvinouze.core.models.RecognitionStatus
import com.phoenixoverlord.pravega.api.PravegaService
import com.phoenixoverlord.pravega.api.core.friend.Friend
import com.phoenixoverlord.pravega.api.core.friend.onResult
import com.phoenixoverlord.pravega.cloud.firebase.remoteConfig
import com.phoenixoverlord.pravega.config.PravegaConfig
import com.phoenixoverlord.pravega.extensions.logDebug
import com.phoenixoverlord.pravega.extensions.logError
import com.phoenixoverlord.pravega.framework.BaseActivity
import com.phoenixoverlord.pravega.framework.extensions.finishAndStart
import com.phoenixoverlord.pravega.mechanisms.PermissionsModule
import com.phoenixoverlord.pravega.toast
import com.phoenixoverlord.pravega.views.recyclerview.PravegaAdapter
import com.phoenixoverlord.pravegaapp.rockPaperScissor.RockPaperScissorActivity
import com.phoenixoverlord.pravegaapp.rockPaperScissor.RockPaperScissorActivity.Companion.WINNER
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.view.*
import okhttp3.*



class MainActivity : BaseActivity() {

    private val pravega = PravegaService(PravegaConfig.PROD)
    private lateinit var adapter: PravegaAdapter<Friend>
    // See why there are two okhttp, maybe one is from retrofit
    private val http = okhttp3.OkHttpClient()
    private lateinit var notificationHelper: NotificationHelper
    private lateinit var textToSpeech: TextToSpeech
    private var loaded = false

    private val ACTIVATION_KEYWORD = "Hello Kratos"
    private val recognitionManager: KontinuousRecognitionManager by lazy {
        KontinuousRecognitionManager(this, activationKeyword = ACTIVATION_KEYWORD, shouldMute = false, callback = object: RecognitionCallback {
            override fun onBeginningOfSpeech() {
            }

            override fun onBufferReceived(buffer: ByteArray) {
            }

            override fun onEndOfSpeech() {
            }

            override fun onError(errorCode: Int) {
            }

            override fun onEvent(eventType: Int, params: Bundle) {
            }

            override fun onKeywordDetected() {
             toast("DETECTED KEYWORD")
            }

            override fun onPartialResults(results: List<String>) {
            }

            override fun onPrepared(status: RecognitionStatus) {
            }

            override fun onReadyForSpeech(params: Bundle) {
            }

            override fun onResults(results: List<String>, scores: FloatArray?) {
                results.forEach {
                    logDebug("SCORES", it)
                }
            }

            override fun onRmsChanged(rmsdB: Float) {
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationHelper = NotificationHelper(this)
        notificationHelper.setUpNotificationChannels()

//        recognitionManager.createRecognizer()

        textToSpeech = TextToSpeech(this, {
            loaded = true
            textToSpeech.voice = textToSpeech.voices.find { it.name == "en-in-x-ahp-local" }
        }, "com.google.android.tts")

        DataStore.Command.observe(this, Observer { text ->
            textToSpeech.apply {
                speak(text, QUEUE_ADD, null, "TEXT")
                playSilentUtterance(100, QUEUE_ADD, "SILENCE")
            }
        })

        withPermissions(Manifest.permission.RECORD_AUDIO)
            .execute {
//                recognitionManager.startRecognition()
            }

        buttonSensorActivity.setOnClickListener {
            notificationHelper.showNotification(true)

        }

        rockPaperScissorButton.setOnClickListener {
            val intent = Intent(this, RockPaperScissorActivity::class.java)
            startActivityForResult(intent, 1)
        }

        mainFab.setOnClickListener {
            if (loaded) {
                DataStore.Command.value = "Hi. I'm Eeeevaah, your Myntra assistant. "
                DataStore.Command.value = "How may I help you?"
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            val winner = data.getStringExtra(WINNER)
            Toast.makeText(this, winner, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
//        recognitionManager.startRecognition()
    }

    override fun onPause() {
        super.onPause()
//        recognitionManager.stopRecognition()
    }

    override fun onDestroy() {
        super.onDestroy()
//        recognitionManager.destroyRecognizer()
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
}
