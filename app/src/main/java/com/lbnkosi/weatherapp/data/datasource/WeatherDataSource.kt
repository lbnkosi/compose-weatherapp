package com.lbnkosi.weatherapp.data.datasource

import com.lbnkosi.weatherapp.data.network.RemoteRepository
import com.lbnkosi.weatherapp.data.service.ApiUrl
import com.lbnkosi.weatherapp.data.service.OpenWeatherMapApiService
import com.lbnkosi.weatherapp.domain.models.openweathermaps.OpenWeatherMapResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.await
import javax.inject.Inject

class WeatherDataSource @Inject constructor(private val apiService: OpenWeatherMapApiService) : RemoteRepository() {

    suspend fun getWeatherData(lat: Double, lon: Double): Flow<Result<OpenWeatherMapResponse?>> {
        val result = apiCall { apiService.getWeatherData(lat, lon, ApiUrl.APP_ID).await() }
        return result
    }

}