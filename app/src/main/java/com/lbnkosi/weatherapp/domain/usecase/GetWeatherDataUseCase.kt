package com.lbnkosi.weatherapp.domain.usecase

import com.lbnkosi.weatherapp.domain.repository.WeatherDataRepo
import javax.inject.Inject

class GetWeatherDataUseCase @Inject constructor(private val repo: WeatherDataRepo) {
    suspend fun getWeatherData(lat: Double, lon: Double) = repo.getWeatherData(lat, lon)
}