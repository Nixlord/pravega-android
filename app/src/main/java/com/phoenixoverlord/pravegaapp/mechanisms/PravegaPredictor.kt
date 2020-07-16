package com.phoenixoverlord.pravegaapp.mechanisms

import com.phoenixoverlord.pravega.config.PravegaConfig
import com.phoenixoverlord.pravega.extensions.logDebug
import com.phoenixoverlord.pravega.extensions.logError
import com.squareup.moshi.Moshi
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException

data class AudioRecognition(val content: String)

class PravegaPredictor(private val http: OkHttpClient, moshi: Moshi) {

    private val adapter = moshi.adapter(AudioRecognition::class.java)

    fun convert(responseBody: ResponseBody): AudioRecognition? {
        val response = responseBody.string()
        logDebug("UPLOAD", response)
        return adapter.fromJson(response)
    }

    // Retrofit this later
    fun recogniseAudio(audio: File, onResponse: (recognition: AudioRecognition?) -> Unit) {
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file", audio.name, audio.asRequestBody("audio/3gp".toMediaType()))
            .build()

        val request = Request.Builder().url("${PravegaConfig.PROD.AI}/audio-file")
            .post(body)
            .build()

        http.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    logError(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    val responseBody = response.body
                    val recognition = responseBody?.use(::convert)
                    responseBody?.close()
                    onResponse(recognition)
                }
            })
    }
}