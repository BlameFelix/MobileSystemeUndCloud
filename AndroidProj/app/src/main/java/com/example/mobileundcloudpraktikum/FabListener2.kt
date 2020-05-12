package com.example.mobileundcloudpraktikum

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class FabListener2(
    private val tv1: TextView,
    private val activity: Activity,
    private val linearLayout: LinearLayout
) : View.OnClickListener {

    private val lightControl = LightsensorController2(tv1, linearLayout)
    private var lightSensorActive: Boolean = false
    private var sensorManager: SensorManager = activity.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    override fun onClick(v: View?) {

        lightSensorActive = when (lightSensorActive) {
            false -> {
                sensorManager.registerListener(lightControl, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL)
                true
            }
            true -> {
                sensorManager.unregisterListener(lightControl)
                false
            }
        }
    }
}
