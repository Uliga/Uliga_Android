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
    val subTitle1: TextStyle
) {
    constructor(
        defaultFontFamily: FontFamily = Pretendard,
        title1: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.scaledSpOutside,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        subTitle1: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 18.scaledSpOutside,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        )
    ) : this(
        title1 = title1.withDefaultFontFamily(defaultFontFamily),
        subTitle1 = subTitle1.withDefaultFontFamily(defaultFontFamily)
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