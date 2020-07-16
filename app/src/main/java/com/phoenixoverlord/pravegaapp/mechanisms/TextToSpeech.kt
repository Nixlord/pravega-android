package com.phoenixoverlord.pravegaapp.mechanisms

import android.speech.tts.TextToSpeech
import com.phoenixoverlord.pravega.framework.BaseActivity

object EvaVoice {
    var textToSpeech: TextToSpeech? = null

    fun onStop() {
        textToSpeech?.shutdown()
        textToSpeech = null
    }

    fun onStart(activity: BaseActivity) {
        textToSpeech = TextToSpeech(activity, {
            textToSpeech?.voice = textToSpeech?.voices?.find { it.name == "en-in-x-ahp-local" }
        }, "com.google.android.tts")
    }

    fun speak(text: String) {
        textToSpeech?.apply {
            speak(text, TextToSpeech.QUEUE_ADD, null, "TEXT")
            playSilentUtterance(200, TextToSpeech.QUEUE_ADD, "SILENCE")
        }
    }
}