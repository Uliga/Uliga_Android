//package com.uliga.app
//
//import androidx.compose.foundation.LocalIndication
//import androidx.compose.foundation.text.selection.LocalTextSelectionColors
//import androidx.compose.material.ProvideTextStyle
//import androidx.compose.material.ripple.LocalRippleTheme
//import androidx.compose.material.ripple.rememberRipple
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.runtime.ReadOnlyComposable
//import androidx.compose.runtime.remember
//
//@Composable
//fun UligaTheme(
//    colorScheme:  = MemorywordTheme.colorScheme,
//    shapes: MemorywordShapes = MemorywordTheme.shapes,
//    typography: MemorywordTypography = MemorywordTheme.typography,
//    content: @Composable () -> Unit
//    ) {
//        val rememberColorScheme = remember {
//            colorScheme.copy()
//        }.apply {
//            updateColorsFrom(colorScheme)
//        }
//
//        val rippleIndication = rememberRipple()
//        val selectionColors = rememberTextSelection(colorScheme = rememberColorScheme)
//        CompositionLocalProvider(
//            LocalColorScheme provides rememberColorScheme,
//            LocalIndication provides rippleIndication,
//            LocalRippleTheme provides MemorywordRippleTheme,
//            LocalShapes provides shapes,
//            LocalTextSelectionColors provides selectionColors,
//            LocalTypography provides typography
//        ) {
//            ProvideTextStyle(value = typography.body1, content = content)
//        }
//    }
//
//    object MemorywordTheme {
//
//        val colorScheme: MemorywordColorScheme
//            @Composable
//            @ReadOnlyComposable
//            get() = LocalColorScheme.current
//
//        val typography: MemorywordTypography
//            @Composable
//            @ReadOnlyComposable
//            get() = LocalTypography.current
//
//        val shapes: MemorywordShapes
//            @Composable
//            @ReadOnlyComposable
//            get() = LocalShapes.current
//    }
//
//    @Immutable
//    private object MemorywordRippleTheme : RippleTheme {
//        @Composable
//        override fun defaultColor() = LocalContentColor.current
//
//        @Composable
//        override fun rippleAlpha() = DefaultRippleAlpha
//    }
//
//    private val DefaultRippleAlpha = RippleAlpha(
//        pressedAlpha = StateTokens.PressedStateLayerOpacity,
//        focusedAlpha = StateTokens.FocusStateLayerOpacity,
//        draggedAlpha = StateTokens.DraggedStateLayerOpacity,
//        hoveredAlpha = StateTokens.HoverStateLayerOpacity
//    )
//
//    @Composable
//    internal fun rememberTextSelection(colorScheme: MemorywordColorScheme): TextSelectionColors {
//        val primaryColor = colorScheme.primary
//        return remember(primaryColor) {
//            TextSelectionColors(
//                handleColor = primaryColor,
//                backgroundColor = primaryColor.copy(alpha = TextSelectionBackgroundOpacity)
//            )
//        }
//    }
//
//    internal const val TextSelectionBackgroundOpacity = 0.4f