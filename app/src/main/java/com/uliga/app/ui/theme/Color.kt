package com.uliga.app.ui.theme

import androidx.compose.ui.graphics.Color

val Primary = Color(0xFF7798FC)
val Secondary = Color(0xFFFFC144)
val LightBlue = Color(0xFFADC1FF)

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

val Grey100 = Color(0xFFF5F5F9)
val Grey200 = Color(0xFFE9E9EE)
val Grey300 = Color(0xFFE4E4E4)
val Grey400 = Color(0xFFC6C6DD)
val Grey500 = Color(0xFF9090A0)
val Grey600 = Color(0xFF626273)
val Grey700 = Color(0xFF464656)

val CustomGrey100 = Color(0xFFF4F4F9)
val CustomGrey200 = Color(0xFFE9EEFF)
val CustomGray300 = Color(0xFFF9F9F9)
val CustomGrey400 = Color(0xFFE3E3E3)
val CustomGrey500 = Color(0xFF9B9B9B)
val CustomGrey600 = Color(0xFF9590A0)
val CustomGrey700 = Color(0xFF777777)

val Success100 = Color(0xFF76E8AD)
val Success200 = Color(0xFF1BBF83)

val Danger100 = Color(0xFFFF8E89)
val Danger200 = Color(0xFFF24147)

val KakaoYellow = Color(0xFFFEDC3F)

fun pieChartColor(categoryName: String): Color {
    when (categoryName) {
        "\uD83C\uDF7D️ 식비" -> {
            return Primary
        }

        "\uD83C\uDF59 편의점,마트,잡화" -> {
            return Secondary
        }

        "\uD83D\uDC55 쇼핑" -> {
            return Success100
        }

        "\uD83C\uDFE0 생활" -> {
            return Danger100
        }

        "☕ 카페 · 간식" -> {
            return Grey700
        }

        else -> {
            return Grey200
        }
    }
}