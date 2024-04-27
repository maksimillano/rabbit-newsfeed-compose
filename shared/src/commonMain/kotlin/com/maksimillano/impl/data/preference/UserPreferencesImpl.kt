package com.maksimillano.impl.data.preference

import com.maksimillano.api.data.preference.UserPreferences
import com.maksimillano.api.theme.DefaultThemeIds

class UserPreferencesImpl : UserPreferences {
    override var isStartUp: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    override var currentTheme: Int
        get() = DefaultThemeIds.DEFAULT_LIGHT_THEME_ID
        set(value) {}
}