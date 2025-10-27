package com.lbnkosi.weatherapp.domain.repository

import com.lbnkosi.weatherapp.domain.models.openweathermaps.OpenWeatherMapResponse
import kotlinx.coroutines.flow.Flow

interface WeatherDataRepo {
    suspend fun getWeatherData(lat: Double, lon: Double): Flow<Result<OpenWeatherMapResponse?>>
}