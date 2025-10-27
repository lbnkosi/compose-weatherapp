package com.lbnkosi.weatherapp.data.repository

import com.lbnkosi.weatherapp.data.datasource.WeatherDataSource
import com.lbnkosi.weatherapp.domain.models.openweathermaps.OpenWeatherMapResponse
import com.lbnkosi.weatherapp.domain.repository.WeatherDataRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherDataRepository @Inject constructor(private val dataSource: WeatherDataSource): WeatherDataRepo {

    override suspend fun getWeatherData(lat: Double, lon: Double): Flow<Result<OpenWeatherMapResponse?>> {
        return dataSource.getWeatherData(lat, lon).map { resource ->
            resource
        }
    }

}