package com.maksimillano.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.TextUnit
import com.maksimillano.presentation.theme.AppThemeProvider
import com.maksimillano.api.domain.theme.DimenScheme

@Composable
inline fun dimenThemed(
    attribute: (DimenScheme) -> TextUnit
): TextUnit {
    val theme by AppThemeProvider.theme.collectAsState()
    return attribute(theme.dimenScheme)
}