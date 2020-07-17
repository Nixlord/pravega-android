package com.phoenixoverlord.pravegaapp.mechanisms

import android.media.MediaRecorder
import android.util.Log
import com.phoenixoverlord.pravega.framework.BaseActivity
import java.io.File
import java.io.IOException
import java.util.*

object VoiceRecorder {
    private var mediaRecorder: MediaRecorder? = null


    fun createFile(activity: BaseActivity): File {
        return File.createTempFile("sound_${Date().time}.3gp", null, activity.cacheDir)
    }


    fun start(file: File) {
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(file)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setAudioSamplingRate(32000)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e("VoiceRecorder", "prepare() failed")
            }

            start()
        }
    }

    fun stop() {
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
    }
}