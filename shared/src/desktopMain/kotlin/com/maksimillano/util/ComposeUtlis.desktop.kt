package com.maksimillano.util

import androidx.compose.runtime.Composable

@Composable
actual fun getScreenSizeInfo(): ScreenSizeInfo {
    TODO()
//    val density = LocalDensity.current
//    val config = LocalConfiguration.current
//    val hDp = config.screenHeightDp.dp
//    val wDp = config.screenWidthDp.dp
//
//    return remember(density, config) {
//        ScreenSizeInfo(
//            hPX = with(density) { hDp.roundToPx() },
//            wPX = with(density) { wDp.roundToPx() },
//            hDP = hDp,
//            wDP = wDp
//        )
//    }
}