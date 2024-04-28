package com.maksimillano.presentation.features.navigation

import androidx.compose.runtime.Stable
import com.maksimillano.api.domain.features.navigation.NavigationMenuItem
import com.maksimillano.api.domain.model.account.UserAccount
import com.maksimillano.presentation.base.mvi.MviState

@Stable
data class NavigationState(
    val theme: ThemeType,
    val accountBadge: AccountInfoBadge,
    val menuItems: List<NavigationMenuItem>,
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
}