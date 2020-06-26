package com.phoenixoverlord.pravegaapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.phoenixoverlord.pravega.extensions.logDebug
import com.phoenixoverlord.pravega.toast
import kotlinx.android.synthetic.main.activity_sensors.*

// Bad way, done for PoC. Use Rx and some other way, interface is stupid
class SensorActivity : AppCompatActivity(), SensorEventListener {
    lateinit var sensorManager: SensorManager
    var accelerometer: Sensor? = null
    var gyroscope: Sensor? = null
    var measuring = false

    val sensorName = mapOf(
        Sensor.TYPE_LINEAR_ACCELERATION to "linearAcceleration",
        Sensor.TYPE_GYROSCOPE to "gyroscope"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensors)

        linearAccelerationView.text = "1.2"
        gyroscopeView.text = "1.3"

        buttonStartMeasuring.setOnClickListener {
            toast("Starting sensor reading")
            startMeasuring()
        }
        buttonStop.setOnClickListener { stopMeasuring() }

        initialiseSensors()
    }

    private fun initialiseSensors() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {
        val name = event?.sensor?.type?.let { sensorName[it] } ?: "Unknown"
        logDebug("$name, ${event?.values?.joinToString { "$it" }}")
    }

    // Good fit for Rx
    private fun startMeasuring() {
        measuring = true
        listOf(accelerometer, gyroscope)
            .mapNotNull {
                sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
            }
    }

    private fun stopMeasuring() {
        sensorManager.unregisterListener(this, accelerometer)
        sensorManager.unregisterListener(this, gyroscope)
    }

    override fun onResume() {
        super.onResume()
        if (measuring) {
            startMeasuring()
        }
    }

    override fun onPause() {
        super.onPause()
        stopMeasuring()
    }
}