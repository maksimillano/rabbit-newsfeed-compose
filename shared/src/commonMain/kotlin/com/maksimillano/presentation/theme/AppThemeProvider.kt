package com.maksimillano.presentation.theme

import com.maksimillano.api.domain.features.theme.ThemeSupplier
import com.maksimillano.api.domain.theme.Theme
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object AppThemeProvider : KoinComponent {
    val theme: StateFlow<Theme>

    init {
        val delegate: ThemeSupplier = get()
        theme = delegate.state
    }
}