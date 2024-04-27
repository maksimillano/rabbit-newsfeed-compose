package com.maksimillano.api.model.profile

interface Channel : Profile {
    val shortName: String
    val isAdmin: Boolean
    val isOwner: Boolean
    val isMember: Boolean
    val accessState: AccessState
    val adminLevel: AdminLevel?
    val deactivatedStatus: DeactivatedStatus?

    enum class AccessState {
        OPEN,
        CLOSED,
        PRIVATE
    }

    enum class DeactivatedStatus {
        DELETED,
        BANNED
    }

    enum class AdminLevel {
        MODERATOR,
        EDITOR,
        ADMIN
    }
}