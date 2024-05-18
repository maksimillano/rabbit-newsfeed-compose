package com.maksimillano.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import com.maksimillano.presentation.theme.Dimens

var localPostWidthProvider: () -> Int = { 0 }
var localPostTextStyleProvider: () -> TextStyle = { TextStyle() }
lateinit var localTextMeasurerProvider: TextMeasurer

@Composable
fun setWidthWithPadding() {
    val margin = 2 * with(LocalDensity.current) { Dimens.postMargin.toPx().toInt() }
    val padding = 2 * with(LocalDensity.current) { Dimens.postPadding.toPx().toInt() }
    val result = getScreenSizeInfo().wPx - margin - padding
    localPostWidthProvider = remember {
        { result }
    }

    localPostTextStyleProvider = remember {
        {
            TextStyle(
                fontSize = Dimens.postTextSize,
                lineHeight = Dimens.postTextSize
            )
        }
    }

    localTextMeasurerProvider = rememberTextMeasurer()
}