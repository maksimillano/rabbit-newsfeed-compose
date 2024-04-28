package com.maksimillano.api.domain.preference

interface PreferenceProvider {
    val userPreferences: UserPreferences
    val globalPreferences: GlobalPreferences
}