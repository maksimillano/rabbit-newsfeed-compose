package com.maksimillano.presentation.features.navigation

import androidx.compose.runtime.Stable
import com.maksimillano.api.model.account.UserAccount
import com.maksimillano.presentation.base.mvi.MviState

@Stable
data class NavigationState(
    val theme: ThemeType,
    val accountBadge: AccountInfoBadge,
    val menuItems: List<MenuItem>,
) : MviState {

    sealed interface AccountInfoBadge {
        data class UsersBadge(
            val current: UserAccount,
            val others: List<UserAccount>,
            val canHideOthers: Boolean = false
        ) : AccountInfoBadge
        data object LoginBadge : AccountInfoBadge
    }

    enum class ThemeType {
        LIGHT, DARK
    }

    sealed interface MenuItem {
        data object Exit : MenuItem
    }
}