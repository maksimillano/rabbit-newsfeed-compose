package com.maksimillano.util

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Int.pxToDp(density: Density): Int {
    return (this / density.density).toInt()
}

fun Float.pxToDp(density: Density): Int {
    return (this / density.density).toInt()
}

fun Int.pxToDpValue(density: Density): Dp {
    return (this / density.density).toInt().dp
}

fun Float.pxToDpValue(density: Density): Dp {
    return (this / density.density).toInt().dp
}