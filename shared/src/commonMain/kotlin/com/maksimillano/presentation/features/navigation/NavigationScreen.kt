package com.maksimillano.presentation.features.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.maksimillano.presentation.component.DrawerNavigationPanel

@Composable
fun NavigationScreen(component: NavigationComponent) {
    val state by component.viewModel.state.collectAsState()
    DrawerNavigationPanel(
        state = state,
        updateTheme = {
            component.viewModel.onNewEvent(NavigationMviEvent.SwitchTheme)
        },
        accountSelect = {
        },
        anotherAccountSelect = {
        },
        menuSelect = {
            component.viewModel.onNewEvent(NavigationMviEvent.OnMenuClick(it))
        }
    )
}