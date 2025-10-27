package com.lbnkosi.weatherapp.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle

object Utility {

    fun Activity.launchActivity(packageName: String, className: String, flags: Int = -1, bundle: Bundle? = null) {
        val intent = Intent(Intent.ACTION_VIEW).setClassName(packageName, className)
        if (flags != -1) {
            intent.flags = flags
        }
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    fun Double.kelvinToCelsius(): Int {
        val celsius = this - 273.15
        return celsius.toInt()
    }

    fun uvIndexLabel(uvi: Double): String = when {
        uvi < 3 -> "Low"
        uvi < 6 -> "Moderate"
        uvi < 8 -> "High"
        uvi < 11 -> "Very high"
        else -> "Extreme"
    }

    fun getWeatherIconUrl(iconCode: String?): String {
        if (iconCode.isNullOrBlank()) return ""
        return "https://openweathermap.org/img/wn/${iconCode}@4x.png"
    }
}