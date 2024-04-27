package com.maksimillano.presentation.features.navigation

import com.maksimillano.api.data.account.AccountLoader
import com.maksimillano.api.data.account.AccountLoaderData
import com.maksimillano.api.data.theme.ThemeProvider
import com.maksimillano.api.theme.DarkTheme
import com.maksimillano.api.theme.Theme
import korlibs.io.async.launch

class NavigationViewModelImpl(
    private val accountLoader: AccountLoader,
    private val themeProvider: ThemeProvider,
) : NavigationViewModel(createDefaultState(accountLoader, themeProvider)) {

    init {
        observeAccounts()
        observeTheme()
    }

    private fun observeAccounts() {
        launch {
            accountLoader.data.collect { data ->
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
            themeProvider.theme.collect { theme ->
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
            is NavigationMviEvent.OnMenuClick -> {
                // track stat
                handleMenuClick(event.menuItem)
            }
            else -> {}
        }
    }

    private fun handleMenuClick(menuItem: NavigationState.MenuItem) {
        when (menuItem) {
            is NavigationState.MenuItem.Exit -> {
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
        fun map(account: AccountLoaderData): NavigationState.AccountInfoBadge {
            return when (account) {
                is AccountLoaderData.UserData -> {
                    NavigationState.AccountInfoBadge.UsersBadge(account.current, account.others)
                }
                is AccountLoaderData.AnonData -> {
                    NavigationState.AccountInfoBadge.LoginBadge
                }
            }
        }
    }

    private data object MenuItemsProvider {
        fun get(): List<NavigationState.MenuItem> {
            return listOf(
                NavigationState.MenuItem.Exit
            )
        }
    }

    companion object {
        private fun createDefaultState(accountLoader: AccountLoader, themeProvider: ThemeProvider): NavigationState {
            return NavigationState(
                theme = ThemeMapper.map(themeProvider.theme.value),
                accountBadge = AccountMapper.map(accountLoader.data.value),
                menuItems = MenuItemsProvider.get()
            )
        }
    }
}