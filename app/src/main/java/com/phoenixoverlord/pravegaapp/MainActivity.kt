package com.phoenixoverlord.pravegaapp

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.annotation.DrawableRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.phoenixoverlord.pravega.extensions.loadImage
import com.phoenixoverlord.pravega.extensions.logDebug
import com.phoenixoverlord.pravega.framework.BaseActivity
import com.phoenixoverlord.pravega.toast
import com.phoenixoverlord.pravegaapp.assistant.AssistantDialog
import com.phoenixoverlord.pravegaapp.chat.CustomerCare
import com.phoenixoverlord.pravegaapp.mechanisms.*
import com.phoenixoverlord.pravegaapp.mechanisms.WebSocket
import com.phoenixoverlord.pravegaapp.rockPaperScissor.RockPaperScissorActivity
import com.phoenixoverlord.pravegaapp.rockPaperScissor.RockPaperScissorActivity.Companion.WINNER
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
    private val inProgress = MutableLiveData(false)

    fun recognise() {
        if (! inProgress.value!! ) {
            withPermissions(
                Manifest.permission.INTERNET,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).execute {
                file = VoiceRecorder.createFile(this)
                VoiceRecorder.start(file!!)
                inProgress.postValue(true)
                Handler().postDelayed({
                    VoiceRecorder.stop()
                    predictor.recogniseAudio(file!!) {
                        inProgress.postValue(false)
                        it?.let {
                            ws.sendMessage(
                                DialogFlowRequest(
                                    Agent.PersonalAssistant,
                                    it.content
                                )
                            )
                        }
                    }
                }, 8000)
            }
        } else {
            toast("Ongoing record")
        }
    }



    fun getImage(result: Result) = when(result) {
        Result("black", "men", "Shirts") -> R.drawable.black_shirt_list_page
        Result("blue", "women", "Jeans") -> R.drawable.blue_jeans_list_page
        Result("black", "men", "Shoes") -> R.drawable.black_formal_shoes
        Result("red", "women", "Ethnic Dress") -> R.drawable.red_ethnic_dress
        else -> null
    }

    fun loadImage(@DrawableRes id: Int?) {
        id?.apply {
            loadImage(homeImageView, this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myntra)

        loadImage(R.drawable.home_page)

        EvaVoice.onStart(this)

        ws.onMessage(this) {

            if (it.text.equals("GO BACK", ignoreCase = true)) {
                Handler().postDelayed({
                    EvaVoice.speak("Going back to homepage")
                    loadImage(R.drawable.home_page)
                }, 200)
                return@onMessage
            }

            if (it.text.equals("I NEED HELP", ignoreCase = true)) {
                EvaVoice.speak("I'll answer all your queries.")
                Handler().postDelayed({
                    customerCare()
                }, 2000)
                assistant?.dismiss()
                return@onMessage
            }

            if (it.text.equals("LET'S PLAY", ignoreCase = true)) {
                gameScenario()
                assistant?.dismiss()
                return@onMessage
            }

            if (it.fulfillment != "")
                EvaVoice.speak(it.fulfillment)
            else
                EvaVoice.speak("Sorry, please try again")


            it.items?.let {
                runOnUiThread {
                    it.apply {
                        toast("Colour: $colour, Gender: $gender, Item: $item")
                        if (isValid()) {
                            assistant?.dismiss()
                            Handler().postDelayed({
                                loadImage(getImage(this))
                            }, 200)
                        }
                    }
                }
            }
        }


        DataStore.Command.observe(this, Observer {
            when(it) {
                Action.GAME -> gameScenario()
                Action.EVA -> talkWithEva()
                Action.CC -> customerCare()
            }
        })


        buttonEva.setOnClickListener {
            assistant = AssistantDialog()
            assistant?.show(supportFragmentManager, "assistant")
        }
    }

    private var assistant: AssistantDialog? = null

    fun talkWithEva() {
        recognise()
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
                        Handler().postDelayed({
                            when (winner) {
                                "Eva wins" -> EvaVoice.speak("Haha, I am hard to defeat!!")
                                "You win" -> EvaVoice.speak("Oops, You are good at this")
                                "DRAW MATCH" -> EvaVoice.speak("Finally, a worthy opponent")
                            }
                        }, 1000)

                    }
            }, 1000
        )
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
