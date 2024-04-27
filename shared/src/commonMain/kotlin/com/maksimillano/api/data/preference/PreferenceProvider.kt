package com.maksimillano.api.data.preference

import com.maksimillano.api.data.preference.GlobalPreferences
import com.maksimillano.api.data.preference.UserPreferences

interface PreferenceProvider {
    val userPreferences: UserPreferences
    val globalPreferences: GlobalPreferences
}