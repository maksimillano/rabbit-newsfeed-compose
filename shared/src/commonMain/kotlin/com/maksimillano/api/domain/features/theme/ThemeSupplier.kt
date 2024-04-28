package com.maksimillano.api.domain.features.theme

import com.maksimillano.api.domain.loader.Supplier
import com.maksimillano.api.domain.theme.Theme
import kotlinx.coroutines.flow.StateFlow

interface ThemeSupplier : Supplier<Theme> {
    override val state: StateFlow<Theme>
}