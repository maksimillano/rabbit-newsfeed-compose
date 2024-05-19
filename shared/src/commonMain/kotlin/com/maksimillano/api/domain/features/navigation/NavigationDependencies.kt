package com.maksimillano.api.domain.features.navigation

import com.maksimillano.api.domain.features.account.AccountSupplier
import com.maksimillano.api.domain.features.theme.ThemeSupplier

interface NavigationDependencies {
    val accountSupplier: AccountSupplier
    val themeSupplier: ThemeSupplier

    val switchThemeInteractor: SwitchThemeInteractor
    val switchAccountInteractor: SwitchAccountInteractor
    val logoutInteractor: LogoutInteractor
    val menuItemsIterator: MenuItemsInteractor

    interface SwitchThemeInteractor {
        suspend fun execute()
    }
    interface SwitchAccountInteractor {
        suspend fun execute(number: Int)
    }
    interface LogoutInteractor {
        suspend fun execute()
    }
    interface MenuItemsInteractor {
        fun execute(): List<NavigationMenuItem>
    }
}