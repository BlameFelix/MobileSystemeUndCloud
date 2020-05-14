package com.example.mobileundcloudpraktikum

import android.app.Activity
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorManager
import android.location.Location
import android.os.SystemClock.sleep
import android.util.Log
import android.view.View
import android.widget.TextView
import com.google.android.gms.location.LocationServices
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import java.util.*

class FabListener(
    private val tv1: TextView,
    tv2: TextView,
    tv3: TextView,
    private val activity: Activity
) : View.OnClickListener {

    private val accControl = AccelerometerController(tv3)
    private val lightControl = LightsensorController(tv2)
    private var accSensorActive: Boolean = false
    private var lightSensorActive: Boolean = false
    private var sensorManager: SensorManager = activity.getSystemService(SENSOR_SERVICE) as SensorManager
    private val TAG = "MyFirebaseMsgService"
    var lastGPSSensorSave = System.currentTimeMillis()

    override fun onClick(v: View?) {
        readGPS()
        readPhotometer()
        readAcc()
    }

    // Orientiert an: https://www.androdocs.com/kotlin/getting-current-location-latitude-longitude-in-android-using-kotlin.html
    fun readGPS() {
        var fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                var t: String = "lat: " + location.latitude.toString() + "\n"
                t += "long: " + location.longitude.toString()
                tv1.text = t


                if (System.currentTimeMillis() - lastGPSSensorSave > 6000) {
                    val random = Random()
                    val fm = FirebaseMessaging.getInstance()
                    val projectID = "1047518041749"
                    Log.d(TAG, "Try To send Message at Server: $projectID")
                    lastGPSSensorSave = System.currentTimeMillis()
                    fm.send(
                        RemoteMessage.Builder("$projectID@gcm.googleapis.com")
                            .setMessageId("" + random.nextInt())
                            .addData("latitude", location.latitude.toString())
                            .addData("longitude", location.longitude.toString())
                            .addData("timestamp", "" + lastGPSSensorSave)
                            .addData("action", "GPS")
                            .build())
                }
            }
            else {
                tv1.text = "Unexpected Error!"
            }
        }
    }

    fun readPhotometer() {
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

    fun readAcc() {
        accSensorActive = when (accSensorActive) {
            false -> {
                sensorManager.registerListener(accControl, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
                true
            }
            true -> {
                sensorManager.unregisterListener(accControl)
                false
            }
        }
    }
}
