package com.maksimillano.api.data.account

import com.maksimillano.api.model.account.UserAccount

sealed interface AccountLoaderData {
    data class UserData(val current: UserAccount, val others: List<UserAccount>) : AccountLoaderData
    data object AnonData : AccountLoaderData
}