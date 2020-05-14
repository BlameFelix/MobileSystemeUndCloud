package com.example.mobileundcloudpraktikum

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.widget.TextView

class AccelerometerController(private val tv3: TextView) : SensorEventListener {

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        tv3.text = "x: ${event!!.values[0]}\n" + "y: ${event!!.values[1]}\n" + "z: ${event!!.values[2]}"
    }
}