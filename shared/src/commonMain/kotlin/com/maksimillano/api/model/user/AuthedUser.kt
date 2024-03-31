package com.maksimillano.api.model.user

import kotlinx.serialization.Serializable

@Serializable
data class AuthedUser(
    override val number: Int,
    val userId: Long,
    val firstName: String = "",
    val lastName: String = "",
    val photo: String = "",
    val phone: String = "",
) : EngineUser