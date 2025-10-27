package com.lbnkosi.weatherapp.ui.weatherfeature

sealed class WeatherScreenEvent {
    data class GetWeatherScreenData(val lat: Double, val long: Double): WeatherScreenEvent()
}