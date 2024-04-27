package com.maksimillano.presentation.features.settings

import com.maksimillano.presentation.base.mvi.MviState

data class SettingsState(
    val avatar: String = "",
    val name: String = "",
    val about: String = "",
    val phone: String = "",
    val link: String? = null,
    val settings: List<SettingItem> = emptyList()
) : MviState {
    sealed interface SettingItem

    data class SimpleSettingItem(
        val icon: String,
        val title: String
    ) : SettingItem
}
