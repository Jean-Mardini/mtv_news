package com.example.mtv_news.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val MTVColors = lightColorScheme(
    primary = NavyBlue,
    secondary = MTVRed,
    background = LightGrayBg,
    surface = White,
    onPrimary = White,
    onSurface = TextDark
)

@Composable
fun Mtv_newsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MTVColors,
        typography = Typography(),
        content = content
    )
}