package com.maksimillano.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Int.pxToDp(): Dp {
    return (this / LocalDensity.current.density).toInt().dp
}

@Composable
fun Float.pxToDp(): Dp {
    return (this / LocalDensity.current.density).toInt().dp
}

@Composable
fun Dp.dpToPx(): Int {
    return (this.value * LocalDensity.current.density).toInt()
}

fun Int.pxToDpValue(density: Density): Dp {
    return (this / density.density).toInt().dp
}

fun Float.pxToDpValue(density: Density): Dp {
    return (this / density.density).toInt().dp
}
