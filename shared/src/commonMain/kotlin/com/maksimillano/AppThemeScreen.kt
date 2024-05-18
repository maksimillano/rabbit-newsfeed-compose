package com.maksimillano

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import com.maksimillano.presentation.base.RenderComponent
import com.maksimillano.presentation.component.AppTheme
import com.maksimillano.presentation.theme.Dimens
import com.maksimillano.util.getScreenSizeInfo
import com.maksimillano.util.localPostTextStyleProvider
import com.maksimillano.util.localPostWidthProvider
import com.maksimillano.util.localTextMeasurerProvider

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

@Composable
fun AppThemeScreen(rootComponent: RenderComponent) {
    AppTheme {
        rootComponent.render()
    }
}