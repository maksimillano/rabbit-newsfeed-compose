package com.maksimillano.api.domain.model.account

import kotlinx.serialization.Serializable

@Serializable
data class UserAccount(
    override val number: Int,
    val userId: Long,
    val firstName: String = "",
    val lastName: String = "",
    val photo: String = "",
    val phone: String = "",
) : Account