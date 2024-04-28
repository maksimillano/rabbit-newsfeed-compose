package com.maksimillano.presentation.features.navigation

import com.maksimillano.api.domain.features.navigation.NavigationMenuItem
import com.maksimillano.presentation.base.mvi.MviEvent

sealed interface NavigationMviEvent : MviEvent {
    data class SwitchAccount(val number: Int) : NavigationMviEvent
    data object SwitchTheme : NavigationMviEvent
    data object Logout : NavigationMviEvent
    data object AddAccount : NavigationMviEvent

    data class OnMenuClick(val menuItem: NavigationMenuItem) : NavigationMviEvent
}