package com.maksimillano.data.model

import com.maksimillano.api.model.User

data class UserImpl(
    override val userId: Long,
    override val firstName: String,
    override val lastName: String,
    override val nickname: String,
    override val about: String,
    override val photos: List<String>,
    override val birthdayDate: String,
    override val sex: User.Sex
) : User {
}