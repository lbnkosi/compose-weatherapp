package com.lbnkosi.weatherapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lbnkosi.weatherapp.utils.Utility.uvIndexLabel
import kotlin.math.roundToInt

@Composable
fun WeatherStatsBar(
    humidity: Int,
    visibilityMeters: Int,
    uvi: Double,
    modifier: Modifier = Modifier
) {
    val km = (visibilityMeters / 1000.0)
    val uvLabel = uvIndexLabel(uvi)

    val cornerRadius = 10.dp

    Box(
        modifier = modifier
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(cornerRadius))
            .border(
                width = 0.5.dp,
                color = Color.White,
                shape = RoundedCornerShape(cornerRadius)
            )
            .background(Color.White.copy(alpha = 0.20f))
            .padding(horizontal = 18.dp, vertical = 14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatItem(value = "${humidity}%", label = "Humidity")
            DividerDot()
            StatItem(
                value = if (km < 1) "${(km * 1000).roundToInt()} m" else "${km.roundToInt()} km",
                label = "Visibility"
            )
            DividerDot()
            StatItem(value = "$uvLabel ${uvi.roundToInt()}", label = "UV Index")
        }
    }
}

@Composable
private fun StatItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.85f),
            fontSize = 12.sp
        )
    }
}

@Composable
private fun DividerDot() {
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .size(6.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.65f))
    )
}
