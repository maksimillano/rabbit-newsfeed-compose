package com.maksimillano.api.model

interface User {
    val userId: Long
    val firstName: String
    val lastName: String
    val nickname: String
    val about: String
    val photos: List<String>
    val birthdayDate: String
    val sex: Sex

    enum class Sex {
        UNKNOWN,
        WOMAN,
        MAN
    }
}