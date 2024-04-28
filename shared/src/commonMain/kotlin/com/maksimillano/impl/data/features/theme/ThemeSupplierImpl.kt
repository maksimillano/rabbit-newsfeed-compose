package com.maksimillano.impl.data.features.theme

import com.maksimillano.api.domain.features.theme.ThemeSupplier
import com.maksimillano.api.domain.preference.UserPreferences
import com.maksimillano.api.domain.theme.DarkTheme
import com.maksimillano.api.domain.theme.DefaultThemeIds
import com.maksimillano.api.domain.theme.LightTheme
import com.maksimillano.api.domain.theme.Theme
import com.maksimillano.api.domain.theme.themes.DefaultDarkTheme
import com.maksimillano.api.domain.theme.themes.DefaultLightTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ThemeSupplierImpl(
    private val preferences: UserPreferences,
) : ThemeSupplier {
    private val _state: MutableStateFlow<Theme> = MutableStateFlow(getTheme(preferences.currentTheme))
    override val state: StateFlow<Theme>
        get() = _state

    private fun getTheme(themeId: Int): Theme {
        return when (themeId) {
            DefaultThemeIds.DEFAULT_LIGHT_THEME_ID -> DefaultLightTheme
            DefaultThemeIds.DEFAULT_DARK_THEME_ID -> DefaultDarkTheme
            else -> throw IllegalArgumentException("Unsupported theme id")
        }
    }

    fun updateTheme() {
        val currentTheme = _state.value
        when (currentTheme) {
            is DarkTheme -> updateTheme(DefaultLightTheme)
            is LightTheme -> updateTheme(DefaultDarkTheme)
        }
    }

    private fun updateTheme(theme: Theme) {
        preferences.currentTheme = theme.themeId
        _state.tryEmit(theme)
    }
}