package com.maksimillano.presentation.theme

import com.maksimillano.api.data.theme.ThemeProvider
import com.maksimillano.api.theme.Theme
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object AppThemeProvider : KoinComponent {
    val theme: StateFlow<Theme>

    init {
        val delegate: ThemeProvider = get()
        theme = delegate.theme
    }
}