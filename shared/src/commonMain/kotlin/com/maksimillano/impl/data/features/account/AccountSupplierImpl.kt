package com.maksimillano.impl.data.features.account

import com.maksimillano.api.domain.features.account.AccountSupplier
import com.maksimillano.api.domain.features.account.AccountSupplierData
import com.maksimillano.api.domain.model.account.UserAccount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AccountSupplierImpl : AccountSupplier {
    private val defaultUser = UserAccount(
        1,
        1L,
        firstName = "Maksim",
        lastName = "Shengelia",
        photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjQWdB4gI3iz4jjFD7T1lSrmzWngEWLzDU2QxHA3ppHjg3LUJUok5jnTZFvNGp2Jpzl2M&usqp=CAU",
        phone = "+7 (965) 714-42-23"
    )
    private val _data = MutableStateFlow(
        AccountSupplierData.UserData(current = defaultUser, others = emptyList())
    )
    override val state: StateFlow<AccountSupplierData>
        get() = _data
}