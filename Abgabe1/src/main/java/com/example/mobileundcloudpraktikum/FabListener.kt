package com.example.mobileundcloudpraktikum


import android.app.Activity
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorManager
import android.location.Location
import android.view.View
import android.widget.TextView
import com.google.android.gms.location.LocationServices


class FabListener(private val tv1: TextView, tv2: TextView, tv3: TextView, private val activity: Activity) : View.OnClickListener {

    private val accControl = AccelerometerController(tv3)
    private val lightControl = LightsensorController(tv2)
    private var accSensorActive: Boolean = false
    private var lightSensorActive: Boolean = false
    private var sensorManager: SensorManager = activity.getSystemService(SENSOR_SERVICE) as SensorManager


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