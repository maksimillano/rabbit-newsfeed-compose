package com.maksimillano

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import com.maksimillano.presentation.base.RenderComponent
import com.maksimillano.presentation.component.AppTheme
import com.maksimillano.presentation.theme.Dimens
import com.maksimillano.util.getScreenSizeInfo

var localPostWidthProvider: () -> Int = { 0 }
@Composable
fun setWidthWithPadding() {
    val result = getScreenSizeInfo().wPx - (2 * with(LocalDensity.current) {
        Dimens.postMargin.toPx().toInt()
    })
    localPostWidthProvider = remember {
        { result }
    }
}

@Composable
fun AppThemeScreen(rootComponent: RenderComponent) {
    AppTheme {
        rootComponent.render()
    }
}