package com.example.fc3.themes

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Define tu esquema de colores
private val MyLightColors = lightColorScheme(
    primary = Color(0xFF364F59),
    secondary = Color(0xFFFFFFFF),
    // Define otros colores necesarios
)

// Define un objeto de tipografía
val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

// Aplica el esquema de colores al tema
@Composable
fun MyAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MyLightColors,
        typography = Typography,
        content = content
    )
}