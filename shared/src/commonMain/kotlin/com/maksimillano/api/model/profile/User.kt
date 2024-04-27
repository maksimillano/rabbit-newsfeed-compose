package com.maksimillano.api.model.profile

interface User : Profile {
    val firstName: String
    val lastName: String
    val nickname: String
    val birthdayDate: String
    val sex: Sex

    enum class Sex {
        UNKNOWN,
        WOMAN,
        MAN
    }
}