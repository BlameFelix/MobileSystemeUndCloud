package com.example.mobileundcloudpraktikum

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.util.Log
import android.widget.TextView
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import java.util.*

class LightsensorController(private val tv2: TextView) : SensorEventListener {
    private val TAG = "MyFirebaseMsgService"
    var lastLightSensorSave = System.currentTimeMillis()

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        tv2.text = "lx: " + event!!.values[0].toString()

        if (System.currentTimeMillis() - lastLightSensorSave > 6000) {
            val random = Random()
            val fm = FirebaseMessaging.getInstance()
            val projectID = "1047518041749"
            Log.d(TAG, "Try To send Message at Server: $projectID")
            lastLightSensorSave = System.currentTimeMillis()
            fm.send(
                RemoteMessage.Builder("$projectID@gcm.googleapis.com")
                    .setMessageId("" + random.nextInt())
                    .addData("lux", event!!.values[0].toString())
                    .addData("timestamp", "" + lastLightSensorSave)
                    .addData("action", "LIGHT")
                    .build())
        }
    }
}
