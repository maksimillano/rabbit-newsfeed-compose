package com.maksimillano.impl.data.preference

import com.maksimillano.api.domain.preference.UserPreferences
import com.maksimillano.api.domain.theme.DefaultThemeIds

class UserPreferencesImpl : UserPreferences {
    override var isFirstLaunch: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    override var currentTheme: Int
        get() = DefaultThemeIds.DEFAULT_LIGHT_THEME_ID
        set(value) {}
}