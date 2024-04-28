package com.maksimillano.api.domain.model.account

sealed interface Peer {
    val publicId: Long

    data class User(
        override val publicId: Long,
        val userId: Long
    ) : Peer

    data class Channel(
        override val publicId: Long,
        val channelId: Long
    ) : Peer
}
