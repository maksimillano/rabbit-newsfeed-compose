package com.maksimillano.presentation.features.navigation

import com.maksimillano.presentation.base.mvi.MviEvent

sealed interface NavigationMviEvent : MviEvent {
    data class SwitchAccount(val number: Int) : NavigationMviEvent
    data object SwitchTheme : NavigationMviEvent
    data object AddAccount : NavigationMviEvent

    data class OnMenuClick(val menuItem: NavigationState.MenuItem) : NavigationMviEvent
}