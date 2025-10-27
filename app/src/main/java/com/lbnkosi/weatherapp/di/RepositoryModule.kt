package com.lbnkosi.weatherapp.di

import com.lbnkosi.weatherapp.data.datasource.WeatherDataSource
import com.lbnkosi.weatherapp.data.repository.WeatherDataRepository
import com.lbnkosi.weatherapp.data.service.OpenWeatherMapApiService
import com.lbnkosi.weatherapp.domain.usecase.GetWeatherDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesWeatherDataUseCase(weatherDataRepository: WeatherDataRepository): GetWeatherDataUseCase = GetWeatherDataUseCase(weatherDataRepository)

    @Provides
    fun providesWeatherDataRemoteDataSource(weatherMapApiService: OpenWeatherMapApiService): WeatherDataSource = WeatherDataSource(weatherMapApiService)

}