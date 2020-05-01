package com.example.mobileundcloudpraktikum


import android.app.Activity
import android.location.Location
import android.view.View
import android.widget.TextView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class FabListener(tv: TextView, activity: Activity) : View.OnClickListener {
    val tv: TextView = tv
    val activity: Activity = activity

    override fun onClick(v: View?) {
        readGPS()
        readPhotometer()
        readAcc()
    }

    // Orientiert an: https://www.androdocs.com/kotlin/getting-current-location-latitude-longitude-in-android-using-kotlin.html
    fun readGPS() {
        lateinit var fusedLocationClient: FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
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

    }

}