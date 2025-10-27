package com.lbnkosi.weatherapp.ui.weatherfeature

import android.location.Location
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lbnkosi.weatherapp.domain.models.openweathermaps.OpenWeatherMapResponse
import com.lbnkosi.weatherapp.ui.components.ErrorScreen
import com.lbnkosi.weatherapp.ui.components.LoadingScreen
import com.lbnkosi.weatherapp.ui.components.RefreshButton
import com.lbnkosi.weatherapp.ui.components.WeatherIcon
import com.lbnkosi.weatherapp.ui.components.WeatherStatsBar
import com.lbnkosi.weatherapp.ui.theme.coldGradient
import com.lbnkosi.weatherapp.ui.theme.warmGradient
import com.lbnkosi.weatherapp.utils.LocationProvider
import com.lbnkosi.weatherapp.utils.Utility.kelvinToCelsius

private val StatsBarHeight: Dp = 84.dp

@Composable
fun WeatherScreen(
    viewModel: WeatherScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val locationProvider = remember(context) { LocationProvider(context) }

    var coordinates by remember { mutableStateOf<Pair<Double, Double>?>(null) }

    LaunchedEffect(locationProvider) {
        locationProvider.getBestLocation()?.let { location: Location ->
            val latLon = location.latitude to location.longitude
            coordinates = latLon
            viewModel.onEvent(
                WeatherScreenEvent.GetWeatherScreenData(latLon.first, latLon.second)
            )
        }
    }

    val onRefresh: () -> Unit = remember(coordinates) {
        {
            coordinates?.let { (lat, lon) ->
                viewModel.onEvent(WeatherScreenEvent.GetWeatherScreenData(lat, lon))
            }
        }
    }

    Box(Modifier.fillMaxSize()) {
        Crossfade(targetState = uiState, modifier = Modifier.fillMaxSize()) { state ->
            when (state) {
                is WeatherScreenState.Success -> WeatherContent(
                    response = state.weatherData,
                    onRefresh = onRefresh
                )

                is WeatherScreenState.Loading -> LoadingScreen()

                is WeatherScreenState.Error -> ErrorScreen(
                    errorMessage = state.errorMessage,
                    onRefresh = onRefresh
                )
            }
        }
    }
}

@Composable
private fun WeatherContent(
    response: OpenWeatherMapResponse,
    onRefresh: () -> Unit
) {
    val current = response.current
    val tempC = remember(current.temp) { current.temp.kelvinToCelsius() }
    val isCold = tempC <= 15

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isCold) coldGradient else warmGradient)
            .padding(horizontal = 32.dp, vertical = 20.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterStart),
            horizontalAlignment = Alignment.Start
        ) {
            TimezoneTitle(response.timezone)

            Row(verticalAlignment = Alignment.CenterVertically) {
                TemperatureBig(tempC.toString())
                Spacer(modifier = Modifier.weight(1f))
                WeatherIcon(response.current.weather?.firstOrNull()?.icon)
            }

            Spacer(modifier = Modifier.height(32.dp))

            WeatherStatsBar(
                humidity = current.humidity,
                visibilityMeters = current.visibility,
                uvi = current.uvi,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(StatsBarHeight)
            )

            Spacer(modifier = Modifier.height(32.dp))

            RefreshButton(onRefresh = onRefresh)
        }
    }
}

@Composable
private fun TimezoneTitle(text: String) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(bottom = 8.dp),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun TemperatureBig(tempC: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = tempC,
            color = Color.White,
            fontSize = 120.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "°",
            color = Color.White,
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, start = 2.dp)
        )
        Text(
            text = "C",
            color = Color.White,
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
