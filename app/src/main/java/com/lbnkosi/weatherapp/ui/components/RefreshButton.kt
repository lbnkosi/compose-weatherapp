package com.lbnkosi.weatherapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RefreshButton(onRefresh: (() -> Unit)) {
    Button(
        onClick = onRefresh,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White.copy(alpha = 0.25f),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(50),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .height(48.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Refresh",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }

}