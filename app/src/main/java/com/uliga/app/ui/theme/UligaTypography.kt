package com.uliga.app.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.uliga.app.R
import com.uliga.app.ext.scaledSpOutside

val Pretendard = FontFamily(
    Font(R.font.pretendard_light, FontWeight.Light),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_bold, FontWeight.Bold)
)

@Immutable
class UligaTypography internal constructor(
    val title1: TextStyle,
    val title2: TextStyle,
    val title3: TextStyle,
    val subTitle1: TextStyle,
    val subTitle2: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val body3: TextStyle,
    val body4: TextStyle,
    val body5: TextStyle,
    val body6: TextStyle,
    val body7: TextStyle,
    val body8: TextStyle
) {
    constructor(
        defaultFontFamily: FontFamily = Pretendard,
        title1: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.scaledSpOutside,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        title2: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 18.scaledSpOutside,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        title3: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 22.scaledSpOutside,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        subTitle1: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 20.scaledSpOutside,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        subTitle2: TextStyle = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 12.scaledSpOutside,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        body1: TextStyle = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 10.scaledSpOutside,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        body2: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.scaledSpOutside,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        body3: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 18.scaledSpOutside,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        body4: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 15.scaledSpOutside,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        body5: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 11.scaledSpOutside,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        body6: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 15.scaledSpOutside,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        body7: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.scaledSpOutside,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        body8: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 18.scaledSpOutside,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
    ) : this(
        title1 = title1.withDefaultFontFamily(defaultFontFamily),
        title2 = title2.withDefaultFontFamily(defaultFontFamily),
        title3 = title3.withDefaultFontFamily(defaultFontFamily),
        subTitle1 = subTitle1.withDefaultFontFamily(defaultFontFamily),
        subTitle2 = subTitle2.withDefaultFontFamily(defaultFontFamily),
        body1 = body1.withDefaultFontFamily(defaultFontFamily),
        body2 = body2.withDefaultFontFamily(defaultFontFamily),
        body3 = body3.withDefaultFontFamily(defaultFontFamily),
        body4 = body4.withDefaultFontFamily(defaultFontFamily),
        body5 = body5.withDefaultFontFamily(defaultFontFamily),
        body6 = body6.withDefaultFontFamily(defaultFontFamily),
        body7 = body7.withDefaultFontFamily(defaultFontFamily),
        body8 = body8.withDefaultFontFamily(defaultFontFamily)
    )

    fun copy(
        title1: TextStyle = this.title1,
        subTitle1: TextStyle = this.subTitle1
    ): UligaTypography = UligaTypography(
        title1 = title1,
        subTitle1 = subTitle1
    )
}

private fun TextStyle.withDefaultFontFamily(default: FontFamily): TextStyle {
    return if (fontFamily != null) this else copy(fontFamily = default)
}

internal val LocalTypography = staticCompositionLocalOf { UligaTypography() }