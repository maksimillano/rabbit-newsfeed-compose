package com.maksimillano.presentation.features.navigation

import com.maksimillano.presentation.base.mvi.MviNavigationEvent

interface NavigationNavEvent : MviNavigationEvent {
    data object OpenSettings : NavigationNavEvent
    data object Logout : NavigationNavEvent
}