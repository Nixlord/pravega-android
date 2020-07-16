package com.phoenixoverlord.pravegaapp.mechanisms

import android.media.MediaPlayer
import android.media.MediaRecorder
import android.util.Log
import com.phoenixoverlord.pravega.framework.BaseActivity
import java.io.File
import java.io.IOException
import java.util.*

object AudioPlayer {
    private var mediaPlayer: MediaPlayer? = null

    fun start(file: File) {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(file.path)
            try {
                prepare()
            } catch (e: IOException) {
                Log.e("VoiceRecorder", "prepare() failed")
            }
            start()
        }
    }

    fun stop() {
        mediaPlayer?.apply {
            stop()
            release()
        }
        mediaPlayer = null
    }
}