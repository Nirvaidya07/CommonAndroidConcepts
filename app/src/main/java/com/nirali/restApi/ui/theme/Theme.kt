package com.nirali.restApi.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF42A974),   // Custom primary color for light theme
    onPrimary = Color.White,
    secondary = Color(0xFF00796B), // Custom secondary color for light theme
    onSecondary = Color.White,
    background = Color(0xFFF5F5F5),
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF42A974),   // Custom primary color for light theme
    onPrimary = Color.White,
    secondary = Color(0xFF00796B), // Custom secondary color for light theme
    onSecondary = Color.White,
    background = Color(0xFFF5F5F5),
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black
)

@Composable
fun InterviewTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

val CustomColorScheme = lightColorScheme(
    primary = Color(0xFF42A974),  // Custom primary color
    onPrimary = Color.White,       // Text color on primary
    secondary = Color(0xFF00796B), // Custom secondary color
    onSecondary = Color.White,     // Text color on secondary
    background = Color(0xFFF5F5F5), // Background color
    onBackground = Color.Black,    // Text color on background
    surface = Color.White,          // Surface color
    onSurface = Color.Black         // Text color on surface
)