package com.maksimillano.api.model.user

sealed interface EngineUser {
    val number: Int

    val isAuthed: Boolean
        get() = this is AuthedUser

    val isAnonymity: Boolean
        get() = this is AnonymityUser
}