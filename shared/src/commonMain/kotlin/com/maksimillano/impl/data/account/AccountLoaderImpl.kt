package com.maksimillano.impl.data.account

import com.maksimillano.api.data.account.AccountLoader
import com.maksimillano.api.data.account.AccountLoaderData
import com.maksimillano.api.model.account.UserAccount
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AccountLoaderImpl : AccountLoader {
    private val defaultUser = UserAccount(
        1,
        1L,
        firstName = "Maksim",
        lastName = "Shengelia",
        photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjQWdB4gI3iz4jjFD7T1lSrmzWngEWLzDU2QxHA3ppHjg3LUJUok5jnTZFvNGp2Jpzl2M&usqp=CAU",
        phone = "+7 (965) 714-42-23"
    )
    private val _data = MutableStateFlow(AccountLoaderData.UserData(current = defaultUser, others = emptyList()))
    override val data: StateFlow<AccountLoaderData>
        get() = _data


    override fun load() {
    }

    override fun destroy() {
    }
}