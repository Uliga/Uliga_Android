package com.uliga.app.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Stable
class UligaColorScheme(
    primary: Color
) {
    var primary by mutableStateOf(primary)
        internal set

    fun copy(
        primary: Color = this.primary
    ) = UligaColorScheme(
        primary = primary
    )

    fun updateColorsFrom(
        other: UligaColorScheme
    ) = UligaColorScheme(
        primary = other.primary
    )
}

fun lightColors(
    primary: Color = Primary
): UligaColorScheme = UligaColorScheme(
    primary
)

internal val LocalColorScheme = staticCompositionLocalOf { com.uliga.app.ui.theme.lightColors() }

internal val LocalContentColor = compositionLocalOf { Color.Black }

