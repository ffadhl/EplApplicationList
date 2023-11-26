package com.fadhlalhafizh.submissionjetpackcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColors(
    primary = Purple,
    primaryVariant = PurpleGrey,
    secondary = Yellow
)

private val LightColorScheme = lightColors(
    primary = Purple,
    primaryVariant = PurpleGrey,
    secondary = Yellow
)

@Composable
fun PremierLeagueClubsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}