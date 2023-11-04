package com.uliga.app.ext

import android.content.res.Resources
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private fun Dp.toSpOutside(density: Density): TextUnit {
    val spValue = this.value * density.density / density.fontScale
    return spValue.sp
}

private val getSystemDensity = Density(
    Resources.getSystem().displayMetrics.density * 1.1f,
    Resources.getSystem().displayMetrics.scaledDensity
)

val Int.scaledSpOutside: TextUnit
    get() = this.dp.toSpOutside(getSystemDensity)
