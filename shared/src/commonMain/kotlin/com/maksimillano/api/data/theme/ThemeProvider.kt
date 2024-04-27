package com.maksimillano.api.data.theme

import com.maksimillano.api.theme.Theme
import kotlinx.coroutines.flow.StateFlow

interface ThemeProvider {
    val theme: StateFlow<Theme>
    fun updateTheme(themeId: Int)
}