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
    primary: Color,
    secondary: Color,
    lightBlue: Color,
    white: Color,
    black: Color,
    grey100: Color,
    grey200: Color,
    grey300: Color,
    grey400: Color,
    grey500: Color,
    grey600: Color,
    grey700: Color,
    customGrey100: Color,
    customGrey200: Color,
    customGrey300: Color,
    customGrey400: Color,
    customGrey500: Color,
    customGrey600: Color,
    customGrey700: Color,
    success100: Color,
    success200: Color,
    danger100: Color,
    danger200: Color
) {
    var primary by mutableStateOf(primary)
        internal set

    var secondary by mutableStateOf(secondary)
        internal set

    var lightBlue by mutableStateOf(lightBlue)
        internal set

    var white by mutableStateOf(white)
        internal set

    var black by mutableStateOf(black)
        internal set

    var grey100 by mutableStateOf(grey100)
        internal set

    var grey200 by mutableStateOf(grey200)
        internal set

    var grey300 by mutableStateOf(grey300)
        internal set

    var grey400 by mutableStateOf(grey400)
        internal set

    var grey500 by mutableStateOf(grey500)
        internal set

    var grey600 by mutableStateOf(grey600)
        internal set

    var grey700 by mutableStateOf(grey700)
        internal set

    var customGrey100 by mutableStateOf(customGrey100)
        internal set

    var customGrey200 by mutableStateOf(customGrey200)
        internal set

    var customGrey300 by mutableStateOf(customGrey300)
        internal set

    var customGrey400 by mutableStateOf(customGrey400)
        internal set

    var customGrey500 by mutableStateOf(customGrey500)
        internal set

    var customGrey600 by mutableStateOf(customGrey600)
        internal set

    var customGrey700 by mutableStateOf(customGrey700)
        internal set

    var success100 by mutableStateOf(success100)
        internal set

    var success200 by mutableStateOf(success200)
        internal set

    var danger100 by mutableStateOf(danger100)
        internal set

    var danger200 by mutableStateOf(danger200)
        internal set

    fun copy(
        primary: Color = this.primary
    ) = UligaColorScheme(
        primary = primary,
        secondary = secondary,
        lightBlue = lightBlue,
        white = white,
        black = black,
        grey100 = grey100,
        grey200 = grey200,
        grey300 = grey300,
        grey400 = grey400,
        grey500 = grey400,
        grey600 = grey600,
        grey700 = grey700,
        customGrey100 = customGrey100,
        customGrey200 = customGrey200,
        customGrey300 = customGrey300,
        customGrey400 = customGrey400,
        customGrey500 = customGrey500,
        customGrey600 = customGrey600,
        customGrey700 = customGrey700,
        success100 = success100,
        success200 = success200,
        danger100 = danger100,
        danger200 = danger200
    )

    fun updateColorsFrom(
        other: UligaColorScheme
    ) = UligaColorScheme(
        primary = other.primary,
        secondary = other.secondary,
        lightBlue = other.lightBlue,
        white = other.white,
        black = other.black,
        grey100 = other.grey100,
        grey200 = other.grey200,
        grey300 = other.grey300,
        grey400 = other.grey400,
        grey500 = other.grey500,
        grey600 = other.grey600,
        grey700 = other.grey700,
        customGrey100 = other.customGrey100,
        customGrey200 = other.customGrey200,
        customGrey300 = other.customGrey300,
        customGrey400 = other.customGrey400,
        customGrey500 = other.customGrey500,
        customGrey600 = other.customGrey600,
        customGrey700 = other.customGrey700,
        success100 = other.success100,
        success200 = other.success200,
        danger100 = other.danger100,
        danger200 = other.danger200
    )
}

fun lightColors(
    primary: Color = Primary,
    secondary: Color = Secondary,
    lightBlue: Color = LightBlue,
    white: Color = White,
    black: Color = Black,
    grey100: Color = Grey100,
    grey200: Color = Grey200,
    grey300: Color = Grey300,
    grey400: Color = Grey400,
    grey500: Color = Grey500,
    grey600: Color = Grey600,
    grey700: Color = Grey700,
    customGrey100: Color = CustomGrey100,
    customGrey200: Color = CustomGrey200,
    customGrey300: Color = CustomGray300,
    customGrey400: Color = CustomGrey400,
    customGrey500: Color = CustomGrey500,
    customGrey600: Color = CustomGrey600,
    customGrey700: Color = CustomGrey700,
    success100: Color = Success100,
    success200: Color = Success200,
    danger100: Color = Danger100,
    danger200: Color = Danger200
): UligaColorScheme = UligaColorScheme(
    primary = primary,
    secondary = secondary,
    lightBlue = lightBlue,
    white = white,
    black = black,
    grey100 = grey100,
    grey200 = grey200,
    grey300 = grey300,
    grey400 = grey400,
    grey500 = grey500,
    grey600 = grey600,
    grey700 = grey700,
    customGrey100 = customGrey100,
    customGrey200 = customGrey200,
    customGrey300 = customGrey300,
    customGrey400 = customGrey400,
    customGrey500 = customGrey500,
    customGrey600 = customGrey600,
    customGrey700 = customGrey700,
    success100 = success100,
    success200 = success200,
    danger100 = danger100,
    danger200 = danger200
)

internal val LocalColorScheme = staticCompositionLocalOf { com.uliga.app.ui.theme.lightColors() }

internal val LocalContentColor = compositionLocalOf { Color.Black }

