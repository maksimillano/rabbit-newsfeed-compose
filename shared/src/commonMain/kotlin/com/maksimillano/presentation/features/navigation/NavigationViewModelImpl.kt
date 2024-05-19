package com.maksimillano.presentation.features.navigation

import com.maksimillano.api.domain.features.account.AccountSupplierData
import com.maksimillano.api.domain.features.navigation.NavigationDependencies
import com.maksimillano.api.domain.features.navigation.NavigationMenuItem
import com.maksimillano.api.domain.theme.DarkTheme
import com.maksimillano.api.domain.theme.Theme
import kotlinx.coroutines.launch

class NavigationViewModelImpl(
    private val dependencies: NavigationDependencies,
) : NavigationViewModel(createDefaultState(dependencies)) {

    init {
        observeAccounts()
        observeTheme()
    }

    private fun observeAccounts() {
        launch {
            dependencies.accountSupplier.state.collect { data ->
                withState { state ->
                    state.copy(
                        accountBadge = AccountMapper.map(data)
                    )
                }
            }
        }
    }

    private fun observeTheme() {
        launch {
            dependencies.themeSupplier.state.collect { theme ->
                withState { state ->
                    state.copy(
                        theme = ThemeMapper.map(theme)
                    )
                }
            }
        }
    }

    override fun onNewEvent(event: NavigationMviEvent) {
        when (event) {
            is NavigationMviEvent.SwitchTheme -> {
                launch {
                    dependencies.switchThemeInteractor.execute()
                }
            }
            is NavigationMviEvent.Logout -> {
                launch {
                    dependencies.logoutInteractor.execute()
                }
            }
            is NavigationMviEvent.OnMenuClick -> {
                // track stat
                handleMenuClick(event.menuItem)
            }
            else -> {}
        }
    }

    private fun handleMenuClick(menuItem: NavigationMenuItem) {
        when (menuItem) {
            is NavigationMenuItem.Exit -> {
                postNavigationEvent(NavigationNavEvent.Logout)
            }
        }
    }

    private data object ThemeMapper {
        fun map(theme: Theme): NavigationState.ThemeType {
            return when {
                theme is DarkTheme -> NavigationState.ThemeType.DARK
                else -> NavigationState.ThemeType.LIGHT
            }
        }
    }

    private data object AccountMapper {
        fun map(account: AccountSupplierData): NavigationState.AccountInfoBadge {
            return when (account) {
                is AccountSupplierData.UserData -> {
                    NavigationState.AccountInfoBadge.UsersBadge(account.current, account.others)
                }
                is AccountSupplierData.AnonData -> {
                    NavigationState.AccountInfoBadge.LoginBadge
                }
            }
        }
    }

    companion object {
        private fun createDefaultState(dependencies: NavigationDependencies): NavigationState {
            return NavigationState(
                theme = ThemeMapper.map(dependencies.themeSupplier.state.value),
                accountBadge = AccountMapper.map(dependencies.accountSupplier.state.value),
                menuItems = dependencies.menuItemsIterator.execute()
            )
        }
    }
}