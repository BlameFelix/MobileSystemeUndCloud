package com.example.mobileundcloudpraktikum

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.widget.LinearLayout
import android.widget.TextView

class LightsensorController2(private val tv2: TextView, private var linLayout: LinearLayout) : SensorEventListener {

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        var lightvalue = event!!.values[0]
        tv2.text = "lx: " + lightvalue.toString()

        if (lightvalue < 12)
            linLayout.setBackgroundResource(R.drawable.vader)
        else
            linLayout.setBackgroundResource(R.drawable.yoda)
    }
}
