package com.maksimillano.api.domain.features.account

import com.maksimillano.api.domain.model.account.UserAccount

sealed interface AccountSupplierData {
    data class UserData(
        val current: UserAccount,
        val others: List<UserAccount>
    ) : AccountSupplierData
    data object AnonData : AccountSupplierData
}