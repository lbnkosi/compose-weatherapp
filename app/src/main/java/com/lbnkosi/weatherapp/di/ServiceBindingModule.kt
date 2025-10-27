package com.lbnkosi.weatherapp.di

import com.lbnkosi.weatherapp.domain.repository.WeatherDataRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceBindingModule {

    @Singleton
    @Binds
    abstract fun bindWeatherDataRepository(weatherDataRepository: WeatherDataRepo): WeatherDataRepo

}