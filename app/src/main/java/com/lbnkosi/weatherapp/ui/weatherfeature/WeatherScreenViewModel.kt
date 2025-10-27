package com.lbnkosi.weatherapp.ui.weatherfeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbnkosi.weatherapp.domain.models.openweathermaps.OpenWeatherMapResponse
import com.lbnkosi.weatherapp.domain.usecase.GetWeatherDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherScreenViewModel @Inject constructor(private val getWeatherDataUseCase: GetWeatherDataUseCase): ViewModel() {

    private val _state: MutableStateFlow<WeatherScreenState> = MutableStateFlow(WeatherScreenState.Success(OpenWeatherMapResponse()))
    val state: StateFlow<WeatherScreenState> = _state.asStateFlow()

    fun onEvent(event: WeatherScreenEvent) {
        when (event) {
            is WeatherScreenEvent.GetWeatherScreenData -> {
                getWeatherData(event.lat, event.long)
            }
        }
    }

    private fun getWeatherData(lat: Double, long: Double) {
        _state.value = WeatherScreenState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            getWeatherDataUseCase.getWeatherData(lat, long).collect { result ->
                if (result.isSuccess) {
                    result.getOrNull()?.let { weatherData ->
                        _state.value = WeatherScreenState.Success(weatherData = weatherData)
                    }
                } else {
                    _state.value = WeatherScreenState.Error("There was an error getting the weather")
                }

            }
        }
    }

}