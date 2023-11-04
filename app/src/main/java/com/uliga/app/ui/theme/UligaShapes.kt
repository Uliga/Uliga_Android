package com.uliga.app.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

class UligaShapes(
    val small: CornerBasedShape = RoundedCornerShape(5.dp),
    val medium: CornerBasedShape = RoundedCornerShape(7.dp),
    val large: CornerBasedShape = RoundedCornerShape(10.dp),
) {
    fun copy(
        small: CornerBasedShape = this.small,
        medium: CornerBasedShape = this.medium,
        large: CornerBasedShape = this.large
    ): UligaShapes = UligaShapes(
        small = small,
        medium = medium,
        large = large
    )

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(other !is UligaShapes) return false

        if(small != other.small) return false
        if (medium != other.medium) return false
        if (large != other.large) return false

        return true
    }

    override fun hashCode(): Int {
        var result = small.hashCode()
        result = 31 * result + medium.hashCode()
        result = 31 * result + large.hashCode()
        return result
    }
}

internal val LocalShapes = staticCompositionLocalOf { UligaShapes() }