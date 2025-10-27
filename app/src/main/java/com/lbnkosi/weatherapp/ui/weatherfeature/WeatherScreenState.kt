package com.lbnkosi.weatherapp.ui.weatherfeature

import com.lbnkosi.weatherapp.domain.models.openweathermaps.OpenWeatherMapResponse

sealed class WeatherScreenState {
    data object Loading: WeatherScreenState()
    data class Success(val weatherData: OpenWeatherMapResponse): WeatherScreenState()
    data class Error(val errorMessage: String): WeatherScreenState()
}