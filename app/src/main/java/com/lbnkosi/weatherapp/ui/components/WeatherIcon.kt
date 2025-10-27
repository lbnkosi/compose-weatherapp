package com.lbnkosi.weatherapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lbnkosi.weatherapp.utils.Utility.getWeatherIconUrl

@Composable
fun WeatherIcon(iconCode: String?, modifier: Modifier = Modifier) {
    val iconUrl = getWeatherIconUrl(iconCode)
    if (iconUrl.isNotEmpty()) {
        AsyncImage(
            model = iconUrl,
            contentDescription = "Weather icon",
            modifier = modifier.size(50.dp)
        )
    }
}