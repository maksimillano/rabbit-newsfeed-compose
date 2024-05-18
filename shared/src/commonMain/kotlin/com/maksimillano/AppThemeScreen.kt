package com.maksimillano

import androidx.compose.runtime.Composable
import com.maksimillano.presentation.base.RenderComponent
import com.maksimillano.presentation.component.AppTheme

@Composable
fun AppThemeScreen(rootComponent: RenderComponent) {
    AppTheme {
        rootComponent.render()
    }
}