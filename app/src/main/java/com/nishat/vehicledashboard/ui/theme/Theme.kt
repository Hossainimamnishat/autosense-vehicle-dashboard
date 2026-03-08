package com.nishat.vehicledashboard.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val BMWDarkColorScheme = darkColorScheme(
    primary = BMWBlueAccent,
    onPrimary = BMWTextPrimary,
    secondary = BMWLightBlue,
    background = BMWDarkBackground,
    surface = BMWCardBackground,
    onSurface = BMWTextPrimary,
    error = BMWRed,
    onError = BMWTextPrimary
)

private val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    secondary = md_theme_light_secondary,
    background = md_theme_light_background,
    surfaceVariant = md_theme_light_surfaceVariant,
    errorContainer = md_theme_light_errorContainer,
    onErrorContainer = md_theme_light_onErrorContainer
)

@Composable
fun AppTheme(
    darkTheme: Boolean = true, // Always use dark theme for BMW
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) BMWDarkColorScheme else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

