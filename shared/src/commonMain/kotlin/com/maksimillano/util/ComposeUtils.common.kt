package com.maksimillano.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

data class ScreenSizeInfo(val hPx: Int, val wPx: Int, val hDp: Dp, val wDp: Dp)

@Composable
expect fun getScreenSizeInfo(): ScreenSizeInfo