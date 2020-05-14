package com.example.mobileundcloudpraktikum

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.util.Log
import android.widget.TextView
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import java.util.*

class AccelerometerController(private val tv3: TextView) : SensorEventListener {
    var lastAccSensorSave = System.currentTimeMillis()
    private val TAG = "MyFirebaseMsgService"

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        tv3.text = "x: ${event!!.values[0]}\n" + "y: ${event!!.values[1]}\n" + "z: ${event!!.values[2]}"

        if (System.currentTimeMillis() - lastAccSensorSave > 6000) {
            val random = Random()
            val fm = FirebaseMessaging.getInstance()
            val projectID = "1047518041749"
            Log.d(TAG, "Try To send Message at Server: $projectID")
            lastAccSensorSave = System.currentTimeMillis()
            fm.send(
                RemoteMessage.Builder("$projectID@gcm.googleapis.com")
                    .setMessageId("" + random.nextInt())
                    .addData("x", event!!.values[0].toString())
                    .addData("y", event!!.values[1].toString())
                    .addData("z", event!!.values[2].toString())
                    .addData("timestamp", "" + lastAccSensorSave)
                    .addData("action", "ACCELERATOR")
                    .build())
        }
    }
}
