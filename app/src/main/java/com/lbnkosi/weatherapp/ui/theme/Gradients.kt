package com.lbnkosi.weatherapp.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val warmGradient = Brush.verticalGradient(
    colors = listOf(Color(0xFFB06AB3), Color(0xFFFED373), Color(0xFFFED373)),
    startY = 0f, endY = Float.POSITIVE_INFINITY
)
val coldGradient = Brush.verticalGradient(
    colors = listOf(Color(0xFF2C3E50), Color(0xFF3A7BD5), Color(0xFF6DD5FA)),
    startY = 0f, endY = Float.POSITIVE_INFINITY
)