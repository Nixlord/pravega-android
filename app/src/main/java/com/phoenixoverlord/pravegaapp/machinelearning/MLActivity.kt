package com.phoenixoverlord.pravegaapp.machinelearning

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.phoenixoverlord.pravega.extensions.logDebug
import com.phoenixoverlord.pravega.extensions.logError
import com.phoenixoverlord.pravega.framework.BaseActivity
import com.phoenixoverlord.pravega.toast
import com.phoenixoverlord.pravegaapp.R
import kotlinx.android.synthetic.main.activity_m_l.*
import java.io.File
import java.lang.Exception
import java.nio.ByteBuffer
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

typealias LumaListener = (luma: Double) -> Unit

class MLActivity : BaseActivity() {
    private var imageCapture: ImageCapture? = null
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    private class LabellingAnalyser: ImageAnalysis.Analyzer {
        @SuppressLint("UnsafeExperimentalUsageError")
        override fun analyze(image: ImageProxy) {
            image.image?.apply {
                val inputImage =
                    InputImage.fromMediaImage(this, image.imageInfo.rotationDegrees)

                val options = ImageLabelerOptions.Builder().setConfidenceThreshold(0.7f).build()
                val client = ImageLabeling.getClient(options)

                client.process(inputImage)
                    .addOnSuccessListener {
                        it.forEach {
                            logDebug("IMAGE_CONTENTS", "${it.text}, ${it.confidence}")
                        }
                        image.close()
                    }
                    .addOnFailureListener(::logError)
            }
        }
    }

    private class LuminosityAnalyser(private val listener: LumaListener): ImageAnalysis.Analyzer {
        private fun ByteBuffer.toByteArray(): ByteArray {
            rewind()
            val data = ByteArray(remaining())
            get(data)
            return data
        }

        override fun analyze(image: ImageProxy) {
            val buffer = image.planes[0].buffer
            val data = buffer.toByteArray()
            val pixels = data.map { it.toInt() and 0xFF }
            val luma = pixels.average()
            listener(luma)
            image.close()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_l)
        cameraExecutor = Executors.newSingleThreadExecutor()
        outputDirectory = getOutputDirectory()
        withPermissions(Manifest.permission.CAMERA)
            .execute {
                startCamera()
            }

        camera_capture_button.setOnClickListener {
            takePhoto()
        }
    }

    private fun takePhoto() {
        imageCapture?.apply {

            val photoFile = File(
                outputDirectory,
                SimpleDateFormat(FILENAME_FORMAT, Locale.UK)
                    .format(System.currentTimeMillis()) + ".jpg"
            )

            val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

            takePicture(outputOptions, ContextCompat.getMainExecutor(this@MLActivity), object: ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    logError(exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    toast("Photo capture succeeded: $savedUri")
                    logDebug("Photo capture succeeded: $savedUri")
                }
            })
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(viewFinder.createSurfaceProvider())
                }

            imageCapture = ImageCapture.Builder().build()

            val imageAnalyser = ImageAnalysis.Builder()
                .build()
                .also {
//                    it.setAnalyzer(cameraExecutor, LuminosityAnalyser {
//                        logDebug(it.toString())
//                    })
                    it.setAnalyzer(cameraExecutor, LabellingAnalyser())
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture, imageAnalyser)
            } catch (e: Exception) {
                logError(e)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() } }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    companion object {
        private const val TAG = "CAMERA_BASIC"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    }

}
