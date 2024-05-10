package com.maksimillano.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenSizeInfo(): ScreenSizeInfo {
    val density = LocalDensity.current
    val config = LocalWindowInfo.current.containerSize

    return remember(density, config) {
        ScreenSizeInfo(
            hPx = config.height,
            wPx = config.width,
            hDp = with(density) { config.height.toDp() },
            wDp = with(density) { config.width.toDp() }
        )
    }
}