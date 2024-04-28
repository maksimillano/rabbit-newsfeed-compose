package com.maksimillano.api.domain.features.navigation

sealed interface NavigationMenuItem {
    data object Exit : NavigationMenuItem
}