package com.example.mobileundcloudpraktikum

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.widget.TextView

class LightsensorController (val tv2: TextView) : SensorEventListener {

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        tv2.text = "lx: " + event!!.values[0].toString()
    }


}