package com.phoenixoverlord.pravegaapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.phoenixoverlord.pravega.cloud.firebase.extensions.Firebase
import com.phoenixoverlord.pravega.extensions.logDebug
import com.phoenixoverlord.pravega.extensions.logError
import com.phoenixoverlord.pravega.ml.Transform
import com.phoenixoverlord.pravega.toast
import kotlinx.android.synthetic.main.activity_sensors.*
import java.util.*

data class Reading(val x: Float, val y: Float, val z: Float) {
    constructor(values: FloatArray) : this(values[0], values[1], values[2]) {}
    operator fun plus(reading: Reading): Reading {
        return Reading(x + reading.x, y + reading.y, z + reading.z)
    }
    operator fun div(n: Int): Reading {
        return Reading(x / n, y / n, z / n)
    }

    override fun toString(): String {
        return "x: $x, y: $y, z: $z"
    }
}

// Bad way, done for PoC. Use Rx and some other way, interface is stupid
class SensorActivity : AppCompatActivity(), SensorEventListener {
    val sensorDB = Firebase.realtime.child("sensorRoot")

    lateinit var sensorManager: SensorManager
    var accelerometer: Sensor? = null
    var gyroscope: Sensor? = null
    var measuring = false

    val sensorName = mapOf(
        Sensor.TYPE_LINEAR_ACCELERATION to "linearAcceleration",
        Sensor.TYPE_GYROSCOPE to "gyroscope"
    )

    val accValues = arrayListOf<Reading>()
    val gyroValues = arrayListOf<Reading>()

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
        logDebug("SUM =" + Transform.arraySum(floatArrayOf(1.0f, 2.0f, 3.0f, 4.0f, 5.0f)))
    }

    private fun initialiseSensors() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {
//        Use this
//        https://developer.android.com/reference/java/util/concurrent/ThreadPoolExecutor

        val type = event?.sensor?.type
        val name = type?.let { sensorName[it] } ?: "Unknown"
        logDebug("$name, ${event?.values?.joinToString { "$it" }}")
        event?.values?.let {
            if (type == Sensor.TYPE_LINEAR_ACCELERATION) {
                accValues.add(Reading(it))
                if (accValues.size == 50) {
                    val acc = accValues.reduce { acc, reading -> acc + reading } / 50
                    accValues.clear()
                    linearAccelerationView.text = "$acc"
                    sensorDB.child(name)
                        .child(Date().time.toString())
                        .setValue(acc)
                        .addOnFailureListener(::logError)
                }
            }
            else if (type == Sensor.TYPE_GYROSCOPE) {
                gyroValues.add(Reading(it))
                if (gyroValues.size == 50) {
                    val acc = gyroValues.reduce { acc, reading -> acc + reading } / 50
                    gyroValues.clear()
                    gyroscopeView.text = "$acc"
                    sensorDB.child(name)
                        .child(Date().time.toString())
                        .setValue(acc)
                        .addOnFailureListener(::logError)
                }
            }
        }

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

    override fun onStart() {
        super.onStart()
        if (measuring) {
            startMeasuring()
        }
    }

    override fun onStop() {
        super.onStop()
        stopMeasuring()
    }
}