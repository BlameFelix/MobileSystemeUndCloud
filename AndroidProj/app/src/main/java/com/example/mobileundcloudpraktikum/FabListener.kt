package com.example.mobileundcloudpraktikum


import android.app.Activity
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.view.View
import android.widget.TextView
import com.google.android.gms.location.LocationServices


class FabListener(tv: TextView, tv3: TextView, activity: Activity) : View.OnClickListener {
    val tv: TextView = tv
    val tv3: TextView = tv3
    val activity: Activity = activity
    val accControl = AccelerometerController(tv3)
    var sensorActive: Boolean = false
    var sensorManager: SensorManager = activity.getSystemService(SENSOR_SERVICE) as SensorManager

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
                tv.text = t
            }
            else {
                tv.text = "Unexpected Error!"
            }
        }
    }

    fun readPhotometer() {

    }

    fun readAcc() {
        if (!sensorActive) {
            sensorManager.registerListener(accControl, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
            sensorActive = true
        }
        else {
            sensorManager.unregisterListener(accControl)
            sensorActive = false
        }
    }

}