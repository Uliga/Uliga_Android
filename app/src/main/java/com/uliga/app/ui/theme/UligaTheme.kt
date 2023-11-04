package com.uliga.app.ui.theme

import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

@Composable
fun UligaTheme(
    colorScheme: UligaColorScheme = UligaTheme.colorScheme,
    shapes: UligaShapes = UligaTheme.shapes,
    typography: UligaTypography = UligaTheme.typography,
    content: @Composable () -> Unit
) {
    val rememberColorScheme = remember {
        colorScheme.copy()
    }.apply {
        updateColorsFrom(colorScheme)
    }

    CompositionLocalProvider(
        LocalColorScheme provides rememberColorScheme,
        LocalShapes provides shapes,
        LocalTypography provides typography
    ) {
        ProvideTextStyle(value = typography.title1, content = content)
    }
}

object UligaTheme {
    val colorScheme: UligaColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: UligaTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: UligaShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

}