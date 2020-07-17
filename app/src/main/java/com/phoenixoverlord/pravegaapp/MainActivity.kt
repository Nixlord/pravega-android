package com.phoenixoverlord.pravegaapp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.phoenixoverlord.pravega.extensions.logDebug
import com.phoenixoverlord.pravega.framework.BaseActivity
import com.phoenixoverlord.pravega.framework.extensions.addFragment
import com.phoenixoverlord.pravega.framework.extensions.finishAndStart
import com.phoenixoverlord.pravega.framework.extensions.replaceFragment
import com.phoenixoverlord.pravega.toast
import com.phoenixoverlord.pravegaapp.assistant.AssistantDialog
import com.phoenixoverlord.pravegaapp.chat.CustomerCare
import com.phoenixoverlord.pravegaapp.mechanisms.*
import com.phoenixoverlord.pravegaapp.mechanisms.WebSocket
import com.phoenixoverlord.pravegaapp.rockPaperScissor.RockPaperScissorActivity
import com.phoenixoverlord.pravegaapp.rockPaperScissor.RockPaperScissorActivity.Companion.WINNER
import com.phoenixoverlord.pravegaapp.screens.HomePage
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_myntra.*
import okhttp3.*
import java.io.File


class MainActivity : BaseActivity() {
    private val http = OkHttpClient()
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private var file: File? = null
    private val ws = WebSocket(http, moshi, "/text")
    private val predictor = PravegaPredictor(http, moshi)

    fun recognise() {
        file = VoiceRecorder.createFile(this)
        VoiceRecorder.start(file!!)
        Handler().postDelayed({
            VoiceRecorder.stop()
            predictor.recogniseAudio(file!!) {
                withPermissions(Manifest.permission.INTERNET)
                    .execute {
                        it?.let{
                            ws.sendMessage(
                                DialogFlowRequest(
                                    Agent.PersonalAssistant,
                                    it.content
                                )
                            )
                        }

                    }
            }
        }, 5000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_myntra)
        addFragment(R.id.fragmentContainer, HomePage())

        EvaVoice.onStart(this)

        ws.onMessage(this) {
            EvaVoice.speak(it.fulfillment)
        }


        DataStore.Command.observe(this, Observer {
            when(it) {
                Action.GAME -> gameScenario()
                Action.EVA -> toast("EVA")
                Action.CC -> customerCare()
            }
        })


        buttonEva.setOnClickListener {
            AssistantDialog().show(supportFragmentManager, "assistant")
        }
    }

    fun customerCare() {
        startActivity(Intent(this, CustomerCare::class.java))
    }

    fun gameScenario() {
        EvaVoice.speak("Let's play then")
        Handler().postDelayed(
            {
                startActivityGetResult(
                    Intent(this, RockPaperScissorActivity::class.java)
                )
                    .addOnSuccessListener {
                        val winner = it.getStringExtra(WINNER)
                        logDebug("WINNER", winner)
                        when (winner) {
                            "Eva wins" -> EvaVoice.speak("Haha, I am hard to defeat!!")
                            "You win" -> EvaVoice.speak("Oops, You are good at this")
                            "DRAW MATCH" -> EvaVoice.speak("Finally, a worthy opponent")
                        }
                    }
            }, 400
        )

    }

//    override fun onStop() {
//        super.onStop()
//        EvaVoice.onStop()
//    }
//
//    override fun onStart() {
//        super.onStart()
//        EvaVoice.onStart(this)
//    }
}
