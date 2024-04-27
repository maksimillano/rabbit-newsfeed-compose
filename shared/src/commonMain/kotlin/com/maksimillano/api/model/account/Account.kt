package com.maksimillano.api.model.account

sealed interface Account {
    val number: Int
    val isUser: Boolean
        get() = this is UserAccount
    val isAnonymity: Boolean
        get() = this is AnonymityAccount
}