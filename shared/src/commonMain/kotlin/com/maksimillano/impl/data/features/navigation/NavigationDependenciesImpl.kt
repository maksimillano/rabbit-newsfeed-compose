package com.maksimillano.impl.data.features.navigation

import com.maksimillano.api.domain.features.navigation.NavigationDependencies
import com.maksimillano.api.domain.features.navigation.NavigationMenuItem
import com.maksimillano.api.domain.features.theme.ThemeSupplier
import com.maksimillano.api.domain.preference.UserPreferences
import com.maksimillano.impl.data.features.account.AccountSupplierImpl
import com.maksimillano.impl.data.features.theme.ThemeSupplierImpl

class NavigationDependenciesImpl(userPreferences: UserPreferences, themeSupplier: ThemeSupplier) : NavigationDependencies {
    override val accountSupplier =  AccountSupplierImpl()
    override val themeSupplier = themeSupplier

    override val switchThemeInteractor = SwitchThemeInteractorImpl(themeSupplier as ThemeSupplierImpl)
    override val switchAccountInteractor = SwitchAccountInteractorImpl()
    override val logoutInteractor = LogoutInteractorImpl()
    override val menuItemsIterator = MenuItemsInteractorImpl()

    class SwitchThemeInteractorImpl(private val themeSupplier: ThemeSupplierImpl) : NavigationDependencies.SwitchThemeInteractor {
        override fun execute() {
            themeSupplier.updateTheme()
        }
    }
    class SwitchAccountInteractorImpl : NavigationDependencies.SwitchAccountInteractor {
        override fun execute(number: Int) {
        }
    }
    class LogoutInteractorImpl : NavigationDependencies.LogoutInteractor {
        override fun execute() {
        }
    }
    class MenuItemsInteractorImpl : NavigationDependencies.MenuItemsInteractor {
        override fun execute(): List<NavigationMenuItem> {
            return listOf(
                NavigationMenuItem.Exit
            )
        }
    }
}