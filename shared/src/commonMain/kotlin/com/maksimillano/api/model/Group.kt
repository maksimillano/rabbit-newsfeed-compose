package com.maksimillano.api.model

interface Group {
    val groupId: Long
    val name: String
    val shortName: String
    val isAdmin: Boolean
    val isMember: Boolean
    val photos: List<String>
    val adminLevel: AdminLevel
    val deactivatedStatus: DeactivatedStatus
    val accessState: AccessState

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