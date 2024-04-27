package com.maksimillano.impl.data.theme

import com.maksimillano.api.data.theme.ThemeProvider
import com.maksimillano.api.data.preference.UserPreferences
import com.maksimillano.api.theme.DefaultThemeIds
import com.maksimillano.api.theme.Theme
import com.maksimillano.api.theme.themes.DefaultDarkTheme
import com.maksimillano.api.theme.themes.DefaultLightTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ThemeProviderImpl(
    private val preferences: UserPreferences
) : ThemeProvider {
    private val _theme: MutableStateFlow<Theme> = MutableStateFlow(getTheme(preferences.currentTheme))
    override val theme: StateFlow<Theme>
        get() = _theme

    private fun getTheme(themeId: Int): Theme {
        return when (themeId) {
            DefaultThemeIds.DEFAULT_LIGHT_THEME_ID -> DefaultLightTheme
            DefaultThemeIds.DEFAULT_DARK_THEME_ID -> DefaultDarkTheme
            else -> throw IllegalArgumentException("Unsupported theme id")
        }
    }

    override fun updateTheme(themeId: Int) {
        val newTheme = getTheme(themeId)
        preferences.currentTheme = themeId
        _theme.tryEmit(newTheme)
    }
}